package com.servlet.admin.notice;

import com.validate.domain.Notice;
import com.validate.service.NoticeService;
import com.validate.service.NoticeServiceImpl;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 后台用于查看所有公告的Servlet
 */
@WebServlet("/com.servlet.admin.notice/ListNoticesServlet")
public class ListNoticesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ListNoticesServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        // 创建Service层对象
        NoticeService service = new NoticeServiceImpl();
        // 调用Service层对象的findAllOrder()方法查询订单列表
        List<Notice> notices = service.getAllNotices();
        //将查询到的公告信息添加到request作用域
        request.setAttribute("notices", notices);

        request.getRequestDispatcher("/WEB-INF/admin/notice/notice.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 设置请求和响应编码
        doGet(request,response);
    }

}
