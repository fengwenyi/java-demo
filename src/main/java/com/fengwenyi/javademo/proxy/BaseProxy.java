package com.fengwenyi.javademo.proxy;

import java.lang.reflect.Proxy;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2022-06-08
 */
public class BaseProxy {

    public static Object getInstance(Class<?> clzz) {
        ClassLoader classLoader = BaseProxy.class.getClassLoader();
        Class<?>[] clzzes = new Class<?>[]{clzz};
        BaseDaoHandler baseDaoHandler = new BaseDaoHandler();
        return Proxy.newProxyInstance(classLoader, clzzes, baseDaoHandler);
    }

}
