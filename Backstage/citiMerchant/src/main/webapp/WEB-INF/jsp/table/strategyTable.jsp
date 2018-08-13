<%--
  Created by IntelliJ IDEA.
  User: lzb
  Date: 2018/8/9
  Time: 21:43
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         isELIgnored="false"%>
<table class="layui-table">
    <thead>
    <tr><th>需满金额</th><th>折扣后金额</th><th>所需积分</th><th>操作</th></tr>
    </thead>
    <tbody>
    <c:forEach items="${strategies}" var="strategy">
        <tr>
            <td>${strategy.full}</td>
            <td>${strategy.priceAfter}</td>
            <td>${strategy.points}</td>
            <td><button class="layui-btn" onclick="load('strategy/editStrategy?strategyID=${strategy.strategyID}')">编辑</button> </td>
            <td><button class="layui-btn layui-btn-danger" onclick="load('strategy/deleteStrategy?strategyID=${strategy.strategyID}')">删除</button></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<script type="text/javascript" src="${pageContext.request.contextPath}/statics/js/util.js"></script>
