package com.yong.design.watch.jdk;

import java.util.EventListener;
import java.util.EventObject;
import java.util.Observable;
import java.util.Observer;

/**
 * @Author: liyong
 * @Date: 2024/6/14 9:17
 * @Email: dixin_liyong@163.com
 * @Desc：
 */
public class CustomEventObject implements Observer, EventListener {
    @Override
    public void update(Observable o, Object arg) {
        System.out.println("receive msg : " + arg);
    }
}
