//
//  ShadowView.swift
//  PointExchange
//
//  Created by yiner on 2018/7/20.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import UIKit

class ShadowView: UIView {
	
	override init(frame: CGRect) {
		super.init(frame: frame)
		self.setUp()
	}
	
	required init?(coder aDecoder: NSCoder) {
		fatalError("init(coder:) has not been implemented")
	}
	
	func setUp() {
		self.layer.cornerRadius = 14
		self.layer.shadowColor = UIColor(red: CGFloat(132/255.0), green: 143/255.0, blue: 161/255.0, alpha: 0.5/1.0).cgColor
		self.layer.shadowOffset = CGSize(width:0, height:2)
		self.layer.shadowOpacity = 0.5;
		self.layer.shadowRadius = 5;
	}
	
}
