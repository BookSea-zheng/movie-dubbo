package com.etc.amovie.service;

import com.etc.amovie.entity.Order;
import com.etc.amovie.entity.OrderVo;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface OrderService {

    /**
     * 增加订单
     *
     * @param order
     */
    int addOrder(Order order);

    /**
     * 删除订单
     *
     * @param id
     */
    int deleteOrder(Integer id);

    /**
     * 更新订单
     *
     * @param order
     */
    int updateOrder(Order order);

    /**
     * 获取所有订单
     *
     * @return
     */
    List<Order> getAll();

    /**
     * 获取个人所有订单
     *
     * @param userId
     * @return
     */
    List<Order> getAllById(Integer userId);

    /**
     * 获取单个订单
     *
     * @param id
     * @return
     */
    Order getOneById(Integer id);

    /**
     * 获取个人票面
     *
     * @param userId
     * @return
     */
    List<OrderVo> getTicketByUserId(Integer userId);

    /**
     * 获取某张票
     *
     * @param userId
     * @param ticketNum
     * @return
     */
    OrderVo getTicketByNum(Integer userId, String ticketNum);
    /**
     * 获取个人所有订单
     *
     * @param
     * @return
     */
    @Select("select o.*,s.movie_name,s.showtime from `order` o LEFT JOIN scene s on o.scene_id=s.id")
    List<OrderVo> getAllTicket();
}
