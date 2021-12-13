package com.servlet.common;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * 用于受保护的jsp之间跳转的Servlet
 */
@WebServlet("/com.servlet.common/jump")
public class jump extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public jump() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String address = request.getParameter("address");
        // 使用request对象的getSession()获取session，如果session不存在则创建一个
        HttpSession session = request.getSession();

        request.getRequestDispatcher(address).forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 设置请求和响应编码
        doGet(request,response);
    }

}