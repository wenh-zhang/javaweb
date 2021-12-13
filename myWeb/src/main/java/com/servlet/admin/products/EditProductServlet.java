package com.servlet.admin.products;

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
 * 后台系统
 * 进入编辑商品信息页面的servlet
 */
@WebServlet("/com.servlet.admin.products/EditProductServlet")
public class EditProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public EditProductServlet() {
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
        // 1.创建service层的对象
        ProductService service = new ProductServiceImpl();
        String id=request.getParameter("id");
        try {
            // 2.调用service层用于查询商品的方法
            Product p= service.findProductById(id);
            // 3.将查询出的所有商品放进request域中
            request.setAttribute("p", p);
            // 4.重定向到list.jsp页面
            request.getRequestDispatcher("/WEB-INF/admin/products/edit.jsp").forward(
                    request, response);
            return;
        } catch (FindProductByIdException e) {
            e.printStackTrace();
            response.getWriter().write(e.getMessage());
            return;
        }
    }
}
