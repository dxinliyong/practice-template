package com.yong.design.watch.handwrite;

import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;

/**
 * @Author: liyong
 * @Date: 2024/6/14 9:26
 * @Email: dixin_liyong@163.com
 * @Desc：
 */
public class WatchSource implements WatchAble {

    private final List<WatcherListener> listeners;

    public WatchSource() {
        this.listeners = new ArrayList<>();
    }

    @Override
    public void addWatcher(WatcherListener listener) {
        this.listeners.add(listener);
    }

    @Override
    public void notifyWatcher(EventObject args) {
        for (WatcherListener listener : listeners) {
            listener.update(this, args);
        }
    }
}
