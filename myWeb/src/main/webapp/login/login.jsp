<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page isELIgnored="false" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>爆gan大作业</title>
    <link rel="stylesheet" type="text/css" href="<%=path%>/css/login.css">
    <link rel="stylesheet" href="https://cdn.staticfile.org/font-awesome/4.7.0/css/font-awesome.css">
    <script src="http://libs.cdnjs.net/jquery/2.2.1/jquery.js" rel="external nofollow"  rel="external nofollow" ></script>
    <script src="http://libs.cdnjs.net/jquery-validate/1.14.0/jquery.validate.min.js" rel="external nofollow"  rel="external nofollow" ></script>
    <script src="<%=path%>/js/login.js"></script>
    <style>
        .error{color:RED}
    </style>

</head>

<body>
<header class="header">
    <div></div>
</header>


<div id="bigBox">
    <h1>LOGIN</h1>

    <div class="inputBox">
        <form action="<%=path%>/com.servlet.common/LoginServlet" method="post"  id="login">
            <div class="inputText">
                <i class="fa fa-user-circle" style="color: whitesmoke;"></i>
                <input type="text" placeholder="邮箱" name="email" id="email" value="${emailInput}"
                       required/>
            </div>
            <div class="inputText">
                <i class="fa fa-key" style="color: whitesmoke;"></i>
                <input type="password" placeholder="密码" name="password" id="pwd" required/>
            </div>
            <input type="submit" class="inputButton" value="登录" id="submit"/><br>
            <div style="margin: 15px auto">
                <a href="<%=path%>/login/register.jsp">免费注册</a>
            </div>
        </form>
    </div>
</div>


</body>
</html>