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
            document.getElementsByClassName("sidebar__menu--item")[0].classList.add('is-active');
            setProductCategory('${p.category}');
        }
        //设置类别的默认值
        function setProductCategory(t) {
            var category = document.getElementById("category");

            var ops = category.options;
            for ( var i = 0; i < ops.length; i++) {

                if (ops[i].value == t) {
                    ops[i].selected = true;
                    return;
                }
            }

        };
    </script>
</head>
<body>
<jsp:include page="../header.jsp"></jsp:include>
<jsp:include page="../sidebar.jsp"></jsp:include>

<main class="main">
    <form id="userAction_save_do" name="Form1"
          action="<%=path%>/com.servlet.admin.products/EditSubmitProductServlet" method="post"
          enctype="multipart/form-data">
        <input type="hidden" name="id" value="${p.id}" /> &nbsp;
        <table cellSpacing="1" cellPadding="5" width="100%" align="center"
               bgColor="#f8f8ff" style="border: 1px solid #8ba7e3" border="0">
            <tr>
                <td  align="center" bgColor="#afd1f3" colSpan="4" height="26">
                    <strong>编辑商品</strong>
                </td>
            </tr>
            <tr>
                <td align="center" >商品名称：</td>
                <td >
                    <input type="text" name="name"  value="${p.name }" />
                </td>
                <td align="center"  >商品价格：</td>
                <td >
                    <input type="text" name="price" value="${p.price }" />
                </td>
            </tr>
            <tr>
                <td align="center" >商品数量：</td>
                <td>
                    <input type="text" name="pnum" value="${p.pnum}" />
                </td>
                <td align="center" >商品类别：</td>
                <td class="ta_01" >
                    <select name="category" id="category">
                        <option value="">--选择商品类加--</option>
                        <option value="文学">文学</option>
                        <option value="生活">生活</option>
                        <option value="计算机">计算机</option>
                        <option value="外语">外语</option>
                        <option value="经营">经营</option>
                        <option value="励志">励志</option>
                        <option value="社科">社科</option>
                        <option value="学术">学术</option>
                        <option value="少儿">少儿</option>
                        <option value="艺术">艺术</option>
                        <option value="原版">原版</option>
                        <option value="科技">科技</option>
                        <option value="考试">考试</option>
                        <option value="生活百科">生活百科</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td align="center" >商品图片：</td>
                <td colSpan="3">
                    <input type="file" name="upload" size="30"  value="" /></td>
            </tr>
            <TR>
                <TD  align="center" >商品描述：</TD>
                <TD  colSpan="3">
                    <textarea name="description" cols="30" rows="3" style="WIDTH: 96%">${p.description}</textarea>
                </TD>
            </TR>
            <tr>
                <td style="WIDTH: 100%" align="center"  colSpan="4">
                    <input type="submit" value="确定">
                    <FONT face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT>
                    <input type="reset" value="重置"  />
                    <FONT face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT>
                    <INPUT  type="button" onclick="history.go(-1)" value="返回" />
                    <span id="Label1"> </span>
                </td>
            </tr>
        </table>
    </form>
</main>
</body>
</html>
