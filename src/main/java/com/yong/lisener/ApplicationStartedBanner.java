package com.yong.lisener;

import cn.hutool.core.util.StrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.management.Query;
import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Set;

/**
 * @Author: liyong
 * @Date: 2024/5/10 10:37
 * @Email: dixin_liyong@163.com
 * @Desc：打印系统的IP
 */
@Component
public class ApplicationStartedBanner implements ApplicationListener<ApplicationReadyEvent> {

    Logger logger = LoggerFactory.getLogger(ApplicationStartedBanner.class);

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        ConfigurableApplicationContext applicationContext = event.getApplicationContext();
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        String port = getPort(environment);
        String ip = getIp();
        String format = StrUtil.format("应用访问的地址为：{}:{}/{}", ip, port, "doc.html");
        logger.info(format);
    }

    public String getIp() {
        InetAddress localhost = null;
        try {
            localhost = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            return "unKnownIp";
        }
        return localhost.getHostAddress();
    }

    public String getPort(Environment environment) {
        String port = environment.resolvePlaceholders("${server.port:8080}");
        try {
            MBeanServer beanServer = ManagementFactory.getPlatformMBeanServer();
            Set<ObjectName> objectNames = beanServer.queryNames(new ObjectName("*:type=Connector,*"), Query.match(Query.attr("protocol"), Query.value("HTTP/1.1")));
            port = objectNames.iterator().next().getKeyProperty("port");
        } catch (Exception var5) {
        }
        return port;
    }
}
