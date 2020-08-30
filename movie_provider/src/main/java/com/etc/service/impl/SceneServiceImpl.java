package com.etc.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.etc.dao.SceneMapper;
import com.etc.entity.Scene;
import com.etc.service.SceneService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@Service
public class SceneServiceImpl implements SceneService {

    @Autowired
    private SceneMapper sceneMapper;


    @Override
    public List<Scene> getAll() {
        return sceneMapper.getAll();
    }

    @Override
    public Scene getById(Integer id) {
        return sceneMapper.getById(id);
    }

    @Override
    public int addScene(Scene scene) {
        return sceneMapper.addScene(scene);
    }

    @Override
    public int updateScene(Scene scene) {
        return sceneMapper.updateScene(scene);
    }

    @Override
    public int deleteScene(Integer id) {
        return sceneMapper.deleteScene(id);
    }

    @Override
    public List<Scene> getSceneCount(Integer movieId) {
        return sceneMapper.getSceneCount(movieId);
    }
}
