package com.yong.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: liyong
 * @Date: 2024/6/14 14:46
 * @Email: dixin_liyong@163.com
 * @Desc： Gson 模板
 * @see <a href="https://github.com/google/gson/blob/main/UserGuide.md">...</a>
 */
public class GsonDemo {
    public static void main(String[] args) {
        forPrimitives();
        forObject();
        forMap();
        forGeneric();
    }

    /**
     * 泛型处理
     */
    public static void forGeneric() {
        Foo<CacheKey> foo = new Foo<>();
        foo.value = new CacheKey("111", "111");
        print(new Gson(), foo);
    }
    /**
     * 对于复杂的map 结构需要用GsonBuilder 构建对象
     */
    public static void forMap() {
        Map<CacheKey, Integer> map = new HashMap<>();
        map.put(new CacheKey("1", "1"), 10);
        map.put(new CacheKey("2", "2"), 20);
        // 如果只是普通的 new
        // {"com.yong.gson.GsonDemo$CacheKey@1ef7fe8e":20,"com.yong.gson.GsonDemo$CacheKey@4c75cab9":10}
        Gson gson = new Gson();
        print(gson, map);
        // 使用这个构造函数创建
        // [[{"targetNum":"2","desc":"2"},20],[{"targetNum":"1","desc":"1"},10]]
        Gson complexGson = new GsonBuilder().enableComplexMapKeySerialization().create();
        print(complexGson, map);
    }

    /**
     * 对象类型 复杂类型 使用 TypeToken
     */
    public static void forObject() {
        Gson gson = new Gson();
        TypeToken<List<Map<String, String>>> mapType = new TypeToken<List<Map<String, String>>>() {
        };
        String json = "[\n" +
                "    {\"Name\": \"Alice\", \"Age\": \"25\", \"City\": \"New York\"},\n" +
                "    {\"Name\": \"Bob\", \"Age\": \"30\", \"City\": \"San Francisco\"},\n" +
                "    {\"Name\": \"Charlie\", \"Age\": \"22\", \"City\": \"Los Angeles\"}\n" +
                "]";
        List<Map<String, String>> result = gson.fromJson(json, mapType);
        print(gson, result);
    }

    /**
     * 原生类型
     */
    public static void forPrimitives() {
        Gson gson = new Gson();
        print(gson, 1);
        // "str"
        print(gson, "str");
        // 10
        print(gson, new Long(10));
        // [1,2,3]
        print(gson, new int[]{1, 2, 3});
    }

    public static void print(Gson gson, Object data) {
        System.out.println(gson.toJson(data));
    }

    static class CacheKey {
        private final String targetNum;
        private final String desc;

        public CacheKey(String targetNum, String desc) {
            this.desc = desc;
            this.targetNum = targetNum;
        }
    }
    static class Foo<T> {
        T value;
    }
}
