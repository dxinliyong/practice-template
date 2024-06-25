package com.yong.exception;

import com.yong.base.ErrorCode;

/**
 * @Author: liyong
 * @Date: 2024/6/25 14:48
 * @Email: dixin_liyong@163.com
 * @Desc：
 */
public class BizException extends BusinessException{
    /**
     * 构造一个没有错误信息的 <code>BizException</code>
     */
    public BizException() {
        super();
    }


    /**
     * 使用指定的 Throwable 和 Throwable.toString() 作为异常信息来构造 BizException
     *
     * @param cause 错误原因， 通过 Throwable.getCause() 方法可以获取传入的 cause信息
     */
    public BizException(Throwable cause) {
        super(cause);
    }

    /**
     * 使用错误信息 message 构造 BizException
     *
     * @param message 错误信息
     */
    public BizException(String message) {
        super(message);
    }

    /**
     * 使用错误码和错误信息构造 BizException
     *
     * @param code    错误码
     * @param message 错误信息
     */
    public BizException(String code, String message) {
        super(code, message);
    }

    /**
     * 使用错误信息和 Throwable 构造 BizException
     *
     * @param message 错误信息
     * @param cause   错误原因
     */
    public BizException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param code    错误码
     * @param message 错误信息
     * @param cause   错误原因
     */
    public BizException(String code, String message, Throwable cause) {
        super(code, message, cause);
    }

    /**
     * @param errorCode ErrorCode
     */
    public BizException(ErrorCode errorCode) {
        super(errorCode);
    }

    /**
     * @param errorCode ErrorCode
     * @param cause     错误原因
     */
    public BizException(ErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }
}
