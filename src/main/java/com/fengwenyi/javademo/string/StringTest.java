package com.fengwenyi.javademo.string;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author <a href="https://www.fengwenyi.com">Erwin Feng</a>
 * @since 2022-04-25
 */
@Slf4j
public class StringTest {

    @Test
    public void substring() {
        String str = "test-" + System.currentTimeMillis();
        int i = str.lastIndexOf("-");
        if (i > -1) {
            str = str.substring(0, i);
            log.info("str: [{}]", str);
            Assert.assertEquals("test", str);
        }
    }

}
