package com.yong.design.watch.handwrite;

/**
 * @Author: liyong
 * @Date: 2024/6/14 9:26
 * @Email: dixin_liyong@163.com
 * @Desc： 监听者
 */
public class Watcher implements WatcherListener{
    @Override
    public void update(WatchAble watchAble, Object args) {
        System.out.println("data changed");
        System.out.println("args" + args);
    }
}
