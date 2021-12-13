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

                    document.getElementById("link").href="<%=path%>/com.servlet.common/logoutServlet"
                }
            }
        }
        //减数量
        function reductionOf(obj) {
            //减前判断
            if ($(obj).next().val() == '' || $(obj).next().val() == '1') {
                $(obj).next().val(1);
            } else {
                $(obj).next().val(parseInt($(obj).next().val()) - 1);//数值减
                $(obj).next().val($(obj).next().val());//赋值给框
                var num = parseInt($(obj).next().val());
                var price = parseInt(document.getElementById("price").innerText)

                $("#all").text(num * price);
            }
        }
        //加数量
        function add(obj) {
            //加前判断
            if ($(obj).prev().val() == '') {
                $(obj).prev().val(1);
            } else {
                $(obj).prev().val(parseInt($(obj).prev().val()) + 1);//数值加
                $(obj).prev().val($(obj).prev().val());//赋值给框
                var num = parseInt($(obj).prev().val());
                var price = parseInt(document.getElementById("price").innerText)
                $("#all").text(num * price);
            }
        }

            //校验数字格式（只能输入正整数）
            function checkNumber(obj) {
                var reg = /^[1-9]\d*$/;
                if (!reg.test($(obj).val()) || $(obj).val() == '') {
                    $(obj).val(1);
                } else {
                    var num = parseInt($(obj).val());
                    var price = parseInt(document.getElementById("price").innerText)
                    $("#all").text(num * price);
                }
            }
    </script>
</head>

<body>
<jsp:include page="../../user/header.jsp"></jsp:include>
<jsp:include page="../../user/sidebar.jsp"></jsp:include>
<main class="main">
    <div id="cartBox">
        <form name="form1" style="width: 100%;height: 100%">

            <div class="cartBox1">
                <table class="cart" border="1" cellspacing="0" cellpadding="0"
                       style="text-align:center;BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse;  WORD-WRAP: break-word";>
                    <tr>

                        <td style="width: 15%">编号</td>
                        <td style="width: 10%">封面</td>
                        <td style="width: 18%">书籍名称</td>
                        <td style="width: 10%">单价(元)</td>
                        <td style="width: 18%">数量</td>


                    </tr>

                        <tr>
                            <td style="width: 15%" ><input name="pid" id="pid" value="${product.id}"  style="background: none;color: whitesmoke;" readonly type="text" /></td>
                            <td style="width: 10%"><img style="margin: 0px auto;width: auto;height: 60px" src="${pageContext.request.contextPath}${product.imgurl}"  ></td>
                            <td style="width: 18%"><a href="<%=path%>/com.servlet.common/ViewProductServlet?id=${product.id}">${product.name}</a></td>
                            <td style="width: 10%"><a id="price">${product.price}</a></td>
                            <td style="width: 18%" >
                                <input style="width:25%;" type="button" value="-"  onclick="reductionOf(this)"/>
                                <input style="width:30%;" type="text" value="1" name="num" id="num" onblur="checkNumber(this)"/>
                                <input style="width:25%; " type="button" value="+" onclick="add(this)"/>
                            </td>

                        </tr>


                </table>
            </div>
            <div class="cartBox2">
                <div style="height: 80%;width: 100%;padding-left: 50%;font-size: 20px">
                    <div style="width: 100%;display: inline-block">
                        <a>商品总价（不含运费）: </a><a type="text" id="all">${product.price}</a><a> 元</a>
                    </div>
                    <div style="padding-left: 70%">
                        <button style="background-image:  linear-gradient(-20deg, #ee7550 0%, #f5593d 100%);font-size: 20px;" type="submit"
                                onclick="form1.action='<%=path%>/com.servlet.user/SettleServlet';"
                        >结算</button>
                    </div>
                </div>
            </div>
        </form>


    </div>
</main>
</body>
</html>
