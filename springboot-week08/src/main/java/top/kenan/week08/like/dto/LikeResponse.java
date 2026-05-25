package top.kenan.week08.like.dto;

/**
 * 点赞/取消点赞响应体
 */
public record LikeResponse(
        Long contentId,
        Long userId,
        /**
         * true：点赞成功；false：取消点赞成功
         */
        boolean liked,
        /**
         * 当前点赞数
         */
        Long currentCount
) {
}
