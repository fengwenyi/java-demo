package com.fengwenyi.javademo.jackson;

import com.fengwenyi.javalib.convert.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2022-06-28
 */
@Slf4j
public class JacksonTest {

    @Test
    public void testToJson() {
        User user = new User();
        user.setUsername("zs");
        user.setPassword("123456");
        user.setNickname("张三");
        user.setAge(18);
        String json = JsonUtils.convertString(user);
        log.info(json);
    }

}
