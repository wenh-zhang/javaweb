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
            document.getElementsByClassName("sidebar__menu--item")[1].classList.add('is-active');
        }
    </script>

</head>
<body>
<jsp:include page="../header.jsp"></jsp:include>
<jsp:include page="../sidebar.jsp"></jsp:include>
<main class="main" id="main">
    <form id="Form1" name="Form1" style="padding-left: 3rem;padding-right: 3rem;padding-bottom: 3rem"
          action="<%=path%>/com.servlet.admin.sell/DownloadServlet" method="post">
        <table cellSpacing="1" cellPadding="0" width="100%" align="center" bgColor="#f8f8ff" border="0">
            <tbody>
            <tr>
                <td align="center" bgColor="#afd1f3">
                    <strong>查 询 条 件</strong>
                </td>
            </tr>
            <tr>
                <td>
                    <table cellpadding="0" cellspacing="0" border="0" width="100%" style="padding-left: 200px;padding-right: 100px">
                        <tr>
                            <td height="22" align="center">
                                请输入年份
                            </td>
                            <td>
                                <input type="text" name="year" size="15" value="" id="Form1_userName" />
                            </td>
                            <td height="22" align="center">
                                请选择月份
                            </td>
                            <td>
                                <select name="month" id="month">
                                    <option value="0">--选择月份--</option>
                                    <option value="1">一月</option>
                                    <option value="2">二月</option>
                                    <option value="3">三月</option>
                                    <option value="4">四月</option>
                                    <option value="5">五月</option>
                                    <option value="6">六月</option>
                                    <option value="7">七月</option>
                                    <option value="8">八月</option>
                                    <option value="9">九月</option>
                                    <option value="10">十月</option>
                                    <option value="11">十一月</option>
                                    <option value="12">十二月</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td width="100" height="22" align="center">
                            </td>
                            <td>
                                <font face="宋体"> &nbsp;</font>
                            </td>
                            <td align="right">
                                <br /><br />
                            </td>
                            <td align="center">
                                <input type="submit" id="search" name="search" value="下载">
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <input type="reset" name="reset" value="重置"/>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
            </tbody>
        </table>
    </form>
</main>

</body>
</html>
