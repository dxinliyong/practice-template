package com.yong.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.junit.validator.ValidateWith;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * @Author: liyong
 * @Date: 2024/6/24 17:27
 * @Email: dixin_liyong@163.com
 * @Desc：172.16.16.93:3030/doc.html
 */
@Data
public class NestValidRequest {

    /**
     * @Valid 注解一定要加否则 无法进行嵌套验证
     */
    @Valid
    ValidateRequest request;
    /**
     * 校验长度
     */
    @Max(100)
    @Min(10)
    private Long id;
    /**
     * 校验字符串长度
     */
    @Length(min = 10, max = 10)
    private String name;
    /**
     * 校验是否为空
     */
    @NotBlank
    private String notBlank;
}
