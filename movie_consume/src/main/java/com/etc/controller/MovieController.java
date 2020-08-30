package com.etc.controller;

import com.etc.entity.Movie;
import com.etc.entity.MovieScore;
import com.etc.entity.ReviewVo;
import com.etc.entity.Scene;
import com.etc.service.MovieService;
import com.etc.service.ReviewService;
import com.etc.service.SceneService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MovieController {

    @Reference
    private MovieService movieService;

    @Reference
    private SceneService sceneService;

    @Reference
    private ReviewService reviewService;


    @RequestMapping(value = "/movie-list")
    public String movieList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                            @RequestParam(value = "size", defaultValue = "3") Integer size,
                            Model model) {
        Page<MovieScore> movies = PageHelper.startPage(page, size).doSelectPage(() -> movieService.getAll());
        model.addAttribute("movies",movies.toPageInfo());
        return "movie-list";
    }

    @RequestMapping("/movie/{id}")
    public String getOneMovie(@PathVariable(value = "id") Integer id, Model model) {
        Movie movie = movieService.getById(id);
        List<Scene> scenes = sceneService.getSceneCount(id);
        List<ReviewVo> reviews = reviewService.getMovieReview(id);
        model.addAttribute("reviews", reviews);
        model.addAttribute("movie", movie);
        model.addAttribute("scenes", scenes);
        return "movie";
    }

    @GetMapping("/rate")
    public String getMovieScore(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                @RequestParam(value = "size", defaultValue = "5") Integer size,
                                Model model) {
        Page<MovieScore> movieScores = PageHelper.startPage(page, size).doSelectPage(() -> movieService.getMovieScore());
        model.addAttribute("movies", movieScores.toPageInfo());
        return "rate";
    }
}
