package com.fengwenyi.javademo.demo;

import lombok.Getter;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * @author <a href="https://www.fengwenyi.com">Erwin Feng</a>
 * @since 2022-05-16
 */
public class LockTest {

    @Test
    public void test() {
        IntStream.rangeClosed(1, 10000)
                .parallel() // 采用多线程执行
                .forEach(i -> new Data().wrong());
        System.out.println(Data.counter);
    }

    static class Data {
        @Getter
        private static final AtomicInteger counter = new AtomicInteger(0);

        public void wrong() {
            counter.getAndIncrement();
        }
    }

}
