package com.fengwenyi.demo.future;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * @author <a href="https://www.fengwenyi.com">Erwin Feng</a>
 * @since 2022-05-13
 */
@Slf4j
public class FutureTest {

    @Test
    public void test() {
        long startTime = System.currentTimeMillis();
        List<Future<String>> taskList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Future<String> futureTask = task(i);
            taskList.add(futureTask);
        }
        taskList.forEach(stringFutureTask -> {
            try {
                log.info(stringFutureTask.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
        long endTime = System.currentTimeMillis();
        log.info("耗时：[{}]ms", endTime - startTime);
    }

    private Future<String> task(int i) {
        return new FutureTask<>(() -> {
            log.info("httpService执行：");
            Thread.sleep(3000);
            return "hi-" + i;
        });
    }
}
