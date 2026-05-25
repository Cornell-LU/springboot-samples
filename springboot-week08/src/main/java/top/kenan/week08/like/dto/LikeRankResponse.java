package top.kenan.week08.like.dto;

/**
 * 点赞排行响应体
 */
public record LikeRankResponse(
        Long contentId,
        Long count,
        Integer rank
) {
}
