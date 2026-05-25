package top.kenan.week08.like;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;
import top.kenan.week08.like.dto.LikeResponse;
import top.kenan.week08.like.dto.LikeRankResponse;
import top.kenan.week08.util.RedisUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * 点赞、统计、排行服务：基于 Redis Set（点赞关系）、String（计数）、ZSet（排行）实现
 */
@Service
@RequiredArgsConstructor
public class LikeService {

    private final RedisUtil redisUtil;

    /**
     * 点赞（幂等：同一用户仅能点赞一次）
     */
    public LikeResponse like(Long contentId, Long userId, String category) {
        String likeKey = LikeRedisKey.ofContentLike(contentId);
        String countKey = LikeRedisKey.ofContentCount(contentId);
        String rankKey = LikeRedisKey.ofContentRank(category);

        // 1. 添加点赞关系（Set 去重，返回 1 表示新增成功，0 表示已点赞）
        Long addResult = redisUtil.sAdd(likeKey, userId);
        boolean liked = addResult != null && addResult > 0;

        // 2. 点赞数+1（仅新增成功时）
        if (liked) {
            redisUtil.increment(countKey);
            // 3. 排行分数+1
            redisUtil.getRedisTemplate().opsForZSet().incrementScore(rankKey, contentId, 1);
        }

        // 获取当前点赞数
        Long currentCount = getCount(contentId);
        return new LikeResponse(contentId, userId, liked, currentCount);
    }

    /**
     * 取消点赞
     */
    public LikeResponse cancelLike(Long contentId, Long userId, String category) {
        String likeKey = LikeRedisKey.ofContentLike(contentId);
        String countKey = LikeRedisKey.ofContentCount(contentId);
        String rankKey = LikeRedisKey.ofContentRank(category);

        // 1. 移除点赞关系（返回 1 表示移除成功，0 表示未点赞）
        Long remResult = redisUtil.sRemove(likeKey, userId);
        boolean canceled = remResult != null && remResult > 0;

        // 2. 点赞数-1（仅移除成功时，保证计数≥0）
        if (canceled) {
            Long count = redisUtil.decrement(countKey);
            if (count == null || count < 0) {
                redisUtil.set(countKey, 0);
            }
            // 3. 排行分数-1
            redisUtil.getRedisTemplate().opsForZSet().incrementScore(rankKey, contentId, -1);
        }

        // 获取当前点赞数
        Long currentCount = getCount(contentId);
        return new LikeResponse(contentId, userId, false, currentCount);
    }

    /**
     * 查询用户是否已点赞某内容
     */
    public boolean isLiked(Long contentId, Long userId) {
        String likeKey = LikeRedisKey.ofContentLike(contentId);
        Boolean isMember = redisUtil.sIsMember(likeKey, userId);
        return Boolean.TRUE.equals(isMember);
    }

    /**
     * 查询单条内容点赞数
     */
    public Long getCount(Long contentId) {
        String countKey = LikeRedisKey.ofContentCount(contentId);
        Object countObj = redisUtil.get(countKey);
        return countObj == null ? 0L : Long.parseLong(countObj.toString());
    }

    /**
     * 查询内容点赞排行
     *
     * @param category 内容分类
     * @param top      获取前 N 条
     * @param order    排序方式：asc（正序）/desc（倒序）
     */
    public List<LikeRankResponse> getRank(String category, int top, String order) {
        String rankKey = LikeRedisKey.ofContentRank(category);
        Set<ZSetOperations.TypedTuple<Object>> contentIdsWithScores;

        // 倒序（默认）：点赞数从高到低
        if ("desc".equalsIgnoreCase(order)) {
            contentIdsWithScores = redisUtil.getRedisTemplate().opsForZSet().reverseRangeWithScores(rankKey, 0, top - 1);
        } else {
            // 正序：点赞数从低到高
            contentIdsWithScores = redisUtil.getRedisTemplate().opsForZSet().rangeWithScores(rankKey, 0, top - 1);
        }

        // 转换为排行响应体
        List<LikeRankResponse> rankList = new ArrayList<>();
        if (contentIdsWithScores == null || contentIdsWithScores.isEmpty()) {
            return rankList;
        }

        int rank = 1;
        for (ZSetOperations.TypedTuple<Object> tuple : contentIdsWithScores) {
            Long contentId = Long.parseLong(Objects.toString(tuple.getValue()));
            Long count = tuple.getScore() == null ? 0L : tuple.getScore().longValue();
            rankList.add(new LikeRankResponse(contentId, count, rank++));
        }
        return rankList;
    }

    /**
     * （可选）设置点赞数据过期时间
     */
    public Boolean expireContentLike(Long contentId, long timeout, TimeUnit unit) {
        String likeKey = LikeRedisKey.ofContentLike(contentId);
        return redisUtil.expire(likeKey, timeout, unit);
    }
}
