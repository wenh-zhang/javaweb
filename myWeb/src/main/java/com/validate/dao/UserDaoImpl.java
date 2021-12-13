package com.validate.dao;

import java.sql.SQLException;
import java.util.List;


import com.validate.util.DataSourceUtils;
import com.validate.domain.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

public class UserDaoImpl implements UserDao{
    @Override
    public boolean insert(User user) throws SQLException{
        int result = 0;
        String sql = "insert into userinfo (`username`,`email`,`password`,`activate_state`,`code`,`role`) values(?,?,?,?,?,?)";
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        result=runner.update(sql, user.getUsername(), user.getEmail(), user.getPassword(),
                user.getActivate_state(), user.getCode(), user.getRole());
        return result>0?true:false;

    }

    @Override
    public boolean check(String email,int[] activate_state) throws SQLException{
        boolean res = false;
        String sql = "select * from userinfo where email=?";
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        List<User> result = runner.query(sql,new BeanListHandler<User>(User.class), email);
        for (User user : result){
            res=true;
            activate_state[0]=user.getActivate_state();
        }
        return res;
    }

    @Override
    public boolean register(User user) throws SQLException{

        boolean res=false;
        int activate_state[]={0};
        if(check(user.getEmail(),activate_state)){
            if(activate_state[0]==1){
                System.out.println("注册失败，该邮箱已被注册并激活！");
                return res;
            }
            //该邮箱注册但未激活、重新修改数据
            String sql = "update userinfo set username=?,password=?,code=? where email=?";
            QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
            int result=runner.update(sql, user.getUsername(),  user.getPassword(),
                    user.getCode(), user.getEmail());
            if (result>0)res=true;

        }
        else{
            res=insert(user);
        }

        return res;
    }

    /**
     * 注册表改为激活状态
     * @param code
     * @return
     */
    @Override
    public boolean activation(String code) throws SQLException{

        int result = 0;
        String sql = "update userinfo set activate_state=1 where code=?";
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        result=runner.update(sql, code);


        return result>0?true:false;
    }

    @Override
    public User userVerify(String email, String password) throws SQLException{

        User user=null;
        String sql = "select * from userinfo where  email=? and password=? and activate_state=1";
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        user = runner.query(sql,new BeanHandler<User>(User.class), email,password);
        return user;

    }


}
