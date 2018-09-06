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
    <c:forEach items="${activities}" var="activity">
        <tr>
            <td>
                <a href="#" class="thumbnail"><img src=${activity.imageURL} alt="${activity.name}" height="50" width="50"></a>
            </td>
            <td>${activity.name}</td>
            <td>${activity.description}</td>
            <td>${activity.startDate}</td>
            <td>${activity.endDate}</td>
            <td><button class="layui-btn" onclick="load('activity/editActivity?activityID=${activity.activityID}')">编辑</button> </td>
            <td><button class="layui-btn layui-btn-danger" onclick="load('activity/delete?activityID=${activity.activityID}')">删除</button></td>
        </tr>
    </c:forEach>
    </tbody>
</table>