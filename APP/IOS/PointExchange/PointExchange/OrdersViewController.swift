//
//  OrdersViewController.swift
//  PointExchange
//
//  Created by panyy on 2018/8/12.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import UIKit
import RxSwift
import RxCocoa
import RxDataSources

class OrdersViewController: UIViewController {
	@IBOutlet weak var segmentControl: UISegmentedControl!
	
	@IBOutlet weak var couponContainer: UIView!
	@IBOutlet weak var activityContainer: UIView!
	
	override func viewWillAppear(_ animated: Bool) {
		super.viewWillAppear(animated)
		couponContainer.isHidden = false
		activityContainer.isHidden = true
		// segment设置
		self.segmentControl.frame = CGRect(x: 0, y: 64, width: 375, height: 50)
		self.segmentControl.tintColor = UIColor.clear
		let unselectedSegmentStyle = [NSAttributedStringKey.font:UIFont.systemFont(ofSize: 18),NSAttributedStringKey.foregroundColor:UIColor(red: 255/255, green: 149/255, blue: 70/255, alpha: 1.0)] as [AnyHashable : Any]
		segmentControl.setTitleTextAttributes(unselectedSegmentStyle as [AnyHashable : Any], for: UIControlState.normal)
		let selectedSegmentStyle = [NSAttributedStringKey.font:UIFont.systemFont(ofSize: 18),NSAttributedStringKey.foregroundColor:UIColor(red: 255/255, green: 149/255, blue: 70/255, alpha: 1.0)] as [AnyHashable : Any]
		segmentControl.setTitleTextAttributes(selectedSegmentStyle as [AnyHashable : Any], for: UIControlState.selected)
		
		
	}
	
	override func didReceiveMemoryWarning() {
		super.didReceiveMemoryWarning()
		// Dispose of any resources that can be recreated.
	}
	
	@IBAction func segmentChange(_ sender: Any) {
			switch segmentControl.selectedSegmentIndex {
				case 0:
					couponContainer.isHidden = false
					activityContainer.isHidden = true
		        case 1:
					couponContainer.isHidden = true
					activityContainer.isHidden = false
		        default:
		            break
			}
	}
	

	

}
