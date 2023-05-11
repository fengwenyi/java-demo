package com.fengwenyi.demo.file;

import cn.hutool.core.io.FileUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2022-07-17
 */
public class FileTests {

    @Test
    public void testLogFileRead() {
        String logPath = "E:\\projects\\idea-projects\\erwin\\data\\log\\erwin-service-2022-07-17.log";
        LogFileUtils.read(logPath);
        try {
            Thread.sleep(30 * 1000000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testReadFileByBufferedReader() {



        long startTime = System.currentTimeMillis();

        int total = 0;

        String dataFile = "/Users/fengwenyi/data/mmsi/polar_sorted_processed10.csv";

        // 创建 reader
        try (BufferedReader br = Files.newBufferedReader(Paths.get(dataFile))) {
            // 按行读取
            String line;
            while ((line = br.readLine()) != null) {
                // System.out.println(line);
                total++;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Duration duration = Duration.ofMillis((System.currentTimeMillis()) - startTime);
        System.out.println("读文件耗时" + duration.toMillis() + " 毫秒，共 " + total + " 条数据");
    }

    @Test
    public void testReadFileByRandomAccessFile() {

        long startTime = System.currentTimeMillis();

        int total = 0;

        String fileName = "/Users/fengwenyi/data/mmsi/polar_sorted_processed10.csv";
        try {
            RandomAccessFile randomFile = new RandomAccessFile(fileName, "r");
//            long fileLength = randomFile.length();
//            System.out.println("文件大小:" + fileLength);

            // 776057638
            // 9527047

            // randomFile.seek(9527047);

            String line;
            while ((line = randomFile.readLine()) != null) {
                  // System.out.println(line);
                // line = new String(line.getBytes("UTF-8"),"GBK");
                total++;
            }
            randomFile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Duration duration = Duration.ofMillis((System.currentTimeMillis()) - startTime);
        System.out.println("读文件耗时" + duration.toMillis() + " 毫秒，共 " + total + " 条数据");
    }

    @Test
    public void testReadFile() {

        long startTime = System.currentTimeMillis();

        String fileName = "/Users/fengwenyi/data/mmsi/polar_sorted_processed10.csv";

//        List<String> dataList = readTxtFile(fileName, "utf-8");
        // 读文件耗时4273 毫秒，共 9527047 条数据

//        List<String> dataList = readFileByBufferedReader(fileName);
        // 读文件耗时3865 毫秒，共 9527047 条数据

//        List<String> dataList = readFileByHuTool(fileName);
        // 读文件耗时8038 毫秒，共 9527047 条数据

//        List<String> dataList = readFileByGuava(fileName);
        // 读文件耗时9399 毫秒，共 9527047 条数据

//        List<String> dataList = readFileByCommonsIo(fileName);
        // 读文件耗时7197 毫秒，共 9527047 条数据

        List<String> dataList = readFileByCommonsIoLineIterator(fileName);
        // 读文件耗时8058 毫秒，共 9527047 条数据

        int total = dataList.size();

        Duration duration = Duration.ofMillis((System.currentTimeMillis()) - startTime);

        System.out.println("读文件耗时" + duration.toMillis() + " 毫秒，共 " + total + " 条数据");
    }


    public List<String> readTxtFile(String filePath, String charset) {
        try {
            File file = new File(filePath);
            if (file.isFile() && file.exists()) {
                InputStreamReader read = new InputStreamReader(new FileInputStream(file), charset);
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                // 每行的字符串列表
                List<String> strList = new ArrayList<>();
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    strList.add(lineTxt);
                }
                bufferedReader.close();
                read.close();
                return strList;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<String> readFileByBufferedReader(String filePath) {
        // 创建 reader
        try (BufferedReader br = Files.newBufferedReader(Paths.get(filePath))) {
            // 按行读取
            String line;
            List<String> list = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                list.add(line);
            }
            br.close();
            return list;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public List<String> readFileByHuTool(String filePath) {
        return FileUtil.readLines(filePath, "UTF-8");
    }

    public List<String> readFileByGuava(String filePath) {
        try {
            return com.google.common.io.Files.readLines(new File(filePath), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> readFileByCommonsIo(String filePath) {
        try {
            return FileUtils.readLines(new File(filePath), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> readFileByCommonsIoLineIterator(String filePath) {
        LineIterator it;
        try {
            it = FileUtils.lineIterator(new File(filePath), StandardCharsets.UTF_8.name());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            List<String> list = new ArrayList<>();
            while (it.hasNext()) {
                String line = it.nextLine();
                list.add(line);
            }
            return list;
        } finally {
            LineIterator.closeQuietly(it);
        }
    }

}
