package com.fengwenyi.javademo.designpattern.factory.base;

import com.fengwenyi.javalib.util.PrintUtils;

/**
 * @author Erwin Feng
 * @since 2021-01-12
 */
public class IPhone implements Phone {
    @Override
    public void make() {
        PrintUtils.info("制造苹果手机");
    }
}
