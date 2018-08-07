//
//  ChoosePointInfo.swift
//  PointExchange
//
//  Created by panyy on 2018/8/3.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import Foundation
/// 积分兑换时选择的积分的信息
/// - userID：不用更改
/// - merchants:[ChooseMerchants] 选择的商户积分数组
/// 	- merchantID: 商户id
/// 	- selectedMSCardPoints: 选择的积分数
struct ChoosePointInfo:Codable{
	var userID = User.getUser().id
	var merchants = [ChooseMerchants]()
	
}
struct ChooseMerchants:Codable{
	var merchantID:String = ""
	var selectedMSCardPoints:String = ""
}
