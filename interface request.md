### 1 - 刘瑞康 ###
- 获取指定用户的generalPoints、 availablePoints
- 暂定post userID=xxx
- 若不存在userID或其他错误情况返回空json {}
- 正确则返回{"generalPoints" : int ,"availablePoints" : int }

### 2 - 刘瑞康 ###
- 获取指定ID的商家信息
```
xxxx/merchant/{MerchantID}
{"merchantID":"xxxx","merchantName":"xxxx","description":"xxxx","logoURL":"xxxx"}
```
- 需新增imageURL，提供商家卡片图片的URL

### 3 - 潘艳玥 ###
- ```
xxxx/mscard/cardtype
merchantID
```
- ```
[{"MerchantID":"xxxx","Mtype":"xxxx","cardType":"xxxx"},{},{}...]
```
- 添加几类代表性cardType用于测试，该值不要返回空