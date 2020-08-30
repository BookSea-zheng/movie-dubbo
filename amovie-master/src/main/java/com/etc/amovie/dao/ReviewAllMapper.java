package com.etc.amovie.dao;


import com.etc.amovie.entity.ReviewAll;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ReviewAllMapper {
    /**
     * 查询所有评论
     * @return
     */
    @Select("select r.*,m.name,u.nickname from review r,movie m,user u where r.movie_id=m.id and r.user_id=u.id")
    public List<ReviewAll> getAll();
}
