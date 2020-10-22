package com.wzl.j8new.interceptor;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;

/**
 * @author wangzhilong
 * @date 2020/9/10 13:24
 * @Description: 限制域名访问
 */
public class RequestInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(RequestInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        int endIndex = request.getRequestURL().length() - request.getPathInfo().length() + 1;
//        String url = request.getRequestURL().substring(0, endIndex);
        String url = request.getRequestURL().toString();
        String origin = request.getHeader("Origin");
        if (StringUtils.equals(url,"http://localhost:8080/app/insert")){
            response.reset();
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding(StandardCharsets.UTF_8.name());
            response.getWriter().println("非法ip禁止访问");
            request.setAttribute("flag","invalid");
            return true;
        }
        logger.info("----------------url:----------" + url);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
