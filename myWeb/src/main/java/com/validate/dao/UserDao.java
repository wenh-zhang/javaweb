package com.validate.dao;

import com.validate.domain.User;

import java.sql.SQLException;

public interface UserDao {
    boolean insert(User user) throws SQLException;
    boolean check(String email,int[] activate_state) throws SQLException;
    boolean register(User user) throws SQLException;
    boolean activation(String code) throws SQLException;
    User userVerify(String email,String password) throws SQLException;
}
