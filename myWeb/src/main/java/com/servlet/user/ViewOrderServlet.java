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
 * 客户用于查看订单详细内容的Servlet
 */
@WebServlet("/com.servlet.user/ViewOrderServlet")
public class ViewOrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ViewOrderServlet() {
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

        //获取订单编号
        String id = request.getParameter("id");

        // 创建Service层对象
        OrderService service = new OrderServiceImpl();
        // 调用Service层对象的findOrderById()方法查询订单列表
        Order order = service.findOrderById(id);
        //将查询到的订单信息添加到request作用域
        request.setAttribute("order", order);

        request.getRequestDispatcher("/WEB-INF/userinfo/orderdetail.jsp").forward(request, response);
    }

}