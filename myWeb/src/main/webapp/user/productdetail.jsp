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
            var i=0;
            switch ('${p.category}'){
                case "文学":i=1;break;
                case "生活":i=2;break;
                case "计算机":i=3;break;
                case "外语":i=4;break;
                case "经营":i=5;break;
                case "励志":i=6;break;
                case "社科":i=7;break;
                case "学术":i=8;break;
                case "少儿":i=9;break;
                case "艺术":i=10;break;
                case "原版":i=11;break;
                case "科技":i=12;break;
                case "考试":i=13;break;
                case "生活百科":i=14;break;
            }
            document.getElementsByClassName("sidebar__menu--item")[i].classList.add('is-active');
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

                    document.getElementById("link").href="<%=path%>/com.servlet.user/logoutServlet"
                }
            }
        }

    </script>
</head>

<body>
<jsp:include page="header.jsp"></jsp:include>
<jsp:include page="sidebar.jsp"></jsp:include>
<main class="main" style="text-align: center">

            <div class="productDetails"style="display: inline-block">
                <div>
                    <img style="border-style: solid;border-color: whitesmoke" src="${pageContext.request.contextPath}${p.imgurl}" height="100%" width="90%">
                </div>
                <div>
                    <form action="" name="form1" style="width: 100%;height: 100%">
                    <h2>${p.name}</h2>
                        <input style="text-align: center; width: 100px; height: 20px; float: left; display: none" type="text" value="${p.id}" name="pid"/>
                    <br>
                    <textarea style="overflow-y:scroll;width: 100%;height: 50%;background:transparent;border-style:none;color: whitesmoke;font-size: 18px;" disabled
                    >${p.description}</textarea>
                    <br>
                    <br>
                    <textarea style="overflow-y:scroll;width: 100%;height: 5%;background:transparent;border-style:none;color: whitesmoke;font-size: 15px;" disabled
                    >剩余:${p.pnum}</textarea>
                    <br>

<%--                    <div style="width: 40%;height:10%;display: flex">--%>

<%--                        <input style="width: 50px; height: 38px; border: 2px white; float: left;" type="button" value="-" onclick="reductionOf(this)" />--%>

<%--                        <input style="text-align: center; width: 50px; height: 32px; float: left;" type="text" value="1" name="num" onblur="checkNumber(this)" />--%>

<%--                        <input style="width: 50px; height: 38px; border: 2px white;" type="button" value="+" onclick="add(this)" />--%>

<%--                    </div>--%>
                    <div><a style="color: red;position:absolute;bottom: 0px;font-size: 40px">￥${p.price}</a></div>
                    <div>
                        <button style="background-image:  linear-gradient(-20deg, #f3e199 0%, #f18c53 100%)" type="submit"
                                 onclick="form1.action='<%=path%>/com.servlet.user/AddCartServlet';form1.submit();"
                        >加入购物车</button>
                    </div>
                    <div>
                        <button style="background-image:  linear-gradient(-20deg, #ee7550 0%, #f5593d 100%)" type="submit"
                                 onclick="form1.action='<%=path%>/com.servlet.user/OneCartServlet';"
                        >立即购买</button>
                    </div>
                    </form>
                </div>
            </div>
</main>
</body>
</html>
