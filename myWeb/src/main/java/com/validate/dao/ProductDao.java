package com.validate.dao;

import com.validate.domain.Order;
import com.validate.domain.OrderItem;
import com.validate.domain.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductDao {
    void addProduct(Product p) throws SQLException;
    List<Product> listAll() throws SQLException;
    Product findProductById(String id) throws SQLException;
    void changeProductNum(Order order) throws SQLException;
    List<Object[]> salesList(String year, String month) throws SQLException;
    List<Product> findProductByManyCondition(String id, String name,
                                             String category, String minprice, String maxprice) throws SQLException;
    void editProduct(Product p) throws SQLException;
    void updateProductNum(List<OrderItem> items) throws SQLException;
    void deleteProduct(String id) throws SQLException;

}
