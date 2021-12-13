package com.servlet.user;

import com.validate.domain.Product;
import com.validate.domain.User;
import com.validate.exception.FindProductByIdException;
import com.validate.service.CartService;
import com.validate.service.CartServiceImpl;
import com.validate.service.ProductService;
import com.validate.service.ProductServiceImpl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 后台系统
 * 客户进行结算并进入提交订单页面的servlet
 */
@WebServlet("/com.servlet.user/SettleServlet")
public class SettleServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public SettleServlet() {
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


        String[] pids = (String[]) request.getParameterValues("pid"); // 商品id
        String[] nums =(String[]) request.getParameterValues("num");

        Map<Product, Integer> waitorder=new HashMap<Product, Integer>();
        // 1.创建service层的对象
        ProductService service=new ProductServiceImpl();

        try {
            double sum=0;
            for(int i=0;i<pids.length;i++){
                Product p=service.findProductById(pids[i]);
                waitorder.put(p,Integer.parseInt(nums[i]));
                sum+=p.getPrice()*Integer.parseInt(nums[i]);
            }
            //将等待提交的订单信息（商品，数量）键值对组添加到request作用域
            request.getSession().setAttribute("waitorder",waitorder);
            request.getSession().setAttribute("sum",sum);
            request.getRequestDispatcher("/WEB-INF/userinfo/commitorder.jsp").forward(
                    request, response);
        }catch (FindProductByIdException e){
            e.printStackTrace();
        }
    }
}