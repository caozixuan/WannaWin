//
//  ServerService.swift
//  PointExchange
//
//  Created by panyy on 2018/7/8.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import Foundation
import Moya
import SwiftyJSON

enum ServerService {
    //登录相关
    /// 获取验证码
    case getVCode(phoneNumber:String)
    /// 提交密码并验证验证码
    case sendPassword(phoneNumber:String, vcode:String, password:String)
    /// 登录
    case login(phoneNum:String, password:String)
    /// 获得积分信息
    case getPointsInfo(userID:String)
    
    /// 修改密码
    case changePassword(old:String,new:String)
    /// 重置密码验证码
    case getResetVCode(phoneNum:String, vcode:String)
    /// 重置密码
    case resetPassword(phoneNum:String, newPassword:String)
    
    //花旗卡相关
    /// 绑定花旗银行卡
    case bindCard(citiCardNum:String,phoneNum:String,ID:String,password:String)
    /// 解绑银行卡
    case unbind(citiCardNum:String,phoneNum:String,ID:String,password:String)
    
    // 商户相关
    /// 获取从start开始的n条商户信息
    case getMerchantsInfos(start:Int,n:Int)
    /// 获取指定商家详细信息
    case getMerchantInfoByID(id:String)
    /// 获得商户数量
    case getMerchantCount()
    
    // 会员卡相关
    /// 获取指定用户积分最多的n张卡
    case getMostPointCards(n:Int)
    /// 获取用户会员卡数量
    case getCardCount()
    /// 获取卡详情
    case getCardDetail(merchantID:String)
    /// 绑定会员卡
    case addCard(merchantID:String, cardNum:String,password:String)
    
    // 积分相关
    /// 积分兑换
    case changePoints(merchants:[JSON])
    /// 获取通用积分
    case getGeneralPoints()
    /// 获取可兑换积分
    case getAvailablePoints()
    /// 获取一个人所有积分兑换记录
    case getAllPointsHistory()
    /// 获取一个人某张卡的积分兑换记录
    case getPointsHistoryByMerchantID(merchantID:String)
    
    // 支付相关
    /// 二维码轮询
    case pollingQR(timestamp:String)
    
    // 订单相关
    /// 获取历史订单
    case getOrders(intervalTime:String)
}



extension ServerService:TargetType {
    var baseURL:URL{
        return URL(string: "http://193.112.44.141:80/citi")!
    }
    
    var path : String {
        switch self {
        // 登录
        case .getVCode:
            return "/account/getVCode"
        case .sendPassword:
            return "/account/sendVCode"
        case .login:
            return "/account/login"
        case .changePassword:
            return "/account/changePassword"
        case .getResetVCode:
            return "/account/vfcode"
        case .resetPassword:
            return "/account/resetPassword"
            
        case .getPointsInfo:
            return "/user/getInfo"
            
        // 商户
        case .getMerchantsInfos:
            return "/merchant/getInfos"
        case .getMerchantInfoByID(let merchantID):
            return "/merchant/\(merchantID)"
        case .getMerchantCount:
            return "/merchant/getNum"
            
        case .bindCard:
            return "/citi/bindCard"
        case .unbind:
            return "/citi/unbind"
        
        // 会员卡
        case .getMostPointCards:
            return "/mscard/infos"
        case .getCardCount:
            return "/mscard/getNum"
        case .getCardDetail:
            return "/mscard/getDetailCard"
        case .addCard:
            return "/mscard/addcard"
        
        // 积分
        case .changePoints:
            return "/points/changePoints"
        case .getGeneralPoints:
            return "/points/generalPoints"
        case .getAvailablePoints:
            return "/points/availablePoints"
        case .getAllPointsHistory:
            return "/points/getPointsHistoryByID"
        case .getPointsHistoryByMerchantID:
            return "/points/getPointsHistoryByMerchantID"
            
        //支付
        case .pollingQR:
            return "/pay/QRCode"
            
        //订单相关
        case .getOrders:
            return "/order/getOrders"
        }
    }
    
    var method : Moya.Method{
        return .post
    }
    
