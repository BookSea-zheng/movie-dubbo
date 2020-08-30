package com.etc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category implements Serializable {
    private Integer id;
    private String name;

    public Category(int i, String sex) {
        this.id=i;
        this.name=sex;
    }
}
