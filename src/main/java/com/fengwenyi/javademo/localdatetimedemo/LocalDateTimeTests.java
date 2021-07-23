package com.fengwenyi.javademo.localdatetimedemo;

import org.junit.Test;

import java.time.LocalDateTime;

/**
 * @author <a href="https://www.fengwenyi.com">Erwin Feng</a>
 * @since 2021-07-23
 */
public class LocalDateTimeTests {

    @Test
    public void testMinus() {
        LocalDateTime localDateTime = LocalDateTime.now().minusMonths(3).minusDays(1);
        System.out.println(localDateTime);
    }

}
