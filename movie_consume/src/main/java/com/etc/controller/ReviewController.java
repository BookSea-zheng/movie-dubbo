package com.etc.controller;

import com.etc.entity.Review;
import com.etc.entity.User;
import com.etc.service.ReviewService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.Date;


@Controller
public class ReviewController {

    @Reference
    private ReviewService reviewService;

    @PostMapping(value = "/review")
    public ResponseEntity addReview(Review review, HttpSession session) {
        User user = (User) session.getAttribute("login");
        if (user == null) {
            return ResponseEntity.ok("请先登录！");
        }
        review.setCreateTime(new Date());
        review.setUserId(user.getId());
        int result = reviewService.addReview(review);
        if (result != 0) {
            return ResponseEntity.ok("评论成功！");
        }
        return ResponseEntity.ok("评论异常！");
    }
}
