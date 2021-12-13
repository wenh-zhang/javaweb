package com.servlet.common;

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
 * 前台系统
 * 用于展示最近公告的Servlet
 */
@WebServlet("/com.servlet.common/ShowNoticeServlet")
public class ShowNoticeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ShowNoticeServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        // 创建Service层对象
        NoticeService service = new NoticeServiceImpl();
        // 调用Service层对象的getRecentNotice()方法查询公告列表
        List<Notice> notices = service.getRecentNotice();
        //将查询到的公告信息添加到request作用域
        request.setAttribute("notices", notices);

        request.getRequestDispatcher("/user/notice.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 设置请求和响应编码
        doGet(request,response);
    }

}