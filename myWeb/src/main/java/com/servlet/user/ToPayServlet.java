package com.servlet.user;

import com.validate.domain.Order;
import com.validate.service.OrderService;
import com.validate.service.OrderServiceImpl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 前台系统
 * 客户跳转到支付页面的servlet
 */
@WebServlet("/com.servlet.user/ToPayServlet")
public class ToPayServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ToPayServlet() {
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

        String id = request.getParameter("oid"); //获取订单id


        // 1.创建service层的对象
        OrderService service = new OrderServiceImpl();

        // 2.调用service层用于查询order的方法
        Order order=service.findOrderById(id);
        //将查询到的收藏信息添加到request作用域
        request.setAttribute("order", order);
        request.getRequestDispatcher("/WEB-INF/userinfo/pay.jsp").forward(
                request, response);
        return;

    }
}