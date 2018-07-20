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
    @IBOutlet weak var cardImage1: UIImageView!
    @IBOutlet weak var cardImage3: UIImageView!
    @IBOutlet weak var cardImage2: UIImageView!
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
        self.view = nib.instantiate(withOwner: self, options: nil)[0] as! UIView as! UIStackView
        self.view.frame = bounds
        self.addSubview(view)
        
        // Do any additional setup after loading the view.
        
    }
    
    
    

}
