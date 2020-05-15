package com.utils.exception;

public class NullException extends Exception {
    /**
     * 构造器
     */
    public NullException() {
        super();
    }
    /**
     * 打印异常信息
     *
     * @param message 异常信息
     */
    public NullException(String message) {
        super(message);
    }
    /**
     * 打印异常信息和异常
     *
     * @param message 异常信息
     * @param cause 异常
     */
    public NullException(String message, Throwable cause) {
        super(message, cause);
    }
    /**
     * 只打印异常
     *
     * @param cause 异常
     */
    public NullException(Throwable cause) {
        super(cause);
    }

    protected NullException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
