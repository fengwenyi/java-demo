package com.fengwenyi.javademo.spi.dubbo;

import org.apache.dubbo.common.extension.SPI;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2022-06-27
 */
@SPI
public interface DatabaseSPI {

    void dataBaseOperation();

}
