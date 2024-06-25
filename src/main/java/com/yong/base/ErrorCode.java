package com.yong.base;

/**
 * @Author: liyong
 * @Date: 2024/6/24 17:42
 * @Email: dixin_liyong@163.com
 * @Desc：对异常进行分类归档  好处就是根据错误代码就能追踪到发生了什么异常
 */
public enum ErrorCode {
    /**
     * 非法参数
     */
    PARAM_ILLEGAL("C000", "参数不合法"),

    /**
     * 系统异常
     */
    SYSTEM_ERROR("A000", "系统异常"),
    /**
     * 业务异常
     */
    BIZ_ERROR("B000", "业务异常"),
    /**
     * 没有权限
     */
    NO_PERMISSION("B001", "没有权限"),

    ;
    /**
     * 错误码
     */
    private String code;
    /**
     * 错误信息
     */
    private String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
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
     * 获取错误信息
     *
     * @return 错误信息
     */
    public String getMessage() {
        return message;
    }

    /**
     * 设置错误码
     *
     * @param code 错误码
     * @return 返回当前枚举
     */
    public ErrorCode setCode(String code) {
        this.code = code;
        return this;
    }

    /**
     * 设置错误信息
     *
     * @param message 错误信息
     * @return 返回当前枚举
     */
    public ErrorCode setMessage(String message) {
        this.message = message;
        return this;
    }

    public String desc;

}
