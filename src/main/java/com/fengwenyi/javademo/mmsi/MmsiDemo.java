package com.fengwenyi.javademo.mmsi;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
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
     *   区域A   |  区域B   |     区域C   |  区域D  |   区域D
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
//        testWriteCsv();

    }

    private static void mmsi() {
        List<MmsiBo> list = readCsv("d:\\data.csv");

        System.out.println("原始数据条数：" + list.size());

        List<MmsiBo> list1 = new ArrayList<>();
        List<MmsiBo> list2 = new ArrayList<>();
        List<MmsiBo> list3 = new ArrayList<>();
        List<MmsiBo> list4 = new ArrayList<>();
        List<MmsiBo> list5 = new ArrayList<>();
        for (MmsiBo mmsiBo : list) {

            float a = mmsiBo.getLon();
            float b = mmsiBo.getLat();

            if (judgeRegion01Left(a, b)) {
                list1.add(mmsiBo);
            } else if (judgeWithinRegion01(a, b)) {
                list2.add(mmsiBo);
            } else if (judgeRegion01_02(a, b)) {
                list3.add(mmsiBo);
            } else if (judgeWithinRegion02(a, b)) {
                list4.add(mmsiBo);
            } else if (judgeRegion02Right(a, b)) {
                list5.add(mmsiBo);
            } else {
                System.err.println("警告：该点不再指定的区域！" + mmsiBo);
            }
        }

        System.out.println("最左边==>" + list1.size() + "条");
        System.out.println("1区域内==>" + list2.size() + "条");
        System.out.println("1_2区域之间==>" + list3.size() + "条");
        System.out.println("2区域内==>" + list4.size() + "条");
        System.out.println("最右边==>" + list5.size() + "条");

        Set<String> set = new HashSet<>();
        List<MmsiBo> resultList = new ArrayList<>();

        for (MmsiBo mmsiBo : list) {
            if (
                    judgeMmsi(list1, mmsiBo) &&
                            judgeMmsi(list2, mmsiBo)
                            && judgeMmsi(list3, mmsiBo)
                            && judgeMmsi(list4, mmsiBo)
                            && judgeMmsi(list5, mmsiBo)
            ) {
                resultList.add(mmsiBo);
                set.add(mmsiBo.getMmsi());
            }
        }

        System.out.println("满足条件的：" + set);
        if (!resultList.isEmpty()) {
            writeResultCsv(resultList);
        }
    }

    private static void writeResultCsv(List<MmsiBo> resultList) {
        List<String> titleList = new ArrayList<>();
        titleList.add("MMSI");
        titleList.add("LON");
        titleList.add("LAT");
        List<String[]> dataList = new ArrayList<>();
        for (MmsiBo mmsiBo : resultList) {
            String [] data = new String[] {
                    mmsiBo.getMmsi(),
                    mmsiBo.getLon().toString(),
                    mmsiBo.getLat().toString()
            };
            dataList.add(data);
        }
        writeCsv("d:\\result.csv", titleList, dataList);
    }

    private static void testWriteCsv() {
        List<String> titleList = new ArrayList<>();
        titleList.add("MMSI");
        titleList.add("LON");
        titleList.add("LAT");
        List<String[]> dataList = new ArrayList<>();
        String [] data = new String[] {
                "1.1",
                "1.2",
                "1.3"
        };
        dataList.add(data);
        String [] data2 = new String[] {
                "2.1",
                "2.2",
                "2.3"
        };
        dataList.add(data2);
        writeCsv("/Users/fengwenyi/Temp/mmsi/test.csv", titleList, dataList);
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

        List<MmsiBo> list = new ArrayList<>();

        // 创建 reader
        try (BufferedReader br = Files.newBufferedReader(Paths.get(dataFile))) {
            // CSV文件的分隔符
            String DELIMITER = ",";
            // 按行读取
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                if (i == 0) {
                    i++;
                    continue;
                }
                // 分割
                String[] columns = line.split(DELIMITER);
                // 打印行
                // System.out.println("["+ String.join(", ", columns) +"]");
                MmsiBo mmsiBo = new MmsiBo();
                mmsiBo.setMmsi(columns[1]);
                mmsiBo.setLon(Float.parseFloat(columns[3]));
                mmsiBo.setLat(Float.parseFloat(columns[4]));
                list.add(mmsiBo);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return list;
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

        @Override
        public String toString() {
            return "MmsiBo{" +
                    "mmsi='" + mmsi + '\'' +
                    ", lon=" + lon +
                    ", lat=" + lat +
                    '}';
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

}
