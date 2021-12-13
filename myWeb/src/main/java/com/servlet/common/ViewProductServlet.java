package com.servlet.common;

import com.validate.domain.Product;
import com.validate.exception.FindProductByIdException;
import com.validate.service.ProductService;
import com.validate.service.ProductServiceImpl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 前台系统
 * 查看商品详细信息的servlet
 */
@WebServlet("/com.servlet.common/ViewProductServlet")
public class ViewProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ViewProductServlet() {
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

        String id = request.getParameter("id"); // 商品id

        // 1.创建service层的对象
        ProductService service = new ProductServiceImpl();
        // 2.调用service层用于查询商品的方法
        Product p = null;
        try {
            p = service.findProductById(id);
            // 3.将查询出的所有商品放进request域中
            request.setAttribute("p", p);
            // 4.重定向到productdetail.jsp页面
            request.getRequestDispatcher("/user/productdetail.jsp").forward(
                    request, response);
            return;
        } catch (FindProductByIdException e) {
            e.printStackTrace();
        }

    }
}