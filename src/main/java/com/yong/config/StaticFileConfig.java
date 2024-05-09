package com.yong.config;

import com.yong.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

/**
 * @Date: 2024/5/8 14:48
 * @Email: liyong@dist.com.cn
 * @Desc：静态文件预览配置 用于将某个目录直接映射出去进行预览
 * @see {static/**.pdf}
 */
@Configuration
public class StaticFileConfig implements WebMvcConfigurer {

    @Autowired
    private Environment environment;

    /**
     * 启动时页面重定向到swagger页面
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/", "/");
    }

    /**
     * 例如spring.application.name = app
     * 那么映射的路径为 http://127.0.0.1:3030/app/static/demo.pdf 这个时候是http://127.0.0.1:3030/app/static 与 /当前工程路径/static/映射
     * 也就是访问http://127.0.0.1:3030/app/static 等同于访问本地文件文件static 等同于nginx的alias方式
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String envContextPath = environment.getProperty("spring.application.name");
        String localDir = System.getProperty("user.dir") + File.separator + Constant.STATIC_PATH;
        registry.addResourceHandler(String.format("/%s/**", envContextPath))
                .addResourceLocations("file:" + localDir);
    }
}
