package com.servlet.admin.notice;

import com.validate.domain.Notice;
import com.validate.service.NoticeService;
import com.validate.service.NoticeServiceImpl;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 后台系统
 * 进入编辑公告信息页面的servlet
 */
@WebServlet("/com.servlet.admin.notice/EditNoticeServlet")
public class EditNoticeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public EditNoticeServlet() {
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
        NoticeService service = new NoticeServiceImpl();
        String id=request.getParameter("id");
        // 2.调用service层用于查询公告的方法
        Notice n= service.findNoticeById(id);
        // 3.将查询出的所有商品放进request域中
        request.setAttribute("n", n);
        // 4.重定向到edit.jsp页面
        request.getRequestDispatcher("/WEB-INF/admin/notice/edit.jsp").forward(
                request, response);
        return;
    }
}
