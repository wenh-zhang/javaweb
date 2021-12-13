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
            document.getElementsByClassName("sidebar__menu--item")[2].classList.add('is-active');
        }
        //删除订单
        function o_del() {
            var msg = "您确定要删除该订单吗？";
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
          action="<%=path%>/com.servlet.admin.orders/FindOrdersByManyConditionServlet" method="post">
        <table cellSpacing="1" cellPadding="0" width="100%" align="center" bgColor="#f8f8ff" border="0">
            <TBODY>
            <tr>
                <td class="ta_01" align="center" bgColor="#afd1f3">
                    <strong>查 询 条 件</strong>
                </td>
            </tr>
            <tr>
                <td>
                    <table cellpadding="0" cellspacing="0" border="0" width="100%" style="padding-left: 60px">
                        <tr>
                            <td height="22" align="center">
                                订单编号</td>
                            <td>
                                <input type="text" name="id" size="15" value="" id="Form1_orderId" />
                            </td>
                            <td height="22" align="center">
                                收件人：</td>
                            <td class="ta_01">
                                <input type="text" name="receiverName" size="15" value="" id="Form1_userName"/>
                            </td>
                        </tr>
                        <tr>
                            <td width="100" height="22" align="center"></td>
                            <td>
                                <font face="宋体"> &nbsp;</font>
                            </td>
                            <td align="right">
                                <br /><br />
                            </td>
                            <td align="right" style="padding-right: 80px">
                                <button type="submit" id="search" name="search" value="查询">
                                    查询
                                </button> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <input type="reset" name="reset" value="重置" />
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
            <tr>
                <td align="center" bgColor="#afd1f3">
                    <strong>订 单 列 表</strong>
                </td>
            </tr>
            <tr>
                <td align="right"></td>
            </tr>
            <tr>
                <td align="center" bgColor="#f8f8ff">
                    <table cellspacing="0" cellpadding="1" rules="all"
                           bordercolor="gray" border="1" id="DataGrid1"
                           style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
                        <tr style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">
                            <td align="center" width="20%">订单编号</td>
                            <td align="center" width="10%">收件人</td>
                            <td align="center" width="22%">地址</td>
                            <td align="center" width="10%">联系电话</td>
                            <td width="8%" align="center">总价</td>
                            <td width="8%" align="center">所属用户</td>
                            <td width="8%" align="center">订单状态</td>
                            <td width="7%" align="center">查看</td>
                            <td width="7%" align="center">删除</td>
                        </tr>
                        <c:forEach items="${orders}" var="order">
                            <tr onmouseover="this.style.backgroundColor = 'white'"
                                onmouseout="this.style.backgroundColor = '#F5FAFE';">
                                <td style="CURSOR: hand; HEIGHT: 22px" align="center" width="20%">${order.id}</td>
                                <td style="CURSOR: hand; HEIGHT: 22px" align="center" width="10%">${order.receiverName}</td>
                                <td style="CURSOR: hand; HEIGHT: 22px" align="center" width="22%">${order.receiverAddress }</td>
                                <td style="CURSOR: hand; HEIGHT: 22px" align="center" width="10%">${order.receiverPhone }</td>
                                <td style="CURSOR: hand; HEIGHT: 22px" align="center">${order.money}</td>
                                <td width="8%" align="center">${order.user.username}</td>
                                <td width="8%" align="center">${order.paystate==0?"未支付":"已支付"}</td>
                                <td align="center" width="7%" style="HEIGHT: 22px">
                                    <a href="<%=path%>/com.servlet.admin.orders/ViewOrderServlet?id=${order.id}">
                                        <img src="${pageContext.request.contextPath}/img/order_view.gif" border="0" style="CURSOR: hand">
                                    </a>
                                </td>
                                <td align="center" style="HEIGHT: 22px">
                                    <!--  已支付，弹出提示框由操作人确认 -->
                                    <c:if test="${order.paystate!=0 }">
                                        <a href="<%=path%>/com.servlet.admin.orders/DeleteOrderServlet?id=${order.id}" onclick="javascript:return o_del()">
                                            <img src="${pageContext.request.contextPath}/img/i_del.gif" width="16" height="16" border="0" style="CURSOR: hand">
                                        </a>
                                    </c:if>
                                    <!--  未支付 ，不能删除-->
                                    <c:if test="${order.paystate==0 }">
                                        <a href="javascript:alert('不能删除未支付订单')">
                                            <img src="${pageContext.request.contextPath}/img/i_del.gif" width="16" height="16" border="0" style="CURSOR: hand">
                                        </a>
                                    </c:if>
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
