<%--
  Created by IntelliJ IDEA.
  User: zhong
  Date: 2018/8/28
  Time: 10:45
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         isELIgnored="false"%>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>活动编辑</legend>
</fieldset>
<form class="layui-form layui-form-pane" method="post" id="add-item-form">
    <input type="hidden" name="activityID" value="${activity.activityID}"/>
    <input type="hidden" name="merchantID" value="${activity.merchantID}">
    <div class="layui-form-item">
        <label class="layui-form-label">活动名称</label>
        <div class="layui-input-inline">
            <input type="text" name="name" lay-verify="required" placeholder="请输入商品名称" autocomplete="off" class="layui-input" value="${activity.name}">
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">活动起始时间</label>
            <div class="layui-input-block">
                <input type="text" name="overdueTime" lay-verify="date" id="startDate" class="layui-input" value="${item.overdueTime}">
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">活动截止时间</label>
            <div class="layui-input-block">
                <input type="text" name="overdueTime" lay-verify="date" id="endDate" class="layui-input" value="${item.overdueTime}">
            </div>
        </div>
    </div>

    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">活动描述</label>
        <div class="layui-input-block">
            <textarea placeholder="请输入内容" class="layui-textarea" name="description">${activity.description}</textarea>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">商品图片</label>
        <div class="layui-upload-drag" id="upload-img">
            <i class="layui-icon"></i>
            <p>点击上传，或将文件拖拽到此处</p>
        </div>
    </div>
    <input id="image-url" type="hidden" name="logoURL" value="${activity.imageURL}">
    <button class="layui-btn" lay-submit="" type="submit">提交</button>
    <button class="layui-btn layui-btn-primary" type="reset">重新输入</button>
</form>
<script type="text/javascript">
    //日期渲染
    layui.use('laydate', function(){
        var laydate = layui.laydate;
        //执行一个laydate实例
        laydate.render({
            elem: '#startDate' //指定元素
        });
        laydate.render({
            elem:'#endDate'
        });
    });
    //上传图片渲染
    layui.upload.render({
        elem: '#upload-img'
        ,url: 'upload/activity'
        ,done: function(res){
            alert(res.status);
            if (res.status=="success"){
                $("#image-url").val(res.url);
                var img="<img src='"+res.url+"'>";
                $("#upload-img").append(img)
            }
        }
    });

    $(function(){
        $("#add-item-form").ajaxForm({
            url: "activity/submit",
            type: "post",
            dataType: "json",
            success: function(responseText) {
                var status = responseText.status;
                if(status == "fail"){

                }
                else if(status == "success"){
                    $("#content-body").load("activity/all");
                }
            }
        });
    });

</script>