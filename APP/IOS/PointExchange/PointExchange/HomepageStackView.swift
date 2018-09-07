//
//  HomepageStackView.swift
//  PointExchange
//
//  Created by panyy on 2018/7/20.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import UIKit

class HomepageStackView: UIStackView{
    @IBOutlet var view: UIStackView!
    
	
	@IBOutlet weak var cardScrollView: UIScrollView!
    @IBOutlet weak var cardImage1: UIView!
    @IBOutlet weak var cardImage3: UIView!
    @IBOutlet weak var cardImage2: UIView!
	
    @IBOutlet weak var availablePointsLabel: UILabel!
    @IBOutlet weak var currentCitiPointLabel: UILabel!
    @IBOutlet weak var exchangeBtn: UIButton!
    
    var delegate:HomepageStackViewDelegate?
	
    override init(frame: CGRect) {
        super.init(frame: frame)
        initViewFromNib()
    }
    
    required init(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
        initViewFromNib()
    }
    
    private func initViewFromNib(){
        let bundle = Bundle(for: type(of: self))
        let nib = UINib(nibName: "HomepageStackView", bundle: bundle)
		var views = nib.instantiate(withOwner: self, options: nil)
        self.view = views[0] as! UIView as! UIStackView
        self.view.frame = bounds

        self.addSubview(view)
    }
    
    @IBAction func checkAllCards(_ sender: Any) {
        delegate?.checkAllCards()
    }
	
	//让超出父视图的部分响应点击后
	override func hitTest(_ point: CGPoint, with event: UIEvent?) -> UIView? {
		
		if (!self.isUserInteractionEnabled || self.isHidden || self.alpha <= 0.01 ){
			return nil
		}
		let resultView  = super.hitTest(point, with: event)
		if resultView != nil {
			return resultView
		}else{
			for subView in self.subviews.reversed() {
				// 这里根据层级的不同，需要遍历的次数可能不同，看需求来写，我写的例子是一层的
				let convertPoint : CGPoint = subView.convert(point, from: self)
				let hitView = subView.hitTest(convertPoint, with: event)
				if (hitView != nil) {
					return hitView
				}
			}
		}
		return nil
	}

	//可能按钮还是不好点击，原因是按钮图片太小， 所以要扩大按钮响应的范围
	open override func point(inside point: CGPoint, with event: UIEvent?) -> Bool {
		var bounds = self.bounds
		let widthDelta = max(17 - bounds.width, 0)
		let heightDelta = max(17 - bounds.height, 0)
		bounds = bounds.insetBy(dx: -widthDelta, dy: -heightDelta)
		return bounds.contains(point)
	}
	

}



protocol HomepageStackViewDelegate {
    func checkAllCards()
}


