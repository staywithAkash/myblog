package com.blog9.payload;

import lombok.Data;

import javax.persistence.Column;
@Data
public class UserDto {
    private Long id;
    private String email;
    private String name;
    private String password;
    private String username;
}
