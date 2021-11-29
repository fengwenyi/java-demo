package com.fengwenyi.javademo.functiondemo;

import java.util.function.Function;

/**
 * @author <a href="https://www.fengwenyi.com">Erwin Feng</a>
 * @since 2021-11-29
 */
public class FunctionDemo2 {

    public static void main(String[] args) {
        // 计算给定一个数，加1
        System.out.println(increaseOne(1));
        Function<Integer, Integer> increaseOneByFunction = num -> num + 1;
        System.out.println(increaseOneByFunction.apply(1));
    }

    private static int increaseOne(int num) {
        return ++num;
    }

}
