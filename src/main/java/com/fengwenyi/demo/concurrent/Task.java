package com.fengwenyi.demo.concurrent;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2022-09-01
 */
@FunctionalInterface
public interface Task {

    boolean exec();

}
