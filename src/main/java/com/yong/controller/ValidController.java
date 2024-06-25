package com.yong.controller;

import com.yong.dto.NestValidRequest;
import com.yong.dto.ValidateRequest;
import com.yong.valid.First;
import com.yong.valid.GroupValidDto;
import com.yong.valid.Second;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: liyong
 * @Date: 2024/6/18 16:23
 * @Email: dixin_liyong@163.com
 * @Desc：校验模板 例子来源阿里云示例
 * @see <a href="https://mp.weixin.qq.com/s?__biz=MzIzOTU0NTQ0MA==&mid=2247504551&idx=1&sn=82f6899c6db88af1de7f15b6442bb864&chksm=e92aefa8de5d66bea87217a9399450c073bd7944e5833bc0b6955f3c41b05e106ebaa14a57d4&cur_album_id=1391790902901014528&scene=189#wechat_redirect">...</a>
 * @see <a href="https://blog.csdn.net/qq_32352777/article/details/108424932"></a>
 */

/**
 * 分组校验使用场景：
 *  同一个对象在不同的请求中需要不同的规则进行校验
 */

@Api(tags = {"校验"})
@RequestMapping("/valid")
@RestController
@Validated
public class ValidController {
    /**
     * 单参数校验 注意这里如果类型 和校验的类型不匹配会报错 比如 Long 类型 和 NotBlank 一起使用就会报错 不信你试试
     *
     * @param id
     * @return
     * @see <a href="https://www.cnblogs.com/softidea/p/12183236.html">...</a>
     */
    @DeleteMapping("/delete")
    @ApiImplicitParam(name = "id", required = false)
    public Object deleteUser(@NotNull(message = "校验提示,id不能为空") @Max(100) @RequestParam(value = "id", required = false) Long id) {
        return Boolean.TRUE;
    }

    /**
     * 校验字符串
     *
     * @param id
     * @return
     */
    @DeleteMapping("/deleteStrId")
    @ApiImplicitParam(name = "id", required = false)
    public Object deleteStrId(@NotBlank(message = "校验提示,id不能为空") @Length(min = 10, max = 12) @RequestParam(value = "id", required = false) String id) {
        return Boolean.TRUE;
    }

    /**
     * 校验对象
     *
     * @param request 需要校验的对象
     * @return
     * @desc 这里如果校验不通过 会badRequest
     */
    @PostMapping("/validDto")
    public Object validDto(@RequestBody @Validated ValidateRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // 如果有校验错误，处理错误信息
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            String errMsg = allErrors.stream().map(error -> {
                String defaultMessage = error.getDefaultMessage();
                defaultMessage = error.getArguments() + ":" + defaultMessage;
                return defaultMessage;
            }).collect(Collectors.joining(","));
            return errMsg;
        } else {
            return Boolean.TRUE;
        }
    }

    /**
     * 校验对象 嵌套校验对象
     *
     * @param
     * @return
     * @desc 这里如果校验不通过会400 Bad Request
     */
    @PostMapping("/nestvalidDto")
    public Object validDto(@RequestBody @Validated NestValidRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // 如果有校验错误，处理错误信息
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            String errMsg = allErrors.stream().map(error -> {
                String defaultMessage = error.getDefaultMessage();
                defaultMessage = error.getArguments() + ":" + defaultMessage;
                return defaultMessage;
            }).collect(Collectors.joining(","));
            return errMsg;
        } else {
            return Boolean.TRUE;
        }
    }

    /**
     * 校验对象 不通过BindingResult 获取结果
     *
     * @param
     * @return
     * @desc 这里如果校验不通过 会400 Bad Request
     */
    @PostMapping("/nestvalidDtoWithoutBinding")
    public Object validDtoNoBindingResult(@RequestBody @Validated NestValidRequest request) {
        // 校验不通过 通过全局异常捕获 友好提示
        return Boolean.TRUE;
    }

    /**
     * 校验对象 不通过BindingResult 获取结果
     *
     * @param
     * @return
     * @desc 这里如果校验不通过 会400 Bad Request
     */
    @PostMapping("/groupValidFirst")
    public Object groupValidFirst(@RequestBody @Validated(value = First.class) GroupValidDto request) {
        return Boolean.TRUE;
    }

    /**
     * 校验对象 不通过BindingResult 获取结果
     *
     * @param
     * @return
     * @desc 这里如果校验不通过 会400 Bad Request
     */
    @PostMapping("/groupValidSecond")
    public Object groupValidSecond(@RequestBody @Validated(value = Second.class) GroupValidDto request) {
        return Boolean.TRUE;
    }

    /**
     * 校验对象 不通过BindingResult 获取结果
     *
     * @param
     * @return
     * @desc 这里如果校验不通过 会400 Bad Request
     */
    @PostMapping("/groupValidDefault")
    public Object groupValidDefault(@RequestBody @Validated(value = Default.class) GroupValidDto request) {
        return Boolean.TRUE;
    }

    /**
     * 校验对象 不通过BindingResult 获取结果
     *
     * @param
     * @return
     * @desc 默认验证 和 Default.class 一样的效果 如果有分组在的话验证
     */
    @PostMapping("/groupValidNoGroup")
    public Object groupValidNoGroup(@RequestBody @Validated GroupValidDto request) {
        return Boolean.TRUE;
    }
}
