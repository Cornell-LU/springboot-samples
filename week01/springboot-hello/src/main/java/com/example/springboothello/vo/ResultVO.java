package com.example.springboothello.vo;

import lombok.Data;

/**
 * 通用返回结果封装类
 * @param <T> 数据泛型
 */
@Data
public class ResultVO<T> {
    /**
     * 响应状态码
     */
    private Integer code;

    /**
     * 响应消息
     */
    private String msg;

    /**
     * 响应数据
     */
    private T data;

    /**
     * 成功响应构造方法
     * @param code 状态码
     * @param msg 消息
     * @param data 数据
     */
    public ResultVO(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 快速构建成功响应
     * @param data 响应数据
     * @param <T> 数据类型
     * @return 成功响应对象
     */
    public static <T> ResultVO<T> success(T data) {
        return new ResultVO<>(200, "success", data);
    }
}
