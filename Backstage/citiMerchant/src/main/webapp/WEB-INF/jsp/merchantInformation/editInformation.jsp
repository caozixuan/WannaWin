<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" isELIgnored="false" autoFlush="false" buffer="300kb"%>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>商户管理界面 | Starter</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
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
<link href="${pageContext.request.contextPath}/js/bootstrap-fileinput/css/fileinput.css" media="all" rel="stylesheet" type="text/css"/>
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" media="all" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/js/bootstrap-fileinput/themes/explorer-fa/theme.css" media="all" rel="stylesheet" type="text/css"/>
<script src="${pageContext.request.contextPath}/js/bootstrap-fileinput/js/plugins/sortable.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap-fileinput/js/fileinput.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap-fileinput/js/locales/fr.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap-fileinput/js/locales/es.js" type="text/javascript"></script>
     <script src="${pageContext.request.contextPath}/js/bootstrap-fileinput/themes/explorer-fa/theme.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap-fileinput/themes/fa/theme.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap-fileinput/js/locales/zh.js"></script>
  <!-- jQuery 3 -->
  <script src="${pageContext.request.contextPath}/js/bower_components/jquery/dist/jquery.min.js"></script>
  <!-- Bootstrap 3.3.7 -->
  <script src="${pageContext.request.contextPath}/js/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
  <!-- AdminLTE App -->
  <script src="${pageContext.request.contextPath}/js/dist/js/adminlte.min.js"></script>
  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->

  <!-- Google Font -->
  <link rel="stylesheet"
        href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">



  <link href="${pageContext.request.contextPath}/js/bootstrap-fileinput/css/xlstyle.css" rel="stylesheet">

  <!-- 图片上传即使预览插件 -->
  <link rel="stylesheet"
        href="${pageContext.request.contextPath}/js/bootstrap-fileinput/css/fileinput.min.css">
  <script type="text/javascript"
          src="${pageContext.request.contextPath}/js/bootstrap-fileinput/js/fileinput.min.js"></script>
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
    <a href="../starter" class="logo">
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
              <img src="${merchant.merchantLogoURL}" class="user-image" alt="User Image">
              <!-- hidden-xs hides the username on small devices so only the image appears. -->
              <span class="hidden-xs">${merchant.name}</span>
            </a>
            <ul class="dropdown-menu">
              <!-- The user image in the menu -->
              <li class="user-header">
                <img src="${merchant.merchantLogoURL}" class="img-circle" alt="User Image">

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
         <img src="${merchant.merchantLogoURL}" class="img-circle" alt="User Image">
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
       <li class="active"><a href="../showData"><i class="fa fa-link"></i> <span>统计信息</span></a></li>
       <li class="active"><a href="../merchant/editMerchantInformation"><i class="fa fa-link"></i>
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
<div class="box box-warning">
            <div class="box-header with-border">
              <h3 class="box-title">商户信息修改</h3>
            </div>
            <!-- /.box-header -->
            <div class="box-body">
              <form role="form" action="submitEditMerchantInformation">
                <!-- text input -->

                <div class="form-group">
                  <label>商户名称</label>
                  <input name="name" type="text" class="form-control" placeholder="输入商户名称" value=${merchant.name}>
                </div>
				<div class="form-group">
                  <label>商户信息</label>
                  <input name="description" type="text" class="form-control" placeholder="输入商户信息" value=${merchant.description}>
                </div>
				<div class="form-group">
                  <label>会员卡信息</label>
                  <input name="cardDescription" type="text" class="form-control" placeholder="输入会员卡信息" value=${merchant.cardDescription}>
                </div>
                <div class="form-group">
                  <label>地址</label>
                  <input name="atock" type="text" class="form-control" placeholder="商家所在地址" value=${merchant.address}>
                </div>
                <div class="form-group">
                  <label>商户LOGO</label>
                  <input type="file" name="myfile1" data-ref="url2"
                         class="col-sm-10 myfile1" value="" /> <input type="hidden"
                                                                     name="url2" value="">
                </div>
                <div class="form-group">
                  <label>卡LOGO</label>
                  <input type="file" name="myfile2" data-ref="url3"
                         class="col-sm-10 myfile2" value="" /> <input type="hidden"
                                                                     name="url3" value="">
                </div>
                   <button value=${merchant.merchantID} type="submit" class="btn btn-success">提 交</button>

              </form>

            </div>
            <!-- /.box-body -->
          </div>
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



<!-- Optionally, you can add Slimscroll and FastClick plugins.
     Both of these plugins are recommended to enhance the
     user experience. -->
