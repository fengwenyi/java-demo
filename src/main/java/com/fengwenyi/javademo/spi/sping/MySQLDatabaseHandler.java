package com.fengwenyi.javademo.spi.sping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2022-06-27
 */
@Slf4j
public class MySQLDatabaseHandler extends NamespaceHandlerSupport {
    @Override
    public void init() {

    }

    @Override
    public BeanDefinition parse(Element element, ParserContext parserContext) {
        log.info("MySQLDatabaseHandler");
        return null;
    }
}
