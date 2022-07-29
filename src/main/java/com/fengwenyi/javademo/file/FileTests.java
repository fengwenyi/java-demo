package com.fengwenyi.javademo.file;

import org.junit.Test;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2022-07-17
 */
public class FileTests {

    @Test
    public void testLogFileRead() {
        String logPath = "E:\\projects\\idea-projects\\erwin\\data\\log\\erwin-service-2022-07-17.log";
        LogFileUtils.read(logPath);
        try {
            Thread.sleep(30 * 1000000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
