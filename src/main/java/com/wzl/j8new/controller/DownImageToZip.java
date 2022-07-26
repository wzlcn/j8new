package com.wzl.j8new.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author wzlong
 * @Date 2022/3/23 16:09
 * @Description:
 */
public class DownImageToZip {
    /**
     * 将下载的图片按天压缩
     * @throws IOException
     */
    /*private boolean zipPic(String picPath,String synDate,Map<String, String> addrMap) throws IOException {
        //这里由于是多线程存储图片流，所以需要使用线程安全的map，因此使用ConcurrentHashMap
        Map<String, InputStream> pictureList=new ConcurrentHashMap<String,InputStream>();
        //这里定义每个线程下载的图片个数
        int count=400;
        //存储需要下载的图片地址
        List<Map.Entry<String, String>> addrList=new ArrayList<Map.Entry<String, String>>(addrMap.entrySet());
        //线程数，加一是因为要创建一个线程下载最后不足400个的图片
        int nThreads=(addrList.size()/count)+1;
        //CountDownLatch countDownLatch = new CountDownLatch(nThreads);
        try
        {
            boolean downPic=false;
            //执行多线程下载图片
            downPic=downPic(picPath,addrList,picList,pictureList,nThreads,count);
            if (downPic)
            {
                ZipUtil.zipByArray(picList,new File(picPath+synDate+".zip"));
            }
            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    *//**
     * 根据url地址下载图片
     * @throws InterruptedException
     *//*
    private boolean downPic(String picPath,List<Entry<String, String>> addrList,Map<String, byte[]> picList,Map<String, InputStream> pictureList,int nThreads,int count)throws IOException, InterruptedException{
        ExecutorService threadPool=Executors.newFixedThreadPool(nThreads);
        // 创建两个个计数器
        CountDownLatch begin=new CountDownLatch(0);
        CountDownLatch end=new CountDownLatch(nThreads);
        // 循环创建线程
        for (int i = 0; i < nThreads; i++) {
            List<Entry<String, String>>subAddrList=null;
            // 计算每个线程执行的数据
            if ((i + 1) == nThreads) {
                int startIndex = (i * count);
                int endIndex = addrList.size();
                subAddrList = addrList.subList(startIndex, endIndex);
            } else {
                int startIndex = (i * count);
                int endIndex = (i + 1) * count;
                subAddrList = addrList.subList(startIndex, endIndex);
            }
            // 线程类
            PicDownload mythead = new PicDownload(picPath,subAddrList,picList,pictureList);
            // 这里执行线程的方式是调用线程池里的threadPool.execute(mythead)方法。
            try
            {
                threadPool.execute(mythead);
            }
            catch (Exception e)
            {
                //记录错误日志
                return false;
            }
        }
        begin.countDown();
        end.await();
        // 执行完关闭线程池
        threadPool.shutdown();
        //这里一定要循环直到线程池中所有线程都结束才能往下走，测试时由于没有这一步导致子线程下载图片还没完成，而主线程已经往下走了，导致压缩包内没有图片
        //也可以使用CountDownLatch实现
         *//*while (true)
         {
         if (threadPool.isTerminated())
         {
          System.out.println("所有子线程已结束！");
          break;
         }
         }*//*
        return true;
    }*/
}
