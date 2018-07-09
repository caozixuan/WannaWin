//
//  Card.swift
//  PointExchange
//
//  Created by Pan on 2018/7/5.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import Foundation
class Card: Codable {
    var number:String = "00000"
    var merchant:Merchant?
    var type:String?
    var point:Int = 0
}
