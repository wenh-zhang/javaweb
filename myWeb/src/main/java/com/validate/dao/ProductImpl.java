package com.validate.dao;

import com.validate.domain.Cart;
import com.validate.domain.Order;
import com.validate.domain.OrderItem;
import com.validate.domain.Product;
import com.validate.util.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductImpl implements ProductDao{
    @Override
    public void addProduct(Product p) throws SQLException{
        String sql = "insert into products values(?,?,?,?,?,?,?)";
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        runner.update(sql, p.getId(), p.getName(), p.getPrice(),
                p.getCategory(), p.getPnum(), p.getImgurl(), p.getDescription());
    }
    // 查找所有商品
    @Override
    public List<Product> listAll() throws SQLException {
        String sql = "select * from products";
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        return runner.query(sql, new BeanListHandler<Product>(Product.class));
    }


    // 根据id查找商品
    @Override
    public Product findProductById(String id) throws SQLException {
        String sql = "select * from products where id=?";
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        return runner.query(sql, new BeanHandler<Product>(Product.class), id);
    }

    // 生成订单时，将商品数量减少
    @Override
    public void changeProductNum(Order order) throws SQLException {
        String sql = "update products set pnum=pnum-? where id=?";
        QueryRunner runner = new QueryRunner();
        List<OrderItem> items = order.getOrderItems();
        Object[][] params = new Object[items.size()][2];

        for (int i = 0; i < params.length; i++) {
            params[i][0] = items.get(i).getBuynum();
            params[i][1] = items.get(i).getP().getId();
        }

        runner.batch(DataSourceUtils.getConnection(), sql, params);
    }

    // 销售榜单
    @Override
    public List<Object[]> salesList(String year, String month)
            throws SQLException {
        String sql = "SELECT products.name,SUM(orderitem.buynum) totalsalnum FROM orders,products,orderItem WHERE orders.id=orderItem.order_id AND products.id=orderItem.product_id AND orders.paystate=1 and year(ordertime)=? and month(ordertime)=? GROUP BY products.name ORDER BY totalsalnum DESC";
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        return runner.query(sql, new ArrayListHandler(), year, month);
    }

    // 多条件查询
    @Override
    public List<Product> findProductByManyCondition(String id, String name,
                                                    String category, String minprice, String maxprice)
            throws SQLException {
        List<Object> list = new ArrayList<Object>();
        String sql = "select * from products where 1=1 ";

        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());

        if (id != null && id.trim().length() > 0) {
            sql += " and id=?";
            list.add(id);
        }

        if (name != null && name.trim().length() > 0) {
            sql += " and name=?";
            list.add(name);
        }
        if (category != null && category.trim().length() > 0) {
            sql += " and category=?";
            list.add(category);
        }
        if (minprice != null && maxprice != null
                && minprice.trim().length() > 0 && maxprice.trim().length() > 0) {
            sql += " and price between ? and ?";
            list.add(minprice);
            list.add(maxprice);
        }

        Object[] params = list.toArray();

        return runner.query(sql, new BeanListHandler<Product>(Product.class),
                params);
    }
    // 修改商品信息
    @Override
    public void editProduct(Product p) throws SQLException {
        //1.创建集合并将商品信息添加到集合中
        List<Object> obj = new ArrayList<Object>();
        obj.add(p.getName());
        obj.add(p.getPrice());
        obj.add(p.getCategory());
        obj.add(p.getPnum());
        obj.add(p.getDescription());
        //2.创建sql语句，并拼接sql
        String sql  = "update products " +
                "set  name=?,price=?,category=?,pnum=?,description=? ";
        //判断是否有图片
        if (p.getImgurl() != null && p.getImgurl().trim().length() > 0) {
            sql += " ,imgurl=?";
            obj.add(p.getImgurl());
        }
        sql += " where id=?";
        obj.add(p.getId());
        System.out.println(sql);
        System.out.println(obj);
        //3.创建QueryRunner对象
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        //4.使用QueryRunner对象的update()方法更新数据
        runner.update(sql, obj.toArray());
    }
    //删除订单时，修改商品数量
    @Override
    public void updateProductNum(List<OrderItem> items) throws SQLException {

        String sql = "update products set pnum=pnum+? where id=?";
        QueryRunner runner = new QueryRunner();

        Object[][] params = new Object[items.size()][2];

        for (int i = 0; i < params.length; i++) {
            params[i][0] = items.get(i).getBuynum();
            params[i][1] = items.get(i).getP().getId();
        }

        runner.batch(DataSourceUtils.getConnection(), sql, params);
    }

    //后台系统，根据id删除商品信息
    @Override
    public void deleteProduct(String id) throws SQLException {
        String sql = "DELETE FROM products WHERE id = ?";
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        runner.update(sql, id);
    }



}
