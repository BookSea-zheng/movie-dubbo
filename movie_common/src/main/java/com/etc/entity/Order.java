package com.etc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order implements Serializable {
    private Integer id;
    private Integer status;
    private Integer userId;
    private Date createTime;
    private Integer sceneId;
    private String ticketNum;
    private Integer totalPrice;
    private String seat;
}
