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

    </script>

</head>
<body>
<jsp:include page="../header.jsp"></jsp:include>
<jsp:include page="../sidebar.jsp"></jsp:include>
<main class="main" id="main">
    <form id="userAction_save_do" name="Form1" action="<%=path%>/com.servlet.admin.notice/AddNoticeServlet" method="post">
        <table cellSpacing="1" cellPadding="5" width="100%" align="center"
               bgColor="#f8f8ff" style="border: 1px solid #8ba7e3" border="0">
            <tr>
                <td align="center" bgColor="#afd1f3" colSpan="4"
                    height="26"><strong>添加公告</strong>
                </td>
            </tr>
            <tr>
                <td align="center" >公告标题：</td>
                <td  colSpan="3">
                    <input type="text" name="title"  maxlength="10"/>
                </td>
            </tr>
            <tr>
                <td align="center" >公告内容：</td>
                <td colSpan="3">
                    <textarea name="details" cols="30" rows="3" style="WIDTH: 96%"></textarea>
                </td>
            </tr>

            <tr>
                <td style="WIDTH: 100%" align="center" colSpan="4">
                    <input type="submit" value="确定" />
                    <font face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>
                    <input type="reset" value="重置" >
                    <font face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>
                    <input type="button" onclick="history.go(-1)" value="返回" />
                    <span id="Label1"></span>
                </td>
            </tr>
        </table>
    </form>
</main>
</body>
</html>
