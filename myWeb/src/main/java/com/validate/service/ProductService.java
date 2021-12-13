package com.validate.service;


import com.validate.domain.Product;
import com.validate.exception.AddProductException;
import com.validate.exception.FindProductByIdException;
import com.validate.exception.ListProductException;

import java.sql.SQLException;
import java.util.List;

public interface ProductService {
    void addProduct(Product p) throws AddProductException;
    List<Product> listAll() throws ListProductException;
    Product findProductById(String id) throws FindProductByIdException;
    List<Object[]> download(String year, String month);
    List<Product> findProductByManyCondition(String id, String name, String category, String minprice, String maxprice);
    void editProduct(Product p);
    void deleteProduct(String id);

}

