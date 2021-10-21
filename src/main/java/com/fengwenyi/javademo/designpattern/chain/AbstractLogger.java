package com.fengwenyi.javademo.designpattern.chain;

import java.util.Objects;

/**
 * @author <a href="https://www.fengwenyi.com">Erwin Feng</a>
 * @since 2021-10-21
 */
public abstract class AbstractLogger {

    public static final int LEVEL_INFO = 1;
    public static final int LEVEL_DEBUG = 2;
    public static final int LEVEL_ERROR = 3;

    protected int level;

    private AbstractLogger nextLogger;

    public void setNextLogger(AbstractLogger nextLogger) {
        this.nextLogger = nextLogger;
    }

    public void log(int level, String message) {
        if (this.level <= level) {
            write(message);
        }
        if (Objects.nonNull(nextLogger)) {
            nextLogger.log(level, message);
        }
    }

    protected abstract void write(String message);
}
