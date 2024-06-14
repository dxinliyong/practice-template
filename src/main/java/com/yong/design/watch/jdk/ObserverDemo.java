package com.yong.design.watch.jdk;

import java.util.Observable;

/**
 * @Author: liyong
 * @Date: 2024/6/14 9:14
 * @Email: dixin_liyong@163.com
 * @Desc：
 */
public class ObserverDemo {
    public static void main(String[] args) {
        Observable observable = new EventObservable();
        observable.addObserver(new CustomEventObject());
        observable.notifyObservers("change");
    }
}
