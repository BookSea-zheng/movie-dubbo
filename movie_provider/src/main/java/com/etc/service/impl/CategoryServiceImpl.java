package com.etc.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.etc.dao.CategoryMapper;
import com.etc.entity.Category;
import com.etc.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> getAll() {
        return categoryMapper.getAll();
    }

    @Override
    public Category getById(Integer id) {
        return categoryMapper.getById(id);
    }

    @Override
    public int addCategory(Category category) {
        return categoryMapper.addCategory(category);
    }

    @Override
    public Category getByName(String name) {
        return categoryMapper.getByName(name);
    }

    @Override
    public int updateCategory(Category category) {
        return categoryMapper.updateCategory(category);
    }

    @Override
    public int deleteCategory(Integer id) {
        return categoryMapper.deleteCategory(id);
    }
}
