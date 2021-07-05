package com.wzl.j8new.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author wangzhilong
 * @date 2021/7/5 14:44
 * @Description: 利用注解的形式实现aop
 */
@Aspect
@Component
public class AopAdvice {
    @Pointcut("@annotation(AopTest)")
    private void aopAdvice(){}

    @Around(value = "aopAdvice()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        System.out.println("aop前的业务逻辑");
        Object proceed = point.proceed();
        System.out.println("aop后的业务逻辑");
        return proceed;
    }
}
