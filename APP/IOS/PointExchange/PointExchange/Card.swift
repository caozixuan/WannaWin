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
    var point:Double = 0
	var proportion:Double?
	
	//test
	convenience init(merchant:Merchant,point:Double,proportion:Double){
		self.init()
		self.merchant = merchant
		self.point = point
		self.proportion = proportion
	}
}
