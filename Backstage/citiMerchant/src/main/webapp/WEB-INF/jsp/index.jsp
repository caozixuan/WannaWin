<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         isELIgnored="false"%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>商户管理界面</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/layui/css/layui.css">
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">商户管理界面</div>
        <!-- 头部区域（可配合layui已有的水平导航） -->
        <ul class="layui-nav layui-layout-left">
            <li class="layui-nav-item"><a href="">控制台</a></li>
            <li class="layui-nav-item"><a href="">商品管理</a></li>
            <li class="layui-nav-item"><a href="">用户</a></li>
            <li class="layui-nav-item">
                <a href="javascript:;">其它系统</a>
                <dl class="layui-nav-child">
                    <dd><a href="">邮件管理</a></dd>
                    <dd><a href="">消息管理</a></dd>
                    <dd><a href="">授权管理</a></dd>
                </dl>
            </li>
        </ul>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <img src="${merchant.merchantLogoURL}" class="layui-nav-img">
                    贤心
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="">基本资料</a></dd>
                    <dd><a href="">安全设置</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item"><a href="/logout">退了</a></li>
        </ul>
    </div>


    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree"  lay-filter="test">
                <li class="layui-nav-item layui-nav-itemed">
                    <a class="" href="javascript:;">商品优惠</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:load('item/getItem');">商品清单</a></dd>
                        <dd><a href="javascript:load('item/addItem');">添加商品</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item"><a href="javascript:;">减免策略</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:load('strategy/getStrategyList');">策略清单</a></dd>
                        <dd><a href="javascript:load('strategy/addStrategy');">添加策略</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item"><a href="javascript:;">活动信息</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:load('activity/all');">活动清单</a></dd>
                        <dd><a href="javascript:load('activity/addActivity');">添加活动</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item"><a href="javascript:load('history');">历史订单</a></li>
                <li class="layui-nav-item"><a href="javascript:load('showData');">统计信息</a></li>
                <li class="layui-nav-item"><a href="javascript:load('merchant/editMerchant')">商户信息</a></li>
            </ul>
        </div>
    </div>

    <div class="layui-body">
        <!-- 内容主体区域 -->
        <div id="content-body" style="padding: 15px;" >
            <script type="text/javascript">
                window.onload = function(){load('item/getItem');};
            </script>

        </div>
    </div>

    <div class="layui-footer">
        © layui.com - 底部固定区域
    </div>
</div>
<script src="${pageContext.request.contextPath}/statics/layui/layui.all.js"></script>
<script src="${pageContext.request.contextPath}/statics/js/jquery/jquery-3.3.1.min.js"></script>
<script src="${pageContext.request.contextPath}/statics/js/util.js?v=3.0"></script>
<script src="${pageContext.request.contextPath}/statics/js/jquery/jquery.form.js"></script>
</body>
</html>
