package top.kenan.week08.like.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 点赞/取消点赞请求体
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LikeRequest {
    /**
     * 用户ID
     */
    @NotNull(message = "用户ID不能为空")
    private Long userId;

    /**
     * 内容分类（如 article/video/comment，默认 all）
     */
    private String category = "all";
}
