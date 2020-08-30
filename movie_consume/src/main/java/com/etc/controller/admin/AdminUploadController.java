package com.etc.controller.admin;

import com.etc.entity.Movie;
import com.etc.service.MovieService;
import com.etc.utils.Myproperties;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@CrossOrigin(value = "*", allowCredentials = "true")
@RequestMapping("/api/upload")
public class AdminUploadController {

    @Autowired
    private Myproperties myproperties;

    @Reference
    private MovieService movieService;

    @PostMapping
    public String upload(MultipartFile attact, Movie movie, String[] type, String movieTime) throws IOException, ParseException {
        System.out.println(movie);
        /*-------------------海报上传-------------------------*/
        String filepath = myproperties.getFilepath();
        File file1 = new File(filepath);
        if (!file1.exists()) {
            file1.mkdir();
        }
        String filename = attact.getOriginalFilename();
        String localPath = filepath + File.separator + filename;
        File file2 = new File(localPath);
        attact.transferTo(file2);
        Map map = new HashMap();
        //   /upload/xxx.jpg
        String url = File.separator + "upload" + File.separator + filename;
        /*---------------------海报上传结束-------------------------*/
        movie.setPoster(url);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(movieTime);
        movie.setReleaseDate(date);
        Integer[] categoryIds= new Integer[type.length];
        for (int i = 0; i <type.length; i++) {
            categoryIds[i]=Integer.valueOf(type[i]);
        }
        int result = movieService.addMovie(movie,categoryIds);
        if(result>0){
            return "admin/movie";
        }else{
            return "admin/movie-add";
        }
    }
}
