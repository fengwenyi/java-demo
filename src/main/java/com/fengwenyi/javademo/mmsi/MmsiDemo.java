package com.fengwenyi.javademo.mmsi;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-03-12
 */
public class MmsiDemo {

    /*
     *
     *
     *          n1        n2            n3       n4
     *          |          |            |        |
     *          |          |            |        |
     *          |          |            ---------- m4
     *          ------------m1          |        |
     *          |          |            |        |
     *   区域A   |  区域B   |     区域C   |  区域D  |   区域E
     *          |          |            |        |
     *          ------------m2          |        |
     *          |          |            ---------- m3
     *          |          |            |        |
     *          |          |            |        |
     *
     */

    private static  final float m1 = 68;
    private static  final float m2 = 66;
    private static  final float m3 = 66;
    private static  final float m4 = 68;
    private static  final float n1 = 10;
    private static  final float n2 = 12.5f;
    private static  final float n3 = 13;
    private static  final float n4 = 14;

    public static void main(String[] args) {
        mmsi();
    }

    private static void mmsi() {

        log("开始");

//        String filePath = "/Users/fengwenyi/data/mmsi/polar_sorted_processed10.csv";
        String filePath = "d:download/polar_sorted_processed10.csv";

        List<MmsiBo> list = readCsv(filePath);

        List<MmsiBo> listA = new ArrayList<>();
        List<MmsiBo> listB = new ArrayList<>();
        List<MmsiBo> listC = new ArrayList<>();
        List<MmsiBo> listD = new ArrayList<>();
        List<MmsiBo> listE = new ArrayList<>();
        for (MmsiBo mmsiBo : list) {

            float a = mmsiBo.getLon();
            float b = mmsiBo.getLat();

            if (judgeRegion01Left(a, b)) {
                listA.add(mmsiBo);
            } else if (judgeWithinRegion01(a, b)) {
                listB.add(mmsiBo);
            } else if (judgeRegion01_02(a, b)) {
                listC.add(mmsiBo);
            } else if (judgeWithinRegion02(a, b)) {
                listD.add(mmsiBo);
            } else if (judgeRegion02Right(a, b)) {
                listE.add(mmsiBo);
            } else {
                System.err.println("警告：该点不再指定的区域！" + mmsiBo);
            }
        }

        log("A区域(最左边)      ==> " + listA.size() + " 条");
        log("B区域(1区域内)     ==> " + listB.size() + " 条");
        log("C区域(1和2区域之间) ==> " + listC.size() + " 条");
        log("D区域(2区域内)     ==> " + listD.size() + " 条");
        log("E区域(最右边)      ==> " + listE.size() + " 条");

        Set<String> set = new HashSet<>();
        List<MmsiBo> resultList = new ArrayList<>();

        for (MmsiBo mmsiBo : list) {
            if (
                    judgeMmsi(listA, mmsiBo)
                            && judgeMmsi(listB, mmsiBo)
                            && judgeMmsi(listC, mmsiBo)
                            && judgeMmsi(listD, mmsiBo)
                            && judgeMmsi(listE, mmsiBo)
            ) {
                resultList.add(mmsiBo);
                set.add(mmsiBo.getMmsi());
            }
        }

        // System.out.println("满足条件的：" + set);
        if (!resultList.isEmpty()) {
            log("满足条件的数据 " + resultList.size() + " 条");
            writeResultCsv(resultList);
            log("结果写入 csv 完成");
        } else {
            log("没有满足条件的数据");
        }
    }

    private static void writeResultCsv(List<MmsiBo> resultList) {
        List<String> titleList = new ArrayList<>();
        titleList.add("MMSI");
        titleList.add("Time");
        titleList.add("Lon");
        titleList.add("Lat");
        titleList.add("Speed");
        titleList.add("Course");
        titleList.add("Length");
        titleList.add("Name");
        titleList.add("NavigationStatus");
        titleList.add("Type");
        titleList.add("ArriveTime");
        titleList.add("Destination");
        List<String[]> dataList = new ArrayList<>();
        for (MmsiBo mmsiBo : resultList) {
            String [] data = new String[] {
                    mmsiBo.getMmsi(),
                    mmsiBo.getTimestamp() + "",
                    mmsiBo.getLon() + "",
                    mmsiBo.getLat() + "",
                    mmsiBo.getSpend() + "",
                    mmsiBo.getCourse() + "",
                    mmsiBo.getCourse() + "",
                    mmsiBo.getLength(),
                    mmsiBo.getName(),
                    mmsiBo.getNavigationStatus(),
                    mmsiBo.getType(),
                    mmsiBo.getArriveTime(),
                    mmsiBo.getDestination(),
            };
            dataList.add(data);
        }
        writeCsv("d:\\result.csv", titleList, dataList);
    }

    private static boolean judgeMmsi(List<MmsiBo> list, MmsiBo mmsiBo) {
        for (MmsiBo bo : list) {
            if (bo.getMmsi().equals(mmsiBo.getMmsi())) {
                return true;
            }
        }
        return false;
    }

    public static List<MmsiBo> readCsv(String dataFile) {

        List<String> csvList = readCsvFile(dataFile);

        if (csvList == null || csvList.isEmpty()) {
            System.err.println("读取csv文件为空");
            return new ArrayList<>();
        }

        log("读取csv文件结束，共 " + csvList.size() + " 行");

        List<MmsiBo> list = new ArrayList<>(csvList.size() - 1);

        for (int i = 1; i < csvList.size(); i++) {
            list.add(parseToMmsiBo(csvList.get(i)));
        }

        log("csv数据转换完成，共 " + list.size() + " 条");

        return list;
    }

