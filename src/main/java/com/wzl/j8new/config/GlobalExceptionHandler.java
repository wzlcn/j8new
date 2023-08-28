package com.wzl.j8new.config;

import javax.servlet.http.HttpServletRequest;


import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * 全局异常处理器
 *
 * @author wxy
 */
@RestControllerAdvice
public class GlobalExceptionHandler
{
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(GlobalExceptionHandler.class);


    /**
     * 业务异常
     */
    @ExceptionHandler(value = Exception.class)
    public AjaxResult handleServiceException(Exception e, HttpServletRequest request)
    {
        log.error(e.getMessage(), e);
        return StringUtils.isNotEmpty(e.getMessage()) ? AjaxResult.error("未知异常1111") : AjaxResult.error("未知异常2222");
    }

}

