package com.yong.controller;

import com.github.benmanes.caffeine.cache.Cache;
import com.yong.dto.CacheRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * @Author: liyong
 * @Date: 2024/6/18 14:13
 * @Email: dixin_liyong@163.com
 * @Desc：
 */
@RestController
@RequestMapping("/cache")
@Slf4j
public class CacheController {

    @Autowired
    Cache<String, Object> cache;

    @PostMapping("putIfAbsent")
    public Object putIfAbsent(@RequestBody CacheRequest request) {
        String key = request.getKey();
        String cacheVal = request.getCacheVal();
        Object cacheInMem = cache.getIfPresent(key);
        if (Objects.isNull(cacheInMem)) {
            cache.put(key, cacheVal);
            log.info("未缓存或缓存过期");
            return cacheVal;
        }
        log.info("查询缓存直接返回");
        return cacheInMem;
    }
}
