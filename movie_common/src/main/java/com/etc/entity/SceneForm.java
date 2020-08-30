package com.etc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SceneForm implements Serializable {
    private Integer id;
    private Integer movieId;
    private String movieName;
    private Integer price;
    private Integer seatNum;
    private String showtime;
    private String[] bookedSeat;
}
