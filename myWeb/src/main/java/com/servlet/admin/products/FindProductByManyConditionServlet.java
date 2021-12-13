package com.servlet.admin.products;

import com.validate.domain.Product;
import com.validate.service.ProductService;
import com.validate.service.ProductServiceImpl;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 后台系统
 * 根据条件查询商品信息的servlet
 */
@WebServlet("/com.servlet.admin.products/FindProductByManyConditionServlet")
public class FindProductByManyConditionServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //指定request字符集防止中文乱码
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        //1.获取表单数据
        String id = request.getParameter("id"); // 商品id
        String name = request.getParameter("name"); // 商品名称
        String category = request.getParameter("category"); // 商品类别
        String minprice = request.getParameter("minprice"); // 最小价格
        String maxprice = request.getParameter("maxprice"); // 最大价格
        // 2.创建ProductService对象
        ProductService service = new ProductServiceImpl();
        // 3.调用service层用于条件查询的方法
        List<Product> ps = service.findProductByManyCondition(id, name,
                category, minprice, maxprice);
        // 4.将条件查询的结果放进request域中
        request.setAttribute("ps", ps);

        request.setAttribute("id",id);
        request.setAttribute("name",name);
        request.setAttribute("category",category);
        request.setAttribute("minprice",minprice);
        request.setAttribute("maxprice",maxprice);
        // 5.请求重定向到商品管理首页list.jsp页面
        request.getRequestDispatcher("/WEB-INF/admin/products/list.jsp").forward(
                request, response);
    }
}