package com.yong.exception;

import cn.hutool.core.collection.CollectionUtil;
import com.yong.base.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.text.MessageFormat;
import java.util.List;
import java.util.Set;

/**
 * @Author: liyong
 * @Date: 2024/6/24 17:38
 * @Email: dixin_liyong@163.com
 * @Desc：
 */

@Slf4j
@RestControllerAdvice(basePackages = {"com.yong.controller"})
public class GlobalExceptionHandler {

    /**
     * 处理业务异常
     *
     * @param request 请求参数
     * @param e       异常
     * @return Result
     */
    @ExceptionHandler(value = BizException.class)
    public Object bizExceptionHandler(HttpServletRequest request, BizException e) {
        log.warn("业务异常：" + e.getMessage(), e);
        return Result.fail(e.getCode(), e.getMessage());
    }

    /**
     * 处理系统异常
     *
     * @param request 请求参数
     * @param e       异常
     * @return Result
     */
    @ExceptionHandler(value = SystemException.class)
    public Object systemExceptionHandler(HttpServletRequest request, SystemException e) {
        log.error("系统异常：" + e.getMessage(), e);
        return Result.fail(e.getCode(), e.getMessage());
    }

    /**
     * 处理未知异常  兜底处理
     *
     * @param request 请求参数
     * @param e       异常
     * @return Result
     */
    @ExceptionHandler(value = Throwable.class)
    public Object unknownExceptionHandler(HttpServletRequest request, Throwable e) {
        log.error("未知异常：" + e.getMessage(), e);
        return Result.fail(e.getMessage());
    }


    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public Object missingServletRequestParameterExceptionHandler(HttpServletRequest request, Throwable e) {
        String msg = MessageFormat.format("缺少参数{0}", ((MissingServletRequestParameterException) e).getParameterName());
        log.error("参数校验异常：" + e.getMessage(), e);
        return Result.fail(msg);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Object methodArgumentNotValidExceptionHandler(HttpServletRequest request, Throwable e) {
        List<ObjectError> errors = ((MethodArgumentNotValidException) e).getBindingResult().getAllErrors();
        String msg = getValidExceptionMsg(errors);
        log.error("参数校验异常：" + e.getMessage(), e);
        return Result.fail(msg);
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    public Object constraintViolationExceptionHandler(HttpServletRequest request, Throwable e) {
        Set<ConstraintViolation<?>> sets = ((ConstraintViolationException) e).getConstraintViolations();
        String msg = Strings.EMPTY;
        if (CollectionUtil.isNotEmpty(sets)) {
            StringBuilder sb = new StringBuilder();
            sets.forEach(error -> {
                if (error instanceof FieldError) {
                    sb.append(((FieldError) error).getField()).append(":");
                }
                sb.append(error.getMessage()).append(";");
            });
            msg = sb.toString();
            msg = StringUtils.substring(msg, 0, msg.length() - 1);
        }
        log.error("参数校验异常：" + e.getMessage(), e);
        return Result.fail(msg);
    }

    @ExceptionHandler(value = BindException.class)
    public Object bindExceptionHandler(HttpServletRequest request, Throwable e) {
        List<ObjectError> errors = ((BindException) e).getBindingResult().getAllErrors();
        String msg = getValidExceptionMsg(errors);
        log.error("参数校验异常：" + e.getMessage(), e);
        return Result.fail(msg);
    }

    private String getValidExceptionMsg(List<ObjectError> errors) {
        if (CollectionUtil.isNotEmpty(errors)) {
            StringBuilder sb = new StringBuilder();
            errors.forEach(error -> {
                if (error instanceof FieldError) {
                    sb.append(((FieldError) error).getField()).append(":");
                }
                sb.append(error.getDefaultMessage()).append(";");
            });
            String msg = sb.toString();
            msg = StringUtils.substring(msg, 0, msg.length() - 1);
            return msg;
        }
        return null;
    }

}