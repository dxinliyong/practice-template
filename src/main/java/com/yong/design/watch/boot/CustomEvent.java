package com.yong.design.watch.boot;

import org.springframework.context.ApplicationEvent;

/**
 * @Author: liyong
 * @Date: 2024/6/14 11:12
 * @Email: dixin_liyong@163.com
 * @Desc：
 */
public class CustomEvent extends ApplicationEvent {
    public CustomEvent(Object source) {
        super(source);
    }
    public void executeEvent() {
        System.out.println("event run");
    }
}
