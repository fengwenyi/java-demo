package com.fengwenyi.demo.designpattern.chain;

import java.text.MessageFormat;

/**
 * @author <a href="https://www.fengwenyi.com">Erwin Feng</a>
 * @since 2021-10-21
 */
public class FileLogger extends AbstractLogger {

    public FileLogger(int level) {
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println(MessageFormat.format("FileLogger: {0}", message));
    }
}
