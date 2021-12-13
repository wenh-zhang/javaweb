package com.servlet.common;


import com.validate.service.UserService;
import com.validate.service.UserServiceImpl;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 用于接受激活信息的Servlet
 */
@WebServlet("/com.servlet.common/ActivationServlet")
public class ActivationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ActivationServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 设置请求和响应编码
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String code = request.getParameter("code");
        UserService userservice=new UserServiceImpl();
        try {
            if (userservice.activation(code)) {
                response.getWriter().append("恭喜您，激活成功！");
            } else {
                response.getWriter().append("激活失败，请检查邮箱！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}