package com.etc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    private Integer id;
    private String nickname;
    private String email;
    private String password;
    private String salt;
    private String phone;
    private Integer gender;
    private Integer role;
}
