package top.kenan.week04.common;

import lombok.Data;

/**
 * 通用返回结果封装类
 * @param <T> 泛型，返回的数据类型
 */
@Data
public class Result<T> {

    // 响应状态码：200表示成功
    private int code;

    // 响应消息
    private String msg;

    // 响应数据
    private T data;

    // 构造函数私有化，禁止外部new，只能通过静态方法创建
    private Result() {}

    /**
     * 成功返回结果
     */
    public static <T> Result<T> success() {
        Result<T> result = new Result<>();
        result.setCode(200);
        result.setMsg("操作成功");
        return result;
    }

    /**
     * 成功返回结果（带数据）
     * @param data 数据
     */
    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setCode(200);
        result.setMsg("操作成功");
        result.setData(data);
        return result;
    }

    /**
     * 失败返回结果
     */
    public static <T> Result<T> error() {
        Result<T> result = new Result<>();
        result.setCode(500);
        result.setMsg("操作失败");
        return result;
    }

    /**
     * 失败返回结果（自定义消息）
     * @param msg 错误消息
     */
    public static <T> Result<T> error(String msg) {
        Result<T> result = new Result<>();
        result.setCode(500);
        result.setMsg(msg);
        return result;
    }

    /**
     * 失败返回结果（自定义状态码+消息）
     * @param code 状态码
     * @param msg 错误消息
     */
    public static <T> Result<T> error(int code, String msg) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}
