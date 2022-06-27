package com.fengwenyi.javademo.spi.sping;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.xml.DefaultNamespaceHandlerResolver;
import org.springframework.beans.factory.xml.NamespaceHandler;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2022-06-27
 */
@Slf4j
public class SpringSPITest {

    @Test
    public void testSpringHandler() {
        // spring中提供的默认namespace URI解析器
        DefaultNamespaceHandlerResolver handlerResolver = new DefaultNamespaceHandlerResolver();
        // 此处假设nameSpaceUri已从xml文件中解析出来，正常流程是在项目启动的时候会解析xml文件，获取到对应的自定义标签
        // 然后根据自定义标签取得对应的nameSpaceUri
        String mysqlNamespaceUri = "http://www.mysql.org/schema/mysql";
        NamespaceHandler handler = handlerResolver.resolve(mysqlNamespaceUri);
        // 验证自定义NamespaceHandler，这里参数传null，实际使用中传具体的Element
        handler.parse(null, null);

        String oracleNamespaceUri = "http://www.oracle.org/schema/oracle";
        handler = handlerResolver.resolve(oracleNamespaceUri);
        handler.parse(null, null);
    }

}
