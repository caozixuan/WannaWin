//
//  ChoosePointInfo.swift
//  PointExchange
//
//  Created by yiner on 2018/8/3.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import Foundation
struct ChoosePointInfo:Codable{
	var userID = User.getUser().id
	var merchants = [ChooseMerchants]()
	
}
struct ChooseMerchants:Codable{
	var merchantID:String = ""
	var selectedMSCardPoints:String = ""
}
