package com.etc.service;

import com.etc.entity.ReviewAll;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ReviewAllService {

    /**
     * 查询所有评论
     * @return
     */
    public List<ReviewAll> getAll();
}
