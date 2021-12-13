package com.servlet.user;

import com.validate.domain.Product;
import com.validate.domain.User;
import com.validate.exception.FindProductByIdException;
import com.validate.service.CartService;
import com.validate.service.CartServiceImpl;
import com.validate.service.ProductService;
import com.validate.service.ProductServiceImpl;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 前台系统
 * 添加商品进购物车的servlet
 */
@WebServlet("/com.servlet.user/AddCartServlet")
public class AddCartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AddCartServlet() {
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

        String pid = request.getParameter("pid"); // 商品id
        User user=(User) request.getSession().getAttribute("user");

        // 1.创建service层的对象
        CartService service = new CartServiceImpl();
        ProductService service1=new ProductServiceImpl();
        // 2.调用service层用于查询商品的方法
        Product p = null;
        try {
            service.addProductToCart(pid, user.getId());
            p=service1.findProductById(pid);
            request.setAttribute("p",p);
            request.getRequestDispatcher("/user/productdetail.jsp").forward(
                    request, response);
            return;
        } catch (SQLException | FindProductByIdException e) {
            e.printStackTrace();
        }

    }
}