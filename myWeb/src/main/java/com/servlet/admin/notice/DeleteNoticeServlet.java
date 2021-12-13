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
 *
 *	后台删除公告的servlet
 */
@WebServlet("/com.servlet.admin.notice/DeleteNoticeServlet")
public class DeleteNoticeServlet extends HttpServlet{

    private static final long serialVersionUID = 1L;
    public DeleteNoticeServlet(){super();}

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
        NoticeService service = new NoticeServiceImpl();
        String id=request.getParameter("id");
        // 2.调用service层用于删除公告的方法
        service.deleteNotice(id);
        // 3.调用Service层对象的getAllNotices()方法查询公告列表
        List<Notice> notices = service.getAllNotices();
        //将查询到的公告信息添加到request作用域
        request.setAttribute("notices", notices);
        // 4.重定向到notice.jsp页面
        request.getRequestDispatcher("/WEB-INF/admin/notice/notice.jsp").forward(
                request, response);
        return;
    }
}
