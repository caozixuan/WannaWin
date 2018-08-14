<%--
  Created by IntelliJ IDEA.
  User: lzb
  Date: 2018/8/10
  Time: 10:23
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         isELIgnored="false"%>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>订单积分消费情况统计</legend>
</fieldset>
<div id="order-table" style="width: 100%;height:400px;"></div>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>会员卡积分兑换情况统计</legend>
</fieldset>
<div id="point-table" style="width: 100%;height:400px;"></div>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>积分使用分布图</legend>
</fieldset>
<div id="pie-table" style="width: 100%;height:400px;"></div>
<script src="${pageContext.request.contextPath}/statics/js/echarts.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/statics/js/showData.js?v=2.0"></script>


