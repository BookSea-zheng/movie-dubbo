package com.etc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Watch implements Serializable {

    private Integer id;
    private Integer userId;
    private Integer movieId;
    private Date createTime;

}
