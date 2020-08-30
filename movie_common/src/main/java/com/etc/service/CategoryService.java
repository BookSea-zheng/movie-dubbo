package com.etc.service;

import com.etc.entity.Category;

import java.util.List;


public interface CategoryService {

    /**
     * 获取所有类型
     *
     * @return
     */
    List<Category> getAll();

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    Category getById(Integer id);

    /**
     * 添加类别
     *
     * @param category
     */
    int addCategory(Category category);

    /**
     * 根据内容查询
     *
     * @param name
     * @return
     */
    Category getByName(String name);

    /**
     * 更新类别
     *
     * @param category
     * @return
     */
    int updateCategory(Category category);

    /**
     * 删除类别
     *
     * @param id
     * @return
     */
    int deleteCategory(Integer id);
}
