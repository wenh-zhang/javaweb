package com.servlet.user;

import com.validate.domain.Product;
import com.validate.exception.FindProductByIdException;
import com.validate.service.*;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 前台系统
 * 进入用户单件商品结算页面的Servlet
 */
@WebServlet("/com.servlet.user/OneCartServlet")
public class OneCartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public OneCartServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request,response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 设置请求和响应编码
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String id=request.getParameter("pid");
        // 创建Service层对象
        ProductService service=new ProductServiceImpl();
        // 调用Service层对象的getAllCarts()方法查询购物车列表
        try {
            Product product=(Product) service.findProductById(id);
            System.out.println(product.toString());
            //将查询到的收藏信息添加到request作用域
            request.setAttribute("product", product);
            request.getRequestDispatcher("/WEB-INF/userinfo/oneCart.jsp").forward(request, response);
        } catch (FindProductByIdException e) {
            e.printStackTrace();
        }
    }

}