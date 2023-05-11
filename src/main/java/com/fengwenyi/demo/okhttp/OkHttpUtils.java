package com.fengwenyi.demo.okhttp;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

import javax.net.ssl.SSLSession;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2022-07-29
 */
public class OkHttpUtils {

    private static final int CONN_TIMEOUT = 10;
    private static final int READ_TIMEOUT = 30;
    private static final int WRITE_TIMEOUT = 30;

    private static OkHttpClient initOkHttpClient() {
        return new OkHttpClient().newBuilder()
                .connectTimeout(CONN_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                .build();
    }

    public static String post() {
        OkHttpClient client = initOkHttpClient();
        //RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8");
        return "";
    }

}
