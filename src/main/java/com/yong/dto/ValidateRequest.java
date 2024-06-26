package com.yong.dto;

import cn.hutool.log.Log;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * @Author: liyong
 * @Date: 2024/6/18 17:16
 * @Email: dixin_liyong@163.com
 * @Desc：
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidateRequest {


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
