package com.servlet.common;

import com.validate.domain.User;
import com.validate.service.UserService;
import com.validate.service.UserServiceImpl;
import com.validate.util.IdUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Servlet 注册 插入用户信息到数据库中，并发送激活邮件
 */
@WebServlet("/com.servlet.common/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    /**
     * Constructor of the object.
     */
    public RegisterServlet() {
        super();
    }

    /**
     * Destruction of the com.servlet. <br>
     */
    public void destroy() {
        super.destroy(); // Just puts "destroy" string in log
        // Put your code here
    }

    /**
     * The doGet method of the com.servlet. <br>
     * <p>
     * This method is called when a form has its tag value method equals to get.
     *
     * @param request  the request send by the client to the server
     * @param response the response send by the server to the client
     * @throws ServletException if an error occurred
     * @throws IOException      if an error occurred
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doPost(request, response);
    }

    /**
     * The doPost method of the com.servlet. <br>
     * <p>
     * This method is called when a form has its tag value method equals to post.
     *
     * @param request  the request send by the client to the server
     * @param response the response send by the server to the client
     * @throws ServletException if an error occurred
     * @throws IOException      if an error occurred
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //指定request字符集防止中文乱码
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        User user = new User();
        user.setUsername(request.getParameter("username"));
        user.setPassword(request.getParameter("password"));
        user.setEmail(request.getParameter("email"));
        user.setActivate_state(0);
        user.setCode(IdUtils.get8UUID());// 随机激活码
        user.setRole("user");

        // User信息插入到数据库中
        UserService userservice = new UserServiceImpl();
        try {
            if (userservice.register(user)) {
                System.out.println("邮箱信息发送成功");
                response.getWriter().append("<script   language=javascript>alert('注册成功，请到相应邮箱激活');window.location='"+request.getContextPath()+"/login/register.jsp'</script>");
//                request.getRequestDispatcher("/login/register.jsp").forward(request, response);
            }
            else {
                response.getWriter().append("<script   language=javascript>alert('用户名/邮箱已被注册，注册失败');window.location='"+request.getContextPath()+"/login/register.jsp'</script>");
//                request.getRequestDispatcher("/login/register.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Initialization of the com.servlet. <br>
     *
     * @throws ServletException if an error occurs
     */
    public void init() throws ServletException {
        // Put your code here
    }

}
