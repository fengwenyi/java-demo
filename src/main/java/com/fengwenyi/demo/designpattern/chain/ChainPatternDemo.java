package com.fengwenyi.demo.designpattern.chain;

import org.junit.Test;

/**
 * @author <a href="https://www.fengwenyi.com">Erwin Feng</a>
 * @since 2021-10-21
 */
public class ChainPatternDemo {

    @Test
    public void test() {
        AbstractLogger loggerChain = getChainOfLoggers();

        loggerChain.log(AbstractLogger.LEVEL_INFO, "This is an info log.");
        loggerChain.log(AbstractLogger.LEVEL_DEBUG, "This is a debug log.");
        loggerChain.log(AbstractLogger.LEVEL_ERROR, "This is an error log.");
    }


    private AbstractLogger getChainOfLoggers() {
        AbstractLogger errorLogger = new ErrorLogger(AbstractLogger.LEVEL_ERROR);
        AbstractLogger fileLogger = new FileLogger(AbstractLogger.LEVEL_DEBUG);
        AbstractLogger consoleLogger = new ConsoleLogger(AbstractLogger.LEVEL_INFO);

        errorLogger.setNextLogger(fileLogger);
        fileLogger.setNextLogger(consoleLogger);

        return errorLogger;
    }
}
