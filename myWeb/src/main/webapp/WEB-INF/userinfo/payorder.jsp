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
    <script src="http://libs.cdnjs.net/jquery/2.2.1/jquery.js" rel="external nofollow"  rel="external nofollow" ></script>
    <script src="http://libs.cdnjs.net/jquery-validate/1.14.0/jquery.validate.min.js" rel="external nofollow"  rel="external nofollow" ></script>
    <script src="<%=path%>/js/common.js"></script>
    <script src="<%=path%>/js/order.js"></script>
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

                    document.getElementById("link").href="<%=path%>/com.servlet.common/logoutServlet"
                }
            }
        }
    </script>
    <style>
        .error{color:RED}
    </style>
</head>

<body>
<jsp:include page="../../user/header.jsp"></jsp:include>
<jsp:include page="../../user/sidebar.jsp"></jsp:include>
<main class="main">
    <div id="cartBox">
        <form  id="orderForm" style="width: 100%;height: 100%" action="<%=path%>/com.servlet.user/ToPayServlet?oid=${order.id}" method="post">

            <div class="receiverinfo">
                <div>
                    <input name="receiverName" id="receiverName" placeholder="${order.receiverName}" disabled>
                    <input name="receiverPhone" id="receiverPhone" placeholder="${order.receiverPhone}" disabled>
                    <input name="receiverAddress" id="receiverAddress" placeholder="${order.receiverAddress}" disabled>

                </div>
            </div>
            <div class="cartBox1" style="height: 50%">
                <table class="cart" border="1" cellspacing="0" cellpadding="0"
                       style="text-align:center;BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse;  WORD-WRAP: break-word";>
                    <tr>
                        <td style="width: 10%">序号</td>
                        <td style="width: 15%">编号</td>
                        <td style="width: 15%">封面</td>
                        <td style="width: 15%">书籍名称</td>
                        <td style="width: 10%">单价(元)</td>
                        <td style="width: 15%">数量</td>
                        <td style="width: 12%">小计(元)</td>

                    </tr>
                    <c:forEach items="${order.orderItems}" var="oo" varStatus="vs">
                        <tr>
                            <td style="width: 10%">${vs.count}</td>
                            <td style="width: 15%">${oo.p.id}</td>
                            <td style="width: 10%"><img style="margin: 0px auto;width: auto;height: 60px" src="${pageContext.request.contextPath}${oo.p.imgurl}"  ></td>
                            <td style="width: 15%"><a href="<%=path%>/com.servlet.common/ViewProductServlet?id=${oo.p.id}">${oo.p.name}</a></td>
                            <td style="width: 10%">${oo.p.price}</td>
                            <td style="width: 15%">${oo.buynum}</td>
                            <td style="width: 12%">${oo.p.price*oo.buynum}</td>
                        </tr>
                    </c:forEach>

                </table>
            </div>
            <div class="cartBox2">
                <div style="height: 80%;width: 100%;padding-left: 50%;font-size: 20px">
                    <div style="width: 100%;display: inline-block">
                        <a>商品总价（不含运费）: </a><a type="text" id="all">${order.money}</a><a> 元</a>
                    </div>
                    <div style="padding-left: 70%">
                        <button style="background-image:  linear-gradient(-20deg, #ee7550 0%, #f5593d 100%);font-size: 20px;" type="submit"
                        >支付</button>
                    </div>
                </div>
            </div>
        </form>


    </div>
</main>
</body>
</html>
