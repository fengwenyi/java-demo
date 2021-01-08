package com.fengwenyi.javademo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fengwenyi.javalib.convert.JsonUtils;
import com.fengwenyi.javalib.util.PrintUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Erwin Feng
 * @since 2021-01-08
 */
public class JsonDemo {

    public static void main(String[] args) {
        String jsonString = genJsonString();
        PrintUtils.info(jsonString);

        List<User> userList = JsonUtils.convertCollection(jsonString, List.class, User.class);

//        userList = userList.stream().peek(user -> user.age = 18).collect(Collectors.toList());

        userList.forEach(user -> user.age = 18);

        userList.forEach(PrintUtils::info);
        userList.forEach(user -> PrintUtils.info(JsonUtils.convertString(user)));
    }

    private static String genJsonString() {
        List<User> userList = new ArrayList<>();
        User user1 = new User();
        user1.name = "Zhangsan";
        userList.add(user1);
        User user2 = new User();
        user2.name = "Lisi";
        userList.add(user2);
        return JsonUtils.convertString(userList);
    }

    static class User {
        @JsonProperty("Name")
        String name;
        Integer age;
        String gender;

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", gender='" + gender + '\'' +
                    '}';
        }
    }

}
