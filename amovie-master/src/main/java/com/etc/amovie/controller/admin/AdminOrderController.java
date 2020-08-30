package com.etc.amovie.controller.admin;

import com.alibaba.fastjson.JSON;
import com.etc.amovie.entity.Order;
import com.etc.amovie.entity.OrderVo;
import com.etc.amovie.service.OrderService;
import com.etc.amovie.utils.Msg;
import com.etc.amovie.utils.Result;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@CrossOrigin
@Controller
@RequestMapping(value = "/api/order")
public class AdminOrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public String getAll(@RequestParam(value = "page", defaultValue = "1") Integer page,
                         @RequestParam(value = "size", defaultValue = "5") Integer size, Model model) {
        Page<OrderVo> orders = PageHelper.startPage(page, size).doSelectPage(() -> orderService.getAllTicket());
        model.addAttribute("adminOrders",orders.toPageInfo());
        return "admin/order";
    }

    @GetMapping(value = "{id}")
    public ResponseEntity getById(@PathVariable(value = "id") Integer id) {
        Order order = orderService.getOneById(id);
        return Result.checkObject(order);
    }

    @PostMapping
    public ResponseEntity addMovie(@RequestBody Order order) {
        order.setCreateTime(new Date());
        int result = orderService.addOrder(order);
        return Result.checkAdd(result);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity updateMovie(@PathVariable(value = "id") Integer id,
                                      @RequestBody Order order) {
        order.setCreateTime(new Date());
        int result = orderService.updateOrder(order);
        return Result.checkUpdate(result);
    }

    @RequestMapping("/adminDelOrder")
    @ResponseBody
    public String deleteMovie(Integer id) {
        int result = orderService.deleteOrder(id);
        Map<String,Object> map = new HashMap<String, Object>();
        if(result>0){
            return "true";
        }else{
           return "false";
        }

    }
}
