package com.etc.amovie.controller.admin;

import com.etc.amovie.entity.OrderVo;
import com.etc.amovie.entity.Review;
import com.etc.amovie.entity.ReviewAll;
import com.etc.amovie.service.ReviewAllService;
import com.etc.amovie.service.ReviewService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@Controller
@RequestMapping(value = "/api/review")
public class AdminReviewController {
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private ReviewAllService reviewAllService;

    @GetMapping
    public String getAllReview(@RequestParam(value = "page", defaultValue = "1") Integer page,
                         @RequestParam(value = "size", defaultValue = "5") Integer size, Model model) {
        Page<ReviewAll> reviews = PageHelper.startPage(page, size).doSelectPage(() -> reviewAllService.getAll());
        model.addAttribute("adminReviews",reviews.toPageInfo());
        return "admin/review";
    }

    @RequestMapping("/adminDelReview")
    @ResponseBody
    public String delReview(int id){
        int result = reviewService.delReview(id);
        Map<String,Object> map = new HashMap<String, Object>();
        if(result>0){
            return "true";
        }else{
            return "false";
        }

    }
}
