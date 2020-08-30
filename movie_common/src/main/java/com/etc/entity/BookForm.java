package com.etc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookForm implements Serializable {
    //第一次提交

    private String movieName;
    private String showtime;
    private String showDate;
    private Integer sceneId;
    private Integer price;

    //第二次提交

    private Integer siteNum;
    private Integer totalPrice;
    private String bookedSits;
}
