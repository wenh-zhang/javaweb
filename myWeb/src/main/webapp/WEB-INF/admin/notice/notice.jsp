<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>爆gan大作业</title>
    <link rel="stylesheet" type="text/css" href="<%=path%>/css/admin.css">
    <script src=""<%=path%>/webjars/jquery/3.3.1-2/jquery.min.js"></script>
    <script src="<%=path%>/js/common.js"></script>
    <script>
        window.onload = function()
        {
            document.getElementsByClassName("sidebar__menu--item")[3].classList.add('is-active');
        }
        function addNotice() {
            window.location.href = "<%=path%>/com.servlet.common/jump?address=/WEB-INF/admin/notice/add.jsp";
        }
        //删除公告
        function n_del() {
            var msg = "您确定要删除该公告吗？";
            if (confirm(msg)==true){
                return true;
            }else{
                return false;
            }
        }
    </script>

</head>
<body>
<jsp:include page="../header.jsp"></jsp:include>
<jsp:include page="../sidebar.jsp"></jsp:include>
<main class="main" id="main">
    <form id="Form1" name="Form1" style="padding-left: 3rem;padding-right: 3rem;padding-bottom: 3rem"
          action="" method="post">
        <table cellSpacing="1" cellPadding="0" width="100%" align="center" bgColor="#f8f8ff" border="0">
            <tbody>
            <tr>
                <td align="center" bgColor="#afd1f3">
                    <strong>公 告 列 表</strong>
                </td>
            </tr>
            <tr>
                <td align="right" style="padding-right: 200px">
                    <button type="button" id="add" name="add" value="添加"  onclick="addNotice()">
                        添加
                    </button>
                </td>
            </tr>
            <tr>
                <td align="center" >
                    <table cellspacing="0" cellpadding="1" rules="all"
                           bordercolor="gray" border="1" id="DataGrid1"
                           style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
                        <tr style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">
                            <td align="center" width="12%">公告编号</td>
                            <td align="center" width="12%">公告标题</td>
                            <td align="center" width="24%">公告内容</td>
                            <td align="center" width="8%">时间</td>
                            <td width="8%" align="center">编辑</td>
                            <td width="8%" align="center">删除</td>
                        </tr>
                        <c:forEach items="${notices}" var="n">
                            <tr onmouseover="this.style.backgroundColor = 'white'" onmouseout="this.style.backgroundColor = '#F5FAFE';">
                                <td style="CURSOR: hand; HEIGHT: 22px" align="center"
                                    width="23">${n.id }</td>
                                <td style="CURSOR: hand; HEIGHT: 22px" align="center"
                                    width="18%">${n.title }</td>
                                <td style="CURSOR: hand; HEIGHT: 22px" align="center"
                                    width="8%">${n.details }</td>
                                <td style="CURSOR: hand; HEIGHT: 22px" align="center"
                                    width="8%">${n.n_time }</td>
                                <td align="center" style="HEIGHT: 22px" width="7%">
                                    <a href="<%=path%>/com.servlet.admin.notice/EditNoticeServlet?id=${n.id}">
                                        <img src="${pageContext.request.contextPath}/img/i_edit.gif" border="0" style="CURSOR: hand">
                                    </a>
                                </td>
                                <td align="center" style="HEIGHT: 22px" width="7%">
                                    <a href="<%=path%>/com.servlet.admin.notice/DeleteNoticeServlet?id=${n.id}" onclick="javascript:return n_del()">
                                        <img src="${pageContext.request.contextPath}/img/i_del.gif" width="16" height="16" border="0" style="CURSOR: hand">
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </td>
            </tr>
            </TBODY>
        </table>
    </form>
</main>

</body>
</html>
