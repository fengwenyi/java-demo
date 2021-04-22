package com.fengwenyi.javademo.threadpooldemo;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池拒绝策略
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

    void testCallerRunsPolicy() {
        /*
        交有线程池所在的线程运行
         */
        new ThreadPoolExecutor(1, 2, 0,
                TimeUnit.MICROSECONDS,
                new LinkedBlockingDeque<Runnable>(),
                new ThreadPoolExecutor.CallerRunsPolicy());
    }

    void testDiscardPolicy() {
        /*
        不处理，丢弃，空实现
         */
        new ThreadPoolExecutor(1, 2, 0,
                TimeUnit.MICROSECONDS,
                new LinkedBlockingDeque<Runnable>(),
                new ThreadPoolExecutor.DiscardPolicy());
    }

    void testDiscardOldestPolicy() {
        /*
        丢弃老的任务
         */
        new ThreadPoolExecutor(1, 2, 0,
                TimeUnit.MICROSECONDS,
                new LinkedBlockingDeque<Runnable>(),
                new ThreadPoolExecutor.DiscardOldestPolicy());
    }

    void testCustomPolicy() {
        /*
        自定义实现拒绝策略
         */
        new ThreadPoolExecutor(1, 2, 0,
                TimeUnit.MICROSECONDS,
                new LinkedBlockingDeque<Runnable>(),
                new ThreadPoolRejectedTests.CustomPolicy());
    }


    static class CustomPolicy implements RejectedExecutionHandler {

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {

        }
    }

}
