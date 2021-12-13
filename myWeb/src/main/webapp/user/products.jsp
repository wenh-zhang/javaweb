<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>爆gan大作业</title>
    <link rel="stylesheet" type="text/css" href="./css/user.css">
    <script src="webjars/jquery/3.3.1-2/jquery.min.js"></script>
    <script src="<%=path%>/js/common.js"></script>
    <script>
        window.onload=function (){
                var i=0;
                switch ('${category}'){
                    case "文学":i=1;break;
                    case "生活":i=2;break;
                    case "计算机":i=3;break;
                    case "外语":i=4;break;
                    case "经营":i=5;break;
                    case "励志":i=6;break;
                    case "社科":i=7;break;
                    case "学术":i=8;break;
                    case "少儿":i=9;break;
                    case "艺术":i=10;break;
                    case "原版":i=11;break;
                    case "科技":i=12;break;
                    case "考试":i=13;break;
                    case "生活百科":i=14;break;
                }
                document.getElementsByClassName("sidebar__menu--item")[i].classList.add('is-active');
            if(${user!=null}){
                document.getElementById("link").textContent="注销";
            }
        }
        //注销账户
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
</head>

<body>
<jsp:include page="header.jsp"></jsp:include>
<jsp:include page="sidebar.jsp"></jsp:include>
<main class="main">
<div id="productBox">

<c:forEach items="${ps}" var="p">
<div class="product"style="display: inline-block" onclick="userServlet('<%=path%>/com.servlet.common/ViewProductServlet?id=${p.id}')">
    <div>
    <img style="border-style: solid;border-color: whitesmoke" src="${pageContext.request.contextPath}${p.imgurl}" height="100%" width="80%">
    </div>
    <div>
    <a>${p.name}</a>
    <br>
    <br>
    <a>剩余:${p.pnum}</a>
    <br>
    <a style="color: red;position:absolute;bottom: 0px;">￥${p.price}</a>
    </div>
</div>
</c:forEach>

</div>
</main>
</body>
</html>
