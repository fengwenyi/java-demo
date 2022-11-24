package com.fengwenyi.javademo.http;

import java.io.IOException;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2022-11-24
 */
public interface HttpClient {

    Response execute(Request request, Request.Option option) throws IOException;

}
