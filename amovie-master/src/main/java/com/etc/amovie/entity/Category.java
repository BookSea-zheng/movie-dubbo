package com.etc.amovie.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    private Integer id;
    private String name;

    public Category(int i, String sex) {
        this.id=i;
        this.name=sex;
    }
}
