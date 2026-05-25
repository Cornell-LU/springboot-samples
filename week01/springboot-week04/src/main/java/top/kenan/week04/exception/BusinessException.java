package top.kenan.week04.exception;

import lombok.Getter;

/**
 * @author kenan
 * @date 2026/3/28
 * @description 自定义业务异常类
 **/
@Getter
public class BusinessException extends RuntimeException {
    private final int code;

    public BusinessException(String msg) {
        super(msg);
        this.code = 500;
    }

    public BusinessException(int code, String msg) {
        super(msg);
        this.code = code;
    }
}