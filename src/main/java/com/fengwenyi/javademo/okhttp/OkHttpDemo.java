package com.fengwenyi.javademo.okhttp;

import com.fengwenyi.javalib.convert.JsonUtils;
import com.fengwenyi.javalib.util.PrintUtils;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author Erwin Feng
 * @since 2021-01-22
 */
public class OkHttpDemo {

    public static void main(String[] args) {
        syncRequest();
        PrintUtils.info("---dsnlkdsn");
    }

    private static void syncRequest() {
        String url = "http://localhost:8080/api/user";
        Map<String, Object> map = new HashMap<>();
        map.put("uid",1);
        map.put("name","Zhangsan");
        map.put("gender","男");
        map.put("age", 20);
        String param = JsonUtils.convertString(map);
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), param);
        Request request = new Request.Builder().url(url).post(body).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                System.out.println(e.getMessage());
            }
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    String string = Objects.requireNonNull(response.body()).string();
                    PrintUtils.info(string);
                }
            }
        });
    }

    /*private static OkHttpClient init() {
        return new OkHttpClient().newBuilder()
                .connectTimeout(ConnTimeOut, TimeUnit.SECONDS)
                .readTimeout(CommTimeOut, TimeUnit.SECONDS)
                .writeTimeout(CommTimeOut, TimeUnit.SECONDS)
                //.addNetworkInterceptor(sLogInterceptor)//网络日志
                //.sslSocketFactory(createSSLSocketFactory())//不验证ssl
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                })
                //其他配置
                .build();
    }*/

}
