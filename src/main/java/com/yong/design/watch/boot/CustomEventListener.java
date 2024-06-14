package com.yong.design.watch.boot;

import org.springframework.context.event.EventListener;

/**
 * @Author: liyong
 * @Date: 2024/6/14 11:11
 * @Email: dixin_liyong@163.com
 * @Desc：
 */


public class CustomEventListener {
    @EventListener
    public void demoListener(CustomEvent customEvent) {
        customEvent.executeEvent();
    };
}
