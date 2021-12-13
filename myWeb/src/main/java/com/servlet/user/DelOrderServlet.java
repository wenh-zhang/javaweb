package com.servlet.user;

import com.validate.domain.Order;
import com.validate.domain.User;

import com.validate.service.OrderService;
import com.validate.service.OrderServiceImpl;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 前台系统
 * 客户删除未支付订单的servlet
 */
@WebServlet("/com.servlet.user/DelOrderServlet")
public class DelOrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DelOrderServlet() {
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

        User user=(User)request.getSession().getAttribute("user");
        String id = request.getParameter("id"); //获取订单id
        String orderState=request.getParameter("orderState");

        // 1.创建service层的对象
        OrderService service = new OrderServiceImpl();

        // 2.调用service层用于查询order、删除order的方法
        service.delOrderByIdWithClient(id);
        List<Order>orders = service.findOrderByUser(user);
        //将查询到的订单信息添加到request作用域
        request.setAttribute("orders", orders);
        request.setAttribute("orderState",orderState);
        request.getRequestDispatcher("/WEB-INF/userinfo/myOrder.jsp").forward(
                request, response);
        return;

    }
}