<%--
  Created by IntelliJ IDEA.
  User: lzb
  Date: 2018/8/9
  Time: 22:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         isELIgnored="false"%>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>商户信息</legend>
</fieldset>
<form class="layui-form layui-form-pane" method="post" id="add-item-form">
    <input type="hidden" name="itemID" value="${merchant.merchantID}"/>
    <div class="layui-form-item">
        <label class="layui-form-label">商户名称</label>
        <div class="layui-input-inline">
            <input type="text" name="name" lay-verify="required" placeholder="请输入商品名称" autocomplete="off" class="layui-input" value="${merchant.name}">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">商户信息</label>
        <div class="layui-input-inline">
            <input type="text" name="description" lay-verify="required" placeholder="输入商户信息" autocomplete="off" class="layui-input" value="${merchant.description}">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">会员卡信息</label>
        <div class="layui-input-inline">
            <input type="text" name="cardDescription" lay-verify="required" placeholder="输入会员卡信息" autocomplete="off" class="layui-input" value="${merchant.cardDescription}">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">地址</label>
        <div class="layui-input-inline">
            <input type="text" name="address" lay-verify="address" placeholder="输入地址" autocomplete="off" class="layui-input" value="${merchant.address}">
        </div>
    </div>



    <div class="layui-form-item">
        <label class="layui-form-label">商户LOGO</label>
        <div class="layui-upload-drag" id="upload-merchantLOGO">
            <i class="layui-icon"></i>
            <p>点击上传，或将文件拖拽到此处</p>
        </div>
        <input id="url2" type="hidden" name="url2" value="${merchant.merchantLogoURL}">
    </div>


    <div class="layui-form-item">
        <label class="layui-form-label">卡LOGO</label>
        <div class="layui-upload-drag" id="upload-cardLOGO">
            <i class="layui-icon"></i>
            <p>点击上传，或将文件拖拽到此处</p>
        </div>
        <input id="url3" type="hidden" name="url3" value="${merchant.cardLogoURL}">
    </div>


    <button class="layui-btn" lay-submit="" type="submit">提交</button>
    <button class="layui-btn layui-btn-primary" type="reset">重新输入</button>
</form>
<script type="text/javascript">
    //上传图片渲染
    layui.upload.render({
        elem: '#upload-merchantLOGO'
        ,url: 'uploadFile/merchant'
        ,done: function(res){
            if (res.status=="success"){
                $("#url2").val(status.url);
            }
        }
    });

    layui.upload.render({
        elem: '#upload-cardLOGO'
        ,url: 'uploadFile/card'
        ,done: function(res){
            if (res.status=="success"){
                $("#url3").val(status.url);
            }
        }
    });

    $(function(){
        $("#add-item-form").ajaxForm({
            url: "merchant/submitEditMerchantInformation",
            type: "post",
            dataType: "json",
            success: function(responseText) {
                var status = responseText.status;
                if(status == "success"){
                    alert("更新成功")
                }
            }
        });
    });

</script>