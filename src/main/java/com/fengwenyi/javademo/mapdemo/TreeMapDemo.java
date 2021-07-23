package com.fengwenyi.javademo.mapdemo;

import com.fengwenyi.javalib.util.PrintUtils;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author Erwin Feng
 * @since 2021-02-06
 */
public class TreeMapDemo {

    public static void main(String[] args) {
        Map<String, Integer> map = new TreeMap<>();
        map.put("b", 20);
        map.put("c", 30);
        map.put("d", 40);
        map.put("a", 10);
        map.forEach((k, v) -> PrintUtils.info(k + "->" + v));
    }

}
