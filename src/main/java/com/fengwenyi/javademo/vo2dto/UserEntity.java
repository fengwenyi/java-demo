package com.fengwenyi.javademo.vo2dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2022-08-29
 */
@Data
public class UserEntity {

    private Long id;

    private String username;

    private String password;

    private String name;

    private Integer age;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

}
