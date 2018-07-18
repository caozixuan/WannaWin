//
//  Order.swift
//  PointExchange
//
//  Created by panyy on 2018/7/13.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import Foundation
struct Order{
    var orderID:String?
    var originalPrice:Double?
    var priceAfter:Double?
    var pointsNeeded:Double?
    var state:OrderState?
    var userID:String?
    var merchant:Merchant?
    var time:String?
}
enum OrderState:String,Codable{
    case SUCCESS = "success"
}
