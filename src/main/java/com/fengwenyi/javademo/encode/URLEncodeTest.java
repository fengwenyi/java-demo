package com.fengwenyi.javademo.encode;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * @author <a href="https://www.fengwenyi.com">Erwin Feng</a>
 * @since 2022-05-10
 */
@Slf4j
public class URLEncodeTest {

    @Test
    public void test() {
        String url = "";
        String urlEncode = URLEncoder.encode(url, StandardCharsets.UTF_8);
        log.info("url encode: [{}]", urlEncode);
    }

}
