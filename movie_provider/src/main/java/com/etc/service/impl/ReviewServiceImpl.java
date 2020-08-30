package com.etc.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.etc.dao.ReviewMapper;
import com.etc.entity.Review;
import com.etc.entity.ReviewVo;
import com.etc.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewMapper reviewMapper;

    @Override
    public int addReview(Review review) {
        return reviewMapper.addReview(review);
    }

    @Override
    public List<Review> getAll() {
        return reviewMapper.getAll();
    }

    @Override
    public List<ReviewVo> getMovieReview(Integer movieId) {
        return reviewMapper.getMovieReview(movieId);
    }

    @Override
    public int delReview(int id) { return reviewMapper.delReview(id); }
}
