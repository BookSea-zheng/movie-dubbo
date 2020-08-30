package com.etc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieForm implements Serializable {
    private Integer id;
    private String name;
    private String duration;
    private String directors;
    private String actors;
    private Date releaseDate;
    private Integer[] categoryIds;
    private Integer status;
    private String plot;
    private String poster;
    private String country;
}
