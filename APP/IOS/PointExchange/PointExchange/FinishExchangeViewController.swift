//
//  FinishExchangeViewController.swift
//  PointExchange
//
//  Created by yiner on 2018/7/8.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import UIKit

class FinishExchangeViewController: UIViewController{

    var order:Order?
	@IBOutlet weak var logo: UIImageView!
	@IBOutlet weak var points: UILabel!
	@IBOutlet weak var afterPrice: UILabel!
	@IBOutlet weak var originalPrice: UILabel!
	override func viewDidLoad() {
        super.viewDidLoad()
		self.navigationItem.hidesBackButton = true
		let finishBtn = UIBarButtonItem(title: "完成", style: .done, target: self, action: #selector(goBackToHomePage))
		self.navigationItem.rightBarButtonItem = finishBtn
		
		logo.imageFromURL((order!.merchantLogoURL)!, placeholder: UIImage())
		points.text = String(stringInterpolationSegment: order!.pointsNeeded!)
		originalPrice.text = String(stringInterpolationSegment: order!.originalPrice!)
		afterPrice.text = String(stringInterpolationSegment: order!.priceAfter!)
		
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
	
    // MARK: - Navigation
	@objc func goBackToHomePage(){
		let rootVC = self.navigationController
		self.navigationController?.popToRootViewController(animated: false)
		rootVC?.tabBarController?.selectedIndex = 0
	}
	
}