    var task : Task {
        switch self {
        // 账户
        case .sendPassword(let phoneNum, let vcode, let password):
            var params:[String:String] = [:]
            params["phoneNum"] = phoneNum
            params["vcode"] = vcode
            params["password"] = password
            
            return .requestParameters(parameters: params, encoding: URLEncoding.default)
        case .getVCode(let phoneNum):
            var params:[String:String] = [:]
            params["phoneNum"] = phoneNum
            return .requestParameters(parameters: params, encoding: URLEncoding.default)
        case .login(let phoneNum, let password):
            var params:[String:String] = [:]
            params["phoneNum"] = phoneNum
            params["password"] = password
            return .requestParameters(parameters: params, encoding: URLEncoding.default)
            
        case .changePassword(let old, let new):
            var params:[String:String] = [:]
            params["userID"] = User.getUser().id
            params["oldPassword"] = old
            params["newPassword"] = new
            return .requestParameters(parameters: params, encoding: URLEncoding.default)
            
        case .getResetVCode(let phoneNum, let vcode):
            var params:[String:String] = [:]
            params["phoneNum"] = phoneNum
            params["vcode"] = vcode
            return .requestParameters(parameters: params, encoding: URLEncoding.default)
            
        case .resetPassword(let phoneNum, let newPassword):
            var params:[String:String] = [:]
            params["phoneNUm"] = phoneNum
            params["newPassword"] = newPassword
            return .requestParameters(parameters: params, encoding: URLEncoding.default)
            
        case .getPointsInfo(let userID):
            var params:[String:String] = [:]
            params["userID"] = userID
            return .requestParameters(parameters: params, encoding: URLEncoding.default)
            
        case .bindCard(let citiCardNum, let phoneNum, let ID, let password):
            var params:[String:String] = [:]
            params["citiCardNum"] = citiCardNum
            params["phoneNum"] = phoneNum
            params["ID"] = ID
            params["password"] = password
            return .requestParameters(parameters: params, encoding: URLEncoding.default)
            
        // 商户
        case .getMerchantsInfos(let start, let n):
            var params:[String:String] = [:]
            params["start"] = String(start)
            params["n"] = String(n)
            return .requestParameters(parameters: params, encoding: URLEncoding.default)
        case .getMerchantInfoByID(let id):
            var params:[String:String] = [:]
            params["merchantID"] = id
            return .requestParameters(parameters: params, encoding: URLEncoding.default)
            
        case .getCardCount():
            var params:[String:String] = [:]
            params["userId"] = User.getUser().id
            return .requestParameters(parameters: params, encoding: URLEncoding.default)
            
        // 会员卡
        case .getCardDetail(let merchantID):
            var params:[String:String] = [:]
            params["userId"] = User.getUser().id
            params["merchantID"] = merchantID
            return .requestParameters(parameters: params, encoding: URLEncoding.default)
        case .addCard(let merchantID, let cardNum, let password):
            var params:[String:String] = [:]
            params["userID"] = User.getUser().id
            params["merchantID"] = merchantID
            params["cardNum"] = cardNum
            params["password"] = password
            return .requestParameters(parameters: params, encoding: URLEncoding.default)
        case .getMostPointCards(let n):
            var params:[String:String] = [:]
            params["userId"] = User.getUser().id
            params["n"] = String(n)
            return .requestParameters(parameters: params, encoding: URLEncoding.default)
            
        // 积分相关
        case .changePoints(let merchants):
            var params:[String:String] = [:]
            params["userID"] = User.getUser().id
            let encoder = JSONEncoder()
            let encoded = try? encoder.encode(merchants)
            if encoded != nil {
                if let json = String(data:encoded!,encoding:.utf8){
                    params["merchants"] = json
                }
            }
            return .requestParameters(parameters: params, encoding: URLEncoding.default)
        case .getAllPointsHistory():
            var params:[String:String] = [:]
            params["userID"] = User.getUser().id
            return .requestParameters(parameters: params, encoding: URLEncoding.default)
        case .getPointsHistoryByMerchantID(let merchantID):
            var params:[String:String] = [:]
            params["userID"] = User.getUser().id
            params["merchantID"] = merchantID
            return .requestParameters(parameters: params, encoding: URLEncoding.default)
        
        //支付相关
        case .pollingQR(let timestamp):
            var params:[String:String] = [:]
            params["userId"] = User.getUser().id
            params["timestamp"] = timestamp
            return .requestParameters(parameters: params, encoding: URLEncoding.default)
            
        // 订单相关
        case .getOrders(let intervalTime):
            var params:[String:String] = [:]
            params["userId"] = User.getUser().id
            params["intervaltime"] = intervalTime
            return .requestParameters(parameters: params, encoding: URLEncoding.default)
            
        default:
            return .requestPlain
        }
    }
    
    var sampleData:Data{
        return "{}".data(using: String.Encoding.utf8)!
    }
    
    var headers: [String:String]? {
        switch self{
        case .changePoints:
            return ["Content-Type":"application/json;charset=UTF-8"]
        default:
            return nil
        }
    }
    
    
}
