package com.fengwenyi.javademo.fastjsondemo;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.fengwenyi.javademo.localdatetimedemo.LocalDateTimeTests;
import com.fengwenyi.javalib.convert.JsonUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * @author <a href="https://www.fengwenyi.com">Erwin Feng</a>
 * @since 2021-12-02
 */
public class FastJsonDemo {

    public static void main(String[] args) {
        UserEntity user = new UserEntity()
                .setId("1001")
                .setName("Zhangsan")
                .setRegisterDateTime(LocalDateTime.now())
                .setBirthday(LocalDate.now())
                .setTime(LocalTime.now())
                ;
//        String jsonString = JSON.toJSONString(user);
//        String jsonString = JsonUtils.convertString(user);
        String jsonString = jacksonToString(user);
        System.out.println(jsonString);
        UserEntity newUser = jacksonToObject(jsonString, UserEntity.class);
//        UserEntity newUser = JSON.parseObject(jsonString, UserEntity.class);
        System.out.println(newUser);
    }

    /*
    jackson
    {"id":"1001","name":"Zhangsan","registerDateTime":{"year":2021,"monthValue":12,"dayOfMonth":2,"hour":9,"minute":50,"second":44,"nano":861366000,"dayOfWeek":"THURSDAY","dayOfYear":336,"month":"DECEMBER","chronology":{"id":"ISO","calendarType":"iso8601"}}}
     */

    /*
    fastjson
    {"id":"1001","name":"Zhangsan","registerDateTime":"2021-12-02T09:51:51.035163000"}
     */

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    private static void javaTimeModel() {
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(dateTimeFormatter));
        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(dateFormatter));
        javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(timeFormatter));
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(dateTimeFormatter));
        javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(dateFormatter));
        javaTimeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(timeFormatter));
        objectMapper.registerModule(javaTimeModule);
    }

    private static String jacksonToString(Object value) {
        try {
            javaTimeModel();
            return objectMapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static <T> T jacksonToObject(String value, Class<T> clazz) {
        try {
            javaTimeModel();
            return objectMapper.readValue(value, clazz);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }


}
