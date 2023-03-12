package com.fengwenyi.javademo.mmsi;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

        List<MmsiBo> list = readCsv();

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
    }

    private static boolean judgeMmsi(List<MmsiBo> list, MmsiBo mmsiBo) {
        for (MmsiBo bo : list) {
            if (bo.getMmsi().equals(mmsiBo.getMmsi())) {
                return true;
            }
        }
        return false;
    }

    public static List<MmsiBo> readCsv() {

        List<MmsiBo> list = new ArrayList<>();

//         String dataFile = "D:\\download\\data.csv";
        String dataFile = "D:\\download\\polar_sorted_processed10.csv";

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

}
