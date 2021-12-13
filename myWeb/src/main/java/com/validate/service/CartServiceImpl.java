package com.validate.service;

import com.validate.dao.CartDao;
import com.validate.dao.CartDaoImpl;
import com.validate.domain.Cart;
import com.validate.domain.User;

import java.sql.SQLException;
import java.util.List;

public class CartServiceImpl implements CartService{
    CartDao dao=new CartDaoImpl();
    @Override
    public void addProductToCart(String pid,long uid)throws SQLException {
        try {
            dao.addProductToCart(pid,uid);
        }catch (SQLException e) {
            throw new RuntimeException("后台系统添加购物车信息失败！");
        }
    }
    @Override
    public List<Cart> getAllCarts(User user) throws SQLException{
        try {
            return dao.getAllCarts(user);
        }catch (SQLException e) {
            throw new RuntimeException("后台系统查询购物车信息失败！");
        }
    }
    @Override
    public void delProductFromCart(String pid,long uid)throws SQLException{
        try {
            dao.delProductFromCart(pid,uid);
        }catch (SQLException e) {
            throw new RuntimeException("后台系统删除购物车信息失败！");
        }
    }
}
