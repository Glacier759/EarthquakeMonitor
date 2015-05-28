<%--
  Created by IntelliJ IDEA.
  User: glacier
  Date: 15-5-25
  Time: 下午4:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta http-equiv="content-type" content="text/html; charset=utf-8">
        <title>Earthquake Eye</title>
        <link href="<%=request.getContextPath()%>/resource/css/bootstrap.min.css" rel="stylesheet">
        <link href="<%=request.getContextPath()%>/resource/css/animate.css">
        <link href="<%=request.getContextPath()%>/resource/css/style.min.css" rel="stylesheet">
        <link href="<%=request.getContextPath()%>/resource/fonts/google_api.css?family=Montserrat|Varela+Round" rel="stylesheet">
        <script src="<%=request.getContextPath()%>/resource/js/pace.js"></script>
        <link href="<%=request.getContextPath()%>/resource/css/pace-theme-flash.min.css" rel="stylesheet">
        <script src="<%=request.getContextPath()%>/resource/js/jquery-2.1.1.min.js"></script>
        <script src="<%=request.getContextPath()%>/resource/js/bootstrap.min.js"></script>
        <script src="<%=request.getContextPath()%>/resource/js/menu.js"></script>
        <script src="<%=request.getContextPath()%>/resource/js/highcharts.js"></script>
        <script src="<%=request.getContextPath()%>/resource/js/exporting.js"></script>
    </head>
    <body>
        <%@include file="header.jsp"%>
        <%@include file="system.jsp"%>
        <div id="header-div">
            <nav class="navbar navbar-default">
                <div class="container-fluid">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="glyphicon glyphicon-chevron-down" aria-hidden="true" ></span>
                        </button>
                    </div>
                    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                        <ul class="nav navbar-nav navbar-right">
                            <li><a href="<%=request.getContextPath()%>/showdata.jsp"><h4>查看数据记录</h4></a></li>
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><h4>管理设置<span class="caret"></span></h4></a>
                                <ul class="dropdown-menu" role="menu">
                                    <li><a href="<%=request.getContextPath()%>/settings/manage-disaster.jsp">灾情获取匹配式管理</a></li>
                                    <li class="divider"></li>
                                    <li><a href="<%=request.getContextPath()%>/settings/manage-public.jsp">舆情监测匹配式管理</a></li>
                                    <li class="divider"></li>
                                    <li><a href="<%=request.getContextPath()%>/settings/manage-whitelist.jsp">白名单管理</a></li>
                                    <li class="divider"></li>
                                    <li><a href="<%=request.getContextPath()%>/settings/manage-warning.jsp">报警设置</a></li>
                                    <li class="divider"></li>
                                    <li><a href="<%=request.getContextPath()%>/settings/manage-examine.jsp">审核管理</a></li>
                                </ul>
                            </li>
                        </ul>
                    </div><!-- /.navbar-collapse -->
                </div><!-- /.container-fluid -->
            </nav>
        </div>
        <div class="row">
            <div class="col-md-1"></div>
            <div class="col-md-10">
                <div class="alert alert-success" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                        <span aria-hidden="true">x</span>
                    </button>
                    <p>本系统自2015年5月20日开始收集数据</p>
                </div>
            </div>
            <div class="col-md-1"></div>
        </div>
        <div class="row">
            <div class="col-md-1"></div>
            <div class="col-md-4">
                <div class="row">
                    <div class="alert alert-info" role="alert">
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                            <span aria-hidden="true">x</span>
                        </button>
                        <h4>2015年05月25日 星期一 19:28:34</h4>
                        <p>系统收录信息：111条</p>
                        <p>灾情获取匹配：222条</p>
                        <p>舆情监测匹配：333条</p>
                        <p>今日获取数据：444条（灾情：555条 / 舆情：666条）</p>
                    </div>
                </div>
                <div class="row">
                    <div class="alert alert-danger" role="alert">
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                            <span aria-hidden="true">x</span>
                        </button>
                        <p>已通过审核信息：777条</p>
                        <p>未通过审核信息：888条</p>
                    </div>
                </div>
            </div>
            <br />
            <div class="col-md-6">
                <div class="row">
                    <div id="zhe1" style="min-width:600px; height:343px"></div>
                </div>
            </div>
        </div>
        <br /><br /><br /><br />
        <div class="row">
            <div class="col-md-4">
                <div id="bing1" style="min-width:500px; height:286px"></div>
            </div>
            <div class="col-md-4">
                <div id="bing2" style="min-width:500px; height:286px"></div>
            </div>
            <div class="col-md-4">
                <div id="bing3" style="min-width:500px; height:286px"></div>
            </div>
        </div>
        <br /><br /><br /><br />
        <div class="row">
            <div class="col-md-1"></div>
            <div class="col-md-10">
                <div id="tiao1" style="min-width:700px;height:400px"></div>
            </div>
            <div class="col-md-1"></div>
        </div>
        <script src="<%=request.getContextPath()%>/resource/js/menu.js"></script>
        <script>
            $(function() {
                $(".unlogin").click(function(){
                    document.getElementById("menu").click();
                    document.getElementById("login").click();
                });
            });
        </script>
        <script>
            $(function () {
                $('#bing1').highcharts({
                    chart: {
                        plotBackgroundColor: null,
                        plotBorderWidth: null,
                        plotShadow: false
                    },
                    title: {
                        text: '信息源域名分布'
                    },
                    tooltip: {
                        pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
                    },
                    plotOptions: {
                        pie: {
                            allowPointSelect: true,
                            cursor: 'pointer',
                            dataLabels: {
                                enabled: true,
                                color: '#000000',
                                connectorColor: '#000000',
                                format: '<b>{point.name}</b>: {point.percentage:.1f} %'
                            }
                        }
                    },
                    series: [{
                        type: 'pie',
                        name: 'Browser share',
                        data: [
                            ['Firefox',   45.0],
                            ['IE',       26.8],
                            {
                                name: 'Chrome',
                                y: 12.8,
                                sliced: true,
                                selected: true
                            },
                            ['Safari',    8.5],
                            ['Opera',     6.2],
                            ['Others',   0.7]
                        ]
                    }]
                });
            });
        </script>
        <script>
            $(function () {
                $('#bing2').highcharts({
                    chart: {
                        plotBackgroundColor: null,
                        plotBorderWidth: null,
                        plotShadow: false
                    },
                    title: {
                        text: '后台过滤模块分布情况'
                    },
                    tooltip: {
                        pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
                    },
                    plotOptions: {
                        pie: {
                            allowPointSelect: true,
                            cursor: 'pointer',
                            dataLabels: {
                                enabled: true,
                                color: '#000000',
                                connectorColor: '#000000',
                                format: '<b>{point.name}</b>: {point.percentage:.1f} %'
                            }
                        }
                    },
                    series: [{
                        type: 'pie',
                        name: 'Browser share',
                        data: [
                            ['Firefox',   45.0],
                            ['IE',       26.8],
                            {
                                name: 'Chrome',
                                y: 12.8,
                                sliced: true,
                                selected: true
                            }
                        ]
                    }]
                });
            });
        </script>
        <script>
            $(function () {
                $('#bing3').highcharts({
                    chart: {
                        plotBackgroundColor: null,
                        plotBorderWidth: null,
                        plotShadow: false
                    },
                    title: {
                        text: '还没想好'
                    },
                    tooltip: {
                        pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
                    },
                    plotOptions: {
                        pie: {
                            allowPointSelect: true,
                            cursor: 'pointer',
                            dataLabels: {
                                enabled: true,
                                color: '#000000',
                                connectorColor: '#000000',
                                format: '<b>{point.name}</b>: {point.percentage:.1f} %'
                            }
                        }
                    },
                    series: [{
                        type: 'pie',
                        name: 'Browser share',
                        data: [
                            ['Firefox',   45.0],
                            ['IE',       26.8],
                            {
                                name: 'Chrome',
                                y: 12.8,
                                sliced: true,
                                selected: true
                            },
                            ['Safari',    8.5],
                            ['Opera',     6.2],
                            ['Others',   0.7]
                        ]
                    }]
                });
            });
        </script>
        <script>
            $(function () {
                $('#zhe1').highcharts({
                    title: {
                        text: '近期数据采集情况',
                        x: -20 //center
                    },
                    subtitle: {
                        text: 'Source: spider.glacierlx.com',
                        x: -20
                    },
                    xAxis: {
                        categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun','Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
                    },
                    yAxis: {
                        title: {
                            text: '信息数目 (条)'
                        },
                        plotLines: [{
                            value: 0,
                            width: 1,
                            color: '#808080'
                        }]
                    },
                    tooltip: {
                        valueSuffix: '条'
                    },
                    legend: {
                        layout: 'vertical',
                        align: 'right',
                        verticalAlign: 'middle',
                        borderWidth: 0
                    },
                    series: [{
                        name: 'Tokyo',
                        data: [7.0, 6.9, 9.5, 14.5, 18.2, 21.5, 25.2, 26.5, 23.3, 18.3, 13.9, 9.6]
                    }, {
                        name: 'New York',
                        data: [-0.2, 0.8, 5.7, 11.3, 17.0, 22.0, 24.8, 24.1, 20.1, 14.1, 8.6, 2.5]
                    }, {
                        name: 'Berlin',
                        data: [-0.9, 0.6, 3.5, 8.4, 13.5, 17.0, 18.6, 17.9, 14.3, 9.0, 3.9, 1.0]
                    }, {
                        name: 'London',
                        data: [3.9, 4.2, 5.7, 8.5, 11.9, 15.2, 17.0, 16.6, 14.2, 10.3, 6.6, 4.8]
                    }]
                });
            });
        </script>
        <script>
            $(function () {
                $('#tiao1').highcharts({
                    chart: {
                        type: 'bar'
                    },
                    title: {
                        text: '各条规则匹配情况'
                    },
                    subtitle: {
                        text: 'Source: spider.glacierlx.com'
                    },
                    xAxis: {
                        categories: ['Africa', 'America', 'Asia', 'Europe', 'Oceania'],
                        title: {
                            text: null
                        }
                    },
                    yAxis: {
                        min: 0,
                        title: {
                            text: 'Population (millions)',
                            align: 'high'
                        },
                        labels: {
                            overflow: 'justify'
                        }
                    },
                    tooltip: {
                        valueSuffix: ' millions'
                    },
                    plotOptions: {
                        bar: {
                            dataLabels: {
                                enabled: true
                            }
                        }
                    },
                    legend: {
                        layout: 'vertical',
                        align: 'right',
                        verticalAlign: 'top',
                        x: -40,
                        y: 100,
                        floating: true,
                        borderWidth: 1,
                        backgroundColor: '#FFFFFF',
                        shadow: true
                    },
                    credits: {
                        enabled: false
                    },
                    series: [{
                        name: 'Year 1800',
                        data: [107, 31, 635, 203, 2]
                    }, {
                        name: 'Year 1900',
                        data: [133, 156, 947, 408, 6]
                    }, {
                        name: 'Year 2008',
                        data: [973, 914, 4054, 732, 34]
                    }]
                });
            });
        </script>
    <%@include file="footer.jsp"%>
    </body>
</html>