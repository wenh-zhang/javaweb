package com.validate.dao;

import com.validate.domain.*;
import com.validate.util.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartDaoImpl implements CartDao{
    public boolean productInCart(String pid,long uid)throws SQLException{
        String sql = "select * from cart where user_id=? and product_id=?";
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        Cart result=null;
        result= runner.query(sql, new BeanHandler<Cart>(Cart.class), uid,pid);
        if(result!=null)return true;
        else return false;
    }
    //前台系统，添加物品进购物车
    @Override
    public  void addProductToCart(String pid,long uid)throws SQLException {
        String sql = "";
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        if(productInCart(pid,uid)){
        }
        else{
            sql="insert into cart values(?,?)";
            runner = new QueryRunner(DataSourceUtils.getDataSource());
            runner.update(sql,uid,pid);

        }
        return;
    }
    @Override
    public List<Cart> getAllCarts(final User user) throws SQLException {
        String sql = "select * from cart,products where products.id=cart.product_id and user_id=?";

        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());

        return runner.query(sql, new ResultSetHandler<List<Cart>>() {
            public List<Cart> handle(ResultSet rs) throws SQLException {

                List<Cart> items = new ArrayList<Cart>();
                while (rs.next()) {
                    Cart item = new Cart();

                    item.setU(user);
                    Product p = new Product();
                    p.setCategory(rs.getString("category"));
                    p.setId(rs.getString("id"));
                    p.setDescription(rs.getString("description"));
                    p.setImgurl(rs.getString("imgurl"));
                    p.setName(rs.getString("name"));
                    p.setPnum(rs.getInt("pnum"));
                    p.setPrice(rs.getDouble("price"));
                    item.setP(p);

                    items.add(item);
                }

                return items;
            }
        }, user.getId());
    }
    @Override
    public  void delProductFromCart(String pid,long uid)throws SQLException{
        String sql = "delete from cart where user_id = ? and product_id = ?";
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        runner.update(sql, uid,pid);
    }
}
