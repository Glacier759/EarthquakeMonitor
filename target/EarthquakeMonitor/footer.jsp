<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    $(function () {
        $(".unlogin").click(function () {
            document.getElementById("menu").click();
            document.getElementById("login").click();
        });
    });
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
                }
            });
        });
    });
    <%if(session.getAttribute("userinfo").equals("n") && !request.getServletPath().equals("/manager.jsp")) {%>
    userinfo();
    <%}%>
    function userinfo() {
        alert("用户信息不完整, 请完善信息后使用该系统");
        location.href = "<%=request.getContextPath()%>/manager.jsp";
    }
</script>
