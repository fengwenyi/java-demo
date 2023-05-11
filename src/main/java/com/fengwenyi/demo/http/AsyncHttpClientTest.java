package com.fengwenyi.demo.http;

import com.fengwenyi.javalib.convert.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.Response;
import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static org.asynchttpclient.Dsl.asyncHttpClient;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2022-07-05
 */
@Slf4j
public class AsyncHttpClientTest {

    @Test
    public void testHttpBasicExample() throws ExecutionException, InterruptedException {
        AsyncHttpClient asyncHttpClient = asyncHttpClient();
        Future<Response> whenResponse = asyncHttpClient.prepareGet("https://baidu.com").execute();
        Response response = whenResponse.get();
        log.info(JsonUtils.convertString(response.getResponseBody()));
    }

}
