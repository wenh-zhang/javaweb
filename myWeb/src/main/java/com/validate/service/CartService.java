package com.validate.service;

import com.validate.domain.Cart;
import com.validate.domain.User;

import java.sql.SQLException;
import java.util.List;

public interface CartService {
    void addProductToCart(String pid,long uid)throws SQLException;
    List<Cart> getAllCarts(User user) throws SQLException;
    void delProductFromCart(String pid,long uid)throws SQLException;
}
