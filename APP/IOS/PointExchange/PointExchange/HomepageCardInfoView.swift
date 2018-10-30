//
//  HomepageCardInfoView.swift
//  PointExchange
//
//  Created by yiner on 2018/8/28.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import UIKit

class HomepageCardInfoView: UIView {

	@IBOutlet weak var logoBackgroundView: GradientView!
	@IBOutlet var view: UIView!
	@IBOutlet weak var generalPointLabel: UILabel!
	@IBOutlet weak var merchantNameLabel: UILabel!
	@IBOutlet weak var merchantLogoImageView: UIImageView!
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
		let nib = UINib(nibName: "HomepageCardInfoView", bundle: bundle)
		var views = nib.instantiate(withOwner: self, options: nil)
		self.view = views[0] as! UIView
		self.view.frame = bounds
		self.addSubview(view)
		
		logoBackgroundView.cornerRadius = merchantLogoImageView.frame.width/2
	}

}
