package com.validate.service;

import com.validate.domain.User;

import java.sql.SQLException;

public interface UserService {
    boolean register(User user) throws SQLException;
    boolean activation(String code) throws SQLException;
    User userVerify(String email,String password) throws SQLException;
}
