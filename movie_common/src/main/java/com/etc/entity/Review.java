package com.etc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review implements Serializable {
    private Integer id;
    private String content;
    private Integer movieId;
    private Integer userId;
    private Date createTime;
}
