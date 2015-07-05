<%--
  Created by IntelliJ IDEA.
  User: glacier
  Date: 15-7-4
  Time: 下午9:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta http-equiv="content-type" content="text/html; charset=utf-8">
        <title>API Help</title>
        <link href="<%=request.getContextPath()%>/resource/css/bootstrap.min.css" rel="stylesheet">
        <link href="<%=request.getContextPath()%>/resource/css/style.min.css" rel="stylesheet">
        <script src="<%=request.getContextPath()%>/resource/js/pace.js"></script>
        <link href="<%=request.getContextPath()%>/resource/css/pace-theme-flash.min.css" rel="stylesheet">
        <script src="<%=request.getContextPath()%>/resource/js/jquery-2.1.1.min.js"></script>
        <script src="<%=request.getContextPath()%>/resource/js/bootstrap.min.js"></script>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <%@include file="system.jsp" %>
        <%@include file="navbar.jsp"%>
        <div class="row">
            <div class="col-md-1"></div>
            <div class="col-md-10">
                <div class="row">
                    <table class="table table-striped table-bordered table-hover" id="filters-table"
                           style="table-layout: fixed;">
                        <thead>
                        <tr>
                            <th class="text-center">访问地址</th>
                            <th class="text-center">作用</th>
                            <th class="text-center">参数</th>
                        </tr>
                        </thead>
                        <tbody id="filters-tbody">
                            <tr class="text-info">
                                <td class="text-center">/EarthquakeMonitor/android/login.and</td>
                                <td class="text-center">账户登录</td>
                                <td class="text-center">username(可以是邮箱或手机号) & password(密码)</td>
                            </tr>
                            <tr class="text-info">
                                <td class="text-center">/EarthquakeMonitor/android/logout.and</td>
                                <td class="text-center">退出登录</td>
                                <td class="text-center">NULL(无需参数,将会退出当前登陆的账户)</td>
                            </tr>
                            <tr class="text-info">
                                <td class="text-center">/EarthquakeMonitor/android/register.and</td>
                                <td class="text-center">账户注册</td>
                                <td class="text-center">email(邮箱) & mobile(手机) & password(密码) & repassword(重复密码) & realname(真实姓名) & qqnumber(QQ号) & workplace(工作地) & position(职位)</td>
                            </tr>
                            <tr class="text-info">
                                <td class="text-center">/EarthquakeMonitor/android/user_info.and</td>
                                <td class="text-center">获取用户资料（依据登陆用户或者uid/email/mobile）</td>
                                <td class="text-center">NULL | uid | email | mobile (四种情况并列存在,为空时返回登录用户的资料,否则依据其他条件返回对应用户资料)</td>
                            </tr>
                            <tr class="text-info">
                                <td class="text-center">/EarthquakeMonitor/android/data.and</td>
                                <td class="text-center">数据概览页面 所有表格内的信息</td>
                                <td class="text-center">NULL(无需参数)</td>
                            </tr>
                            <tr class="text-info">
                                <td class="text-center">/EarthquakeMonitor/android/filter_table.and</td>
                                <td class="text-center">获取灾情获取匹配式/舆情监控匹配式/白名单</td>
                                <td class="text-center">type(对应上面三种情况的三个参数值:disaster,public,whitelist)</td>
                            </tr>
                            <tr class="text-info">
                                <td class="text-center">/EarthquakeMonitor/android/filter_delete.and</td>
                                <td class="text-center">删除某条匹配式规则或白名单</td>
                                <td class="text-center">type(同上一条的三种参数值) & filter_id(需要删除的规则id)</td>
                            </tr>
                            <tr class="text-info">
                                <td class="text-center">/EarthquakeMonitor/android/filter_add.and</td>
                                <td class="text-center">增加一条匹配式或白名单记录</td>
                                <td class="text-center">type(同上的三种参数值) & { filter[] | {filter-name[] & filter-matcher[] & filter-unexist} | whitelist[] } (依据三种参数值对应不同的接收参数)</td>
                            </tr>
                            <tr class="text-info">
                                <td class="text-center">/EarthquakeMonitor/android/filter_modify.and</td>
                                <td class="text-center">修改一条匹配式或白名单记录</td>
                                <td class="text-center">type(除去whitelist的其余两种参数) & filter-id(需要修改的id值) & { filter-name | {filter-name & filter-matcher & filter-unexist} }(对应参数)</td>
                            </tr>
                            <tr class="text-info">
                                <td class="text-center">/EarthquakeMonitor/android/user_list.and</td>
                                <td class="text-center">获取所有用户的详细信息</td>
                                <td class="text-center">NULL(无需参数)</td>
                            </tr>
                            <tr class="text-info">
                                <td class="text-center">/EarthquakeMonitor/android/user_modify.and</td>
                                <td class="text-center">修改当前登录用户的资料</td>
                                <td class="text-center">email(邮箱) & mobile(手机) & realname(真名) & qqnumber(qq号) & position(职位) & workplace(工作地)</td>
                            </tr>
                            <tr class="text-info">
                                <td class="text-center">/EarthquakeMonitor/android/password_modify.and</td>
                                <td class="text-center">修改当前登录用户的密码</td>
                                <td class="text-center">password(旧密码) & new-password(新密码) & re-password(重复新密码)</td>
                            </tr>
                            <tr class="text-info">
                                <td class="text-center">/EarthquakeMonitor/android/examine_list.and</td>
                                <td class="text-center">获取审核模块展示的所有数据列表</td>
                                <td class="text-center">NULL(无需参数)</td>
                            </tr>
                            <tr class="text-info">
                                <td class="text-center">/EarthquakeMonitor/android/examine_filter.and</td>
                                <td class="text-center">依据记录信息获取对应的过滤规则</td>
                                <td class="text-center">id(信息记录id)</td>
                            </tr>
                            <tr class="text-info">
                                <td class="text-center">/EarthquakeMonitor/android/examine_result.and</td>
                                <td class="text-center">对信息记录进行审核 通过或淘汰</td>
                                <td class="text-center">check[](保存一组规则id) & type</td>
                            </tr>
                            <tr class="text-info">
                                <td class="text-center">/EarthquakeMonitor/android/examine_switch.and</td>
                                <td class="text-center">开启或关闭信息审核模块</td>
                                <td class="text-center">operate(0表示要关闭审核, 1表示要开启审核)</td>
                            </tr>
                            <tr class="text-info">
                                <td class="text-center">/EarthquakeMonitor/android/system.and</td>
                                <td class="text-center">控制系统的开启与关闭</td>
                                <td class="text-center">operate(system-stop表示关闭系统 system-start表示开启系统) & status(不为空即可返回当前系统的开关状态)</td>
                            </tr>
                            <tr class="text-info">
                                <td class="text-center">/EarthquakeMonitor/android/user_delete.and</td>
                                <td class="text-center">删除某个用户</td>
                                <td class="text-center">uid(需要删除的用户的id)</td>
                            </tr>
                            <tr class="text-info">
                                <td class="text-center">/EarthquakeMonitor/android/manager.and</td>
                                <td class="text-center">将某个用户设置为管理员</td>
                                <td class="text-center">uid(需要设置为管理员的用户id)</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="col-md-1"></div>
        </div>
    </body>
</html>
