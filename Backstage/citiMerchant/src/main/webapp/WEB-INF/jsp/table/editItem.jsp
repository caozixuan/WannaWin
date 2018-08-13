<%--
  Created by IntelliJ IDEA.
  User: lzb
  Date: 2018/8/8
  Time: 11:17
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         isELIgnored="false"%>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>商品添加</legend>
</fieldset>
<form class="layui-form layui-form-pane" method="post" id="add-item-form">
    <input type="hidden" name="itemID" value="${item.itemID}"/>
    <div class="layui-form-item">
        <label class="layui-form-label">商品名称</label>
        <div class="layui-input-inline">
            <input type="text" name="name" lay-verify="required" placeholder="请输入商品名称" autocomplete="off" class="layui-input" value="${item.name}">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">商品原价</label>
        <div class="layui-input-inline">
            <input type="text" name="originalPrice" lay-verify="required|number" placeholder="输入商品原价" autocomplete="off" class="layui-input" value="${item.originalPrice}">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">抵扣积分</label>
        <div class="layui-input-inline">
            <input type="text" name="points" lay-verify="required|number" placeholder="输入抵扣的花旗积分" autocomplete="off" class="layui-input" value="${item.points}">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">库存数量</label>
        <div class="layui-input-inline">
            <input type="text" name="stock" lay-verify="number" placeholder="可兑换总数，若没有限制可不填" autocomplete="off" class="layui-input" value="${item.stock}">
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">抵扣券有效时间</label>
            <div class="layui-input-block">
                <input type="text" name="overdueTime" lay-verify="date" id="overdueTime" class="layui-input" value="${item.overdueTime}">
            </div>
        </div>
    </div>

    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">商品描述</label>
        <div class="layui-input-block">
            <textarea placeholder="请输入内容" class="layui-textarea" name="description">${item.description}</textarea>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">商品图片</label>
        <div class="layui-upload-drag" id="upload-img">
            <i class="layui-icon"></i>
            <p>点击上传，或将文件拖拽到此处</p>
        </div>
    </div>
    <input id="image-url" type="hidden" name="logoURL" value="${item.logoURL}">
    <button class="layui-btn" lay-submit="" type="submit">提交</button>
    <button class="layui-btn layui-btn-primary" type="reset">重新输入</button>
</form>
<script type="text/javascript">
    //日期渲染
    layui.use('laydate', function(){
        var laydate = layui.laydate;
        //执行一个laydate实例
        laydate.render({
            elem: '#overdueTime' //指定元素
        });
    });
    //上传图片渲染
        layui.upload.render({
        elem: '#upload-img'
        ,url: 'item/uploadFile'
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
            url: "item/submitEdit",
            type: "post",
            dataType: "json",
            success: function(responseText) {
                var status = responseText.status;
                if(status == "fail"){

                }
                else if(status == "success"){
                    $("#content-body").load("item/getItem");
                }
            }
        });
    });

</script>