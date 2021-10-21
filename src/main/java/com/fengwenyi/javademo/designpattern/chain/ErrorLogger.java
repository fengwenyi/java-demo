package com.fengwenyi.javademo.designpattern.chain;

import java.text.MessageFormat;

/**
 * @author <a href="https://www.fengwenyi.com">Erwin Feng</a>
 * @since 2021-10-21
 */
public class ErrorLogger extends AbstractLogger {

    public ErrorLogger(int level) {
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.err.println(MessageFormat.format("ErrorLogger: {0}", message));
    }
}
