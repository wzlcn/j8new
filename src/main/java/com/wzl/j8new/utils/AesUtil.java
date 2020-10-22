package com.wzl.j8new.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @author wangzhilong
 * @date 2020/8/31 13:56
 * @Description: AES加解密工具类
 */
public class AesUtil {
    private static Logger log = LoggerFactory.getLogger(AesUtil.class);

    /**
     * AES方式解密文件
     *
     * @param encryPath   未解密文件路径
     * @param sKey
     * @param decryptPath 解密之后文件路径
     * @return
     */
    public static File decryptFile(String encryPath, String sKey, String decryptPath) {
        Cipher cipher = null;
        File decryptFile = new File(decryptPath);
        if (!decryptFile.getParentFile().exists()) {
            decryptFile.getParentFile().mkdirs();
        }
        try {
            cipher = initAESCipher(sKey, Cipher.DECRYPT_MODE, 1);
        } catch (Exception e) {
            log.error("初始化AES加密异常：{}", e);
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(encryPath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(decryptFile))) {
            StringBuilder sb = new StringBuilder();
            String temp;
            while ((temp = reader.readLine()) != null) {
                sb.append(temp);
            }
            String str = new String(sb);
            byte[] tempBuffer = new BASE64Decoder().decodeBuffer(str);
            byte[] original = cipher.doFinal(tempBuffer);
            writer.write(new String(original, StandardCharsets.UTF_8));
        } catch (Exception e) {
            log.error("解密文件出现异常：{}", e);
        }
        return decryptFile;
    }

    /**
     * 对文件进行AES加密
     *
     * @param encryPath   加密之后文件路径
     * @param sKey
     * @param decryptPath 未加密文件路径
     * @return
     */
    public static File encryptFile(String encryPath, String sKey, File decryptPath) {
        Cipher cipher = null;
        File encrypfile = new File(encryPath);
        if (!encrypfile.getParentFile().exists()) {
            encrypfile.getParentFile().mkdirs();
        }
        try {
            encrypfile.createNewFile();
            cipher = initAESCipher(sKey, Cipher.ENCRYPT_MODE, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(decryptPath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(encrypfile))) {
            StringBuilder sb = new StringBuilder();
            String temp;
            while ((temp = reader.readLine()) != null) {
                sb.append(temp);
                sb.append("\n");
            }
            String str = new String(sb);
            byte[] original = cipher.doFinal(str.getBytes(StandardCharsets.UTF_8));
            String encode = new BASE64Encoder().encode(original);
            writer.write(encode);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return encrypfile;
    }

    public static void main(String[] args) {
        encryptFile("D:\\cs\\jiami.txt","wangzhilongwangz",
                new File("C:\\Users\\DELL\\Desktop\\jiami.txt"));
        decryptFile("D:\\cs\\jiami.txt","wangzhilongwangz",
                "D:\\cs\\jiemi.txt");
    }
    /**
     * 初始化 AES Cipher
     *
     * @param
     * @param cipherMode
     * @return
     */
    public static Cipher initAESCipher(String key, int cipherMode, int code) throws Exception {
        //创建Key gen
        SecretKey secretKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
        Cipher c = Cipher.getInstance("AES/ECB/PKCS5Padding");
        if (0 == code) {
            c.init(Cipher.ENCRYPT_MODE, secretKey);
        } else if (1 == code) {
            c.init(Cipher.DECRYPT_MODE, secretKey);
        }
        return c;
    }

}
