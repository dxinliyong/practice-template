package com.yong.mapstruct;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author: liyong
 * @Date: 2024/6/14 15:34
 * @Email: dixin_liyong@163.com
 * @Desc：
 */
@Data
@AllArgsConstructor
public class Car {
    private String name;

    private int drivers;

    private int mm;
    public Car() {

    }
}
