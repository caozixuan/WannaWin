//
//  ServerConnector.swift
//  PointExchange
//
//  Created by panyy on 2018/7/8.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import UIKit
import Moya
import SwiftyJSON
import Alamofire

class ServerConnector: NSObject {
    
    static var provider: MoyaProvider<ServerService>{
        //自定义manager
        let securityPolicy:[String:ServerTrustPolicy]=[
            "193.112.44.141":.disableEvaluation
        ]
        let config = URLSessionConfiguration.default
        config.timeoutIntervalForRequest = 10
        let manager = Manager(configuration: config,serverTrustPolicyManager:ServerTrustPolicyManager(policies: securityPolicy))
//        let manager = Manager(configuration: config)
        
        return MoyaProvider<ServerService>(manager:manager,plugins: [NetworkLoggerPlugin(verbose: true)])
    }
    
    /// 获取验证码
    static func getVCode(phoneNumber:String){
        provider.request(.getVCode(phoneNumber:phoneNumber)){result in
            if case let .success(response) = result {
                print("发送验证码成功")
            }
        }
        
    }
    /// 提交密码并验证验证码
    static func sendPassword(phoneNumber:String, vcode:String, password:String,callback:@escaping (_ result:Bool)->()){
        provider.request(.sendPassword(phoneNumber:phoneNumber, vcode:vcode, password: password)){ result in
            if case let .success(response) = result{
                let data = JSON(try? response.mapJSON())
                if data["isCreate"].bool == true{
                    callback(true)
                }else{
                    callback(false)
                }
            }
            if case let .failure(response) = result{
                callback(false)
                print("连接失败")
            }
        }
    }
    
    /// 登录
    static func login(phoneNum:String, password:String,callback:@escaping (_ result:Bool)->()){
        provider.request(.login(phoneNum:phoneNum, password:password)){ result in
            if case let .success(response) = result{
                let data = JSON(try? response.mapJSON())
                let isLogin = data.count
                if isLogin != 0 {
                    User.getUser().generalPoints = data["generalPoints"].double
                    User.getUser().availablePoints = data["availablePoints"].double
                    User.getUser().id = data["userID"].string
                    print(data["userID"].string)
                    callback(true)
                }
                else{
                    callback(false)
                }
            }
            if case let .failure(response) = result{
                callback(false)
                print("连接失败")
            }
			callback(true)
        }
    }
    /// 获得积分信息
    static func getPointsInfo(callback:@escaping (_ result:Bool)->()){
        if let id = User.getUser().id{
            provider.request(.getPointsInfo(userID: id)){result in
                if case let .success(response) = result {
                    if response.statusCode == 200 {
                        let data = JSON(try? response.mapJSON())
                        User.getUser().availablePoints = data["availablePoints"].double
                        User.getUser().generalPoints = data["generalPoints"].double
                        callback(true)
                    }else{
                        callback(false)
                    }
                    
                }
                if case let .failure(response) = result{
                    callback(false)
                    print("连接失败")
                }
                
            }
        }
        else{
            callback(false)
        }
        
    }
    
    /// 修改密码
    static func changePassword(oldPassword:String, newPassword:String, callback:@escaping (_ result:Bool)->()){
        provider.request(.changePassword(old: oldPassword, new: newPassword)){ result in
            if case let .success(response) = result{
                let data = JSON(try? response.mapJSON())
                let status = data["status"].bool
                if status != false {
                    callback(true)
                }
                else{
                    callback(false)
                }
            }
            if case let .failure(response) = result{
                callback(false)
                print("连接失败")
            }
        }
    }
    /// 重置密码
    static func resetPassword(phoneNum:String, newPassword:String,callback:@escaping (_ result:Bool)->()){
        provider.request(.resetPassword(phoneNum: phoneNum, newPassword: newPassword)){ result in
            if case let .success(response) = result{
                let data = JSON(try? response.mapJSON())
                let status = data["status"].bool
                if status != false {
                    callback(true)
                }
                else{
                    callback(false)
                }
            }
            if case let .failure(response) = result{
                callback(false)
            }
        }
    }
    
    /// 获取重置密码的验证码
    static func getResetVCode(phoneNum:String, vcode:String, callback:@escaping (_ result:Bool)->()){
        provider.request(.getResetVCode(phoneNum:phoneNum, vcode: vcode)){ result in
            if case let .success(response) = result{
                let data = JSON(try? response.mapJSON())
                let status = data["status"].bool
                if status != false {
                    callback(true)
                }
                else{
                    callback(false)
                }
            }
            if case let .failure(response) = result{
                callback(false)
            }
        }
    }
    //花旗卡相关
    /// 绑定花旗银行卡
    static func bindCard(citiCardNum:String,phoneNum:String,ID:String,password:String,callback:@escaping (_ result:Bool)->()){
        provider.request(.bindCard(citiCardNum:citiCardNum, phoneNum:phoneNum, ID:ID, password:password)){ result in
            if case let .success(response) = result{
                let data = JSON(try? response.mapJSON())
                let isBinding = data["isBinding"]
                if isBinding != "false" {
                    callback(true)
                }
                else{
                    callback(false)
                }
            }
            if case let .failure(response) = result{
                callback(false)
                print("连接失败")
            }
        }
    }
    /// 解绑银行卡
    static func unbind(citiCardNum:String,phoneNum:String,ID:String,password:String,callback:@escaping (_ result:Bool)->()){
        provider.request(.unbind(citiCardNum:citiCardNum, phoneNum:phoneNum, ID:ID, password:password)){ result in
            if case let .success(response) = result{
                let data = JSON(try? response.mapJSON())
                let isUnBinding = data["unBinding"]
                if isUnBinding != "false" {
                    callback(true)
                }
                else{
                    callback(false)
                }
            }
            if case let .failure(response) = result{
                callback(false)
                print("连接失败")
            }
        }
    }
    
