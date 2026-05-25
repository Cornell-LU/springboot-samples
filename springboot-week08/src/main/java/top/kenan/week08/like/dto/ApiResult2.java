package top.kenan.week08.like.dto;

import top.kenan.week08.sms.dto.ApiResult;

/**
 * 统一 JSON 包装
 */
public record ApiResult2<T>(int code, String message, T data) {

    public static <T> ApiResult<T> success(T data) {
        return new ApiResult<>(0, "success", data);
    }

    public static <T> ApiResult<T> error(int code, String message) {
        return new ApiResult<>(code, message, null);
    }
}