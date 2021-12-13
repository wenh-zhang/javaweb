package com.validate.service;

import com.validate.dao.*;
import com.validate.domain.Order;
import com.validate.domain.OrderItem;
import com.validate.domain.User;
import com.validate.util.DataSourceUtils;

import java.sql.SQLException;
import java.util.List;

public class OrderServiceImpl implements OrderService{
    private OrderItemDao oidao = new OrderItemDaoImpl();
    private OrderDao odao = new OrderDaoImpl();
    private ProductDao pdao = new ProductImpl();
    // 1.添加订单
    @Override
    public void addOrder(Order order) {
        try {
            // 1.开启事务
            DataSourceUtils.startTransaction();
            // 2.完成操作
            // 2.1向orders表中添加数据
            odao.addProduct(order);
            // 2.2向orderItem表中添加数据
            oidao.addOrderItem(order);
            // 2.3修改商品表中数据.
            pdao.changeProductNum(order);
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                DataSourceUtils.rollback(); // 事务回滚
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                // 关闭，释放以及提交事务
                DataSourceUtils.releaseAndCloseConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    // 根据用户查找订单
    @Override
    public List<Order> findOrderByUser(User user) {
        List<Order> orders = null;
        try {
            // 查找出订单信息
            orders = odao.findOrderByUser(user);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }
    // 根据id查找订单
    @Override
    public Order findOrderById(String id) {
        Order order = null;
        try {
            order = odao.findOrderById(id);
            List<OrderItem> items = oidao.findOrderItemByOrder(order);
            order.setOrderItems(items);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }
    // 查找所有订单
    @Override
    public List<Order> findAllOrder() {
        List<Order> orders = null;
        try {
            // 查找出订单信息
            orders = odao.findAllOrder();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }
    // 支付成功后修改订单状态
    @Override
    public void updateState(String id) {
        try {
            odao.updateOrderState(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // 多条件查询订单信息
    @Override
    public List<Order> findOrderByManyCondition(String id, String receiverName) {
        List<Order> orders = null;
        try {
            orders = odao.findOrderByManyCondition(id, receiverName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }
    //根据id删除订单 管理员删除订单
    @Override
    public void delOrderById(String id) {
        try {
            DataSourceUtils.startTransaction();//开启事务
            oidao.delOrderItems(id);
            odao.delOrderById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                DataSourceUtils.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }finally{
            try {
                DataSourceUtils.releaseAndCloseConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    //普通用户删除订单
    @Override
    public void delOrderByIdWithClient(String id) {
        try {
            DataSourceUtils.startTransaction();//开启事务
            //从订单项中查找商品购买数量
            Order order=new Order();
            order.setId(id);
            List<OrderItem> items=oidao.findOrderItemByOrder(order);
            //修改商品数量
            pdao.updateProductNum(items);
            oidao.delOrderItems(id); //删除订单项
            odao.delOrderById(id); //删除订单
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                DataSourceUtils.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }finally{
            try {
                DataSourceUtils.releaseAndCloseConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
