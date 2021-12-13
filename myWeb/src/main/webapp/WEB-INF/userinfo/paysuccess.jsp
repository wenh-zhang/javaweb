<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>支付结果</title>
    <script>
        var interval;
        window.onload = function () {
            interval = window.setInterval("changeSecond()", 1000);
        };

        function changeSecond() {
            var second = document.getElementById("second");
            var svalue = second.innerHTML;
            svalue = svalue - 1;
            if (svalue == 0) {
                window.clearInterval(interval);
                // 下列两行代码用于获取项目名，例如：bookstore
                // var pathName = window.location.pathname.substring(1);
                // var webName = pathName == '' ? '' : pathName.substring(0, pathName.indexOf('/'));
                // // 拼接访问路径名，例如：http://localhost:8080/bookstore/index.jsp
                location.href = "${pageContext.request.contextPath }/user/index.jsp";
                return;
            }
            second.innerHTML = svalue;
        }

    </script>
</head>

<body>
<div id="divcontent">

    <h1>支付成功</h1>
    <a href="${pageContext.request.contextPath }/user/index.jsp">
        <span id="second">5</span>秒后自动为您转跳回首页
    </a>

</div>
</body>
</html>
