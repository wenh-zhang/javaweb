package com.servlet.user;

import com.validate.domain.Cart;
import com.validate.domain.User;
import com.validate.service.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 前台系统
 * 用于展示客户购物车信息Servlet
 */
@WebServlet("/com.servlet.user/ShowCartServlet")
public class ShowCartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ShowCartServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        User user=(User)request.getSession().getAttribute("user");
        // 创建Service层对象
        CartService service = new CartServiceImpl();
        // 调用Service层对象的getAllCarts()方法查询购物车列表
        try {
            List<Cart> carts = service.getAllCarts(user);
            //将查询到的收藏信息添加到request作用域
            request.setAttribute("carts", carts);

            request.getRequestDispatcher("/WEB-INF/userinfo/myCart.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 设置请求和响应编码
        doGet(request,response);
    }

}