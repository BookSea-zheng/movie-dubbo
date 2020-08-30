package com.etc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class News implements Serializable {
    private Integer id;
    private String title;
    private String content;
    private Date createTime;
}