    private static MmsiBo parseToMmsiBo(String line) {
        // 分割
        String[] columns = line.split(",");
        MmsiBo mmsiBo = new MmsiBo();
        mmsiBo.setMmsi(columns[1]);
        // mmsiBo.setTimestamp(Long.parseLong(columns[2]));
        mmsiBo.setLon(Float.parseFloat(columns[3]));
        mmsiBo.setLat(Float.parseFloat(columns[4]));
        mmsiBo.setSpend(Float.parseFloat(columns[5]));
        mmsiBo.setCourse(Float.parseFloat(columns[6]));
        mmsiBo.setLength(columns[7]);
        mmsiBo.setName(columns[8]);
        mmsiBo.setNavigationStatus(columns[9]);
        mmsiBo.setType(columns[10]);
//            mmsiBo.setArriveTime(columns[11]);
        // mmsiBo.setDestination(columns[12]);
        return mmsiBo;
    }

    // 最左边
    private static boolean judgeRegion01Left(float a, float b) {
        return a < n1;
    }

    // 第一个区域内
    private static boolean judgeWithinRegion01(float a, float b) {
        return (n1 < a && a < n2) && (m2 < b && b < m1);
    }

    // 第一个区域和第二个区域之间
    private static boolean judgeRegion01_02(float a, float b) {
        return n2 < a && a < n3;
    }

    // 第二个区域内
    private static boolean judgeWithinRegion02(float a, float b) {
        return (m3 < b && b < m4) && (n3 < a && a < n4);
    }

    // 最右边
    private static boolean judgeRegion02Right(float a, float b) {
        return a > n4;
    }


    static class MmsiBo {
        private String mmsi;
        private Float lon;
        private Float lat;
        private Long timestamp;
        private Float spend;
        private Float course;
        private String length;
        private String name;
        private String navigationStatus;
        private String type;
        private String arriveTime;
        private String destination;


        public String getMmsi() {
            return mmsi;
        }

        public void setMmsi(String mmsi) {
            this.mmsi = mmsi;
        }

        public Float getLon() {
            return lon;
        }

        public void setLon(Float lon) {
            this.lon = lon;
        }

        public Float getLat() {
            return lat;
        }

        public void setLat(Float lat) {
            this.lat = lat;
        }

        public Long getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(Long timestamp) {
            this.timestamp = timestamp;
        }

        public Float getSpend() {
            return spend;
        }

        public void setSpend(Float spend) {
            this.spend = spend;
        }

        public Float getCourse() {
            return course;
        }

        public void setCourse(Float course) {
            this.course = course;
        }

        public String getLength() {
            return length;
        }

        public void setLength(String length) {
            this.length = length;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return "MmsiBo{" +
                    "mmsi='" + mmsi + '\'' +
                    ", lon=" + lon +
                    ", lat=" + lat +
                    ", timestamp=" + timestamp +
                    ", spend=" + spend +
                    ", course=" + course +
                    ", length='" + length + '\'' +
                    ", name='" + name + '\'' +
                    ", navigationStatus='" + navigationStatus + '\'' +
                    ", type='" + type + '\'' +
                    ", arriveTime='" + arriveTime + '\'' +
                    ", destination='" + destination + '\'' +
                    '}';
        }

        public String getNavigationStatus() {
            return navigationStatus;
        }

        public void setNavigationStatus(String navigationStatus) {
            this.navigationStatus = navigationStatus;
        }

        public String getArriveTime() {
            return arriveTime;
        }

        public void setArriveTime(String arriveTime) {
            this.arriveTime = arriveTime;
        }

        public String getDestination() {
            return destination;
        }

        public void setDestination(String destination) {
            this.destination = destination;
        }

    }

    private static void writeCsv(String filePath, List<String> titleList, List<String[]> dataList) {
        //第一步：设置输出的文件路径
        //如果该目录下不存在该文件，则文件会被创建到指定目录下。如果该目录有同名文件，那么该文件将被覆盖。
        File writeFile = new File(filePath);

        try{
            //第二步：通过BufferedReader类创建一个使用默认大小输出缓冲区的缓冲字符输出流
            BufferedWriter writeText = new BufferedWriter(new FileWriter(writeFile));

            StringJoiner titleStr = new StringJoiner(",");
            for (String title : titleList) {
                titleStr.add(title);
            }

            // writeText.newLine();    //换行
            //调用write的方法将字符串写到流中
            writeText.write(titleStr.toString());

            for (String[] strings : dataList) {
                writeText.newLine();    //换行
                StringJoiner data = new StringJoiner(",");
                for (String d : strings) {
                    data.add(d);
                }
                //调用write的方法将字符串写到流中
                writeText.write(data.toString());
            }

            //使用缓冲区的刷新方法将数据刷到目的地中
            writeText.flush();
            //关闭缓冲区，缓冲区没有调用系统底层资源，真正调用底层资源的是FileWriter对象，缓冲区仅仅是一个提高效率的作用
            //因此，此处的close()方法关闭的是被缓存的流对象
            writeText.close();
        }catch (FileNotFoundException e){
            System.out.println("没有找到指定文件");
        }catch (IOException e) {
            System.out.println("文件读写出错");
        }
    }

    private static List<String> readCsvFile(String filePath) {
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

    private static void log(String msg) {
        msg = String.format("%s | %s", LocalDateTime.now(), msg);
        System.out.println(msg);
    }

}
