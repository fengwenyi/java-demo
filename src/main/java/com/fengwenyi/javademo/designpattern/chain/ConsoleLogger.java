package com.fengwenyi.javademo.designpattern.chain;

import com.fengwenyi.javalib.util.PrintUtils;

import java.text.MessageFormat;

/**
 * @author <a href="https://www.fengwenyi.com">Erwin Feng</a>
 * @since 2021-10-21
 */
public class ConsoleLogger extends AbstractLogger {

    public ConsoleLogger(int level) {
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println(MessageFormat.format("ConsoleLogger: {0}", message));
    }
}
