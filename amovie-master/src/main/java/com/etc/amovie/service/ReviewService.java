package com.etc.amovie.service;

import com.etc.amovie.entity.Review;
import com.etc.amovie.entity.ReviewVo;
import org.apache.ibatis.annotations.Delete;

import java.util.List;

public interface ReviewService {

    /**
     * 增加评论
     *
     * @param review
     * @return
     */
    int addReview(Review review);

    /**
     * 获取所有评论
     *
     * @return
     */
    List<Review> getAll();

    /**
     * 获取某电影的评论
     *
     * @param movieId
     * @return
     */
    List<ReviewVo> getMovieReview(Integer movieId);
    /**
     * 删除评论
     * @param id
     * @return
     */
      int delReview(int id);
}
