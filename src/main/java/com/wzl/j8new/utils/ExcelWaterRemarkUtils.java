package com.wzl.j8new.utils;

import org.apache.poi.ss.usermodel.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.Color;
import java.awt.Font;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;

/**
 * @Author wzlong
 * @Date 2022/10/28 14:55
 * @Description:
 */

public class ExcelWaterRemarkUtils {

    /**
     * 宽度
     */
    private static final int WIDTH = 360;
    /**
     * 高度
     */
    private static final int HEIGHT = 150;
    /**
     * 水印横向位置
     */
    private static int positionWidth = 80;
    /**
     * 水印纵向位置
     */
    private static int positionHeight = 30;
    /**
     * 水印文字 字体类型、风格、大小
     * microsoft-yahei、微软雅黑、宋体
     */
    private static Font font = new Font("微软雅黑", Font.PLAIN, 20);
    /**
     * 设置水印图片路径
     */
//    private static String imgPath = "E:\\水印.png";
    /**
     * 防止生产环境部署在docker或者linux服务器上，很有可能会引起中文水印乱码，成了方块的情况，这时候需要自己导入字体放到项目路径
     */
//    private static Font fontEncode = SystemLoadFont.styleFont(imgPath, Font.PLAIN, 30);
    /**
     * 水印文字颜色
     * 最后一个参数（第四个参数）为水印透明度
     */
    private static Color color = new Color(0, 0, 0, 50);

    /**
     * 根据文字生成水印图片
     *
     * @param text 水印文字
     * @return
     */
    public static BufferedImage createWaterMarkImage(String text) {
        // 获取bufferedImage对象创建空白图片
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
        // 获取Graphics2d对象创建图片画笔
        Graphics2D g = image.createGraphics();
        // 设置背景透明度
        image = g.getDeviceConfiguration().createCompatibleImage(WIDTH, HEIGHT, Transparency.TRANSLUCENT);
        g.dispose();
        g = image.createGraphics();
        // 设置对线段的锯齿状边缘处理
        // g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // 文字处理
        AttributedString ats = new AttributedString(text);

        ats.addAttribute(TextAttribute.FONT, font, 0, text.length());
        //ats.addAttribute(TextAttribute.FONT, fontEncode, 0, text.length());

        AttributedCharacterIterator iter = ats.getIterator();
        // 水印旋转
        g.rotate(Math.toRadians(-15), (double) image.getWidth() / 2, (double) image.getHeight() / 2);
        // 设置水印文字颜色
        g.setColor(color);
        // 设置水印字体加粗
        g.setStroke(new BasicStroke(1));

        // 设置水印文字Font
        g.setFont(font);
        //g.setFont(fontEncode);

        // 设置水印文字透明度
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
        // 水印位置
        g.drawString(iter, positionHeight, positionWidth);
        // 释放资源
        g.dispose();
        return image;
    }

    /**
     * 为Excel打上水印工具函数 请自行确保参数值，以保证水印图片之间不会覆盖。
     *
     * @param wb              Excel Workbook
     * @param sheet           需要打水印的Excel
     * @param image           水印图片
     * @param startXCol       水印起始列
     * @param startYRow       水印起始行
     * @param betweenXCol     水印横向之间间隔多少列
     * @param betweenYRow     水印纵向之间间隔多少行
     * @param XCount          横向共有水印多少个
     * @param YCount          纵向共有水印多少个
     * @param waterMarkWidth  水印图片宽度为多少列
     * @param waterMarkHeight 水印图片高度为多少行
     * @throws IOException
     */
    public static void putWaterMarkToExcel(Workbook wb, Sheet sheet, BufferedImage image, int startXCol,
                                           int startYRow, int betweenXCol, int betweenYRow, int XCount, int YCount, int waterMarkWidth,
                                           int waterMarkHeight) throws IOException {
        // 加载图片
        ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
        if (null == image) {
            throw new RuntimeException("向Excel上面打印水印，读取水印图片失败！");
        }
        ImageIO.write(image, "png", byteArrayOut);

        // 开始打水印
        Drawing drawing = sheet.createDrawingPatriarch();

        // 按照共需打印多少行水印进行循环
        for (int yCount = 0; yCount < YCount; yCount++) {
            // 按照每行需要打印多少个水印进行循环
            for (int xCount = 0; xCount < XCount; xCount++) {
                // 创建水印图片位置
                int xIndexInteger = startXCol + (xCount * waterMarkWidth) + (xCount * betweenXCol);
                int yIndexInteger = startYRow + (yCount * waterMarkHeight) + (yCount * betweenYRow);
                /*
                 * 参数定义： 第一个参数是（x轴的开始节点）； 第二个参数是（是y轴的开始节点）； 第三个参数是（是x轴的结束节点）；
                 * 第四个参数是（是y轴的结束节点）； 第五个参数是（是从Excel的第几列开始插入图片，从0开始计数）；
                 * 第六个参数是（是从excel的第几行开始插入图片，从0开始计数）； 第七个参数是（图片宽度，共多少列）；
                 * 第8个参数是（图片高度，共多少行）；
                 */
                ClientAnchor anchor = drawing.createAnchor(0, 0, 0, 0, xIndexInteger, yIndexInteger, xIndexInteger + waterMarkWidth, yIndexInteger + waterMarkHeight);
                Picture pic = drawing.createPicture(anchor,
                        wb.addPicture(byteArrayOut.toByteArray(), Workbook.PICTURE_TYPE_PNG));
                pic.resize();
            }
        }
    }

    private void export(HttpServletResponse response, Workbook workbook, String fileName, String text) throws Exception {
        // 重置响应对象
        response.reset();
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8") + ".xls");
        response.setHeader("content-Type", "application/vnd.ms-excel");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        // 写出数据输出流到页面
        try {
            OutputStream output = response.getOutputStream();
            BufferedOutputStream bufferedOutPut = new BufferedOutputStream(output);
            byte[] bytes = text.getBytes();
            text = new String(bytes, StandardCharsets.UTF_8);
            BufferedImage bufferedImage = ExcelWaterRemarkUtils.createWaterMarkImage(text);

            int sheets = workbook.getNumberOfSheets();
            //循环sheet给每个sheet添加水印
            for (int i = 0; i < sheets; i++) {
                Sheet sheet = workbook.getSheetAt(i);
                //获取excel实际所占行
                int row = sheet.getFirstRowNum() + sheet.getLastRowNum();
                //获取excel实际所占列
                int cell = sheet.getRow(sheet.getFirstRowNum()).getLastCellNum() + 1;
                for (int n = 0; n < cell; n++) {
                    sheet.autoSizeColumn((short) n, false);
                }
                //根据行与列计算实际所需多少水印
                ExcelWaterRemarkUtils.putWaterMarkToExcel(workbook, sheet, bufferedImage, 0, 0, 5, 5, cell / 5 + 1, row / 5 + 1, 0, 0);
                //excel加密只读
                //sheet.protectSheet(UUID.randomUUID().toString());
            }
            workbook.write(bufferedOutPut);
            bufferedOutPut.flush();
            bufferedOutPut.close();
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

