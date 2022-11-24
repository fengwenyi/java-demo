package com.fengwenyi.javademo.http;

import org.springframework.util.Assert;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Objects;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2022-11-18
 */
public class HttpUtils {

    public  static String get(String url, String param, Map<String, String> headers) {
        try {
            Request request = buildRequest(url, param);
            request.setMethod(Request.Method.GET);
            Request.Option option = buildOption(null, null, headers);
            return execute(request, option);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String get(String url, String param) {
        return get(url, "", null);
    }

    public static String get(String url) {
        return get(url, "");
    }

    public  static String post(String url, String param, Map<String, String> headers) {
        try {
            Request request = buildRequest(url, param);
            request.setMethod(Request.Method.POST);
            Request.Option option = buildOption(null, null, headers);
            return execute(request, option);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public  static String post(String url, String param) {
        return post(url, param, null);
    }

    public static Request buildRequest(String url, String param) {
        return Request.create(url, null, param);
    }

    public static Request buildGetRequest(String url, String param) {
        return Request.create(url, Request.Method.GET, param);
    }

    public static Request buildPostRequest(String url, String param) {
        return Request.create(url, Request.Method.POST, param);
    }

    public static Request.Option buildOption(Integer connectTimeout, Integer readTimeout, Map<String, String> headers) {
        return Request.Option.create(connectTimeout, readTimeout, headers);
    }

    public static String execute(Request request, Request.Option option) throws IOException {
        check(request, option);
        HttpClient httpClient = HttpClientFactory.get(request.getUtil());
        Response response = httpClient.execute(request, option);
        return handleResponse(response);
    }

    private static String handleResponse(Response response) throws IOException {
        if (Objects.isNull(response)) {
            return "";
        }
        InputStream body = response.getBody().getBody();
        String data = readAndClose(body);
        if (response.getCode() == 200) {
            return data;
        }
        throw new RuntimeException(data);
    }

    private static String readAndClose(InputStream inputStream) throws IOException {
        BufferedReader reader;
        StringBuilder resultBuffer = new StringBuilder();
        String tempLine;

        reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        while ((tempLine = reader.readLine()) != null) {
            resultBuffer.append(tempLine);
        }
        inputStream.close();
        return resultBuffer.toString();
    }

    private static void check(Request request, Request.Option option) {
        Assert.notNull(request, "request must be not null");
        Assert.notNull(option, "option must be not null");
        Assert.notNull(request.getUrl(), "url must be not null");
        Assert.notNull(request.getMethod(), "method must be not null");
        Assert.notNull(request.getUtil(), "http util must be not null");
    }

}
