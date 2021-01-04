package com.fengwenyi.javademo;

import java.util.Map;
import java.util.function.Function;

/**
 * @author Erwin Feng
 * @since 2021-01-04
 */
public class FunctionDemo {

    public static void main(String[] args) {
        Function<Map<String, String>, User> mapUserFunction = getMap();
    }

    private static Function<Map<String, String>, User> getMap() {
        return map -> {
            User user = new User();
            user.name = "张三";
            user.age = 20;
            user.gender = "男";
            return user;
        };
    }

    static class User {
        String name;
        Integer age;
        String gender;
    }

}
