package com.yong.exception;

/**
 * @Author: liyong
 * @Date: 2024/6/25 14:46
 * @Email: dixin_liyong@163.com
 * @Desc：
 */

import com.yong.base.Result;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * rpc 调用全局异常处理 aop 类
 * @see https://mp.weixin.qq.com/s/7MbgphfBDBHWLx9nx5lHqA
 */

/**
 * PointCut 表达式
 *
 execution(public * *(..)) 定义任意公共方法的执行
 execution(* set*(..)) 定义任何一个以"set"开始的方法的执行
 execution(* com.xyz.service.AccountService.*(..)) 定义AccountService 接口的任意方法的执行
 execution(* com.xyz.service.*.*(..)) 定义在service包里的任意方法的执行
 execution(* com.xyz.service ..*.*(..)) 定义在service包和所有子包里的任意类的任意方法的执行
 execution(* com.test.spring.aop.pointcutexp…JoinPointObjP2.*(…)) 定义在pointcutexp包和所有子包里的JoinPointObjP2类的任意方法的执行
 */
@Slf4j
@Aspect
@Component
public class RpcGlobalExceptionAop {

    @Pointcut("execution(* com.xyz.service ..*.*(..))")
    public void pointcut() {}

    @Around(value = "pointcut()")
    public Object handleException(ProceedingJoinPoint joinPoint) {
        try {
            //如果对传入对参数有修改，那么需要调用joinPoint.proceed(Object[] args)
            //这里没有修改参数，则调用joinPoint.proceed()方法即可
            return joinPoint.proceed();
        } catch (BizException e) {
            // 对于业务异常，应该记录 warn 日志即可，避免无效告警
            log.warn("全局捕获业务异常", e);
            return Result.fail(e.getCode(), e.getMessage());
        } catch (RpcException e) {
            log.error("全局捕获第三方rpc调用异常", e);
            return Result.fail(e.getCode(), e.getMessage());
        } catch (SystemException e) {
            log.error("全局捕获系统异常", e);
            return Result.fail(e.getCode(), e.getMessage());
        } catch (Throwable e) {
            log.error("全局捕获未知异常", e);
            return Result.fail(e.getMessage());
        }
    }

}
