package com.fengwenyi.javademo.vo2dto;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2022-08-29
 */
public class Vo2DtoTest {


    /**
     * 测试转换
     *
     * @param user 用户
     */
    public void testConvert(UserEntity user) {
        UserVo userVo = new UserVo();
        userVo.setId(user.getId() + "");
        userVo.setUsername(user.getUsername());
        userVo.setName(user.getName());
        userVo.setAge(user.getAge());

    }

}
