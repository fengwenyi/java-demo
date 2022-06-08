package com.fengwenyi.javademo.proxy;

import org.junit.Test;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2022-06-08
 */
public class JdkProxyTest {

    @Test
    public void testProxy() {
        BaseDao baseDao = (BaseDao) BaseProxy.getInstance(BaseDao.class);
        baseDao.queryById();
    }

}
