<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->

<html>
<%@ page contentType="text/html;charset=UTF-8" language="java"
pageEncoding="UTF-8" isELIgnored="false" autoFlush="false" buffer="300kb"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>商户管理界面 | Starter</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <script src="${pageContext.request.contextPath}/js/bower_components/jquery/dist/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bower_components/bootstrap/dist/js/bootstrap.js"></script>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/js/bower_components/bootstrap/dist/css/bootstrap.min.css">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/js/bower_components/font-awesome/css/font-awesome.min.css">
  <!-- Ionicons -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/js/bower_components/Ionicons/css/ionicons.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/js/dist/css/AdminLTE.min.css">
  <!-- AdminLTE Skins. We have chosen the skin-blue for this starter
        page. However, you can choose any other skin. Make sure you
        apply the skin class to the body tag so the changes take effect. -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/js/dist/css/skins/skin-blue.min.css">

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
    <a href=/starter" class="logo">
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
              <img src="${pageContext.request.contextPath}/js/dist/img/user2-160x160.jpg" class="user-image" alt="User Image">
              <!-- hidden-xs hides the username on small devices so only the image appears. -->
              <span class="hidden-xs">Alexander Pierce</span>
            </a>
            <ul class="dropdown-menu">
              <!-- The user image in the menu -->
              <li class="user-header">
                <img src="${pageContext.request.contextPath}/js/dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">

                <p>
                  Alexander Pierce - Web Developer
                  <small>Member since Nov. 2012</small>
                </p>
              </li>
              <!-- Menu Body -->
              <li class="user-body">
                <div class="row">
                  <div class="col-xs-4 text-center">
                    <a href="#">Followers</a>
                  </div>
                  <div class="col-xs-4 text-center">
                    <a href="#">Sales</a>
                  </div>
                  <div class="col-xs-4 text-center">
                    <a href="#">Friends</a>
                  </div>
                </div>
                <!-- /.row -->
              </li>
              <!-- Menu Footer-->
              <li class="user-footer">
                <div class="pull-left">
                  <a href="#" class="btn btn-default btn-flat">Profile</a>
                </div>
                <div class="pull-right">
                  <a href="#" class="btn btn-default btn-flat">Sign out</a>
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
           <img src="${pageContext.request.contextPath}/js/dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
         </div>
         <div class="pull-left info">
           <p>Alexander Pierce</p>
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
             <li><a href="../item/getItem">商品清单</a></li>
             <li><a href="../item/addItem">添加商品</a></li>
           </ul>
         <li class="treeview">
           <a href="#"><i class="fa fa-link"></i> <span>减免策略</span>
             <span class="pull-right-container">
                <i class="fa fa-angle-left pull-right"></i>
              </span>
           </a>
           <ul class="treeview-menu">
             <li><a href="../strategy/getStrategyList">策略清单</a></li>
             <li><a href="../strategy/addStrategyRequest">添加策略</a></li>
           </ul>
         <li><a href=../history><i class="fa fa-link"></i> <span>历史订单</span></a></li>
         <li class="active"><a href="/showData/showData.jsp"><i class="fa fa-link"></i> <span>统计信息</span></a></li>
         <li class="active"><a href="/merchant/editMerchantInformation"><i class="fa fa-link"></i> <span>商户信息</span></a></li>

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
    <table class="table table-striped">
        <thead>
            <tr><th>图片</th><th>编号</th><th>名称</th><th>简述</th><th>原价</th><th>抵扣积分</th><th>操作</th></tr>
        </thead>
        <tbody>
            <tr><td>

            <c:forEach items="${items}" var="item">
              <tr>
                <td>
                  <a href="#" class="thumbnail"><img src=${item.logoURL} alt="通用的占位符缩略图" height="50" width="50"></a>
                </td>
                <td>${item.itemID}</td>
                <td>${item.name}</td>
                <td>${item.description}</td>
                <td>${item.originalPrice}</td>
                <td>${item.points}</td>
              <td><form action="/item/editItem"><button name="itemID" value=${item.itemID} type="submit" class="btn btn-success">编辑</button></form></td>
              <td><form action="/item/deleteItem"><button name="itemID" value=${item.itemID} type="submit" class="btn btn-danger">删除</button></form></td>
              </tr>
            </c:forEach>
        </tbody>
    </table>
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
      Anything you want
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

<!-- Optionally, you can add Slimscroll and FastClick plugins.
     Both of these plugins are recommended to enhance the
     user experience. -->
</body>
</html>