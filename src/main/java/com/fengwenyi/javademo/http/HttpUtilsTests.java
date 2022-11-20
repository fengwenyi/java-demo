package com.fengwenyi.javademo.http;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2022-11-18
 */
public class HttpUtilsTests {

    @Test
    public void testGet() {
//        System.out.println(HttpUtils.get("https://www.baidu.com"));
        Map<String, String> headers = new HashMap<>();
        headers.put("Accept", "application/json");
        System.out.println(HttpUtils.get("https://erwin-api.fengwenyi.com/erwin/bookmark/page?currentPage=1&pageSize=10", null, headers));
    }

}
