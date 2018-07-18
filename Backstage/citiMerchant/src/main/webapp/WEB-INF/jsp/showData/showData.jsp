<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>商户管理界面 | Starter</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/js/bower_components/bootstrap/dist/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/js/bower_components/font-awesome/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/js/bower_components/Ionicons/css/ionicons.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/js/dist/css/AdminLTE.min.css">
    <!-- AdminLTE Skins. We have chosen the skin-blue for this starter
          page. However, you can choose any other skin. Make sure you
          apply the skin class to the body tag so the changes take effect. -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/js/dist/css/skins/skin-blue.min.css">

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/js/bower_components/bootstrap/dist/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/js/bower_components/font-awesome/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/js/bower_components/Ionicons/css/ionicons.min.css">
    <!-- Morris charts -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/js/bower_components/morris.js/morris.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/js/dist/css/AdminLTE.min.css">
    <!-- AdminLTE Skins. Choose a skin from the css/skins
         folder instead of downloading all of them to reduce the load. -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/js/dist/css/skins/_all-skins.min.css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

    <!-- Google Font -->
    <link rel="stylesheet"
          href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
</head>
<!--
BODY TAG OPTIONS:
=================
Apply one or more of the following classes to get the
desired effect
|---------------------------------------------------------|
| SKINS         | skin-blue                               |
|               | skin-black                              |
|               | skin-purple                             |
|               | skin-yellow                             |
|               | skin-red                                |
|               | skin-green                              |
|---------------------------------------------------------|
|LAYOUT OPTIONS | fixed                                   |
|               | layout-boxed                            |
|               | layout-top-nav                          |
|               | sidebar-collapse                        |
|               | sidebar-mini                            |
|---------------------------------------------------------|
-->
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

    <!-- Main Header -->
    <header class="main-header">

        <!-- Logo -->
        <a href="starter" class="logo">
            <!-- mini logo for sidebar mini 50x50 pixels -->
            <span class="logo-mini"><b>商</b></span>
            <!-- logo for regular state and mobile devices -->
            <span class="logo-lg"><b>商户管理界面</b></span>
        </a>

        <!-- Header Navbar -->
        <nav class="navbar navbar-static-top" role="navigation">
            <!-- Sidebar toggle button-->
            <a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
                <span class="sr-only">Toggle navigation</span>
            </a>
            <!-- Navbar Right Menu -->
            <div class="navbar-custom-menu">
                <ul class="nav navbar-nav">

                    <!-- User Account Menu -->
                    <li class="dropdown user user-menu">
                        <!-- Menu Toggle Button -->
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            <!-- The user image in the navbar-->
                            <img src="${merchant.merchantLogoURL}"
                                 class="user-image" alt="User Image">
                            <!-- hidden-xs hides the username on small devices so only the image appears. -->
                            <span class="hidden-xs">${merchant.name}</span>
                        </a>
                        <ul class="dropdown-menu">
                            <!-- The user image in the menu -->
                            <li class="user-header">
                                <img src="${merchant.merchantLogoURL}"
                                     class="img-circle" alt="User Image">

                                <p>
                                    ${merchant.name}
                                    <small>${merchant.description}</small>
                                </p>
                            </li>
                            <!-- Menu Footer-->
                            <li class="user-footer">
                                <div class="pull-left">
                                    <a href="#" class="btn btn-default btn-flat">Profile</a>
                                </div>
                                <div class="pull-right">
                                    <a href="logout" class="btn btn-default btn-flat">Sign out</a>
                                </div>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div>
        </nav>
    </header>
    <!-- Left side column. contains the logo and sidebar -->
    <aside class="main-sidebar">

        <!-- sidebar: style can be found in sidebar.less -->
        <section class="sidebar">

            <!-- Sidebar user panel (optional) -->
            <div class="user-panel">
                <div class="pull-left image">
                    <img src="${merchant.merchantLogoURL}" class="img-circle"
                         alt="User Image">
                </div>
                <div class="pull-left info">
                    <p>${merchant.name}</p>
                </div>
            </div>

            <!-- Sidebar Menu -->
            <ul class="sidebar-menu" data-widget="tree">
                <!-- Optionally, you can add icons to the links -->
                <li class="treeview">
                    <a href="#"><i class="fa fa-link"></i> <span>商品优惠</span>
                        <span class="pull-right-container">
                <i class="fa fa-angle-left pull-right"></i>
              </span>
                    </a>
                    <ul class="treeview-menu">
                        <li><a href="item/getItem">商品清单</a></li>
                        <li><a href="item/addItem">添加商品</a></li>
                    </ul>
                <li class="treeview">
                    <a href="#"><i class="fa fa-link"></i> <span>减免策略</span>
                        <span class="pull-right-container">
                <i class="fa fa-angle-left pull-right"></i>
              </span>
                    </a>
                    <ul class="treeview-menu">
                        <li><a href="strategy/getStrategyList">策略清单</a></li>
                        <li><a href="strategy/addStrategyRequest">添加策略</a></li>
                    </ul>
                <li><a href=history><i class="fa fa-link"></i> <span>历史订单</span></a></li>
                <li class="active"><a href="showData"><i class="fa fa-link"></i> <span>统计信息</span></a></li>
                <li class="active"><a href="merchant/editMerchantInformation"><i class="fa fa-link"></i>
                    <span>商户信息</span></a></li>
            </ul>
            <!-- /.sidebar-menu -->
        </section>
        <!-- /.sidebar -->
    </aside>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                积分兑
                <small>一个积分通兑平台</small>
            </h1>
        </section>

        <!-- Main content -->
        <section class="content container-fluid">


            <div class="box box-info">
                <div class="box-header with-border">
                    <h3 class="box-title">订单积分消费情况统计</h3>
                    <div class="box-tools pull-right">
                        <button type="button" class="btn btn-box-tool" data-widget="collapse"><i
                                class="fa fa-minus"></i>
                        </button>
                        <button type="button" class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i>
                        </button>
                    </div>
                </div>
                <div class="box-body chart-responsive">
                    <div class="chart" id="line-chart1" style="height: 300px;"></div>
                </div>
            </div>
            <!-- /.box-body -->


            <div class="box box-info">
                <div class="box-header with-border">
                    <h3 class="box-title">会员卡积分兑换情况统计</h3>
                    <div class="box-tools pull-right">
                        <button type="button" class="btn btn-box-tool" data-widget="collapse"><i
                                class="fa fa-minus"></i>
                        </button>
                        <button type="button" class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i>
                        </button>
                    </div>
                </div>
                <div class="box-body chart-responsive">
                    <div class="chart" id="line-chart2" style="height: 300px;"></div>
                </div>
            </div>
            <!-- /.box-body -->


            <%--<div class="box box-info">--%>
            <%--<div class="box-header with-border">--%>
            <%--<h3 class="box-title">优惠券积分兑换情况统计</h3>--%>
            <%--<div class="box-tools pull-right">--%>
            <%--<button type="button" class="btn btn-box-tool" data-widget="collapse"><i--%>
            <%--class="fa fa-minus"></i>--%>
            <%--</button>--%>
            <%--<button type="button" class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i>--%>
            <%--</button>--%>
            <%--</div>--%>
            <%--</div>--%>
            <%--<div class="box-body chart-responsive">--%>
            <%--<div class="chart" id="line-chart3" style="height: 300px;"></div>--%>
            <%--</div>--%>
            <%--</div>--%>
            <%--<!-- /.box-body -->--%>


            <div class="box box-danger">
                <div class="box-header with-border">
                    <h3 class="box-title">Donut Chart</h3>

                    <div class="box-tools pull-right">
                        <button type="button" class="btn btn-box-tool" data-widget="collapse"><i
                                class="fa fa-minus"></i>
                        </button>
                        <button type="button" class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i>
                        </button>
                    </div>
                </div>
                <div class="box-body chart-responsive">
                    <div class="chart" id="sales-chart" style="height: 300px; position: relative;"></div>
                </div>
            </div>
            <!-- /.box-body -->

            <!--------------------------
              | Your Page Content Here |
              -------------------------->

        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

    <!-- Main Footer -->
    <footer class="main-footer">
        <!-- To the right -->
        <div class="pull-right hidden-xs">
            感谢大家的支持！
        </div>
        <!-- Default to the left -->
        <strong>Copyright &copy; 2016 <a href="#">Company</a>.</strong> All rights reserved.
    </footer>


    <!-- /.control-sidebar -->
    <!-- Add the sidebar's background. This div must be placed
    immediately after the control sidebar -->
    <div class="control-sidebar-bg"></div>
