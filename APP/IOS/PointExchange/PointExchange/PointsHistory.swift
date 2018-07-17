//
//  PointsHistory.swift
//  PointExchange
//
//  Created by panyy on 2018/7/17.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import Foundation

class PointsHistory:Codable{
    var userID:String?
    var merchantID:String?
    var pointsCard:Double?
    var pointsCiti:Double?
    var cause:String?
    var time:String?
    
    enum CodingKeys:String, CodingKey {
        case pointsCard = "points_card"
        case pointsCiti = "points_citi"
        
        case userID
        case merchantID
        case cause
        case time
    }
}
