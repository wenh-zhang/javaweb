package com.servlet.admin.orders;

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
 * 后台用于删除用户已支付订单信息的Servlet
 * 未支付订单不能被后台删除
 */
@WebServlet("/com.servlet.admin.orders/DeleteOrderServlet")
public class DeleteOrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DeleteOrderServlet() {
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

        // 创建Service层对象
        OrderService service = new OrderServiceImpl();
        // 调用Service层对象的delOrderById()方法删除订单列表
        service.delOrderById(id);

        request.getRequestDispatcher("/WEB-INF/admin/orders/orders.jsp").forward(request, response);
    }

}