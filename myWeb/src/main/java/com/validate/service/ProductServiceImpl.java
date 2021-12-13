package com.validate.service;

import com.validate.dao.ProductDao;
import com.validate.dao.ProductImpl;
import com.validate.domain.Product;
import com.validate.exception.AddProductException;
import com.validate.exception.FindProductByIdException;
import com.validate.exception.ListProductException;

import java.sql.SQLException;
import java.util.List;

public class ProductServiceImpl implements ProductService{
    ProductDao dao = new ProductImpl();
    // 添加商品
    @Override
    public void addProduct(Product p) throws AddProductException {
        try {
            dao.addProduct(p);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new AddProductException("添加商品失败");
        }
    }
    // 查找所有商品信息
    @Override
    public List<Product> listAll() throws ListProductException {
        try {
            return dao.listAll();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ListProductException("查询商品失败");
        }
    }

    // 根据id查找商品
    @Override
    public Product findProductById(String id) throws FindProductByIdException {
        try {
            return dao.findProductById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new FindProductByIdException("根据ID查找商品失败");
        }
    }
    // 下载销售榜单
    @Override
    public List<Object[]> download(String year, String month) {
        List<Object[]> salesList = null;
        try {
            salesList = dao.salesList(year, month);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salesList;
    }
    // 多条件查询
    @Override
    public List<Product> findProductByManyCondition(String id, String name,
                                                    String category, String minprice, String maxprice) {
        List<Product> ps = null;
        try {
            ps = dao.findProductByManyCondition(id, name, category, minprice,
                    maxprice);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ps;
    }
    // 修改商品信息
    @Override
    public void editProduct(Product p) {
        try {
            dao.editProduct(p);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //后台系统，根据id删除商品信息
    @Override
    public void deleteProduct(String id) {
        try {
            dao.deleteProduct(id);
        } catch (SQLException e) {
            throw new RuntimeException("后台系统根据id删除商品信息失败！");
        }
    }



}
