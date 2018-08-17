//
//  DiscoverCouponView.swift
//  PointExchange
//
//  Created by yiner on 2018/8/11.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import UIKit

class DiscoverCouponView: UIScrollView {

	@IBOutlet weak var imageView1: GradientView!
	@IBOutlet weak var imageView3: GradientView!
	@IBOutlet weak var imageView2: GradientView!
	@IBOutlet var view: UIScrollView!
	@IBOutlet weak var image1: UIImageView!
	
	@IBOutlet weak var image2: UIImageView!
	
	@IBOutlet weak var image3: UIImageView!
	
	var images = [UIImageView]()
	var imageViews = [GradientView]()
	
	
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
		self.imageViews.append(imageView1)
		self.imageViews.append(imageView2)
		self.imageViews.append(imageView3)
		self.images.append(self.image1)
		self.images.append(self.image2)
		self.images.append(self.image3)
		image1.contentMode = .scaleAspectFit
		image2.contentMode = .scaleAspectFit
		image3.contentMode = .scaleAspectFit
		imageView1.contentMode = .scaleAspectFit
		imageView2.contentMode = .scaleAspectFit
		imageView3.contentMode = .scaleAspectFit
		
	}
}
