package com.fengwenyi.demo.http;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2022-11-24
 */
@Data
@Accessors(chain = true)
public class Response {

    private int code;

    private String msg;

    private Body body;

    @Data
    @Accessors(chain = true)
    public static class Body implements Closeable {

        private InputStream body;

        @Override
        public void close() throws IOException {
            body.close();
        }
    }
}
