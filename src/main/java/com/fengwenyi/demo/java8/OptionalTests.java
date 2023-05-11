package com.fengwenyi.demo.java8;

import lombok.Data;
import org.junit.Test;

import java.util.Optional;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2022-11-10
 */
public class OptionalTests {

    @Test
    public void testIfPresent() {
        User user = new User();

        String name = "张三";
        Optional.ofNullable(name).ifPresent(user::setName);

        System.out.println(user);
    }

    @Data
    static class User {

        private int id;

        private String name;

    }

}
