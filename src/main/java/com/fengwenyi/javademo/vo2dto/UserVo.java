package com.fengwenyi.javademo.vo2dto;

import lombok.Data;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2022-08-29
 */
@Data
public class UserVo {

    private String id;

    private String username;

    private String name;

    private Integer age;

    private Long createTimestamp;

}
