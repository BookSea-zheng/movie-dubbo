package com.etc.amovie.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Watch {

    private Integer id;
    private Integer userId;
    private Integer movieId;
    private Date createTime;

}
