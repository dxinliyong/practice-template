package com.yong.design.watch.boot;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author: liyong
 * @Date: 2024/6/14 11:11
 * @Email: dixin_liyong@163.com
 * @Desc：
 */
public class Demo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 注册事件监听器
        context.register(CustomEventListener.class);
        context.refresh();
        context.publishEvent(new CustomEvent("test event"));
        context.close();
    }
}
