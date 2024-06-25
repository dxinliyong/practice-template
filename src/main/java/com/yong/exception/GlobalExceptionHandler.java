package com.yong.exception;

import cn.hutool.core.collection.CollectionUtil;
import com.yong.base.ErrorCodeEnum;
import com.yong.base.Result;
import org.apache.commons.lang3.StringUtils;
import org.checkerframework.checker.units.qual.N;
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
@RestControllerAdvice(basePackages = {"com.yong.controller"})
public class GlobalExceptionHandler {
    @ExceptionHandler(value = {Throwable.class})
    Object handleException(Throwable e, HttpServletRequest request) {
        Result result = handleValidException(e);
        return result;
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

    private Result handleValidException(Throwable e) {
        // 异常处理
        if (e instanceof MissingServletRequestParameterException) {
            Result result = Result.buildErrorResult(ErrorCodeEnum.PARAM_ILLEGAL);
            String msg = MessageFormat.format("缺少参数{0}", ((MissingServletRequestParameterException) e).getParameterName());
            result.setMessage(msg);
            return result;
        }
        if (e instanceof MethodArgumentNotValidException) {
            // post请求的对象参数校验异常
            Result result = Result.buildErrorResult(ErrorCodeEnum.PARAM_ILLEGAL);
            List<ObjectError> errors = ((MethodArgumentNotValidException) e).getBindingResult().getAllErrors();
            String msg = getValidExceptionMsg(errors);
            if (StringUtils.isNotBlank(msg)) {
                result.setMessage(msg);
            }
            return result;
        }
        if (e instanceof ConstraintViolationException) {
            // 单个参数校验异常
            Result result = Result.buildErrorResult(ErrorCodeEnum.PARAM_ILLEGAL);
            Set<ConstraintViolation<?>> sets = ((ConstraintViolationException) e).getConstraintViolations();
            if (CollectionUtil.isNotEmpty(sets)) {
                StringBuilder sb = new StringBuilder();
                sets.forEach(error -> {
                    if (error instanceof FieldError) {
                        sb.append(((FieldError) error).getField()).append(":");
                    }
                    sb.append(error.getMessage()).append(";");
                });
                String msg = sb.toString();
                msg = StringUtils.substring(msg, 0, msg.length() - 1);
                result.setMessage(msg);
            }
            return result;
        }

        if (e instanceof BindException) {
            // get请求的对象参数校验异常
            Result result = Result.buildErrorResult(ErrorCodeEnum.PARAM_ILLEGAL);
            List<ObjectError> errors = ((BindException) e).getBindingResult().getAllErrors();
            String msg = getValidExceptionMsg(errors);
            if (StringUtils.isNotBlank(msg)) {
                result.setMessage(msg);
            }
            return result;
        }
        return null;
    }

}