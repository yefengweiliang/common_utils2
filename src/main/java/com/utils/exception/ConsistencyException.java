package com.utils.exception;

/**
 * 数据一致性异常
 *
 * @author wanghongshen
 * @date 2020-03-04
 */
public class ConsistencyException extends Exception {
    /**
     * 构造器
     */
    public ConsistencyException() {
        super();
    }

    /**
     * 打印异常信息
     *
     * @param message 异常信息
     */
    public ConsistencyException(String message) {
        super(message);
    }
    /**
     * 打印异常信息和异常
     *
     * @param message 异常信息
     * @param cause 异常
     */
    public ConsistencyException(String message, Throwable cause) {
        super(message, cause);
    }
    /**
     * 只打印异常
     *
     * @param cause 异常
     */
    public ConsistencyException(Throwable cause) {
        super(cause);
    }

    protected ConsistencyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}


