package com.fengwenyi.demo.listdemo;

import java.util.ArrayList;

/**
 * @author Erwin Feng
 * @since 2020-09-29
 */
public class ArrayListDemo {

    public static void main(String[] args) {
        ArrayList arrayList = new ArrayList();
        // arrayList.subList();
        Object clone = arrayList.clone();

        int size = 10;
        System.out.println(Integer.toBinaryString(size));
        size = size >> 1;
        System.out.println(Integer.toBinaryString(size));
        size = size << 2;
        System.out.println(size);
        System.out.println(Integer.toBinaryString(size));
    }

}
