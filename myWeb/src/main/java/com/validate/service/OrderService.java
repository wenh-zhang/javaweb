package com.validate.service;

import com.validate.domain.Order;
import com.validate.domain.User;

import java.util.List;

public interface OrderService {
    void addOrder(Order order);
    List<Order> findOrderByUser(User user);
    public Order findOrderById(String id);
    List<Order> findAllOrder();
    void updateState(String id);
    List<Order> findOrderByManyCondition(String id, String receiverName);
    void delOrderById(String id);
    void delOrderByIdWithClient(String id);
}
