package com.validate.dao;

import com.validate.domain.Order;
import com.validate.domain.OrderItem;

import java.sql.SQLException;
import java.util.List;

public interface OrderItemDao {
    void addOrderItem(Order order) throws SQLException;
    List<OrderItem> findOrderItemByOrder(final Order order) throws SQLException;
    void delOrderItems(String id) throws SQLException;
}
