package com.wzl.j8new.serviceimpl;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.wzl.j8new.bean.Kqjl;
import com.wzl.j8new.service.ReadExcelService;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;

import javax.net.ssl.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;


/**
 * @Author wzlong
 * @Date 2021/9/26 10:35
 * @Description:
 */
@Service
public class ReadExcelServiceImpl implements ReadExcelService {
    @Override
    public void readExcel(InputStream inputStream) {
        ExcelReader reader = ExcelUtil.getReader(inputStream);
        List<Map<String, Object>> maps = reader.readAll();
        ExcelWriter writer = reader.getWriter();
        Workbook workbook = writer.getWorkbook();
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> iterator = sheet.iterator();
        //记录一个周期内，某一个员工的上班卡
        List<String> rowOfAllCellsAM = new ArrayList<>();
        //记录一个周期内，某一个员工的下班卡(暂时只记录最晚打卡时间)
        List<String> rowOfAllCellsPM = new ArrayList<>();
        //记录某一个周期内，某个员工的考勤数据
        List<Kqjl> kqjlList = new ArrayList<>();
        //控制上下班打卡集合的标志位，当为true时清除两个集合的内容，开始收集下一个人的数据
        boolean flag = true;
        //获取一个考勤周期
        int cycle = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        String beginDate = null,endDate = null;
        int num = 0;
        int start = 3;
        while (iterator.hasNext()) {
            Row row = iterator.next();
            if (row.getRowNum() == 0) {
                continue;
            }
            String cell0Value = null;
            Cell cell0 = null;
            //计算考勤周期并生成对应数量的记录
            if (row.getRowNum() == 1) {
                cell0 = row.getCell(0);
                cell0Value = StringUtils.deleteWhitespace(cell0.getStringCellValue());
                System.out.println("cell0Value:" + cell0Value);
                System.out.println("cell0Value.length():" + cell0Value.length());
                beginDate = cell0Value.substring(5,15);
                endDate = cell0Value.substring(16,26);
                cycle = Period.between(LocalDate.parse(beginDate), LocalDate.parse(endDate)).getDays() + 1;
                for (int i = 0; i < cycle; i++) {
                    Kqjl kqjl = new Kqjl();
                    kqjlList.add(kqjl);
                }
                //2021-08-26 ~ 2021-09-25
                if (StringUtils.isNotEmpty(beginDate)) {
                    int year = Integer.valueOf(beginDate.substring(0, 4));
                    int month = Integer.valueOf(beginDate.substring(5, 7).startsWith("0") ? beginDate.substring(5, 7).substring(1,2) : beginDate.substring(5, 7));
                    int day = Integer.valueOf(beginDate.substring(8, 10));
                    c.set(year,month - 1,day);
                }
                continue;
            }
            cell0 = row.getCell(0);
            cell0Value = StringUtils.deleteWhitespace(cell0.getStringCellValue());
            if (row.getRowNum() > 2) {
                System.out.println("cell0Value:" + cell0Value);
                if (StringUtils.isNotEmpty(cell0Value)) {
                    flag = false;
                }
            }
            if (!flag) {
                //开始处理上一个人的考勤数据
                for (Kqjl kqjl : kqjlList) {
                    kqjl.setStaffNum(Integer.valueOf(rowOfAllCellsAM.get(0)));
                    kqjl.setStaffName(rowOfAllCellsAM.get(1));
                    kqjl.setStaffDepartment(rowOfAllCellsAM.get(2));
                    c.add(Calendar.DAY_OF_MONTH,num++);
                    kqjl.setCheckinTime(c.getTime());
                    try {
                        if (StringUtils.isNotEmpty(rowOfAllCellsAM.get(start))){
                            kqjl.setBegainTime(sdf.parse(beginDate + " "  + rowOfAllCellsAM.get(start) + ":00"));
                        }
                        if (StringUtils.isNotEmpty(rowOfAllCellsPM.get(start))){
                            kqjl.setBegainTime(sdf.parse(beginDate + " "  + rowOfAllCellsPM.get(start) + ":00"));
                        }
                    } catch (Exception e){
                    }
                    start++;
                    //TODO 获取当天是否为工作日

                }
                //TODO 将考勤集合的数据批量加入数据库 同时清除上下班集合，重置num、start变量 改变flag
                rowOfAllCellsAM.clear();
                rowOfAllCellsPM.clear();
                num = 0;
                start = 3;
                flag = true;
            }
            System.out.println("FirstRow=" + sheet.getFirstRowNum()
                    + ",LastRow=" + sheet.getLastRowNum()
                    + ",FirstCell=" + row.getFirstCellNum()
                    + ",LastCell=" + row.getLastCellNum()
                    + ",RowNum=" + row.getRowNum());


            Iterator<Cell> cellIter = row.cellIterator();
            //遍历某行的所有单元格
            while (cellIter.hasNext()) {
                Cell cell = cellIter.next();
                int index = cell.getColumnIndex();
                switch (cell.getCellType()) {
                    //String类型
                    case STRING:
                        System.out.println("index:" + index + "  String：" + cell.getStringCellValue());
                        //在计算一个员工时，如果某一行的第一个单元格不为空，那么将数据加入上班卡集合
                        if (StringUtils.isNotEmpty(cell0Value)) {
                            rowOfAllCellsAM.add(cell.getStringCellValue());
                        }
                        //在计算一个员工时，如果某一行的第一个单元格为空，那么将数据加入下班卡集合
                        else {
                             if (rowOfAllCellsPM.size() == index){
                                rowOfAllCellsPM.add(cell.getStringCellValue());
                             }
                            //如果当天打了多个下班卡，只记录最后打卡时间 TODO：是否要记录全部时间
                             else if (rowOfAllCellsPM.size() > index && StringUtils.isNotEmpty(cell.getStringCellValue())){
                                rowOfAllCellsPM.set(index,cell.getStringCellValue());
                             }
                        }
                        break;
                    //boolean类型
                    case BOOLEAN:
                        System.out.println("Boolean:" + cell.getBooleanCellValue());
                        break;
                    //Double类型
                    case NUMERIC:
                        System.out.println("Double:" + cell.getNumericCellValue());
                        break;
                    case FORMULA:
                        System.out.println("formula:" + cell.getDateCellValue().toString());
                        break;
                    //空白的单元格
                    case BLANK:
                        System.out.println("Blank-->" + "index:" + index + "  String：" + cell.getStringCellValue());
                        break;
                    default:
                        break;
                }
            }
            System.out.println("rowOfAllCellsAM:" + rowOfAllCellsAM);
            System.out.println("rowOfAllCellsPM:" + rowOfAllCellsPM);
            //开始处理一个人的考勤数据

        }
        System.out.println(maps);
    }

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        try {
            String path = "https://esim.10010sh.cn/MP_verify_1RkHAnCEOqKwNmgd.txt";
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            boolean useHttps = path.startsWith("https");
            if (useHttps) {
                HttpsURLConnection https = (HttpsURLConnection) conn;
                trustAllHosts(https);
                https.setHostnameVerifier(DO_NOT_VERIFY);
            }
            conn.setDoInput(true);
            conn.connect();
            InputStream is = conn.getInputStream();
            InputStreamReader isReader = new InputStreamReader(is);
            BufferedReader bReader = new BufferedReader(isReader);
            String input = "";
            while ((input = bReader.readLine()) != null) {
                sb.append(input);
            }

            isReader.close();
            is.close();
            conn.disconnect();

            System.out.println(sb.toString());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    /**
     * 信任所有
     * @param connection
     * @return
     */
    private static SSLSocketFactory trustAllHosts(HttpsURLConnection connection) {
        SSLSocketFactory oldFactory = connection.getSSLSocketFactory();
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            SSLSocketFactory newFactory = sc.getSocketFactory();
            connection.setSSLSocketFactory(newFactory);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return oldFactory;
    }
    /**
     * 覆盖java默认的证书验证
     */
    private static final TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
        @Override
        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
            return new java.security.cert.X509Certificate[]{};
        }

        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
        }
    }};
    /**
     * 设置不验证主机
     */
    private static final HostnameVerifier DO_NOT_VERIFY = new HostnameVerifier() {
        @Override
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    };
}
