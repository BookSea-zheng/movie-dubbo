package com.etc.amovie.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewVo {

    private Integer id;
    private String content;
    private Integer movieId;
    private Integer userId;
    private Date createTime;
    private String nickname;
}
