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
        function reductionOf(obj,obj1) {
            //减前判断
            if ($(obj).next().val() == '' ||$(obj).next().val() == '1') {
                $(obj).next().val(1);
            }
            else {
                $(obj).next().val(parseInt($(obj).next().val()) - 1);//数值减
                $(obj).next().val($(obj).next().val());//赋值给框
                var num=parseInt($(obj).next().val());
                var price=parseInt(document.getElementById("price"+obj1).innerText)
                $("#priceadd"+obj1).text(price*num);
                if(document.getElementById("checkbox"+obj1).checked==true)
                    $("#all").text(parseInt(document.getElementById("all").innerText)-price);
            }

        };
        //加数量
        function add(obj,obj1) {
            //加前判断
            if ($(obj).prev().val() == '') {
                $(obj).prev().val(1);
            }
            else {
                $(obj).prev().val(parseInt($(obj).prev().val()) + 1);//数值加
                $(obj).prev().val($(obj).prev().val());//赋值给框
                var num=parseInt($(obj).prev().val());
                var price=parseInt(document.getElementById("price"+obj1).innerText)
                $("#priceadd"+obj1).text(price*num);
                if(document.getElementById("checkbox"+obj1).checked==true)
                    $("#all").text(parseInt(document.getElementById("all").innerText)+price);
            }

        };
        //校验数字格式（只能输入正整数）
        function checkNumber(obj,obj1) {
            var reg = /^[1-9]\d*$/;
            if(!reg.test($(obj).val()) || $(obj).val()==''){
                $(obj).val(1);
            }
            else{
                var num=parseInt($(obj).val());
                var price=parseInt(document.getElementById("price"+obj1).innerText)
                var prevadd=parseInt(document.getElementById("priceadd"+obj1).innerText)
                var plus=price*num-prevadd;
                $("#priceadd"+obj1).text(price*num);
                if(document.getElementById("checkbox"+obj1).checked==true)
                    $("#all").text(parseInt(document.getElementById("all").innerText)+plus);
            }
        }

        function selectSingle(checkbox,obj1){
            if(checkbox.checked==true){
                $("#all").text(parseInt(document.getElementById("priceadd"+obj1).innerText)+parseInt(document.getElementById("all").innerText));
                $("#pid"+obj1).attr("name","pid");
                $("#num"+obj1).attr("name","num")
            }
            else{
                $("#all").text(parseInt(document.getElementById("all").innerText)-parseInt(document.getElementById("priceadd"+obj1).innerText));
                $("#pid"+obj1).attr("name","pidnone");
                $("#num"+obj1).attr("name","numnone")
            }
        }
        function check(){
            if(parseInt(document.getElementById("all").innerText)==0){
                alert("请选择要结算的商品");
                return false;}
            else return true;
        }
    </script>
</head>

<body>
<jsp:include page="../../user/header.jsp"></jsp:include>
<jsp:include page="../../user/sidebar.jsp"></jsp:include>
<main class="main">
    <div id="cartBox">
        <form name="form1" style="width: 100%;height: 100%" onsubmit="return check()">

            <div class="cartBox1">
            <table class="cart" border="1" cellspacing="0" cellpadding="0"
                   style="text-align:center;BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse;  WORD-WRAP: break-word";>
                <tr>
                    <td style="width: 10%"></td>
                    <td style="width: 15%">编号</td>
                    <td style="width: 10%">封面</td>
                    <td style="width: 18%">书籍名称</td>
                    <td style="width: 10%">单价(元)</td>
                    <td style="width: 18%">数量</td>
                    <td style="width: 12%">小计(元)</td>
                    <td style="width: 7%">操作</td>
                </tr>
                <c:forEach items="${carts}" var="c">
                    <tr>
                        <td style="width: 10%"><input name="select" type="checkbox" id="checkbox${c.p.id}" onclick="selectSingle(this,'${c.p.id}')" /></td>
                        <td style="width: 15%" ><input name="pidnone" id="pid${c.p.id}" value="${c.p.id}"  style="background: none;color: whitesmoke;" readonly type="text" /></td>
                        <td style="width: 10%"><img style="margin: 0px auto;width: auto;height: 60px" src="${pageContext.request.contextPath}${c.p.imgurl}"  ></td>
                        <td style="width: 18%"><a href="<%=path%>/com.servlet.common/ViewProductServlet?id=${c.p.id}">${c.p.name}</a></td>
                        <td style="width: 10%"><a id="price${c.p.id}">${c.p.price}</a></td>
                        <td style="width: 18%" >
                            <input style="width:25%;" type="button" value="-"  onclick="reductionOf(this,'${c.p.id}')"/>
                            <input style="width:30%;" type="text" value="1" name="numnone" id="num${c.p.id}" onblur="checkNumber(this,'${c.p.id}')"/>
                            <input style="width:25%; " type="button" value="+" onclick="add(this,'${c.p.id}')"/>
                        </td>
                        <td style="width: 12%">
                            <a id="priceadd${c.p.id}">${c.p.price}</a>
                        </td>
                        <td style="width: 7%"><input type="button" value="删除" onclick="userServlet('<%=path%>/com.servlet.user/DeleteCartServlet?pid=${c.p.id}')"></td>
                    </tr>
                </c:forEach>

            </table>
            </div>
            <div class="cartBox2">
                <div style="height: 80%;width: 100%;padding-left: 50%;font-size: 20px">
                <div style="width: 100%;display: inline-block">
                    <a>商品总价（不含运费）: </a><a type="text" id="all">0</a><a> 元</a>
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
