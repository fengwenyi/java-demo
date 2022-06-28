package com.fengwenyi.javademo.jackson;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2022-06-28
 */
@Data
public class User {

    @JsonProperty("USERNAME")
    private String username;

    @JsonProperty("PASSWORD")
    private String password;

    @JsonProperty("NICKNAME")
    private String nickname;

    @JsonProperty("AGE")
    private Integer age;

}
