package com.fengwenyi.javademo.lambda;

import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2022-08-21
 */
@Slf4j
public class ForTest {

    public List<UserEntity> init() {
        List<UserEntity> userEntityList = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            userEntityList.add(new UserEntity().setId((long) i).setUsername("u" + i).setAge(18));
        }
        return userEntityList;
    }

    public UserVo entityForVo(UserEntity entity) {
        return new UserVo().setId(entity.getId() + "").setUsername(entity.getUsername()).setAge(entity.getAge());
    }

    @Test
    public void testFor() {
        long startTime = System.currentTimeMillis();
        List<UserEntity> userEntityList = init();
        List<UserVo> voList = new ArrayList<>();
        for (int x = 0; x < userEntityList.size(); x++) {
            voList.add(entityForVo(userEntityList.get(x)));
        }
        log.info("for test finished, size: [{}], time: [{}]ms",
                voList.size(), System.currentTimeMillis() - startTime);
        // for test finished, size: [1000000], time: [222]ms
    }

    @Test
    public void testForLambda() {
        long startTime = System.currentTimeMillis();
        List<UserEntity> userEntityList = init();
        List<UserVo> voList = userEntityList.stream().map(this::entityForVo).collect(Collectors.toList());
        log.info("for lambda test finished, size: [{}], time: [{}]ms",
                voList.size(), System.currentTimeMillis() - startTime);
        // for lambda test finished, size: [1000000], time: [231]ms
    }

    @Test
    public void testForEach() {
        long startTime = System.currentTimeMillis();
        List<UserEntity> userEntityList = init();
        List<UserVo> voList = new ArrayList<>();
        for (UserEntity entity : userEntityList) {
            voList.add(entityForVo(entity));
        }
        log.info("for each test finished, size: [{}], time: [{}]ms",
                voList.size(), System.currentTimeMillis() - startTime);
        // for each test finished, size: [1000000], time: [224]ms
    }

    @Test
    public void testTen() {
        for (int i = 0; i < 10; i++) {
            // testFor();
            // testForEach();
            // testForLambda();
        }
    }

    @Data
    @Accessors(chain = true)
    static class UserEntity {
        private Long id;
        private String username;
        private Integer age;
    }

    @Data
    @Accessors(chain = true)
    static class UserVo {
        private String id;
        private String username;
        private Integer age;
    }

    /*
    test for
02:50:19.231 [main] INFO com.fengwenyi.javademo.lambda.ForTest - for test finished, size: [1000000], time: [227]ms
02:50:19.407 [main] INFO com.fengwenyi.javademo.lambda.ForTest - for test finished, size: [1000000], time: [174]ms
02:50:19.570 [main] INFO com.fengwenyi.javademo.lambda.ForTest - for test finished, size: [1000000], time: [163]ms
02:50:19.746 [main] INFO com.fengwenyi.javademo.lambda.ForTest - for test finished, size: [1000000], time: [176]ms
02:50:19.910 [main] INFO com.fengwenyi.javademo.lambda.ForTest - for test finished, size: [1000000], time: [164]ms
02:50:20.101 [main] INFO com.fengwenyi.javademo.lambda.ForTest - for test finished, size: [1000000], time: [191]ms
02:50:20.247 [main] INFO com.fengwenyi.javademo.lambda.ForTest - for test finished, size: [1000000], time: [146]ms
02:50:20.391 [main] INFO com.fengwenyi.javademo.lambda.ForTest - for test finished, size: [1000000], time: [144]ms
02:50:20.469 [main] INFO com.fengwenyi.javademo.lambda.ForTest - for test finished, size: [1000000], time: [78]ms
02:50:20.707 [main] INFO com.fengwenyi.javademo.lambda.ForTest - for test finished, size: [1000000], time: [238]ms
     */

    /*
    test foreach
02:51:01.498 [main] INFO com.fengwenyi.javademo.lambda.ForTest - for each test finished, size: [1000000], time: [222]ms
02:51:01.676 [main] INFO com.fengwenyi.javademo.lambda.ForTest - for each test finished, size: [1000000], time: [176]ms
02:51:01.835 [main] INFO com.fengwenyi.javademo.lambda.ForTest - for each test finished, size: [1000000], time: [158]ms
02:51:02.022 [main] INFO com.fengwenyi.javademo.lambda.ForTest - for each test finished, size: [1000000], time: [187]ms
02:51:02.195 [main] INFO com.fengwenyi.javademo.lambda.ForTest - for each test finished, size: [1000000], time: [173]ms
02:51:02.383 [main] INFO com.fengwenyi.javademo.lambda.ForTest - for each test finished, size: [1000000], time: [188]ms
02:51:02.528 [main] INFO com.fengwenyi.javademo.lambda.ForTest - for each test finished, size: [1000000], time: [145]ms
02:51:02.673 [main] INFO com.fengwenyi.javademo.lambda.ForTest - for each test finished, size: [1000000], time: [144]ms
02:51:02.750 [main] INFO com.fengwenyi.javademo.lambda.ForTest - for each test finished, size: [1000000], time: [77]ms
02:51:02.984 [main] INFO com.fengwenyi.javademo.lambda.ForTest - for each test finished, size: [1000000], time: [234]ms
     */

    /*
    test for lambda
02:51:51.361 [main] INFO com.fengwenyi.javademo.lambda.ForTest - for lambda test finished, size: [1000000], time: [229]ms
02:51:51.538 [main] INFO com.fengwenyi.javademo.lambda.ForTest - for lambda test finished, size: [1000000], time: [175]ms
02:51:51.701 [main] INFO com.fengwenyi.javademo.lambda.ForTest - for lambda test finished, size: [1000000], time: [163]ms
02:51:51.883 [main] INFO com.fengwenyi.javademo.lambda.ForTest - for lambda test finished, size: [1000000], time: [182]ms
02:51:52.053 [main] INFO com.fengwenyi.javademo.lambda.ForTest - for lambda test finished, size: [1000000], time: [169]ms
02:51:52.242 [main] INFO com.fengwenyi.javademo.lambda.ForTest - for lambda test finished, size: [1000000], time: [189]ms
02:51:52.394 [main] INFO com.fengwenyi.javademo.lambda.ForTest - for lambda test finished, size: [1000000], time: [152]ms
02:51:52.541 [main] INFO com.fengwenyi.javademo.lambda.ForTest - for lambda test finished, size: [1000000], time: [147]ms
02:51:52.620 [main] INFO com.fengwenyi.javademo.lambda.ForTest - for lambda test finished, size: [1000000], time: [78]ms
02:51:52.857 [main] INFO com.fengwenyi.javademo.lambda.ForTest - for lambda test finished, size: [1000000], time: [237]ms
     */

}
