//
//  MerchantList.swift
//  PointExchange
//
//  Created by panyy on 2018/7/9.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import Foundation

class MerchantList:Codable{
    static var list = [Merchant]()
}

extension MerchantList {
    static func add(merchant:Merchant){
        list.append(merchant)
    }
    static func remove(merchantID:String){
        var index = 0
        for merchant in self.list {
            if merchant.id == merchantID {
                list.remove(at: index)
            }
            index += 1
        }
    }
    
    static func get(merchantID id:String)->Merchant?{
        for merchant in self.list {
            if merchant.id == id {
                return merchant
            }
        }
        return nil
    }
}
