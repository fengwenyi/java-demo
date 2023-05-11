package com.fengwenyi.demo.bean;

import lombok.Data;
import org.junit.Test;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-05-11
 */
public class BeanUtilsTests {

    @Test
    public void testGetFieldNameByGetMethod() {
        String fieldName = BeanUtils.getFieldNameByGet(User::getName);
        System.out.println(fieldName);
    }

    @Test
    public void testGetFieldNameBySetMethod() {
        String fieldName = BeanUtils.getFieldNameBySet(User::setName);
        System.out.println(fieldName);
    }

    @Data
    public static class User {
        private String id;
        private String name;
        private String gender;
        private Integer age;
    }

}
