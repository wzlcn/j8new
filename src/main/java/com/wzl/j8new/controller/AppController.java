package com.wzl.j8new.controller;

import com.wzl.j8new.aop.Log;
import com.wzl.j8new.bean.App;
import com.wzl.j8new.enums.BusinessType;
import com.wzl.j8new.service.AppService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author wangzhilong
 * @date 2020/7/17 10:07
 * @Description:
 */
@RestController
@RequestMapping(value = "app")
public class AppController {

    private static final Logger logger = LoggerFactory.getLogger(AppController.class);

    @Autowired
    private AppService appService;
    @Autowired
    HttpServletRequest request;

    @RequestMapping(value = "insert")
    @Log(title = "log测试", businessType = BusinessType.INSERT)
    public Map insert(@RequestBody App app) {
        logger.info("---------------进入insert方法");
        /*String flag = (String) request.getAttribute("flag");
        if (StringUtils.equals(flag,"invalid")){
            logger.info("ip非法，即将退出");
            return;
        }*/
        appService.insert(app);
        logger.info("新增成功");

        Map map = new HashMap();
        map.put("status","0000");
        map.put("messages","新增成功");
        return map;
    }

    @RequestMapping(value = "query")
    @Log(title = "log测试", businessType = BusinessType.QUERY)
    public List<Map> query(@RequestBody App app) {
        return appService.query();
    }

    @RequestMapping(value = "query2")
    @Log(title = "log测试", businessType = BusinessType.QUERY)
    public List<Map> query2(@RequestBody App app) {
        List<Map> list = appService.query2();
        // 0 和 parent_level(lev) 可以让前端传
        List<Map> collect = list.stream().filter(e -> "0".equals(e.get("parent_level").toString()))
                .map(e -> {
                    Map map = new HashMap();
                    map.put("scene_name",e.get("scene_name"));
                    if (CollectionUtils.isNotEmpty(getChildList(e, list))) {
                        map.put("childList", getChildList(e, list));
                    }
                    return map;
                }).collect(Collectors.toList());
        return collect;
    }

    private List<Map> getChildList(Map map, List<Map> list) {
        List<Map> collect = list.stream().filter(e -> e.get("parent_level").toString().equals(map.get("lev").toString()))
                .map(e -> {
                    Map map1 = new HashMap();
                    map1.put("scene_name", e.get("scene_name"));
                    if (CollectionUtils.isNotEmpty(getChildList(e, list))) {
                        map1.put("childList", getChildList(e, list));
                    }
                    return map1;
                }).collect(Collectors.toList());
        return collect;
    }
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> map = new HashMap<>();
        for(int i = 0;i < nums.length;i++){
            int num = target - nums[i];
            if(map.containsKey(num)){
                return new int[]{map.get(num),i};
            }
            map.put(nums[i],i);
        }
        return new int[0];
    }

    public static void main(String[] args) {
//        System.out.println(twoSum(new int[]{2,3,5,7,10},9));
        String date = "2003-03-01";
        char[] chars = date.toCharArray();
        for (int i = 0; i < chars.length; i++) {

        }
        System.out.println(chars[9]);
    }
}
