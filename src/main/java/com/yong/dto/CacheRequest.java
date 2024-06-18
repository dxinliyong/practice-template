package com.yong.dto;

import lombok.Data;

/**
 * @Author: liyong
 * @Date: 2024/6/18 14:14
 * @Email: dixin_liyong@163.com
 * @Desc：
 */
@Data
public class CacheRequest {
    private String key;
    private String cacheVal;
}
