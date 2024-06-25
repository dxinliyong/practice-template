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
    private int code;
    private String message;

    private T data;

    public Result(int code, String desc, T data) {
        this.code = code;
        this.data = data;
        this.message = desc;
    }

    public static Result buildErrorResult(ErrorCodeEnum errorCodeEnum) {
        return new Result(errorCodeEnum.code, errorCodeEnum.desc, null);
    }

    public static Result buildSuccessResult(Object data) {
        return new Result(200, "success", data);
    }
}
