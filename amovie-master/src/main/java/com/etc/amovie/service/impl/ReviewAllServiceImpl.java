package com.etc.amovie.service.impl;

import com.etc.amovie.dao.ReviewAllMapper;
import com.etc.amovie.entity.ReviewAll;
import com.etc.amovie.service.ReviewAllService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
