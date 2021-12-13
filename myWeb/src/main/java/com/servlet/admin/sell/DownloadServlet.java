package com.servlet.admin.sell;

import com.validate.service.ProductService;
import com.validate.service.ProductServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 后台系统
 * 进入下载销售信息页面的servlet
 */
@WebServlet("/com.servlet.admin.sell/DownloadServlet")
public class DownloadServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String year = request.getParameter("year");
        String month = request.getParameter("month");
        ProductService service = new ProductServiceImpl();
        List<Object[]> ps = service.download(year,month);
        String fileName=year+"年"+month+"月销售榜单.csv";
        response.setContentType(this.getServletContext().getMimeType(fileName));
        response.setHeader("Content-Disposition", "attachement;filename="+new String(fileName.getBytes("GBK"),"iso8859-1"));
        response.setCharacterEncoding("gbk");
        PrintWriter out = response.getWriter();
        out.println("商品名称,销售数量");
        for (int i = 0; i < ps.size(); i++) {
            Object[] arr=ps.get(i);
            out.println(arr[0]+","+arr[1]);
        }
        out.flush();
        out.close();
    }
}