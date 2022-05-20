package com.fengwenyi.javademo.util;

import org.junit.Assert;

/**
 * @author <a href="https://www.fengwenyi.com">Erwin Feng</a>
 * @since 2022-05-20
 */
public class AssetUtils {

    public static void isTrue(boolean condition, String message) {
        Assert.assertTrue(message, condition);
    }

}