</body>
<script type="text/javascript">
    $(".myfile1").fileinput({
        //上传的地址
        uploadUrl:"uploadFile1",
        uploadAsync : false, //默认异步上传
        showUpload : false, //是否显示上传按钮,跟随文本框的那个
        showRemove : false, //显示移除按钮,跟随文本框的那个
        showCaption : true,//是否显示标题,就是那个文本框
        showPreview : true, //是否显示预览,不写默认为true
        dropZoneEnabled : false,//是否显示拖拽区域，默认不写为true，但是会占用很大区域
        //minImageWidth: 50, //图片的最小宽度
        //minImageHeight: 50,//图片的最小高度
        //maxImageWidth: 1000,//图片的最大宽度
        //maxImageHeight: 1000,//图片的最大高度
        //maxFileSize: 0,//单位为kb，如果为0表示不限制文件大小
        //minFileCount: 0,
        maxFileCount : 1, //表示允许同时上传的最大文件个数
        enctype : 'multipart/form-data',
        validateInitialCount : true,
        previewFileIcon : "<i class='glyphicon glyphicon-king'></i>",
        msgFilesTooMany : "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
        allowedFileTypes : [ 'image' ],//配置允许文件上传的类型
        allowedPreviewTypes : [ 'image' ],//配置所有的被预览文件类型
        allowedPreviewMimeTypes : [ 'jpg', 'png', 'gif' ],//控制被预览的所有mime类型
        language : 'zh'
    })
    //异步上传返回结果处理
    $('.myfile1').on('fileerror', function(event, data, msg) {
        console.log("fileerror");
        console.log(data);
    });
    //异步上传返回结果处理
    $(".myfile1").on("fileuploaded", function(event, data, previewId, index) {
        console.log("fileuploaded");
        var ref = $(this).attr("data-ref");
        $("input[name='" + ref + "']").val(data.response.url);

    });

    //同步上传错误处理
    $('.myfile1').on('filebatchuploaderror', function(event, data, msg) {
        console.log("filebatchuploaderror");
        console.log(data);
    });

    //同步上传返回结果处理
    $(".myfile1").on("filebatchuploadsuccess",
        function(event, data, previewId, index) {
            console.log("filebatchuploadsuccess");
            console.log(data);
        });

    //上传前
    $('.myfile1').on('filepreupload', function(event, data, previewId, index) {
        console.log("filepreupload");
    });

    $(".myfile2").fileinput({
        //上传的地址
        uploadUrl:"uploadFile2",
        uploadAsync : false, //默认异步上传
        showUpload : false, //是否显示上传按钮,跟随文本框的那个
        showRemove : false, //显示移除按钮,跟随文本框的那个
        showCaption : true,//是否显示标题,就是那个文本框
        showPreview : true, //是否显示预览,不写默认为true
        dropZoneEnabled : false,//是否显示拖拽区域，默认不写为true，但是会占用很大区域
        //minImageWidth: 50, //图片的最小宽度
        //minImageHeight: 50,//图片的最小高度
        //maxImageWidth: 1000,//图片的最大宽度
        //maxImageHeight: 1000,//图片的最大高度
        //maxFileSize: 0,//单位为kb，如果为0表示不限制文件大小
        //minFileCount: 0,
        maxFileCount : 1, //表示允许同时上传的最大文件个数
        enctype : 'multipart/form-data',
        validateInitialCount : true,
        previewFileIcon : "<i class='glyphicon glyphicon-king'></i>",
        msgFilesTooMany : "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
        allowedFileTypes : [ 'image' ],//配置允许文件上传的类型
        allowedPreviewTypes : [ 'image' ],//配置所有的被预览文件类型
        allowedPreviewMimeTypes : [ 'jpg', 'png', 'gif' ],//控制被预览的所有mime类型
        language : 'zh'
    })
    //异步上传返回结果处理
    $('.myfile2').on('fileerror', function(event, data, msg) {
        console.log("fileerror");
        console.log(data);
    });
    //异步上传返回结果处理
    $(".myfile2").on("fileuploaded", function(event, data, previewId, index) {
        console.log("fileuploaded");
        var ref = $(this).attr("data-ref");
        $("input[name='" + ref + "']").val(data.response.url);

    });

    //同步上传错误处理
    $('.myfile2').on('filebatchuploaderror', function(event, data, msg) {
        console.log("filebatchuploaderror");
        console.log(data);
    });

    //同步上传返回结果处理
    $(".myfile2").on("filebatchuploadsuccess",
        function(event, data, previewId, index) {
            console.log("filebatchuploadsuccess");
            console.log(data);
        });

    //上传前
    $('.myfile2').on('filepreupload', function(event, data, previewId, index) {
        console.log("filepreupload");
    });
</script>
</html>