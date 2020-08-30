package com.etc.amovie.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SceneForm {
    private Integer id;
    private Integer movieId;
    private String movieName;
    private Integer price;
    private Integer seatNum;
    private String showtime;
    private String[] bookedSeat;
}
