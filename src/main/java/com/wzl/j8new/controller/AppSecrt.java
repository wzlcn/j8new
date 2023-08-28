package com.wzl.j8new.controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Author wzlong
 * @Date 2022/4/25 16:58
 * @Description:
 */
public class AppSecrt {
    /**
     * 全局数组
     */
    private final static String[] strDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d",
            "e", "f" };

    /**
     * 返回形式为数字和字符串
     *
     * @param bByte
     * @return
     */
    private static String byteToArrayString(byte bByte) {
        int iRet = bByte;
        if (iRet < 0) {
            iRet += 256;
        }
        int iD1 = iRet / 16;
        int iD2 = iRet % 16;
        return strDigits[iD1] + strDigits[iD2];
    }

    /**
     * 转换字节数组为16进制字串
     *
     * @param bByte
     * @return
     */
    private static String byteToString(byte[] bByte) {
        StringBuffer sBuffer = new StringBuffer();
        for (int i = 0; i < bByte.length; i++) {
            sBuffer.append(byteToArrayString(bByte[i]));
        }
        return sBuffer.toString();
    }

    public static void main(String[] args) {
        String APP_ID = "u6a7K0GlCE";
        // 系统当前时间
        String TIMESTAMP = "2023-01-09 11:12:05 279"; // 获取系统当前时间
        String TRANS_ID = "20230109111205279369818"; // 时间戳
//        String app_secret="zRJhMMK7RBczdqb29nIbnJNpzTPHL56A"; // 测试
        String  app_secret="DA5VzBcPEw6tadJPH45F9CsdL59ZV1zm"; // 正式

        String resultString = "APP_ID"+APP_ID+"TIMESTAMP"+TIMESTAMP+"TRANS_ID"+TRANS_ID+app_secret;
        System.out.println("---"+resultString);
        // 将给定字符串追加一个静态字符串，以提高复杂度
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        // md.digest() 该函数返回值为存放哈希值结果的byte数组
        resultString = byteToString(md.digest(resultString.getBytes()));
        System.out.println("====="+resultString);
    }
}
