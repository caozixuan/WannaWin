#登陆注册相关
获取验证码：
```$xslt
url:xxx/login/getVCode
参数：phoneNum
无返回
```
提交密码并，验证验证码:
```$xslt
url:xxx/login/sendVCode
参数：phoneNum，vcode,password
返回值:{"isCreate": flag｝
```
登陆:
```$xslt
xxx/login/user
phoneNum,password
成功：{"userID":"xxxx","generalPoints":0,"availablePoints":0}
失败：{"isLogin":false}

PS：返回的请求头中会包含类似JSESSIONID=CA673FE11BECB01FDED74DD081EA3017
的cookie，请保存并在之后每次请求时加在cookie当中，以维持登陆状态
```
#花旗卡相关
绑定花旗卡:
```
xxxx/citi/bindCard
citiCardNum,phoneNum,ID,password
{"isBinding":flag}
```
解绑花旗卡:
```
xxxx/citi/unbind
citiCardNum,phoneNum,ID,password
{"unBinding":flag}
```
#商户相关


