package com.servlet.common;

import com.validate.domain.User;
import com.validate.service.UserService;
import com.validate.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;



/**
 * 用于用户登录验证的Servlet
 */
@WebServlet("/com.servlet.common/LoginServlet")
public class LoginServlet extends HttpServlet {
    /**
     * Constructor of the object.
     */
    public LoginServlet() {
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
     *
     * This method is called when a form has its tag value method equals to get.
     *
     * @param request the request send by the client to the server
     * @param response the response send by the server to the client
     * @throws ServletException if an error occurred
     * @throws IOException if an error occurred
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doPost(request,response);
    }

    /**
     * The doPost method of the com.servlet. <br>
     *
     * This method is called when a form has its tag value method equals to post.
     *
     * @param request the request send by the client to the server
     * @param response the response send by the server to the client
     * @throws ServletException if an error occurred
     * @throws IOException if an error occurred
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //指定request字符集防止中文乱码
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        //从数据库中读取用户名和密码


        UserService userservice = new UserServiceImpl();

        User user= null;
        try {
            user = userservice.userVerify(email,password);

        // 使用request对象的getSession()获取session，如果session不存在则创建一个
        HttpSession session = request.getSession();

        //保留用户输入的用户名
        session.setAttribute( "emailInput", email );// 保存输入的用户名
        if (user!=null){
            System.out.println("user verify success!");


            // 获取session的Id
            String sessionId = session.getId();
            System.out.println("session的id是：" + sessionId);

            //重定向、转发
            if(user.getRole().equals("user")) {
                response.sendRedirect(request.getContextPath() + "/user/index.jsp");
                //将信息保存在session中
                session.setAttribute("user",user);
            }
            else if(user.getRole().equals("admin")){
                request.getRequestDispatcher("/WEB-INF/admin/index.jsp").forward(request, response);
                //将信息保存在session中
                session.setAttribute("admin",user);
            }
        }
        else{
            //查找失败

            session.setAttribute( "emailInput", email );// 保存输入的用户名
            response.getWriter().append("<script   language=javascript>alert('用户名不存在/密码错误');window.location='"+request.getContextPath()+"/login/login.jsp'</script>");
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
