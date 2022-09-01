package com.fengwenyi.javademo.concurrent;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fengwenyi.api.result.ResultTemplate;
import com.fengwenyi.javalib.convert.JsonUtils;
import com.fengwenyi.javalib.generate.IdUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.junit.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2022-09-01
 */
@Slf4j
public class ConcurrentTests {

    @Test
    public void test() {
        int requestNum = 10;
        ///设置线程池最大执行20个线程并发执行任务
        int threadSize = 20;
        //AtomicInteger通过CAS操作能保证统计数量的原子性
        AtomicInteger successCount = new AtomicInteger(0);
        AtomicInteger failedCount = new AtomicInteger(0);
        CountDownLatch downLatch = new CountDownLatch(requestNum);
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(threadSize);
        long startTime = System.nanoTime();
        for (int i = 0; i < requestNum; i++) {
            fixedThreadPool.submit(() -> {
                String url = "http://localhost:8080/api/ticket/sell";
                Map<String, String> paramMap = new HashMap<>();
                paramMap.put("userId", System.currentTimeMillis() + "");
                String result = postJson(url, paramMap);
                ResultTemplate<Ticket> resultTemplate = JsonUtils.convertObject(result, new TypeReference<ResultTemplate<Ticket>>() {
                });
                if (Objects.nonNull(resultTemplate) && resultTemplate.getSuccess()) {
                    successCount.incrementAndGet();
                } else {
                    log.error(resultTemplate.getMsg());
                    failedCount.incrementAndGet();
                }
                downLatch.countDown();
            });
        }
        //等待所有线程都执行完任务
        try {
            downLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Duration duration = Duration.ofNanos(System.nanoTime() - startTime);
        fixedThreadPool.shutdown();
        log.info("request count: [{}], spent time: [{}] ms, successCount: [{}], failedCount: [{}]",
                requestNum, duration.toMillis(), successCount.get(), failedCount.get());
    }

    private String postJson(String url, Object param) {
        OkHttpClient client = new OkHttpClient();

        String json = JsonUtils.convertString(param);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);

        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        Call call = client.newCall(request);
        Response response;
        try {
            response = call.execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ResponseBody responseBody = response.body();
        try {
            assert responseBody != null;
            return responseBody.string();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Data
    static class Ticket {

        private String ticketNo;

    }

    private void request(int requestNum, int theadNum, Task task) {
        AtomicInteger successCount = new AtomicInteger(0);
        AtomicInteger failedCount = new AtomicInteger(0);
        CountDownLatch downLatch = new CountDownLatch(requestNum);

        int threadPoolNum = theadNum;
        if (theadNum > requestNum) {
            threadPoolNum = requestNum;
        }

        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(threadPoolNum);
        long startTime = System.nanoTime();
        for (int i = 0; i < requestNum; i++) {
            fixedThreadPool.submit(() -> {
                boolean success = task.exec();
                if (success) {
                    successCount.incrementAndGet();
                } else {
                    failedCount.incrementAndGet();
                }
                downLatch.countDown();
            });
        }
        try {
            downLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Duration duration = Duration.ofNanos(System.nanoTime() - startTime);
        fixedThreadPool.shutdown();
        log.info("request count: [{}], spent time: [{}] ms, successCount: [{}], failedCount: [{}]",
                requestNum, duration.toMillis(), successCount.get(), failedCount.get());
    }

    @Test
    public void testApiTicketSell() {

        Task task = () -> {
            String url = "http://localhost:8080/api/ticket/sell";
            Map<String, String> paramMap = new HashMap<>();
            paramMap.put("userId", System.currentTimeMillis() + "");
            String result = postJson(url, paramMap);
            ResultTemplate<Ticket> resultTemplate = JsonUtils.convertObject(result, new TypeReference<ResultTemplate<Ticket>>() {
            });
            if (Objects.nonNull(resultTemplate) && resultTemplate.getSuccess()) {
                return true;
            } else {
                log.error(resultTemplate.getMsg());
                return false;
            }
        };

        request(5, 5, task);

    }

    @Test
    public void testApiTicketList() {

        Task task = () -> {
            String url = "http://localhost:8080/api/ticket/list";
            Map<String, String> paramMap = new HashMap<>();
            String result = postJson(url, paramMap);
            ResultTemplate<List<String>> resultTemplate = JsonUtils.convertObject(result, new TypeReference<ResultTemplate<List<String>>>() {
            });
            if (Objects.nonNull(resultTemplate) && resultTemplate.getSuccess()) {
                return true;
            } else {
                log.error(resultTemplate.getMsg());
                return false;
            }
        };

        request(10, 3, task);

    }
}
