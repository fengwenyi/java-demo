package com.fengwenyi.javademo.functiondemo;

import com.fengwenyi.javalib.convert.JsonUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

/**
 * @author Erwin Feng
 * @since 2021-01-04
 */
public class FunctionDemo {

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("user", "张三");
        map.put("age", "18");
        map.put("gender", "男");
        Function<Map<String, String>, User> function = mapToUser();
        User user = function.apply(map);
        System.out.println(JsonUtils.prettyPrint(user));
    }


    private static Function<Map<String, String>, User> mapToUser() {
        return map -> {
            User user = new User();
            user.name = map.get("user");
            user.age = Integer.valueOf(map.get("age"));
            user.gender = map.get("gender");
            return user;
        };
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

    private static void test1() {

        Map<String, String> map = new HashMap<>();
        map.put("user", "张三");
        map.put("age", "18");
        map.put("gender", "男");

        Function<Map<String, String>, User> func = getMap2();
        User user = func.apply(map);
        System.out.println(user);
    }

    private static Function<Map<String, String>, User> getMap2() {
        return map -> {
            User user = new User();
            user.name = map.get("user");
            user.age = Integer.valueOf(map.get("age"));
            user.gender = map.get("gender");
            return user;
        };
    }

    static class User {
        String name;
        Integer age;
        String gender;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }
    }

    public static Function<Integer, Integer> square() {
        return num -> num * num;
    }

    public static class LambdaQueryWrapper<T> {

        public LambdaQueryWrapper eq(Function<T, Object> func, Object value) {
            return this;
        }

    }

    public static void testBuildLambdaQueryWrapper() {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getName, "张三");
    }


}
