<%--
  Created by IntelliJ IDEA.
  User: zhong
  Date: 2018/8/21
  Time: 15:58
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         isELIgnored="false"%>
<table class="layui-table">
    <thead>
    <tr><th>图片</th><th>名称</th><th>简述</th><th>开始日期</th><th>截止日期</th><th>操作</th></tr>
    </thead>
    <tbody>
    <c:forEach items="${items}" var="item">
        <tr>
            <td>
                <a href="#" class="thumbnail"><img src=${item.logoURL} alt="${item.name}" height="50" width="50"></a>
            </td>
            <td>${item.name}</td>
            <td>${item.description}</td>
            <td>${item.originalPrice}</td>
            <td>${item.points}</td>
            <td><button class="layui-btn" onclick="load('item/editItem?itemID=${item.itemID}')">编辑</button> </td>
            <td><button class="layui-btn layui-btn-danger" onclick="load('item/deleteItem?itemID=${item.itemID}')">删除</button></td>
        </tr>
    </c:forEach>
    </tbody>
</table>