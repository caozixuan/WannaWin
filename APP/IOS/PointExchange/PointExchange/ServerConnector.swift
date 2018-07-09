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

class ServerConnector: NSObject {
    
    static var provider: MoyaProvider<ServerService>{
        //自定义manager
        let config = URLSessionConfiguration.default
        config.timeoutIntervalForRequest = 15
        let manager = Manager(configuration: config)
        return MoyaProvider<ServerService>(manager:manager)
    }
    
    /// 获取验证码
    static func getVCode(phoneNumber:String){
        provider.request(.getVCode(phoneNumber:phoneNumber)){_ in
            print("获取验证码成功")
        }
    }
    /// 提交密码并验证验证码
    static func sendPassword(phoneNumber:String, vcode:String, password:String,callback:@escaping (_ result:Bool)->()){
        provider.request(.sendPassword(phoneNumber:phoneNumber, vcode:vcode, password: password)){ result in
            if case let .success(response) = result{
                let data = JSON(try? response.mapJSON())
                // TODO: 处理验证码正确或错误
                if data["isCreate"].bool == true{
                    callback(true)
                }else{
                    callback(false)
                }
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
                    User.getUser().generalPoints = data["generalPoints"].int
                    User.getUser().availablePoints = data["availablePoints"].int
                    callback(true)
                }
                else{
                    callback(false)
                }
            }
            if case let .failure(response) = result{
                print("连接失败")
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
        }
    }
    
    // 商户相关
    /// 获取从start开始的n条商户信息
    static func getMerchantsInfos(start:String,n:Int,callback:@escaping (_ result:Bool, _ merchants:[(Merchant)])->()){
        provider.request(.getMerchantsInfos(start:start, n:n)){ result in
            if case let .success(response) = result{
                let dataJSON = try? response.mapJSON()
                var merchants = [Merchant]()
                if let json = dataJSON {
                    let datas = JSON(json).array
                    
                    for data in datas! {
                        let merchant = Merchant()
                        merchant.id = data["merchantID"].string!
                        merchant.name = data["mechantName"].string!
                        merchant.description = data["description"].string!
                        merchant.logoURL = data["logoURL"].string!
                        merchants.append(merchant)
                    }
                    callback(true,merchants)
                }else{
                    callback(false,merchants)
                }
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
                    merchant.name = data["mechantName"].string!
                    merchant.description = data["description"].string!
                    merchant.logoURL = data["logoURL"].string!
                    callback(true,merchant)
                }else{
                    callback(false,merchant)
                }
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
                        
                        cards.append(card)
                    }
                    callback(true,cards)
                }else{
                    callback(false,cards)
                }
            }
            
        }
    }
    /// 返回商户所有卡的类型
    func getCardTypeByUserID(merchantID:String){
        // TODO: 返回商户所有卡的类型
    }
    /// 添加会员卡
    func addCard(cardID:String, UserID:String, cardNo:String, msCardType:String){
        // TODO: 添加会员卡
    }
}
