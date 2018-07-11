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
    var cardTypes:[CardType]?
    var Mtype:String?
    var address:String?
	
	//test
	convenience init(name:String){
		self.init()
		self.name = name
	}
}

struct CardType:Codable {
    var merchantID:String?
    var mType:String?
    var proportion:Double?
    var miniExpense:String?
    var cardType:String?
    
}
