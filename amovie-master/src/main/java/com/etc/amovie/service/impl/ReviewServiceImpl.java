package com.etc.amovie.service.impl;

import com.etc.amovie.entity.Review;
import com.etc.amovie.entity.ReviewVo;
import com.etc.amovie.dao.ReviewMapper;
import com.etc.amovie.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
