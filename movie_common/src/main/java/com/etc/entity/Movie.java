package com.etc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movie implements Serializable {
    private int id;
    private String name;
    private String duration;
    private String directors;
    private String actors;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date releaseDate;
    private int status;
    private String plot;
    private String poster;
    private String country;

    private List<Category> categories;
}
