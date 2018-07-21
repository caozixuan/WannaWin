//
//  ShadowView.swift
//  PointExchange
//
//  Created by yiner on 2018/7/20.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import UIKit

class ShadowView: UIView {
    
	@IBOutlet var view: UIView!
	@IBOutlet weak var middleView: UIView!
	
	@IBOutlet weak var sourcePoints: UILabel!
	@IBOutlet weak var editSourcePoints: UITextField!
	@IBOutlet weak var targetPoints: UILabel!
	
	override init(frame: CGRect) {
		super.init(frame: frame)
		initViewFromNib()
	}
	
	required init?(coder aDecoder: NSCoder) {
		super.init(coder: aDecoder)
		self.initViewFromNib()
		self.setUp()
	}
	
	private func initViewFromNib(){
		let bundle = Bundle(for: type(of: self))
		let nib = UINib(nibName: "ShadowView", bundle: bundle)
		self.view = nib.instantiate(withOwner: self, options: nil)[0] as! UIView
		self.view.frame = bounds
		self.addSubview(view)
	}
	
	func setUp() {
		self.view.layer.cornerRadius = 14
		self.view.layer.shadowColor = UIColor(red: CGFloat(132/255.0), green: 143/255.0, blue: 161/255.0, alpha: 0.5/1.0).cgColor
		self.view.layer.shadowOffset = CGSize(width:0, height:2)
		self.view.layer.shadowOpacity = 1;
		self.view.layer.shadowRadius = 5;

        middleView.layer.shadowColor = UIColor(red: CGFloat(119/255.0), green: 110/255.0, blue: 110/255.0, alpha: 0.5/1.0).cgColor
        middleView.layer.shadowOffset = CGSize(width:0, height:0)
        middleView.layer.shadowOpacity = 0.5;
        middleView.layer.shadowRadius = 4;
		
		editSourcePoints.isHidden = true
		sourcePoints.isHidden = false
        
	}
	
}
