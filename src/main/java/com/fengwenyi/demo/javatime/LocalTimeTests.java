package com.fengwenyi.demo.javatime;

import com.fengwenyi.demo.util.AssetUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.time.LocalTime;

/**
 * @author <a href="https://www.fengwenyi.com">Erwin Feng</a>
 * @since 2022-05-07
 */
@Slf4j
public class LocalTimeTests {

    @Test
    public void compareTo() {
        LocalTime startTime = LocalTime.of(9, 0);
        LocalTime endTime = LocalTime.of(11, 30);
        LocalTime nowTime = LocalTime.of(9, 8);
        LocalTime now = LocalTime.of(9, 8);
        AssetUtils.isTrue(now.compareTo(startTime) > 0, "> test failure");
        AssetUtils.isTrue(now.compareTo(endTime) < 0, "< test failure");
        AssetUtils.isTrue(now.compareTo(nowTime) == 0, "= test failure");
    }

    @Test
    public void afterAndBefore() {
        LocalTime startTime = LocalTime.of(9, 0);
        LocalTime endTime = LocalTime.of(11, 30);
        LocalTime nowTime = LocalTime.of(9, 8);
        LocalTime now = LocalTime.of(9, 8);
        AssetUtils.isTrue(now.isAfter(startTime), "after test failure");
        AssetUtils.isTrue(now.isBefore(endTime), "before test failure");
        AssetUtils.isTrue(now.equals(nowTime), "equals test failure");
        AssetUtils.isTrue(!now.isBefore(nowTime), "before has equals");
    }
}
