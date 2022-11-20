package com.fengwenyi.javademo.http;

import lombok.Data;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2022-11-18
 */
@Data
public class Request {

    private String url;

    private String method;

    private String param;

    public enum Method {
        GET, POST, PUT, PATCH, DELETE
    }

    public static Request create(String url, String method, String param) {
        Request request = new Request();
        request.setUrl(url);
        request.setMethod(method);
        request.setParam(param);
        return request;
    }

    @Data
    public static class Option {

        private Integer connectTimeout = 5000;
        private Integer readTimeout = 45000;
        private Map<String, String> headers = new HashMap<>();
        private SSLSocketFactory sslContextFactory;
        private HostnameVerifier hostnameVerifier;

        public static Option create(Integer connectTimeout, Integer readTimeout) {
            Option option = new Option();
            Optional.ofNullable(connectTimeout).ifPresent(option::setConnectTimeout);
            Optional.ofNullable(readTimeout).ifPresent(option::setReadTimeout);
            return option;
        }

        public static Option create(Integer connectTimeout, Integer readTimeout, Map<String, String> headers) {
            Option option = new Option();
            Optional.ofNullable(connectTimeout).ifPresent(option::setConnectTimeout);
            Optional.ofNullable(readTimeout).ifPresent(option::setReadTimeout);
            Optional.ofNullable(headers).ifPresent(option::setHeaders);
            return option;
        }

        public void setSslContextFactory(SSLSocketFactory sslContextFactory) {
            this.sslContextFactory = sslContextFactory;
        }

        public void setHostnameVerifier(HostnameVerifier hostnameVerifier) {
            this.hostnameVerifier = hostnameVerifier;
        }
    }

}
