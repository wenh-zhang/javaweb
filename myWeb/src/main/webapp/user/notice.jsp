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

                    document.getElementById("link").href="<%=path%>/com.servlet.user/logoutServlet"
                }
            }
        }
    </script>
</head>

<body>
<jsp:include page="header.jsp"></jsp:include>
<jsp:include page="sidebar.jsp"></jsp:include>
<main class="main">
    <div id="noticeBox">
        <h2>公告</h2>
        <c:forEach items="${notices}" var="n">
            <div style="border-style: solid;border-radius:5px;border-width: 2px;border-color: #504e4e;width: 100%;height: 60%">
    <textarea style="overflow-y:scroll;width: 100%;height: 15%;background:transparent;border-style:none;color: whitesmoke;font-size: 20px;" disabled >标题: ${n.title}
    </textarea>
                <textarea style="overflow-y:scroll;width: 100%;height: 70%;background:transparent;border-style:none;color: whitesmoke;font-size: 18px;" disabled >正文: ${n.details}
    </textarea>
                <textarea style="overflow-y:scroll;width: 100%;height: 15%;background:transparent;border-style:none;color: #adacac;font-size: 13px;text-align: right" disabled >${n.n_time}
                </textarea>
            </div>
        </c:forEach>
    </div>
</main>
</body>
</html>
