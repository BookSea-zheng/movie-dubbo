package com.etc.amovie.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookForm {
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
