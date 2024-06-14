package com.yong.design.watch.handwrite;

import java.util.EventObject;

/**
 * @Author: liyong
 * @Date: 2024/6/14 9:31
 * @Email: dixin_liyong@163.com
 * @Desc：
 */
public class Demo {
    public static void main(String[] args) {
        WatchSource watchSource = new WatchSource();
        watchSource.addWatcher(new Watcher());
        watchSource.notifyWatcher(new EventObject("liyong"));
    }
}
