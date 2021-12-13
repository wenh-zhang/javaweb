<%@ page import="com.validate.domain.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    User user = new User();// 防空处理
    if (session.getAttribute("status") == "online") {
        user = (User) session.getAttribute("user");// 获取 request 域中保存的用户信息
    } else user.setUsername("未登录");
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
            document.getElementsByClassName("sidebar__menu--item")[4].classList.add('is-active');
        }
    </script>

</head>
<body>
<jsp:include page="../header.jsp"></jsp:include>
<jsp:include page="../sidebar.jsp"></jsp:include>
<main class="main" id="main">
    <p>settings</p>
</main>

</body>
</html>
