package com.yong.function;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: liyong
 * @Date: 2024/5/30 17:00
 * @Email: dixin_liyong@163.com
 * @Desc：
 */
@FunctionalInterface
public interface CallBack<S, T> {
    T run(S s);
}
