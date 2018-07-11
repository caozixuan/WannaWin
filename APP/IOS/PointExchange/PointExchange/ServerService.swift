//
//  ServerService.swift
//  PointExchange
//
//  Created by panyy on 2018/7/8.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import Foundation
import Moya

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
    
    //花旗卡相关
    /// 绑定花旗银行卡
    case bindCard(citiCardNum:String,phoneNum:String,ID:String,password:String)
    /// 解绑银行卡
    case unbind(citiCardNum:String,phoneNum:String,ID:String,password:String)
    
    // 商户相关
    /// 获取从start开始的n条商户信息
    case getMerchantsInfos(start:Int,n:Int)
    case getMerchantInfoByID(id:String)
    
    // 会员卡相关
    /// 获取指定用户积分最多的n张卡
    case getMostPointCards(userID:String, n:Int)
    /// 返回商户所有卡的类型
    case getCardTypeByUserID(merchantID:String)
    /// 添加会员卡
    case addCard(cardID:String, UserID:String, cardNo:String, msCardType:String)
}



extension ServerService:TargetType {
    var baseURL:URL{
        return URL(string: "http://193.112.44.141:80/citi")!
    }
    
    var path : String {
        switch self {
        case .getVCode:
            return "/login/getVCode"
        case .sendPassword:
            return "/login/sendVCode"
        case .login:
            return "/login"
        case .getPointsInfo:
            return "user/getInfo"
        case .getMerchantsInfos:
            return "/merchant/getInfos"
        case .getMerchantInfoByID(let merchantID):
            return "/merchant/\(merchantID)"
        case .bindCard:
            return "/citi/bindCard"
        case .unbind:
            return "/citi/unbind"
        case .getMostPointCards:
            return "/mscard/infos"
        case .getCardTypeByUserID:
            return "/mscard/cardtype"
        case .addCard:
            return "/mscard/addcard"
        }
    }
    
    var method : Moya.Method{
        return .post
    }
    
    var task : Task {
        switch self {
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
            
        case .getMerchantsInfos(let start, let n):
            var params:[String:String] = [:]
            params["start"] = String(start)
            params["n"] = String(n)
            return .requestParameters(parameters: params, encoding: URLEncoding.default)
            
        case .getCardTypeByUserID(let merchantID):
            var params:[String:String] = [:]
            params["merchantID"] = merchantID
            return .requestParameters(parameters: params, encoding: URLEncoding.default)
        case .getMostPointCards(let userID, let n):
            var params:[String:String] = [:]
            params["userId"] = userID
            params["n"] = String(n)
            return .requestParameters(parameters: params, encoding: URLEncoding.default)
        case .addCard(let cardID, let UserID, let cardNo, let msCardType):
            var params:[String:String] = [:]
            params["cardID"] = cardID
            params["UserID"] = UserID
            params["cardNo"] = cardNo
            params["msCardType"] = msCardType
            return .requestParameters(parameters: params, encoding: URLEncoding.default)
            
        default:
            return .requestPlain
        }
    }
    
    var sampleData:Data{
        return "{}".data(using: String.Encoding.utf8)!
    }
    
    var headers: [String:String]? {
        return nil
    }
    
    
}
