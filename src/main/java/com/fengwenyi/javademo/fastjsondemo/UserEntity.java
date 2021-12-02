package com.fengwenyi.javademo.fastjsondemo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @author <a href="https://www.fengwenyi.com">Erwin Feng</a>
 * @since 2021-12-02
 */
@Data
@Accessors(chain = true)
public class UserEntity {

    private String id;

    private String name;

    private LocalDateTime registerDateTime;

    private LocalDate birthday;

    private LocalTime time;

}
