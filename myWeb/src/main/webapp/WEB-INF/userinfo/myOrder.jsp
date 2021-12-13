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
    <script>
        window.onload=function (){
            document.getElementsByClassName("sidebar__menu--item")[${orderState}].classList.add('is-active');
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
        function del_click(oid) {
                var res = confirm("确定删除未支付订单吗？");
                if (!res) return false;
                else{

                    document.location.href="<%=path%>/com.servlet.user/DelOrderServlet?id="+oid+"&orderState=${orderState}";
                }
        }
    </script>
    <style>
        .error{color:RED}
    </style>
</head>

<body>
<jsp:include page="../../user/header.jsp"></jsp:include>
<jsp:include page="ordersidebar.jsp"></jsp:include>
<main class="main">
    <div id="cartBox">
        <table cellspacing="0" cellpadding="1" rules="all"
               bordercolor="gray" border="1" id="DataGrid1"
               style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse;  WORD-WRAP: break-word">
            <tr style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px;">
                <td align="center" width="20%">订单编号</td>
                <td align="center" width="10%">收件人</td>
                <td align="center" width="22%">地址</td>
                <td align="center" width="10%">联系电话</td>
                <td width="8%" align="center">总价</td>
                <td width="8%" align="center">订单状态</td>
                <td width="7%" align="center">查看</td>
                <td width="7%" align="center">删除</td>
            </tr>
            <c:forEach items="${orders}" var="order">
                <tr>
                    <td style="CURSOR: hand; HEIGHT: 22px" align="center" width="20%">${order.id}</td>
                    <td style="CURSOR: hand; HEIGHT: 22px" align="center" width="10%">${order.receiverName}</td>
                    <td style="CURSOR: hand; HEIGHT: 22px" align="center" width="22%">${order.receiverAddress }</td>
                    <td style="CURSOR: hand; HEIGHT: 22px" align="center" width="10%">${order.receiverPhone }</td>
                    <td style="CURSOR: hand; HEIGHT: 22px" align="center">${order.money}</td>
                    <td width="8%" align="center">
                            ${order.paystate==0?"未支付":"已支付"}
                        <c:if test="${order.paystate==0}">
                            <a href="<%=path%>/com.servlet.user/ToPayServlet?oid=${order.id}" style="color: red">去支付</a>
                        </c:if>
                    </td>
                    <td align="center" width="7%" style="HEIGHT: 22px">
                        <a href="<%=path%>/com.servlet.user/ViewOrderServlet?id=${order.id}">
                            <img src="${pageContext.request.contextPath}/img/order_view.gif" border="0" style="CURSOR: hand">
                        </a>
                    </td>
                    <td align="center" style="HEIGHT: 22px">
                        <!--  未支付 -->
                        <c:if test="${order.paystate==0 }">
                            <a onclick="del_click('${order.id}')">
                                <img src="${pageContext.request.contextPath}/img/i_del.gif" width="16" height="16" border="0" style="CURSOR: hand">
                            </a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</main>
</body>
</html>
