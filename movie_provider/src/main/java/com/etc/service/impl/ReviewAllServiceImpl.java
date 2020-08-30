package com.etc.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.etc.dao.ReviewAllMapper;
import com.etc.entity.ReviewAll;
import com.etc.service.ReviewAllService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class ReviewAllServiceImpl implements ReviewAllService {

    @Autowired
    private ReviewAllMapper reviewAllMapper;

    @Override
    public List<ReviewAll> getAll() {
        return reviewAllMapper.getAll();
    }
}
