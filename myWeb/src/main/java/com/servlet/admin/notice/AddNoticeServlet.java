package com.servlet.admin.notice;

import com.validate.domain.Notice;
import com.validate.service.NoticeService;
import com.validate.service.NoticeServiceImpl;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *	后台添加公告的servlet
 */
@WebServlet("/com.servlet.admin.notice/AddNoticeServlet")
public class AddNoticeServlet extends HttpServlet{

    private static final long serialVersionUID = 1L;
    public AddNoticeServlet(){super();}

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //指定request字符集防止中文乱码
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        NoticeService nService = new NoticeServiceImpl();
        Notice bean = new Notice();
        //获取表单参数
        String title = request.getParameter("title");
        String details = request.getParameter("details");
        //将当前时间设为添加公告的时间
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        bean.setTitle(title);
        bean.setDetails(details);
        bean.setN_time(timestamp);
        //调用addNotice方法
        nService.addNotice(bean);

        List<Notice> notices=nService.getAllNotices();
        request.setAttribute("notices",notices);
        request.getRequestDispatcher("/WEB-INF/admin/notice/notice.jsp").forward(request, response);
    }
}
