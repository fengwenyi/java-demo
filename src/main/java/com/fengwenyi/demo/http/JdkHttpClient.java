package com.fengwenyi.demo.http;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.net.ssl.*;
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
 * @since 2022-11-24
 */
public class JdkHttpClient implements HttpClient {
    @Override
    public Response execute(Request request, Request.Option option) throws IOException {
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
        connection.setRequestMethod(request.getMethod().name());
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
        if (Request.Method.POST == request.getMethod()
                && StringUtils.hasText(request.getParam())) {
            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(request.getParam().getBytes(StandardCharsets.UTF_8));
            outputStream.close();
        }
        Response response = new Response();
        response.setCode(connection.getResponseCode());
        response.setMsg(connection.getResponseMessage());
        InputStream inputStream;
        Response.Body body = new Response.Body();
        if (connection.getResponseCode() == 200) {
            inputStream = connection.getInputStream();
        } else {
            inputStream = connection.getErrorStream();
        }
        body.setBody(inputStream);
        response.setBody(body);
        // connection.disconnect();
        return response;
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
}
