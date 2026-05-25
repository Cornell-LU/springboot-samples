package top.kenan.week08.like.dto;

/**
 * 查询点赞状态响应体
 */
public record LikeStatusResponse(
        Long contentId,
        Long userId,
        /**
         * true：已点赞；false：未点赞
         */
        boolean isLiked
) {
}
