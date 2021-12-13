package com.validate.service;

import com.validate.dao.UserDao;
import com.validate.dao.UserDaoImpl;
import com.validate.domain.User;
import com.validate.util.EmailUtil;

import java.sql.SQLException;

public class UserServiceImpl implements UserService{
    UserDao userdao=new UserDaoImpl();
    @Override
    public boolean register(User user) throws SQLException {
        boolean res=userdao.register(user);
        if(res){
            //发送邮件
            System.out.println("数据库添加成功，正在发送邮件......");
            boolean sendEmail = EmailUtil.sendEmail(user.getEmail(), "Register Activation","恭喜"+user.getUsername()+"注册成功，请点击以下链接进行"
                    + "激活<br><a href='http://120.24.227.213:8080/myWeb/com.servlet.common/ActivationServlet?code="+user.getCode()+"'>立即激活</a >");
            System.out.println("发送完成");
            return sendEmail;
        }else{
            System.out.println("数据库添加失败，原因:用户已存在/邮箱已被注册/System Error");
            return false;
        }
    }
    @Override
    public boolean activation(String code) throws SQLException {
        return userdao.activation(code);
    }

    @Override
    public User userVerify(String email, String password) throws SQLException {
        return userdao.userVerify(email,password);
    }


}
