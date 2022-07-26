package com.wzl.j8new.controller;

import com.wzl.j8new.bean.CommonResult;
import com.wzl.j8new.service.ReadExcelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author wzlong
 * @Date 2021/9/26 10:18
 * @Description:
 */
@RestController
@RequestMapping("/excel")
public class ReadExcelController {

    private static final Logger logger = LoggerFactory.getLogger(ReadExcelController.class);

    @Autowired
    private ReadExcelService readExcelService;

    @PostMapping("/read")
    public CommonResult readExcel(@RequestParam("file") MultipartFile file){
        CommonResult commonResult = new CommonResult();
        try {
            InputStream inputStream = file.getInputStream();
            readExcelService.readExcel(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        commonResult.setCode(200);
        commonResult.setMessage("导入excel成功");
        return commonResult;
    }
}
