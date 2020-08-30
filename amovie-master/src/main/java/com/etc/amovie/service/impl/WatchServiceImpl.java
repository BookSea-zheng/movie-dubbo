package com.etc.amovie.service.impl;

import com.etc.amovie.entity.Watch;
import com.etc.amovie.dao.WatchMapper;
import com.etc.amovie.service.WatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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
