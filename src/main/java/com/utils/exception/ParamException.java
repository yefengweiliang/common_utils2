package com.utils.exception;

/**
 * 参数异常类
 *
 * @author wanghongshen
 * @date 2020-03-03
 */
public class ParamException extends Exception {
    /**
     * 构造器
     */
    public ParamException() {
        super();
    }

    /**
     * 异常信息
     *
     * @param message 异常信息
     */
    public ParamException(String message) {
        super(message);
    }

    /**
     * 异常信息和异常
     * @param message 异常信息
     * @param cause 异常
     */
    public ParamException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * 异常
     *
     * @param cause 异常
     */
    public ParamException(Throwable cause) {
        super(cause);
    }

    protected ParamException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
