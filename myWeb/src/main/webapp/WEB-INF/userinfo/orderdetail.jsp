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

</head>
<body>
<jsp:include page="../../user/header.jsp"></jsp:include>
<jsp:include page="ordersidebar.jsp"></jsp:include>
<main class="main" id="main">
    <table cellSpacing="1" cellPadding="5" width="100%" align="center"
           style="border: 1px solid #8ba7e3;color: whitesmoke" border="0">
        <tr>
            <td align="center" colSpan="4" height="26">
                <strong>
                    订单详细信息
                </strong>
            </td>
        </tr>
        <tr>
            <td width="18%" align="center">订单编号：</td>
            <td>${order.id}</td>
            <td align="center">所属用户：</td>
            <td>${order.user.username }</td>
        </tr>
        <tr>
            <td align="center">收件人：</td>
            <td>${order.receiverName }</td>
            <td align="center">联系电话：</td>
            <td>${order.receiverPhone }</td>
        </tr>
        <tr>
            <td align="center">送货地址：</td>
            <td>${order.receiverAddress}</td>
            <td align="center">总价：</td>
            <td>${order.money }</td>
        </tr>
        <tr>
            <td align="center">下单时间：</td>
            <td colSpan="15">${order.ordertime}</td>
        </tr>
        <TR>
            <TD align="center">商品信息</TD>
            <TD colSpan="3">
                <table cellspacing="0" cellpadding="1" rules="all"  border="1" id="DataGrid1"
                       style="BORDER-RIGHT: gray 1px solid;color: whitesmoke; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; WORD-WRAP: break-word">
                    <tr style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px;">
                        <td align="center" width="7%">序号</td>
                        <td width="8%" align="center">商品</td>
                        <td align="center" width="18%">商品编号</td>
                        <td align="center" width="10%">商品名称</td>
                        <td align="center" width="10%">商品价格</td>
                        <td width="7%" align="center">购买数量</td>
                        <td width="7%" align="center">商品类别</td>
                        <td width="31%" align="center">商品描述</td>
                    </tr>
                    <c:forEach items="${order.orderItems}" var="item" varStatus="vs">
                        <tr style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px;">
                            <td align="center" width="7%">${vs.count }</td>
                            <td width="8%" align="center">
                                <img src="${pageContext.request.contextPath}${item.p.imgurl}" width="50" height="50">
                            </td>
                            <td align="center" width="18%">${item.p.id }</td>
                            <td align="center" width="10%">${item.p.name }</td>
                            <td align="center" width="10%">${item.p.price }</td>
                            <td width="7%" align="center">${item.buynum }</td>
                            <td width="7%" align="center">${item.p.category }</td>
                            <td width="31%" align="center">${item.p.description}</td>
                        </tr>
                    </c:forEach>
                </table>
            </TD>
        </TR>
        <TR>
            <td align="center" colSpan="4" >
            </td>
        </TR>
        <TR>
            <td class="ta_01" style="WIDTH: 100%" align="center"  colSpan="4">
                <FONT face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT>
                <INPUT type="button" onclick="history.go(-1)" value="返回"/>
                <span id="Label1"></span>
            </td>
        </TR>
    </table>
</main>
</body>
</html>
