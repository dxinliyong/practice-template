package com.yong.base;

/**
 * @Author: liyong
 * @Date: 2024/6/24 17:42
 * @Email: dixin_liyong@163.com
 * @Desc：
 */
public enum ErrorCodeEnum {
    PARAM_ILLEGAL(500, "参数不合法");
    public int code;
    public String desc;
    ErrorCodeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
