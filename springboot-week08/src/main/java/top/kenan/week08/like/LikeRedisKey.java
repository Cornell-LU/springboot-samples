package top.kenan.week08.like;

/**
 * 点赞场景 Redis Key 规则
 */
public final class LikeRedisKey {

    /**
     * 内容点赞关系前缀：like:content:{contentId} → 存储点赞该内容的用户ID集合
     */
    public static final String CONTENT_LIKE_PREFIX = "like:content:";

    /**
     * 内容点赞数前缀：like:count:{contentId} → 存储该内容的点赞总数
     */
    public static final String CONTENT_COUNT_PREFIX = "like:count:";

    /**
     * 内容点赞排行前缀：like:rank:content:{category} → 按分类存储内容点赞数排行
     */
    public static final String CONTENT_RANK_PREFIX = "like:rank:content:";

    private LikeRedisKey() {
    }

    /**
     * 构建内容点赞关系 Key
     */
    public static String ofContentLike(Long contentId) {
        return CONTENT_LIKE_PREFIX + contentId;
    }

    /**
     * 构建内容点赞数 Key
     */
    public static String ofContentCount(Long contentId) {
        return CONTENT_COUNT_PREFIX + contentId;
    }

    /**
     * 构建内容点赞排行 Key
     */
    public static String ofContentRank(String category) {
        return CONTENT_RANK_PREFIX + (category == null || category.isEmpty() ? "all" : category);
    }
}
