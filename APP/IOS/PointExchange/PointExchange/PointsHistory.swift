//
//  PointsHistory.swift
//  PointExchange
//
//  Created by panyy on 2018/7/17.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import Foundation

class PointsHistory:Codable{
    var historyMerchants:[PointsHistoryItem]?
    var totalPoints:Double?
    
    enum CodingKeys:String, CodingKey {
        case historyMerchants = "points_history_merchants"
        case totalPoints
    }
}

struct PointsHistoryItem:Codable{
    var userID:String?
    var merchantName:String?
    var pointsCard:Double?
    var pointsCiti:Double?
    var cause:String?
    var time:String?
    
    enum CodingKeys:String, CodingKey {
        case pointsCard = "points_card"
        case pointsCiti = "points_citi"
        
        case userID
        case merchantName
        case cause
        case time
    }
}
