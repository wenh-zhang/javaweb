<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<html>
<head>
    <title>在线支付</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
</head>
<body>
<form action="${pageContext.request.contextPath}/com.servlet.user/PayServlet" method="post">
    订单号：<input type="text"  name="orderid" placeholder="${order.id}" value="${order.id}" readonly>
    支付金额：<input type="text" name="money" placeholder="${order.money}" value="${order.money}" readonly>元
    <div class="divBank">
        <div class="divText">选择网上银行</div>
        <div style="margin-left: 20px;">
            <div style="margin-bottom: 20px;">
                <input id="ICBC-NET-B2C" type="radio" name="yh"
                       value="ICBC-NET-B2C" checked="checked" />
                <img name="ICBC-NET-B2C" align="middle" src="${pageContext.request.contextPath}/img/bank_img/icbc.jpg" />
                <input id="CMBCHINA-NET-B2C" type="radio" name="yh" value="CMBCHINA-NET-B2C" />
                <img name="CMBCHINA-NET-B2C" align="middle" src="${pageContext.request.contextPath}/img/bank_img/cmb.jpg" />
                <input id="ABC-NET-B2C" type="radio" name="yh" value="ABC-NET-B2C" />
                <img name="ABC-NET-B2C" align="middle" src="${pageContext.request.contextPath}/img/bank_img/abc.jpg" />
                <input id="CCB-NET-B2C" type="radio" name="yh" value="CCB-NET-B2C" />
                <img name="CCB-NET-B2C" align="middle" src="${pageContext.request.contextPath}/img/bank_img/ccb.jpg" />
            </div>
            <div style="margin-bottom: 20px;">
                <input id="BCCB-NET-B2C" type="radio" name="yh"
                       value="BCCB-NET-B2C" /> <img name="BCCB-NET-B2C" align="middle"
                                                    src="${pageContext.request.contextPath}/img/bank_img/bj.jpg" /> <input
                    id="BOCO-NET-B2C" type="radio" name="yh" value="BOCO-NET-B2C" />
                <img name="BOCO-NET-B2C" align="middle"
                     src="${pageContext.request.contextPath}/img/bank_img/bcc.jpg" /> <input
                    id="CIB-NET-B2C" type="radio" name="yh" value="CIB-NET-B2C" /> <img
                    name="CIB-NET-B2C" align="middle"
                    src="${pageContext.request.contextPath}/img/bank_img/cib.jpg" /> <input
                    id="NJCB-NET-B2C" type="radio" name="yh" value="NJCB-NET-B2C" />
                <img name="NJCB-NET-B2C" align="middle"
                     src="${pageContext.request.contextPath}/img/bank_img/nanjing.jpg" />
            </div>
            <div style="margin-bottom: 20px;">
                <input id="CMBC-NET-B2C" type="radio" name="yh"
                       value="CMBC-NET-B2C" /> <img name="CMBC-NET-B2C" align="middle"
                                                    src="${pageContext.request.contextPath}/img/bank_img/cmbc.jpg" /> <input
                    id="CEB-NET-B2C" type="radio" name="yh" value="CEB-NET-B2C" /> <img
                    name="CEB-NET-B2C" align="middle"
                    src="${pageContext.request.contextPath}/img/bank_img/guangda.jpg" /> <input
                    id="BOC-NET-B2C" type="radio" name="yh" value="BOC-NET-B2C" /> <img
                    name="BOC-NET-B2C" align="middle"
                    src="${pageContext.request.contextPath}/img/bank_img/bc.jpg" /> <input
                    id="PINGANBANK-NET" type="radio" name="yh" value="PINGANBANK-NET" />
                <img name="PINGANBANK-NET" align="middle"
                     src="${pageContext.request.contextPath}/img/bank_img/pingan.jpg" />
            </div>
            <div style="margin-bottom: 20px;">
                <input id="CBHB-NET-B2C" type="radio" name="yh"
                       value="CBHB-NET-B2C" /> <img name="CBHB-NET-B2C" align="middle"
                                                    src="${pageContext.request.contextPath}/img/bank_img/bh.jpg" /> <input
                    id="HKBEA-NET-B2C" type="radio" name="yh" value="HKBEA-NET-B2C" />
                <img name="HKBEA-NET-B2C" align="middle"
                     src="${pageContext.request.contextPath}/img/bank_img/dy.jpg" /> <input
                    id="NBCB-NET-B2C" type="radio" name="yh" value="NBCB-NET-B2C" />
                <img name="NBCB-NET-B2C" align="middle"
                     src="${pageContext.request.contextPath}/img/bank_img/ningbo.jpg" /> <input
                    id="ECITIC-NET-B2C" type="radio" name="yh" value="ECITIC-NET-B2C" />
                <img name="ECITIC-NET-B2C" align="middle"
                     src="${pageContext.request.contextPath}/img/bank_img/zx.jpg" />
            </div>
            <div style="margin-bottom: 20px;">
                <input id="SDB-NET-B2C" type="radio" name="yh" value="SDB-NET-B2C" />
                <img name="SDB-NET-B2C" align="middle"
                     src="${pageContext.request.contextPath}/img/bank_img/sfz.jpg" />
                <input
                    id="GDB-NET-B2C" type="radio" name="yh" value="GDB-NET-B2C" />
                <img
                    name="GDB-NET-B2C" align="middle"
                    src="${pageContext.request.contextPath}/img/bank_img/gf.jpg" />
                <input
                    id="SHB-NET-B2C" type="radio" name="yh" value="SHB-NET-B2C" />
                <img
                    name="SHB-NET-B2C" align="middle"
                    src="${pageContext.request.contextPath}/img/bank_img/sh.jpg" />
                <input
                    id="SPDB-NET-B2C" type="radio" name="yh" value="SPDB-NET-B2C" />
                <img name="SPDB-NET-B2C" align="middle"
                     src="${pageContext.request.contextPath}/img/bank_img/shpd.jpg" />
            </div>
            <div style="margin-bottom: 20px;">
                <input id="POST-NET-B2C" type="radio" name="yh"
                       value="POST-NET-B2C" /> <img name="POST-NET-B2C" align="middle"
                                                    src="${pageContext.request.contextPath}/img/bank_img/post.jpg" /> <input
                    id="BJRCB-NET-B2C" type="radio" name="yh" value="BJRCB-NET-B2C" />
                <img name="BJRCB-NET-B2C" align="middle"
                     src="${pageContext.request.contextPath}/img/bank_img/beijingnongshang.jpg" /> <input
                    id="HXB-NET-B2C" type="radio" name="yh" value="HXB-NET-B2C" /> <img
                    name="HXB-NET-B2C" align="middle"
                    src="${pageContext.request.contextPath}/img/bank_img/hx.jpg" /> <input id="CZ-NET-B2C"
                                                                             type="radio" name="yh" value="CZ-NET-B2C" /> <img
                    name="CZ-NET-B2C" align="middle"
                    src="${pageContext.request.contextPath}/img/bank_img/zheshang.jpg" />
            </div>
        </div>
        <div style="margin: 40px;">
            <INPUT TYPE="submit" value="确定支付">
        </div>
    </div>
</form>
</body>
</html>
