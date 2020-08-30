package com.etc.controller.admin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.etc.entity.Movie;
import com.etc.entity.MovieForm;
import com.etc.entity.Scene;
import com.etc.service.MovieService;
import com.etc.service.SceneService;
import com.etc.utils.Msg;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@Controller
@RequestMapping(value = "/api/movies")
public class AdminMovieController {

    @Reference
    private MovieService movieService;

    @Reference
    private SceneService sceneService;

    @RequestMapping("/adminindex.html")
    public String getAdmin(){
        System.out.println("-----------------------");
        return "admin/index";
    }



    @GetMapping("/getAdminMovies.html")
    public String getMovie(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size, Model model) {
        Page<Movie> movies = PageHelper.startPage(page, size).doSelectPage(() -> movieService.getAll());
        model.addAttribute("adminMovies",movies.toPageInfo());
        return "admin/movie";
    }

    @GetMapping("/getAll")
    @ResponseBody
    public List<Movie> getAll(){
        List<Movie> movieList = movieService.getAll();
        return movieList;
    }


    @RequestMapping("/adminMovies/{id}")
    public String getById(@PathVariable(value = "id") Integer id, Model model) {
        Movie movie = movieService.getById(id);
        model.addAttribute("adminMovies",movie);
        return "/admin/movie-detail";
    }

    @GetMapping(value = "{id}/scenes")
    public ResponseEntity getSceneCount(@PathVariable(value = "id") Integer id) {
        List<Scene> scenes = sceneService.getSceneCount(id);
        return ResponseEntity.ok(scenes);
    }

    @GetMapping(value = "/released")
    public ResponseEntity getMovies() {
        List<Movie> movies = movieService.getMovieReleased();
        return ResponseEntity.ok(movies);
    }


    //跳转到添加电影
    @RequestMapping("/adminGetAdd")
    public String getAdd(){
        return "admin/movie-add";
    }

    @RequestMapping("/adminAddMovie")
    @ResponseBody
    public String addMovie(@RequestParam("movieForm") String movieFormStr) {
        JSON jsonobj = (JSON) JSON.parse(movieFormStr);
        MovieForm movieForm = JSONObject.toJavaObject(jsonobj,MovieForm.class);
        Movie movie = toMovie(movieForm);
        Integer[] categoryIds = movieForm.getCategoryIds();
        int result = movieService.addMovie(movie, categoryIds);
        Map<String,Object> map =new HashMap<String,Object>();
        if(result>0){
            map.put("adminAddMovie",true);
        }else{
            map.put("adminAddMovie",false);
        }
        return JSON.toJSONString(map);
    }

    @RequestMapping("/adminModifyMovie")
    @ResponseBody
    public String updateMovie(@RequestParam("movie") String movieStr) {
        JSON jsonobj = (JSON) JSON.parse(movieStr);
        Movie movie = JSONObject.toJavaObject(jsonobj,Movie.class);
        Map<String,Object> map=new HashMap<String, Object>();
        int result = movieService.updateMovie(movie);
        if (result != 0) {
            map.put("adminModifyMovie",true);
        }else{
            map.put("adminModifyMovie",false);
        }
        return JSON.toJSONString(map);
    }




    @DeleteMapping(value = "{id}")
    public ResponseEntity deleteMovie(@PathVariable(value = "id") Integer id) {
        Movie movie = movieService.getById(id);
        if (movie == null) {
            Msg msg = Msg.fail().add("原因", "该电影不存在！");
            return new ResponseEntity(msg, HttpStatus.NOT_FOUND);
        }
        int result = movieService.deleteMovie(id);
        if (result != 0) {
            return ResponseEntity.ok(Msg.success());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    /**
     * MovieForm对象转到Movie
     *
     * @param movieForm
     * @return
     */
    Movie toMovie(MovieForm movieForm) {
        Movie movie = new Movie();
        BeanUtils.copyProperties(movieForm, movie);
        //TODO 时间转换
        movie.setReleaseDate(new Date());
        return movie;
    }
}
