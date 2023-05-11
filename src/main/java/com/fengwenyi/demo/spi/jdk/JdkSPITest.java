package com.fengwenyi.demo.spi.jdk;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ServiceLoader;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2022-06-27
 */
@Slf4j
public class JdkSPITest {

    @Test
    public void test() {
        // 加载jdk.spi.DataBaseSPI文件中DataBaseSPI的实现类(懒加载)
        ServiceLoader<DatabaseSPI> databaseSPIS = ServiceLoader.load(DatabaseSPI.class);
        // ServiceLoader实现了Iterable，故此处可以使用for循环遍历加载到的实现类
        for (DatabaseSPI databaseSPI : databaseSPIS) {
            databaseSPI.dataBaseOperation();
        }
    }

}
