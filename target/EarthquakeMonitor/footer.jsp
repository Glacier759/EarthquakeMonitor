<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="footer" id="footer">
    <p align="center">——————————————————————————————————————————————————————————</p>
    <p align="center">版权所有：新疆维吾尔自治区地震局</p>
    <p align="center">开发完成：新疆地震局 刘军</p>
    <p align="center">地址：乌鲁木齐市新市区科学二街338号</p>
</div>
<script>
    function footer_fun() {
        var bodyHeight = $(document.body).height();
        var windHeight = $(window).height();
        var footer = $("#footer");
        if (bodyHeight < windHeight) {
            footer.css({
                width: '100%',
                height: '50px',
                position: 'relative',
                bottom: '0px',
                left: '0px'
            });
        }
        else {
            footer.css({
                width: '100%',
                height: '50px',
                position: 'relative',
                bottom: '0px',
                left: '0px'
            });
        }
    }
    $(document).ready(function() {
       footer_fun();
    });
    footer_fun();
    $(function () {
        $(".unlogin").click(function () {
            document.getElementById("menu").click();
            document.getElementById("login").click();
        });
    });
    function system() {
        $.ajax({
            type: "post",
            url: "<%=request.getContextPath()%>/SettingServlet?operate=system",
            data: "",
            success: function (msg) {    //msg是后台调用action时，你传过来的参数
                if ( msg == "stoping" ) {
                    $("#system-stop").remove();
                    $("#system-body").html("系统处于关闭状态.");
                } else if ( msg == "starting" ) {
                    $("#system-body").html("系统处于运行状态.");
                    $("#system-start").remove();
                }
                footer_fun();
            }
        });
        $("#confirm").modal("toggle");
    }
    $(function () {
        $("#system").click(function () {
            document.getElementById("menu").click();
            $.ajax({
                type: "post",
                url: "<%=request.getContextPath()%>/SettingServlet?operate=system",
                data: "",
                success: function (msg) {    //msg是后台调用action时，你传过来的参数
                    if ( msg == "stoping" ) {
                        $("#system-stop").remove();
                        $("#system-body").html("系统处于关闭状态.");
                    } else if ( msg == "starting" ) {
                        $("#system-body").html("系统处于运行状态.");
                        $("#system-start").remove();
                    }
                    footer_fun();
                }
            });
            $("#confirm").modal("toggle");
        });
        $("#system-start").click(function() {
            $.ajax({
                type: "post",
                url: "<%=request.getContextPath()%>/SettingServlet?operate=system-start",
                data: "",
                success: function (msg) {    //msg是后台调用action时，你传过来的参数
                    if ( msg == "permission denied" ) {
                        alert("您没有权限进行此操作")
                    } else if ( msg == "success" ) {
                        alert("系统已经开启");
                        location.reload();
                    }
                    footer_fun();
                }
            });
        });
        $("#system-stop").click(function() {
            $.ajax({
                type: "post",
                url: "<%=request.getContextPath()%>/SettingServlet?operate=system-stop",
                data: "",
                success: function (msg) {    //msg是后台调用action时，你传过来的参数
                    if ( msg == "permission denied" ) {
                        alert("您没有权限进行此操作")
                    } else if ( msg == "success" ) {
                        alert("系统已经关闭");
                        location.reload();
                    }
                    footer_fun();
                }
            });
        });
    });
    <%if( session.getAttribute("userinfo") != null && session.getAttribute("userinfo").equals("n") && !request.getServletPath().equals("/manager.jsp") && !request.getServletPath().equals("/index.jsp") && !request.getServletPath().equals("/index_v2.jsp")) {%>
    userinfo();
    <%}%>
    function userinfo() {
        alert("用户信息不完整, 请完善信息后使用该系统");
        location.href = "<%=request.getContextPath()%>/manager.jsp";
    }
</script>