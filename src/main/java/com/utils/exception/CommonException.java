package com.utils.exception;

/**
 * 常规异常类
 *
 * @author wanghongshen
 * @date 2020-03-03
 */
public class CommonException extends Exception {
    /**
     * 构造器
     */
    public CommonException() {
        super();
    }

    /**
     * 打印异常信息
     *
     * @param message 异常信息
     */
    public CommonException(String message) {
        super(message);
    }

    /**
     * 打印异常信息和异常
     *
     * @param message 异常信息
     * @param cause 异常
     */
    public CommonException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * 只打印异常
     *
     * @param cause 异常
     */
    public CommonException(Throwable cause) {
        super(cause);
    }

    protected CommonException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
