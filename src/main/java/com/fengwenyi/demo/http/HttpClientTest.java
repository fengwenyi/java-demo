package com.fengwenyi.demo.http;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2022-11-17
 */
public class HttpClientTest {

    @Test
    public void test() throws IOException {
        URL url = new URL("https://www.baidu.com");
//        URL url = new URL("https://erwin-api.fengwenyi.com/erwin/bookmark/page?currentPage=1&pageSize=10");
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setRequestProperty("Content-Type", "application/json");
        httpURLConnection.setConnectTimeout(5000);
        httpURLConnection.setReadTimeout(5000);
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setDoInput(true);
        httpURLConnection.connect();
        OutputStream outputStream = httpURLConnection.getOutputStream();
        outputStream.write("".getBytes(StandardCharsets.UTF_8));
        outputStream.close();
        InputStream inputStream = httpURLConnection.getInputStream();
        int contentLength = httpURLConnection.getContentLength();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[contentLength];
        for (int len; (len = inputStream.read(buffer)) > 0;) {
            byteArrayOutputStream.write(buffer, 0, len);
        }
        String result = new String(byteArrayOutputStream.toByteArray(), StandardCharsets.UTF_8);
        byteArrayOutputStream.flush();
        byteArrayOutputStream.close();
        inputStream.close();
        httpURLConnection.disconnect();
        System.out.println(result);
    }

}
