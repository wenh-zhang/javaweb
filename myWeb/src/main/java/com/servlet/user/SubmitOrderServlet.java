package com.servlet.user;

import com.validate.domain.Order;
import com.validate.domain.OrderItem;
import com.validate.domain.Product;
import com.validate.domain.User;
import com.validate.service.*;
import com.validate.util.IdUtils;

import java.io.IOException;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 前台系统
 * 客户提交订单的servlet
 */
@WebServlet("/com.servlet.user/SubmitOrderServlet")
public class SubmitOrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public SubmitOrderServlet() {
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


        //从request获取商品与数量对应信息
        double money=(double)request.getSession().getAttribute("sum");
        Map<Product, Integer> waitorder=(Map<Product, Integer>)request.getSession().getAttribute("waitorder");
        String receiverAddress=request.getParameter("receiverAddress");
        String receiverName=request.getParameter("receiverName");
        String receiverPhone=request.getParameter("receiverPhone");
        User user=(User) request.getSession().getAttribute("user");

        //创建新订单
        Order neworder=new Order();
        neworder.setId(IdUtils.get16UUID());
        neworder.setMoney(money);
        neworder.setReceiverAddress(receiverAddress);
        neworder.setReceiverName(receiverName);
        neworder.setReceiverPhone(receiverPhone);
        neworder.setUser(user);

        List<OrderItem>orderItems=new ArrayList<OrderItem>();
        Set<Map.Entry<Product, Integer>> set=waitorder.entrySet();
        Iterator<Map.Entry<Product, Integer>> iterator=set.iterator();
        while(iterator.hasNext()){
            OrderItem neworderItem=new OrderItem();
            Map.Entry mapEntry=(Map.Entry)iterator.next();
            Product p=(Product) mapEntry.getKey();
            int buynum=(int)mapEntry.getValue();
            Order order=new Order();
            order.setId(neworder.getId());
            neworderItem.setP(p);
            neworderItem.setOrder(order);
            neworderItem.setBuynum(buynum);
            orderItems.add(neworderItem);
        }

        neworder.setOrderItems(orderItems);


        // 创建service层的对象
        OrderService service=new OrderServiceImpl();

        service.addOrder(neworder);
        Order order=service.findOrderById(neworder.getId());
        request.setAttribute("order",order);
        request.getRequestDispatcher("/WEB-INF/userinfo/payorder.jsp").forward(
                request, response);

    }
}