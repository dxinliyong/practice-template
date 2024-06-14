package com.yong.design.watch.jdk;

import java.util.Observable;

/**
 * @Author: liyong
 * @Date: 2024/6/14 9:16
 * @Email: dixin_liyong@163.com
 * @Desc：
 */
public class EventObservable extends Observable {
    @Override
    public void notifyObservers(Object arg) {
        setChanged();
        super.notifyObservers(arg);
        clearChanged();
    }
}
