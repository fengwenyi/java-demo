package com.fengwenyi.demo.designpattern.factory.base;

import com.fengwenyi.javalib.util.PrintUtils;

/**
 * @author Erwin Feng
 * @since 2021-01-12
 */
public class MiPhone implements Phone {
    @Override
    public void make() {
        PrintUtils.info("制造小米手机");
    }
}
