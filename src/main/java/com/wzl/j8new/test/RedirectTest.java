package com.wzl.j8new.test;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author wangzhilong
 * @date 2021/4/27 16:52
 * @Description: 跳转页面测试
 */
@RestController
@RequestMapping(value = "redirectTest")
public class RedirectTest {

    @RequestMapping(value = "redirectTest")
    public void redirectTest(HttpServletRequest req, HttpServletResponse resp){
        StringBuffer requestURL = req.getRequestURL();
        System.out.println("本次请求url：" + requestURL.toString());
        System.out.println(requestURL.toString());
        System.out.println("即将访问百度页面------------");
        try {
            resp.sendRedirect("http://www.baidu.com?123");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        boolean b = false;
        int j = 0;
        for (int i = 0; i < 3; i++) {
            while (!b) {
                j++;
                System.out.println("===========" + i);
                for (int k = 0; k < 3; k++) {
                    if (j == 2) {
                        b = true;
                        break;
                    }
                }
            }
            if (!b) {
                try {
                    Thread.currentThread().sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                break;
            }
        }
    }
}
