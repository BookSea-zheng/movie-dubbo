package com.etc.controller.admin;

import com.etc.entity.Order;
import com.etc.entity.OrderVo;
import com.etc.service.OrderService;
import com.etc.utils.Result;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.alibaba.dubbo.config.annotation.Reference;
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

    @Reference
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
