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
	[{"cardID":"a7a8f255-f129-4b46-9de2-55f07c7ff65e","card_No":"888888","points":0,"canExchangePoints":0,"exchangeRate":1,"logoURL":"xx","cardName":"xxx"}]



### 1 - 刘瑞康 ###
- 获取指定用户的generalPoints、 availablePoints
- 暂定post userID=xxx
- 若不存在userID或其他错误情况返回空json {}
- 正确则返回{"generalPoints" : int ,"availablePoints" : int }
