package com.yong.design.watch.handwrite;

import java.util.EventObject;

/**
 * @Author: liyong
 * @Date: 2024/6/14 9:28
 * @Email: dixin_liyong@163.com
 * @Desc：
 */
public interface WatchAble {
    void addWatcher(WatcherListener listener);
    void notifyWatcher(EventObject args);
}
