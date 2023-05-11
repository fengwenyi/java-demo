package com.fengwenyi.demo.spi.dubbo.impl;

import com.fengwenyi.demo.spi.dubbo.DatabaseSPI;
import lombok.extern.slf4j.Slf4j;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2022-06-27
 */
@Slf4j
public class MySQLDatabaseSPIImpl implements DatabaseSPI {
    @Override
    public void dataBaseOperation() {
        log.info("Operate MySQL Database");
    }
}
