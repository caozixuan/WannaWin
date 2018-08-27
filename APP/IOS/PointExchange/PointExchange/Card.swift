//
//  Card.swift
//  PointExchange
//
//  Created by Pan on 2018/7/5.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import Foundation
class Card: Codable {
    var number:String?
    var merchant:Merchant?
    var points:Double = 0
	var proportion:Double?
    var logoURL:String?
    var description:String?
    var type:Int?
	var cardStyle:Int?
	
	//test
	convenience init(merchant:Merchant,points:Double,proportion:Double){
		self.init()
		self.merchant = merchant
		self.points = points
		self.proportion = proportion
	}
}
