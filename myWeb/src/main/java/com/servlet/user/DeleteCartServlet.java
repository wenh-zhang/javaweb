package com.servlet.user;

import com.validate.domain.Cart;
import com.validate.domain.Product;
import com.validate.domain.User;
import com.validate.service.CartService;
import com.validate.service.CartServiceImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 后台系统
 * 从购物车删除商品的servlet
 */
@WebServlet("/com.servlet.user/DeleteCartServlet")
public class DeleteCartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DeleteCartServlet() {
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

        String pid = request.getParameter("pid"); // 商品id
        User user=(User) request.getSession().getAttribute("user");

        // 1.创建service层的对象
        CartService service = new CartServiceImpl();
        // 2.调用service层用于从购物车删除商品的方法
        try {
            service.delProductFromCart(pid,user.getId());
            List<Cart> carts = service.getAllCarts(user);
            //将查询到的收藏信息添加到request作用域
            request.setAttribute("carts", carts);
            request.getRequestDispatcher("/WEB-INF/userinfo/myCart.jsp").forward(
                    request, response);
            return;
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}