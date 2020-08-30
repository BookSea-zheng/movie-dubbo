package com.etc.amovie.controller.admin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.etc.amovie.entity.Movie;
import com.etc.amovie.entity.Scene;
import com.etc.amovie.entity.SceneForm;
import com.etc.amovie.service.SceneService;
import com.etc.amovie.utils.Msg;
import com.etc.amovie.utils.Result;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.etc.amovie.service.MovieService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@CrossOrigin
@Controller
@RequestMapping(value = "/api/scenes")
public class AdminSceneController {

    @Autowired
    private SceneService sceneService;

    @Autowired
    private MovieService movieService;

    @GetMapping
    public String getScene(
            @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(value = "size", required = false, defaultValue = "10") Integer size, Model model) {
        Page<Scene> scene = PageHelper.startPage(page, size)
                .doSelectPage(() -> sceneService.getAll());
        model.addAttribute("adminScenes",scene.toPageInfo());
        return "admin/scene";
    }

    @GetMapping(value = "{id}", params = "id")
    public ResponseEntity getById(@PathVariable(value = "id") Integer id) {
        Scene scene = sceneService.getById(id);
        return Result.checkObject(scene);
    }

    @RequestMapping("/adminGetScene")
    public String getAddScene(){
        return "admin/scene-add";
    }

    @RequestMapping("/admimAddScene")
    @ResponseBody
    public String addScene(@RequestParam("scene") String sceneStr) {
        JSON jsonobj = (JSON)JSON.parse(sceneStr);
        Scene scene =JSONObject.toJavaObject(jsonobj,Scene.class);
        System.out.println(scene.getPrice());
        int result = sceneService.addScene(scene);
//        Map<String,Object> map =new HashMap<String,Object>();
        if(result>0){
//            map.put("adminAddScene",true);
            return "true";
        }else{
//            map.put("adminAddScene",false);
            return "false";
        }
//        return JSON.toJSONString(map);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity updateScene(@PathVariable(value = "id") Integer id,
                                      @RequestBody SceneForm sceneForm) {
        Scene scene1 = sceneService.getById(id);
        Scene scene = toScene(sceneForm);
        if (scene1 == null) {
            Msg msg = Msg.fail().add("原因", "该场次不存在！");
            return new ResponseEntity(msg, HttpStatus.NOT_FOUND);
        }
        scene.setId(id);
        int result = sceneService.updateScene(scene);
        return Result.checkUpdate(result);
    }

    @RequestMapping("/delScene")
    public String deleteScene(Integer id) {
        int result = sceneService.deleteScene(id);
        Map<String,Object> map = new HashMap<String, Object>();
        if(result>0){
            map.put("delScenceResult",true);
        }else{
            map.put("delScenceResult",false);
        }
        return JSON.toJSONString(map);
    }

    /**
     * 获取场景类型
     *
     * @param sceneForm
     * @return
     */
    Scene toScene(SceneForm sceneForm) {
        Scene scene = new Scene();
/*        String[] seats = sceneForm.getBookedSeat();
        StringBuilder sb = new StringBuilder();
        for (String seat : seats) {
            sb.append(seat + "#");
        }
        String getSeats = sb.toString();*/
        BeanUtils.copyProperties(sceneForm, scene);
        /* scene.setBookedSeat(getSeats.substring(0, getSeats.length() - 1));*/
        return scene;
    }
}
