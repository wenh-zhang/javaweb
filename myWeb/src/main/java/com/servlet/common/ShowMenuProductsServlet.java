package com.servlet.common;

import com.validate.domain.Product;
import com.validate.service.ProductService;
import com.validate.service.ProductServiceImpl;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 前台系统
 * 根据menu展示商品的servlet
 */
@WebServlet("/com.servlet.common/ShowMenuProductsServlet")
public class ShowMenuProductsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ShowMenuProductsServlet() {
        super();
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doPost(request, response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //指定request字符集防止中文乱码
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        String id = request.getParameter("id"); // 商品id
        String name = request.getParameter("name"); // 商品名称
        String category = request.getParameter("category"); // 商品类别
        String minprice = request.getParameter("minprice"); // 最小价格
        String maxprice = request.getParameter("maxprice"); // 最大价格

        // 1.创建service层的对象
        ProductService service = new ProductServiceImpl();
        // 2.调用service层用于查询商品的方法
        List<Product> ps = service.findProductByManyCondition(id,name,category,minprice,maxprice);
        // 3.将查询出的所有商品放进request域中
        request.setAttribute("ps", ps);
        request.setAttribute("category",category);
        // 4.重定向到product.jsp页面
        request.getRequestDispatcher("/user/products.jsp").forward(
                request, response);
        return;
    }
}