    // 商户相关
    /// 获取从start开始的n条商户信息
    static func getMerchantsInfos(start:Int,n:Int,callback:@escaping (_ result:Bool, _ merchants:[(Merchant)])->()){
        provider.request(.getMerchantsInfos(start:start, n:n)){ result in
            if case let .success(response) = result{
                let dataJson = try? response.mapJSON()
                var merchants = [Merchant]()
                if let json = dataJson {
                    let datas = JSON(json).array
                    for data in datas! {
                        let merchant = Merchant()
                        merchant.id = data["merchantID"].string!
                        merchant.name = data["name"].string!
                        merchant.description = data["description"].string!
                        merchant.logoURL = data["logoURL"].string!
                        merchant.address = data["address"].string!
                        merchants.append(merchant)
                    }
                    callback(true,merchants)
                }else{
                    callback(false,merchants)
                }
            }
            if case let .failure(response) = result{
                callback(false,[Merchant]())
                print("连接失败")
            }
        }
    }
    /// 根据id获得商户信息
    static func getMerchantInfoByID(id:String,callback:@escaping (_ result:Bool, _ mechant:Merchant)->()){
        provider.request(.getMerchantInfoByID(id: id)){ result in
            if case let .success(response) = result{
                let dataJSON = try? response.mapJSON()
                let merchant = Merchant()
                if let json = dataJSON {
                    let data = JSON(json)
                    merchant.id = data["merchantID"].string!
                    merchant.name = data["name"].string!
                    merchant.description = data["description"].string!
                    merchant.logoURL = data["logoURL"].string!
                    callback(true,merchant)
                }else{
                    callback(false,merchant)
                }
            }
            if case let .failure(response) = result{
                callback(false,Merchant())
                print("连接失败")
            }
        }
    }
    
    // 会员卡相关
    /// 获取指定用户积分最多的n张卡
    static func getMostPointCards(userID:String, n:Int, callback:@escaping (_ result:Bool, _ cards:[Card])->()){
        provider.request(.getMostPointCards(userID:userID,n:n)){ result in
            if case let .success(response) = result{
                let dataJSON = try? response.mapJSON()
                var cards = [Card]()
                if let json = dataJSON {
                    let datas = JSON(json).array
                    
                    for data in datas! {
                        let card = Card()
                        card.id = data["cardID"].string
                        card.userID = data["userID"].string
                        card.number = data["cardNo"].string
                        card.point = data["points"].double!
                        cards.append(card)
                    }
                    callback(true,cards)
                }else{
                    callback(false,cards)
                }
            }
            if case let .failure(response) = result{
                callback(false,[Card]())
                print("连接失败")
            }
            
        }
    }
    /// 返回商户所有卡的类型
    static func getCardTypeByUserID(merchantID:String, callback:@escaping (_ result:Bool, _ cardTypes:[CardType])->()){
        // TODO: 返回商户所有卡的类型
        provider.request(.getCardTypeByUserID(merchantID:merchantID)){ result in
            if case let .success(response) = result{
                let dataJSON = try? response.mapJSON()
                var types = [CardType]()
                if let json = dataJSON {
                    let datas = JSON(json).array
                    for data in datas! {
                        var cardType = CardType()
                        cardType.merchantID = data["MerchantID"].string
                        cardType.mType = data["MType"].string
                        cardType.cardType = data["CardType"].string
                        cardType.proportion = data["Proportion"].double
                        cardType.miniExpense = data["MiniExpense"].string
                        types.append(cardType)
                    }
                    callback(true,types)
                }else{
                    callback(false,types)
                }
            }
            if case let .failure(response) = result{
                callback(false,[CardType]())
                print("连接失败")
            }
        }
    }
    /// 添加会员卡
    static func addCard(cardID:String, userID:String, cardNo:String, msCardType:String, callback:@escaping (_ result:Bool)->()){
        provider.request(.addCard(cardID: cardID, UserID: userID, cardNo: cardNo, msCardType: msCardType)){ result in
            if case let .success(response) = result{
                let data = JSON(try? response.mapJSON())
                if data["isCreate"].bool == true{
                    callback(true)
                }else{
                    callback(false)
                }
                
            }
            
        }
        
    }
}
