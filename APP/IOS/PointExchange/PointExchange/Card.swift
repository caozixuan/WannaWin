//
//  Card.swift
//  PointExchange
//
//  Created by Pan on 2018/7/5.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import Foundation
class Card: Codable {
    var id: String?
    var userID:String?
    var number:String?
    var merchant:Merchant?
    var type:String?
    var point:Int = 0
}
