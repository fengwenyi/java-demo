package com.fengwenyi.javademo.binarydemo;

/**
 * @author Erwin Feng
 * @since 2020/9/16 2:27 下午
 */
public class BinaryDemo {

    public static void main(String[] args) {
        System.out.println(Integer.toBinaryString(-1));
        System.out.println(Integer.toBinaryString(1));
    }

    public static String toBinaryString(Integer i) {
        String s = Integer.toBinaryString(i);
        while (s.length() < 32) {
            //s = "0".
        }
        return null;
    }
}
