//
//  ShadowView.swift
//  PointExchange
//
//  Created by yiner on 2018/7/20.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import UIKit

class ShadowView: UIView {
    
    
	
	required init?(coder aDecoder: NSCoder) {
		super.init(coder: aDecoder)
	}
	
	override func awakeFromNib() {
		super.awakeFromNib()
		self.setUp()
	}
	
	func setUp() {
		self.layer.cornerRadius = 14
		self.layer.shadowColor = UIColor(red: CGFloat(132/255.0), green: 143/255.0, blue: 161/255.0, alpha: 0.5/1.0).cgColor
		self.layer.shadowOffset = CGSize(width:0, height:2)
		self.layer.shadowOpacity = 1;
		self.layer.shadowRadius = 5;
        
        let middleView = UIView(frame:CGRect(x: self.bounds.width/4, y: 0, width: self.bounds.width/2, height: self.bounds.height))
        middleView.backgroundColor = UIColor(red: CGFloat(250/255.0), green: 250/255.0, blue: 250/255.0, alpha: 1.0)
        middleView.layer.shadowColor = UIColor(red: CGFloat(119/255.0), green: 110/255.0, blue: 110/255.0, alpha: 0.5/1.0).cgColor
        middleView.layer.shadowOffset = CGSize(width:2, height:0)
        middleView.layer.shadowOpacity = 0.5;
        middleView.layer.shadowRadius = 4;
        
        self.addSubview(middleView)
        
	}
	
}
