package com.fengwenyi.javademo.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fengwenyi.api.result.ResultTemplate;
import com.fengwenyi.javademo.fastjsondemo.UserEntity;
import com.fengwenyi.javalib.convert.JsonUtils;
import org.junit.Test;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2022-06-27
 */
public class JacksonTest {

    @Test
    public void testParseResultTemplate() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            ResultTemplate<UserEntity> resultTemplate = objectMapper.readValue("", new TypeReference<ResultTemplate<UserEntity>>() {});
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