</div>
<!-- ./wrapper -->

<!-- REQUIRED JS SCRIPTS -->

<!-- jQuery 3 -->
<script src="${pageContext.request.contextPath}/js/bower_components/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="${pageContext.request.contextPath}/js/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- AdminLTE App -->
<script src="${pageContext.request.contextPath}/js/dist/js/adminlte.min.js"></script>
<!-- Morris.js charts -->
<script src="${pageContext.request.contextPath}/js/bower_components/raphael/raphael.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bower_components/morris.js/morris.min.js"></script>
<!-- FastClick -->
<script src="${pageContext.request.contextPath}/js/bower_components/fastclick/lib/fastclick.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="${pageContext.request.contextPath}/js/dist/js/demo.js"></script>
<!-- page script -->
<%
    String Point = (String) session.getAttribute("points_json");
    String timeStamp = (String) session.getAttribute("timeStamp_json");
    String Points_EXCHANGE = (String) session.getAttribute("points_exchange_json");
    String total_order_points_json = (String) session.getAttribute("total_order_points_json");
    String total_points_exchange_json = (String) session.getAttribute("total_points_exchange_json");
    String total_merchant_coupon_record_json = (String) session.getAttribute("total_merchant_coupon_record_json");
    //String merchant_coupon_record = (String) session.getAttribute("merchant_coupon_record_json");
