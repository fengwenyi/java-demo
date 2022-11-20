package com.fengwenyi.javademo.http;

import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.net.SocketFactory;
import javax.net.ssl.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
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
            request.setMethod(Request.Method.GET.name());
            Request.Option option = buildOption(null, null, headers);
            return request(request, option);
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
            request.setMethod(Request.Method.POST.name());
            Request.Option option = buildOption(null, null, headers);
            return request(request, option);
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

    public static Request.Option buildOption(Integer connectTimeout, Integer readTimeout, Map<String, String> headers) {
        return Request.Option.create(connectTimeout, readTimeout, headers);
    }

    public static String request(Request request, Request.Option option) throws IOException {
        check(request, option);
        URL url = new URL(request.getUrl());
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        if (connection instanceof HttpsURLConnection) {
            HttpsURLConnection sslCon = (HttpsURLConnection) connection;
            SSLSocketFactory sslContextFactory = option.getSslContextFactory();
            HostnameVerifier hostnameVerifier = option.getHostnameVerifier();
            if (Objects.isNull(sslContextFactory)) {
                sslContextFactory = defaultSSLSocketFactory();
            }
            if (Objects.isNull(hostnameVerifier)) {
                hostnameVerifier = (s, sslSession) -> true;
            }
            sslCon.setSSLSocketFactory(sslContextFactory);
            sslCon.setHostnameVerifier(hostnameVerifier);
        }
        connection.setRequestMethod(request.getMethod());
        if (!CollectionUtils.isEmpty(option.getHeaders())) {
            for (Map.Entry<String, String> entry : option.getHeaders().entrySet()) {
                if (Objects.nonNull(entry) && StringUtils.hasText(entry.getKey())) {
                    connection.setRequestProperty(entry.getKey(), entry.getValue());
                }
            }
        }
        connection.setConnectTimeout(option.getConnectTimeout());
        connection.setReadTimeout(option.getReadTimeout());
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.connect();
        if (Request.Method.POST.name().equals(request.getMethod().toUpperCase())
                && StringUtils.hasText(request.getParam())) {
            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(request.getParam().getBytes(StandardCharsets.UTF_8));
            outputStream.close();
        }
        InputStream inputStream = connection.getInputStream();
        int length = connection.getContentLength();
        String result = "";
        if (length > 0) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[length];
            for (int len; (len = inputStream.read(buffer)) > 0; ) {
                byteArrayOutputStream.write(buffer, 0, len);
            }
            result = new String(byteArrayOutputStream.toByteArray(), StandardCharsets.UTF_8);
            byteArrayOutputStream.flush();
            byteArrayOutputStream.close();
        }
        inputStream.close();
        connection.disconnect();
        return result;
    }

    private static SSLSocketFactory defaultSSLSocketFactory() {
        SSLContext ctx;
        try {
            ctx = SSLContext.getInstance("SSL");
            TrustManager[] trustManagers = new TrustManager[1];
            trustManagers[0] = new DefaultTrustMannager();
            ctx.init(null, trustManagers, null);
            HttpsURLConnection.setDefaultSSLSocketFactory(ctx.getSocketFactory());
        } catch (KeyManagementException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        return ctx.getSocketFactory();
    }

    private static final class DefaultTrustMannager implements X509TrustManager {

        @Override
        public void checkClientTrusted(X509Certificate[] x509Certificates, String s) {

        }

        @Override
        public void checkServerTrusted(X509Certificate[] x509Certificates, String s) {

        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    private static void check(Request request, Request.Option option) {
        Assert.notNull(request, "request must be not null");
        Assert.notNull(option, "option must be not null");
        Assert.notNull(request.getUrl(), "url must be not null");
        Assert.notNull(request.getMethod(), "method must be not null");
    }

}
