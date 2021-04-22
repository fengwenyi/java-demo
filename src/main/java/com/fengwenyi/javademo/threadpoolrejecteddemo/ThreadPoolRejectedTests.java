package com.fengwenyi.javademo.threadpoolrejecteddemo;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="https://www.fengwenyi.com">Erwin Feng</a>
 * @since 2021-04-22
 */
public class ThreadPoolRejectedTests {

    void testAbortPolicy() {
        /*
        直接抛出异常
         */
        new ThreadPoolExecutor(1, 2, 0,
                TimeUnit.MICROSECONDS,
                new LinkedBlockingDeque<Runnable>(),
                new ThreadPoolExecutor.AbortPolicy());
    }

}
