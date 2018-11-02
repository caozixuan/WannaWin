<%--
  Created by IntelliJ IDEA.
  User: lzb
  Date: 2018/8/9
  Time: 21:58
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         isELIgnored="false"%>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>上架策略</legend>
</fieldset>
<form class="layui-form layui-form-pane" method="post" id="add-item-form">
    <input type="hidden" name="strategyID" value="${strategy.strategyID}">
    <div class="layui-form-item">
        <label class="layui-form-label">需满金额</label>
        <div class="layui-input-inline">
            <input type="text" name="full" lay-verify="required|number" placeholder="输入需满金额" autocomplete="off" class="layui-input" value="${strategy.full}">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">折扣后金额</label>
        <div class="layui-input-inline">
            <input type="text" name="priceAfter" lay-verify="required|number" placeholder="输入折扣后金额" autocomplete="off" class="layui-input" value="${strategy.priceAfter}">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">所需积分</label>
        <div class="layui-input-inline">
            <input type="text" name="points" lay-verify="required|number" placeholder="输入所需积分" autocomplete="off" class="layui-input" value="${strategy.points}">
        </div>
    </div>

    <button class="layui-btn" lay-submit="" type="submit">提交</button>
    <button class="layui-btn layui-btn-primary" type="reset">重新输入</button>
</form>

<script type="text/javascript">
    $(function(){
        $("#add-item-form").ajaxForm({
            url: "strategy/editStrategySubmit",
            type: "post",
            dataType: "json",
            success: function(responseText) {
                var status = responseText.status;
                if(status == "fail"){

                }
                else if(status == "success"){
                    $("#content-body").load("strategy/getStrategyList");
                }
            }
        });
    });
</script>