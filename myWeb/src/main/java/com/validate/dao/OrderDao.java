package com.validate.dao;

import com.validate.domain.Order;
import com.validate.domain.User;

import java.sql.SQLException;
import java.util.List;

public interface OrderDao {
    void addProduct(Order order) throws SQLException;
    List<Order> findOrderByUser(final User user) throws SQLException;
    Order findOrderById(String id) throws SQLException;
    List<Order> findAllOrder() throws SQLException;
    void updateOrderState(String id) throws SQLException;
    List<Order> findOrderByManyCondition(String id, String receiverName) throws SQLException;
    void delOrderById(String id) throws SQLException;
}
