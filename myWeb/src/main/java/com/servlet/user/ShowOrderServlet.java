package com.servlet.user;

import com.validate.domain.Order;
import com.validate.domain.User;
import com.validate.service.OrderService;
import com.validate.service.OrderServiceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 前台系统
 * 客户查看订单信息的servlet
 */
@WebServlet("/com.servlet.user/ShowOrderServlet")
public class ShowOrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ShowOrderServlet() {
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

        String orderState = request.getParameter("orderState"); // order类别
        User user=(User)request.getSession().getAttribute("user");

        // 1.创建service层的对象
        OrderService service = new OrderServiceImpl();
        // 2.调用service层用于查询order的方法
        List<Order> allOrders = service.findOrderByUser(user);
        // 3.将查询出的所有order放进request域中
        if(orderState.equals("2")){
            List<Order>paidOrders=new ArrayList<Order>();
            for(int i=0;i<allOrders.size();i++){
                if(allOrders.get(i).getPaystate()==1)
                    paidOrders.add(allOrders.get(i));
            }
            request.setAttribute("orders", paidOrders);

        }
        else if(orderState.equals("1")){
            List<Order>unpaidOrders=new ArrayList<Order>();
            for(int i=0;i<allOrders.size();i++){
                if(allOrders.get(i).getPaystate()==0)
                    unpaidOrders.add(allOrders.get(i));
            }
            request.setAttribute("orders", unpaidOrders);
        }
        else
            request.setAttribute("orders",allOrders);
        request.setAttribute("orderState",Integer.parseInt(orderState));
        // 4.重定向到product.jsp页面
        request.getRequestDispatcher("/WEB-INF/userinfo/myOrder.jsp").forward(
                request, response);
        return;
    }
}