package com.yong;

import com.yong.usemybatis.common.MybatisSpuMapper;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @Author: liyong
 * @Date: 2024/5/8 15:25
 * @Email: dixin_liyong@163.com
 * @Desc：
 */
@SpringBootApplication
@EntityScan({"com.yong.entity"})
@EnableJpaRepositories
@MapperScan(basePackages = {"com.yong.usemybatis.plus.mapper", "com.yong.usemybatis.common" })
public class TemplateApplication {
    public static void main(String[] args) {
        new SpringApplication(TemplateApplication.class).run(args);
    }
}
