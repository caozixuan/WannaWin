//
//  Item.swift
//  PointExchange
//
//  Created by yiner on 2018/7/27.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import Foundation

class Item:Codable{
	var ItemID:String?
	var name:String?
	var description:String?
	var merchantID:String?
	var logoURL:String?
	var originalPrice:Double?
	var points:Double?
	var overdueTime:String?
	var stock:Int?
	var itemName:String?
	var couponID:Int?
	var state:String?
	var getTime:String?
	var useTime:String?
}
