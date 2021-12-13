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
 *	后台修改公告后提交的servlet
 */
@WebServlet("/com.servlet.admin.notice/EditSubmitNoticeServlet")
public class EditSubmitNoticeServlet extends HttpServlet{

    private static final long serialVersionUID = 1L;
    public EditSubmitNoticeServlet(){super();}

    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        this.doPost(req, resp);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        //指定request字符集防止中文乱码
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        NoticeService nService = new NoticeServiceImpl();
        Notice bean = new Notice();
        //获取表单参数
        int id = Integer.parseInt(req.getParameter("id"));
        String title = req.getParameter("title");
        String details = req.getParameter("details");

        //将当前时间设为添加公告的时间
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        bean.setId(id);
        bean.setTitle(title);
        bean.setDetails(details);
        bean.setN_time(timestamp);

        //调用dao层方法
        nService.updateNotice(bean);

        List<Notice> notices=nService.getAllNotices();
        req.setAttribute("notices",notices);
        req.getRequestDispatcher("/WEB-INF/admin/notice/notice.jsp").forward(req, resp);
    }
}
