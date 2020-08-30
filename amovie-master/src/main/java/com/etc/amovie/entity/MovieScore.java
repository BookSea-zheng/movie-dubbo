package com.etc.amovie.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieScore {
    private Integer id;
    private String name;
    private String duration;
    private String directors;
    private String actors;
    private Date releaseDate;
    private Integer status;
    private String plot;
    private String poster;
    private String country;
    private List<Category> categories;

    private Double score = 0.0;
    private Integer count = 0;
}
