package com.etc.amovie.entity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

    private int id;
    private String name;
    private String duration;
    private String directors;
    private String actors;

    private Date releaseDate;
    private int status;
    private String plot;
    private String poster;
    private String country;

    private List<Category> categories;

}
