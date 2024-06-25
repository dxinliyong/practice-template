package com.yong.exception;

/**
 * @Author: liyong
 * @Date: 2024/6/25 14:37
 * @Email: dixin_liyong@163.com
 * @Desc：
 */

import com.yong.base.ErrorCode;

/**
 * rpc 异常类
 */
public class RpcException extends SystemException {


    private static final long serialVersionUID = -9152774952913597366L;

    /**
     * 构造一个没有错误信息的 <code>RpcException</code>
     */
    public RpcException() {
        super();
    }


    /**
     * 使用指定的 Throwable 和 Throwable.toString() 作为异常信息来构造 RpcException
     *
     * @param cause 错误原因， 通过 Throwable.getCause() 方法可以获取传入的 cause信息
     */
    public RpcException(Throwable cause) {
        super(cause);
    }

    /**
     * 使用错误信息 message 构造 RpcException
     *
     * @param message 错误信息
     */
    public RpcException(String message) {
        super(message);
    }

    /**
     * 使用错误码和错误信息构造 RpcException
     *
     * @param code    错误码
     * @param message 错误信息
     */
    public RpcException(String code, String message) {
        super(code, message);
    }

    /**
     * 使用错误信息和 Throwable 构造 RpcException
     *
     * @param message 错误信息
     * @param cause   错误原因
     */
    public RpcException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param code    错误码
     * @param message 错误信息
     * @param cause   错误原因
     */
    public RpcException(String code, String message, Throwable cause) {
        super(code, message, cause);
    }

    /**
     * @param errorCode ErrorCode
     */
    public RpcException(ErrorCode errorCode) {
        super(errorCode);
    }

    /**
     * @param errorCode ErrorCode
     * @param cause     错误原因
     */
    public RpcException(ErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }

}