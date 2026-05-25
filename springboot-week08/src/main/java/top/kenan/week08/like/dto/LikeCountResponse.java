package top.kenan.week08.like.dto;

/**
 * 查询点赞数响应体
 */
public record LikeCountResponse(
        Long contentId,
        Long count
) {
}
