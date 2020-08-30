package com.etc.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.etc.dao.WatchMapper;
import com.etc.entity.Watch;
import com.etc.service.WatchService;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public class WatchServiceImpl implements WatchService {

    @Autowired
    private WatchMapper watchMapper;

    @Override
    public int addWatch(Watch watch) {
        return watchMapper.addWatch(watch);
    }

    @Override
    public int deleteWatch(Integer movieId) {
        return watchMapper.deleteWatch(movieId);
    }

    @Override
    public Watch getWatchByIds(Integer userId, Integer movieId) {
        return watchMapper.getWatchByIds(userId, movieId);
    }
}
