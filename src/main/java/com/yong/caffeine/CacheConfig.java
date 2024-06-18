package com.yong.caffeine;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.util.concurrent.TimeUnit;

/**
 * @Author: liyong
 * @Date: 2024/6/14 16:38
 * @Email: dixin_liyong@163.com
 * @Desc：caffeine 本地缓存 如果是 java8 用2.x版本
 * @see <a href="https://developer.aliyun.com/article/1238521">...</a>
 */
@Configuration
@Slf4j
public class CacheConfig {
    @Bean
    public Cache<String, Object> caffeineCache() {
        Cache<String, Object> cache = Caffeine.newBuilder()
                // 初始数量
                .initialCapacity(10)
                // 最大条数
                .maximumSize(10)
                // expireAfterWrite和expireAfterAccess同时存在时，以expireAfterWrite为准
                // 最后一次写操作后经过指定时间过期
                .expireAfterWrite(30, TimeUnit.SECONDS)
                // 最后一次读或写操作后经过指定时间过期
                .expireAfterAccess(30, TimeUnit.SECONDS)
                // 监听缓存被移除 是在查询的时候如果过期就移除的策略
                .removalListener((key, val, removalCause) -> {
                    log.info("淘汰当前缓存 key : {} val : {}", key, val);
                    log.info("removalCause", removalCause);
                })
                // 记录命中
                .recordStats()
                .build();
        return cache;
    }
}
