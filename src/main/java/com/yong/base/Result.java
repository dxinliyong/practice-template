package com.yong.base;

import lombok.Data;

/**
 * @Author: liyong
 * @Date: 2024/6/24 17:40
 * @Email: dixin_liyong@163.com
 * @Desc：
 */
@Data
public class Result<T> {
    private static final long serialVersionUID = -1525914055479353120L;
    /**
     * 错误码
     */
    private final String code;
    /**
     * 提示信息
     */
    private String message;
    /**
     * 返回数据
     */
    private final T data;
    /**
     * 是否成功
     */
    private final Boolean success;

    /**
     * 构造方法
     *
     * @param code    错误码
     * @param message 提示信息
     * @param data    返回的数据
     * @param success 是否成功
     */
    public Result(String code, String message, T data, Boolean success) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.success = success;
    }

    /**
     * 创建 Result 对象
     *
     * @param code    错误码
     * @param message 提示信息
     * @param data    返回的数据
     * @param success 是否成功
     */
    public static <T> Result<T> of(String code, String message, T data, Boolean success) {
        return new Result<>(code, message, data, success);
    }

    /**
     * 成功，没有返回数据
     *
     * @param <T> 范型参数
     * @return Result
     */
    public static <T> Result<T> success() {
        return of("00000", "成功", null, true);
    }

    /**
     * 成功，有返回数据
     *
     * @param data 返回数据
     * @param <T>  范型参数
     * @return Result
     */
    public static <T> Result<T> success(T data) {
        return of("00000", "成功", data, true);
    }

    /**
     * 失败，有错误信息
     *
     * @param message 错误信息
     * @param <T>     范型参数
     * @return Result
     */
    public static <T> Result<T> fail(String message) {
        return of("10000", message, null, false);
    }

    /**
     * 失败，有错误码和错误信息
     *
     * @param code    错误码
     * @param message 错误信息
     * @param <T>     范型参数
     * @return Result
     */
    public static <T> Result<T> fail(String code, String message) {
        return of(code, message, null, false);
    }


    /**
     * 获取错误码
     *
     * @return 错误码
     */
    public String getCode() {
        return code;
    }

    /**
     * 获取提示信息
     *
     * @return 提示信息
     */
    public String getMessage() {
        return message;
    }


    /**
     * 获取数据
     *
     * @return 返回的数据
     */
    public T getData() {
        return data;
    }

    /**
     * 获取是否成功
     *
     * @return 是否成功
     */
    public Boolean getSuccess() {
        return success;
    }
}
