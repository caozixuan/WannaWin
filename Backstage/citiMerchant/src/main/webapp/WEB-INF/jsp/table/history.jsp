<%--
  Created by IntelliJ IDEA.
  User: lzb
  Date: 2018/8/9
  Time: 22:33
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         isELIgnored="false"%>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>订单列表</legend>
</fieldset>
<table class="layui-table">
    <thead>
    <tr><th>原价</th><th>抵扣后价格</th><th>所用积分</th><th>时间</th><th>状态</th></tr>
    </thead>
    <tbody>
    <c:forEach items="${orders}" var="order">
        <tr>
            <td>${order.originalPrice}</td>
            <td>${order.priceAfter}</td>
            <td>${order.pointsNeeded}</td>
            <td>${order.time}</td>
            <td>${order.state}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
