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
            setProductCategory("${category}")
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
        //添加商品
        function addProduct() {
            window.location.href = "<%=path%>/com.servlet.common/jump?address=/WEB-INF/admin/products/add.jsp";
        }
        //删除商品
        function p_del() {
            var msg = "您确定要删除该商品吗？";
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
          action="<%=path%>/com.servlet.admin.products/FindProductByManyConditionServlet"
          method="post">
        <table cellSpacing="1" cellPadding="0" style="background-color:#f8f8ff;text-align: center;width: 100%; ">
            <TBODY>
            <tr>
                <td class="ta_01" align="center" bgColor="#afd1f3">
                    <strong>查 询 条 件</strong>
                </td>
            </tr>
            <tr>
                <td>
                    <table cellpadding="0" cellspacing="0"  width="100%" style="padding-left: 60px" >
                        <tr>
                            <td height="22" align="center" >商品编号：</td>
                            <td class="ta_01" >
                                <input type="text" name="id" size="15" value="${id}" id="Form1_userName" />
                            </td>
                            <td height="22" align="center"  >类别：</td>
                            <td class="ta_01" >
                                <select name="category" id="category">
                                    <option value="" selected="selected">--选择商品类别--</option>
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
                            <td height="22" align="center"  >商品名称：</td>
                            <td>
                                <input type="text" name="name" size="15" value="${name}" id="Form1_userName1" />
                            </td>
                            <td height="22" align="center">价格区间(元)：</td>
                            <td >
                                <input type="text" name="minprice" size="10" value="${minprice}" />
                                -
                                <input type="text" name="maxprice" size="10" value="${maxprice}" />
                            </td>
                        </tr>
                        <tr>
                            <td width="100" height="22" align="center" ></td>
                            <td >
                                <font face="宋体" > &nbsp;</font>
                            </td>
                            <td align="right">
                                <br /><br />
                            </td>
                            <td align="right" style="padding-right: 80px">
                                <button type="submit" id="search" name="search" value="查询">查询</button>
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <input type="reset" name="reset" value="重置" />
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
            <tr>
                <td class="ta_01" align="center" bgColor="#afd1f3"><strong>商 品 列 表</strong>
                </TD>
            </tr>
            <tr>
                <td class="ta_01" align="right" style="padding-right: 80px">
                    <button type="button" id="add" name="add" value="添加" class="button_add" onclick="addProduct()">
                        添加
                    </button>
                </td>
            </tr>
            <tr>
                <td class="ta_01" align="center" bgColor="#f5fafe">
                    <table cellspacing="0" cellpadding="1" rules="all"
                           bordercolor="gray" border="1" id="DataGrid1"
                           style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
                        <tr style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">
                            <td align="center" width="24%">商品编号</td>
                            <td align="center" width="18%">商品名称</td>
                            <td align="center" width="9%">商品价格</td>
                            <td align="center" width="9%">商品数量</td>
                            <td width="8%" align="center">商品类别</td>
                            <td width="8%" align="center">编辑</td>
                            <td width="8%" align="center">删除</td>
                        </tr>
                        <!--  循环输出所有商品 -->
                        <c:forEach items="${ps}" var="p">
                            <tr onmouseover="this.style.backgroundColor = 'white'"
                                onmouseout="this.style.backgroundColor = '#F5FAFE';">
                                <td style="CURSOR: hand; HEIGHT: 22px" align="center" width="200">${p.id }</td>
                                <td style="CURSOR: hand; HEIGHT: 22px" align="center" width="18%">${p.name }</td>
                                <td style="CURSOR: hand; HEIGHT: 22px" align="center" width="8%">${p.price }</td>
                                <td style="CURSOR: hand; HEIGHT: 22px" align="center" width="8%">${p.pnum }</td>
                                <td style="CURSOR: hand; HEIGHT: 22px" align="center">${p.category}</td>
                                <td align="center" style="HEIGHT: 22px" width="7%">
                                    <a href="<%=path%>/com.servlet.admin.products/EditProductServlet?id=${p.id}">
                                        <img src="${pageContext.request.contextPath}/img/i_edit.gif" border="0" style="CURSOR: hand">
                                    </a>
                                </td>
                                <td align="center" style="HEIGHT: 22px" width="7%">
                                    <a href="<%=path%>/com.servlet.admin.products/DeleteProductServlet?id=${p.id}" onclick="javascript:return p_del()">
                                        <img src="${pageContext.request.contextPath}/img/i_del.gif"
                                             width="16" height="16" border="0" style="CURSOR: hand">
                                    </a>
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
