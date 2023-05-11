package com.fengwenyi.demo.spi.dubbo;

import org.apache.dubbo.common.extension.ExtensionLoader;
import org.junit.Test;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2022-06-27
 */
public class DubboSPITest {

    @Test
    public void test() {
        ExtensionLoader<DatabaseSPI> extensionLoader = ExtensionLoader.getExtensionLoader(DatabaseSPI.class);
        DatabaseSPI extension = extensionLoader.getExtension("mysql");
        extension.dataBaseOperation();
        extension = extensionLoader.getExtension("oracle");
        extension.dataBaseOperation();
    }

}
