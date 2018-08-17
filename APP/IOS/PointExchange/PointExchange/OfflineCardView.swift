//
//  OfflineCardView.swift
//  PointExchange
//
//  Created by yiner on 2018/8/14.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import UIKit

class OfflineCardView: UIView {

	@IBOutlet var view: UIView!
	@IBOutlet weak var image: UIImageView!
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
		let nib = UINib(nibName: "OfflineCardView", bundle: bundle)
		self.view = nib.instantiate(withOwner: self, options: nil)[0] as! UIView
		self.view.frame = bounds
		self.addSubview(view)
		self.image.contentMode = .scaleAspectFit
		self.image.clipsToBounds = true
	}

}
