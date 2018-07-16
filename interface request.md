# 十万火急 #
## 1 ##
	getPoints
	获得当前用户的全部通用积分和可兑换积分
	参数:
	userID - 用户id
	返回:
	成功:{"generalPoints":0,"availablePoints":0}
	失败(用户不存在)：{}

## 2 ##
	getMSInfo
	请求积分最多的前n张卡（顺序暂时不重要）
	参数:
	userID - 用户id
	n - 请求卡的数量
	返回:
	若当前用户未绑定任何卡：[]
	有卡则按积分大小顺序：
	[{"cardID":"a7a8f255-f129-4b46-9de2-55f07c7ff65e","cardNum":"888888","points":0,"proportion":1,"logoURL":"xx","merchantName":"xxx"}]

## 3 ##
    cardID
    
    return {"cardID":"a7a8f255-f129-4b46-9de2-55f07c7ff65e","cardNum":"888888","points":0,"proportion":1,"logoURL":"xx","merchantName":"xxx"}

### 4 ###
查看历史订单

###coupon
    userID start length
    
    return {"merchantName"：String,"pointsNeed":}

#接口3.0

## 登录注册
- 忘记密码


```
发送验证码：phoneNum
return:{"status":true}

验证验证码：phoneNum,vcode
return:{"status":true}

重置密码:phoneNum,newPassword
return:{"status":true}

```

- 积分

```
数据库
//卡片加了使用规则，返回时可通用积分
//mscard加密码字段
//merchant的logoURL改为merchantLogoURL
```
```
请求地址： /mscard/infos
查看所有卡片，这个接口融合了后面进入积分兑换页面的接口
参数：userID，n，orderType
return:
merchantID,merchantLogoURL,merchantName,points,proportion,logoURL
```
```
详情页
请求地址： /mscard/getDetailCard
参数：userID,merchantID
return:
cardLogoURL,points,cardNum,cardDescription,type, proportion
```
```
解绑:
参数：userID,cardID
return {"status":true}
```
- 抵扣：


```
二维码：userID，timeStamp（有效期一分钟，轮询）
return:
status: 失效，未失效未使用，抵扣成功,抵扣失败
抵扣成功：
totalPrice总价
PriceAfter折后价，实际支付
PointsNeeded花费积分数
抵扣失败：
totalPrice总价
PriceAfter折后价，实际支付
PointsNeeded最小所需积分数

前端：切换界面停止轮询
后台没有收到轮询但交易成功发送短信
```

- 积分兑换
请求地址： /points/changePoints

```
参数:
{"userID":"","merchants":[{"merchantID":"","selectedMSCardPoints":""}]}
return:
成功：
[]
失败：
[{merchantID，merchantName,merchantLogoURL,reason}]
```

- 进入积分兑换页面


```
参数：userID，orderType
return:
[{"points":0,"proportion":1,"logoURL":"xx","merchantName":"xxx","merchantID":"xx"}]

```


- 返回有几张卡
请求地址： /mscard/getNum
参数：userID
return:
{"num":2}

- 单张卡兑换记录




- 显示地点
- 反馈






    
    