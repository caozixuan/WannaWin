//
//  DiscoverCouponView.swift
//  PointExchange
//
//  Created by yiner on 2018/8/11.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import UIKit

class DiscoverCouponView: UIScrollView {

	@IBOutlet var view: UIScrollView!
	@IBOutlet weak var image1: UIImageView!
	
	@IBOutlet weak var image2: UIImageView!
	
	@IBOutlet weak var image3: UIImageView!
	override init(frame: CGRect) {
		super.init(frame: frame)
		initViewFromNib()
	}
	
	required init(coder aDecoder: NSCoder) {
		super.init(coder: aDecoder)!
		initViewFromNib()
	}
	
	private func initViewFromNib(){
		let bundle = Bundle(for: type(of: self))
		let nib = UINib(nibName: "DiscoverCouponView", bundle: bundle)
		self.view = nib.instantiate(withOwner: self, options: nil)[0] as! UIView as! UIScrollView
		self.view.frame = bounds
		self.addSubview(view)
	}
}
