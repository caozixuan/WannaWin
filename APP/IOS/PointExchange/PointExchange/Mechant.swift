//
//  Mechant.swift
//  PointExchange
//
//  Created by panyy on 2018/7/8.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import Foundation
class Merchant:Codable {
    var id:String = ""
    var name:String = ""
    var description:String?
    var logoURL:String?
    var cardTypes:[(String)]?
    var Mtype:String?
}
