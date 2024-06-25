package com.yong.valid;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * @Author: liyong
 * @Date: 2024/6/25 10:11
 * @Email: dixin_liyong@163.com
 * @Desc：
 */
@Data
public class GroupValidDto {

    private String id;

    @NotBlank(groups = First.class, message = "名称不能为空")
    @Length(min = 10, max = 20, groups = Second.class, message = "长度必须为10-20之间")
    private String name;

    @NotBlank(groups = First.class, message = "电话不能为空")
    @Length(min = 13, max = 13, groups = Second.class, message = "长度必须为13")
    private String phone;


    @NotBlank(groups = First.class, message = "邮件不能为空")
    private String email;

    @Min(10)
    @Max(100)
    private Integer age;


    @NotBlank(groups = First.class, message = "性别不能为空")
    private String gender;
}
