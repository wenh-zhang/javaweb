package com.servlet.user;

import com.validate.domain.User;
import com.validate.service.OrderService;
import com.validate.service.OrderServiceImpl;
import com.validate.util.EmailUtil;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 前台系统
 * 客户执行虚拟支付操作的servlet
 */
@WebServlet("/com.servlet.user/PayServlet")
public class PayServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public PayServlet() {
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

        // 1.将要提交的数据得到

        String orderid = request.getParameter("orderid");
        String money = request.getParameter("money");
        String bank = request.getParameter("yh");

        User user=(User)request.getSession().getAttribute("user");

        OrderService service=new OrderServiceImpl();
        //更新订单状态为已支付
        service.updateState(orderid);
        //发送邮件
        EmailUtil.sendEmail(user.getEmail(), "正经书贩子用户支付","亲爱的 "+user.getUsername()+" 用户，您的订单 "+orderid+
                " 已通过银行 " +bank+" 支付成功，合计 " +money+ " 元，将尽快为您安排发货，祝你生活愉快！");
        request.getRequestDispatcher("/WEB-INF/userinfo/paysuccess.jsp").forward(request, response);
        return;
    }
}