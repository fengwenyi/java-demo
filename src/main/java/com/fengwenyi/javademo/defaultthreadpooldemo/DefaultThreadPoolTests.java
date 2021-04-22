package com.fengwenyi.javademo.defaultthreadpooldemo;

import java.util.concurrent.Executors;

/**
 * @author <a href="https://www.fengwenyi.com">Erwin Feng</a>
 * @since 2021-04-22
 */
public class DefaultThreadPoolTests {

    void testCacheThreadPool() {

        /*
        core = 0
        max = Integer.MAX_VALUE
         */
        Executors.newCachedThreadPool();
    }

    void testFixedThreadPool() {

        /*
        core = coreThreadPoolNum
        max = coreThreadPoolNum
         */

        int coreThreadPoolNum = 10;
        Executors.newFixedThreadPool(coreThreadPoolNum);
    }

    void testSingleThreadPool() {
        /*
        core = 1
        max = 1
         */
        Executors.newSingleThreadExecutor();
    }

    void testScheduledThreadPool() {
        /*
        core = coreThreadPoolNum
        max = Integer.MAX_VALUE
         */
        int coreThreadPoolNum = 10;
        Executors.newScheduledThreadPool(coreThreadPoolNum);
    }

}
