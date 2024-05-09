package com.yong.util;

import java.io.File;

/**
 * @Author: liyong
 * @Date: 2024/5/8 16:33
 * @Email: dixin_liyong@163.com
 * @Desc：
 */
public class PathUtils {
    private static String WORK_DIR_PATH = System.getProperty("user.dir");

    /**
     * 路径拼接 以 / 的方式拼接
     * @param basePath 前缀路径
     * @param otherPath 后缀路径
     */
    public static String combine(String basePath, String otherPath) {
        return basePath + File.separator + otherPath;
    }

    /**
     * 从工作目录下获取文件的路径
     * @param otherPath
     * @return
     */

    public static String getFileFromWorkDir(String otherPath) {
        return  combine(WORK_DIR_PATH, otherPath);
    }
}
