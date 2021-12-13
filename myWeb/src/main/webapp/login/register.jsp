<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ page import="com.validate.domain.User" %>
<%@ page isELIgnored="false" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>爆gan大作业</title>
    <link rel="stylesheet" type="text/css" href="<%=path%>/css/register.css"/>
    <link rel="stylesheet" href="https://cdn.staticfile.org/font-awesome/4.7.0/css/font-awesome.css">
    <script src="http://libs.cdnjs.net/jquery/2.2.1/jquery.js" rel="external nofollow"  rel="external nofollow" ></script>
    <script src="http://libs.cdnjs.net/jquery-validate/1.14.0/jquery.validate.min.js" rel="external nofollow"  rel="external nofollow" ></script>
    <script src="<%=path%>/js/register.js"></script>
    <style>
        .error{color:RED}
    </style>
    <script type="text/javascript">
        window.onload = function()
        {

            if(${user!=null}){
                document.getElementById("link").textContent="注销";
            }
        }

        function link_click() {
            if (${user!=null}) {
                var res = confirm("确定要注销账号吗？");
                if (!res) return false;
                else{

                    document.getElementById("link").href="<%=path%>/com.servlet.common/logoutServlet"
                }
            }
        }
    </script>
    <script>

    </script>
</head>
<body>
<header class="header">
    <div id="linkBox">
        <c:if test="${user!=null}">
            <a>${user.username}
            </a>
        </c:if>
        <a href="<%=path%>/login/login.jsp"  id="link" onclick="link_click()">登录
        </a>
    </div>
</header>
<div id="bigBox">
    <h1>欢迎注册</h1>
    <div class="inputBox">
        <form action="<%=path%>/com.servlet.common/RegisterServlet" method="post" id="register">
            <div class="inputText">
                <i class="fa fa-user-circle" style="color: whitesmoke;"></i>
                <input type="text" placeholder="用户名:3-16位" name="username" id="uname" />
            </div>
            <div class="inputText">
                <i class="fa fa-key" style="color: whitesmoke;"></i>
                <input type="password" placeholder="密码:8-16位" name="password" id="pwd"/>
            </div>
            <div class="inputText">
                <i class="fa fa-lock" style="color: lightgoldenrodyellow;"></i>
                <input type="password" placeholder="确认密码" name="password_confirm" id="pwd2"/>
            </div>
            <div class="inputText">
                <i class="fa fa-inbox" style="color: whitesmoke;"></i>
                <input type="text" placeholder="邮箱" name="email" id="email"/>
            </div>
            <input type="submit" class="inputButton" value="注册" id="submit"/><br>
        </form>
    </div>
</div>
</body>
</html>