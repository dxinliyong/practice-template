package com.yong.mapstruct;

import com.google.gson.Gson;

/**
 * @Author: liyong
 * @Date: 2024/6/14 15:36
 * @Email: dixin_liyong@163.com
 * @Desc：mapstruct 使用
 * @see <a href="https://blog.csdn.net/qq_40194399/article/details/110162124">...</a>
 */
public class demo {
    public static void main(String[] args) {
        CarDto fut = CarMapper.INSTANCE.carToCarDto(new Car(){{
            setDrivers(8);
            setMm(1);
            setName("li");
        }});
        System.out.println(new Gson().toJson(fut));
    }
}
