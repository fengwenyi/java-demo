package com.fengwenyi.demo.http;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2022-11-24
 */
public class HttpClientFactory {

    public static HttpClient get(Request.Util httpUtil) {
        if (Request.Util.JDK == httpUtil) {
            return new JdkHttpClient();
        } else {
            throw new RuntimeException("not find http util: " + httpUtil.name());
        }
    }

}
