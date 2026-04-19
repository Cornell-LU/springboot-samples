package com.example.demo.vo;

import java.io.Serializable;

public class ResultVO<T> implements Serializable {
    private int code;
    private String message;
    private T data;

    // 成功响应
    public static <T> ResultVO<T> success(T data) {
        return new ResultVO<>(200, "success", data);
    }

    // 错误响应
    public static <T> ResultVO<T> error(int code, String message) {
        return new ResultVO<>(code, message, null);
    }

    // 构造器、Getters & Setters
    public ResultVO() {}
    public ResultVO(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    // Getter and Setter ...
    public int getCode() { return code; }
    public void setCode(int code) { this.code = code; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public T getData() { return data; }
    public void setData(T data) { this.data = data; }
}