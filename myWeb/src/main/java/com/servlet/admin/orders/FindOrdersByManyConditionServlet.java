package com.servlet.admin.orders;

import com.validate.domain.Order;
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
 * 后台用于根据条件查找订单的Servlet
 */
@WebServlet("/com.servlet.admin.orders/FindOrdersByManyConditionServlet")
public class FindOrdersByManyConditionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public FindOrdersByManyConditionServlet() {
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

        //获取订单编号和收件人名称
        String id = request.getParameter("id");
        String receiverName = request.getParameter("receiverName");
        // 创建Service层对象
        OrderService service = new OrderServiceImpl();
        // 调用Service层对象的findOrderByManyCondition()方法查询订单列表
        List<Order> orders = service.findOrderByManyCondition(id,receiverName);
        //将查询到的订单信息添加到request作用域
        request.setAttribute("orders", orders);

        request.getRequestDispatcher("/WEB-INF/admin/orders/orders.jsp").forward(request, response);
    }

}