%>
<script>
    var Points = $.parseJSON("<%=Point %>");
    var timeStamps = $.parseJSON("<%=timeStamp %>");
    var Points_EXCHANGES = $.parseJSON("<%=Points_EXCHANGE %>");
    var Total_order_points_json = $.parseJSON("<%=total_order_points_json %>");
    var Total_points_exchange_json = $.parseJSON("<%=total_points_exchange_json %>");
    var Total_merchant_coupon_record_json = $.parseJSON("<%=total_merchant_coupon_record_json %>");
    <%--//var Merchant_coupon_record = $.parseJSON("<%=merchant_coupon_record %>");--%>

    // for (var i = 0; i < 12; ++i)
    //     document.write(Merchant_coupon_record[i] + "  ");

    $(function () {
        "use strict";

        // LINE CHART1 订单积分消费情况统计
        var line1 = new Morris.Line({
            element: 'line-chart1',
            resize: true,
            data: [
                {timeStamp: timeStamps[0], item1: Points[0]},
                {timeStamp: timeStamps[1], item1: Points[1]},
                {timeStamp: timeStamps[2], item1: Points[2]},
                {timeStamp: timeStamps[3], item1: Points[3]},
                {timeStamp: timeStamps[4], item1: Points[4]},
                {timeStamp: timeStamps[5], item1: Points[5]},
                {timeStamp: timeStamps[6], item1: Points[6]},
                {timeStamp: timeStamps[7], item1: Points[7]},
                {timeStamp: timeStamps[8], item1: Points[8]},
                {timeStamp: timeStamps[9], item1: Points[9]},
                {timeStamp: timeStamps[10], item1: Points[10]},
                {timeStamp: timeStamps[11], item1: Points[11]}
            ],
            xkey: 'timeStamp',
            ykeys: ['item1'],
            labels: ['订单使用积分'],
            lineColors: ['#3c8dbc'],
            hideHover: 'auto'
        });


        // LINE CHART2 会员卡积分兑换情况统计
        var line2 = new Morris.Line({
            element: 'line-chart2',
            resize: true,
            data: [
                {timeStamp: timeStamps[0], item1: Points_EXCHANGES[0]},
                {timeStamp: timeStamps[1], item1: Points_EXCHANGES[1]},
                {timeStamp: timeStamps[2], item1: Points_EXCHANGES[2]},
                {timeStamp: timeStamps[3], item1: Points_EXCHANGES[3]},
                {timeStamp: timeStamps[4], item1: Points_EXCHANGES[4]},
                {timeStamp: timeStamps[5], item1: Points_EXCHANGES[5]},
                {timeStamp: timeStamps[6], item1: Points_EXCHANGES[6]},
                {timeStamp: timeStamps[7], item1: Points_EXCHANGES[7]},
                {timeStamp: timeStamps[8], item1: Points_EXCHANGES[8]},
                {timeStamp: timeStamps[9], item1: Points_EXCHANGES[9]},
                {timeStamp: timeStamps[10], item1: Points_EXCHANGES[10]},
                {timeStamp: timeStamps[11], item1: Points_EXCHANGES[11]}
            ],
            xkey: 'timeStamp',
            ykeys: ['item1'],
            labels: ['会员卡兑换积分'],
            lineColors: ['#3c8dbc'],
            hideHover: 'auto'
        });


        // // LINE CHART3 优惠券积分兑换情况统计
        // var line3 = new Morris.Line({
        //     element: 'line-chart3',
        //     resize: true,
        //     data: [
        //         {timeStamp: timeStamps[0], item1: Points_EXCHANGES[0]},
        //         {timeStamp: timeStamps[1], item1: Points_EXCHANGES[1]},
        //         {timeStamp: timeStamps[2], item1: Points_EXCHANGES[2]},
        //         {timeStamp: timeStamps[3], item1: Points_EXCHANGES[3]},
        //         {timeStamp: timeStamps[4], item1: Points_EXCHANGES[4]},
        //         {timeStamp: timeStamps[5], item1: Points_EXCHANGES[5]},
        //         {timeStamp: timeStamps[6], item1: Points_EXCHANGES[6]},
        //         {timeStamp: timeStamps[7], item1: Points_EXCHANGES[7]},
        //         {timeStamp: timeStamps[8], item1: Points_EXCHANGES[8]},
        //         {timeStamp: timeStamps[9], item1: Points_EXCHANGES[9]},
        //         {timeStamp: timeStamps[10], item1: Points_EXCHANGES[10]},
        //         {timeStamp: timeStamps[11], item1: Points_EXCHANGES[11]}
        //     ],
        //     xkey: 'timeStamp',
        //     ykeys: ['item1'],
        //     labels: ['优惠券积分兑换'],
        //     lineColors: ['#3c8dbc'],
        //     hideHover: 'auto'
        // });


        //DONUT CHART
        var donut = new Morris.Donut({
            element: 'sales-chart',
            resize: true,
            colors: ["#3c8dbc", "#f56954", "#00a65a"],
            data: [
                {value: Total_order_points_json, label: "订单积分使用"},
                {value: Total_points_exchange_json, label: "会员卡积分兑换"},
                {value: Total_merchant_coupon_record_json, label: "优惠券积分使用"}
            ],
            hideHover: 'auto'
        });


    });
</script>
<!-- Optionally, you can add Slimscroll and FastClick plugins.
     Both of these plugins are recommended to enhance the
     user experience. -->
</body>
</html>