package com.yong.controller;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONUtil;
import com.github.benmanes.caffeine.cache.Cache;
import com.yong.dto.CacheRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

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

    /**
     * 随机生成 英文字符串
     *
     * @param length
     * @return
     */
    public static String generateRandomString(int length) {
        // 'a'
        int leftLimit = 97;
        // 'z'
        int rightLimit = 122;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return generatedString;
    }

    @PostMapping("putIfAbsent")
    public Object putIfAbsent(@RequestBody CacheRequest request) {
        String key = request.getKey();
        String cacheVal = request.getCacheVal();
        Object cacheInMem = cache.getIfPresent(key);
        System.out.println(cache.asMap());
        if (Objects.isNull(cacheInMem)) {
            cache.put(key, cacheVal);
            log.info("未缓存或缓存过期");
            return cacheVal;
        }
        log.info("查询缓存直接返回");
        return cacheInMem;
    }

    @GetMapping("/mockCache")
    public Object mockCache(@RequestParam("threadCount") Integer threadCount, @RequestParam("length") Integer length) {
        for (int i = 0; i < threadCount; i++) {
            new Thread(() -> {
                Map<String, String> map = new HashMap<>();
                map.put("cacheVal", generateRandomString(length));
                map.put("key", generateRandomString(length));
                HttpResponse execute = HttpRequest.post("http://127.0.0.1:3030/cache/putIfAbsent")
                        .body(JSONUtil.toJsonStr(map))
                        .header("Content-Type", "application/json;charset=UTF-8")
                        .execute();
                System.out.println(execute.body());
            }).start();
        }
        return Boolean.TRUE;
    }
}
