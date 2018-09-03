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
import PageMenu

class OrdersViewController: UIViewController {
	@IBOutlet weak var segmentControl: UISegmentedControl!
	
	
	@IBOutlet weak var couponContainer: UIView!
	@IBOutlet weak var activityContainer: UIView!
	
	var pageMenu : CAPSPageMenu?
	
	override func viewDidAppear(_ animated: Bool) {
		super.viewDidAppear(animated)
		
		if pageMenu == nil {
			var controllerArray:[UIViewController] = []
			let couponController = UIStoryboard(name: "User", bundle: nil).instantiateViewController(withIdentifier: "CouponHistoryViewController")
			couponController.title = "优惠券"
			let offlineController = UIStoryboard(name: "User", bundle: nil).instantiateViewController(withIdentifier: "OfflineHistoryViewController")
			offlineController.title = "线下支付"
			controllerArray.append(couponController)
			controllerArray.append(offlineController)
			
			let params:[CAPSPageMenuOption] = [
				.useMenuLikeSegmentedControl(true),
				.addBottomMenuHairline (true),
				.scrollMenuBackgroundColor (UIColor.white),
				.menuHeight(40),
				.menuItemFont(UIFont.systemFont(ofSize:18)),
				.unselectedMenuItemLabelColor (UIColor(red: 255/255, green: 149/255, blue: 70/255, alpha: 1.0)),
				.selectedMenuItemLabelColor (UIColor(red: 255/255, green: 149/255, blue: 70/255, alpha: 1.0)),
				.selectionIndicatorColor (UIColor(red: 255/255, green: 149/255, blue: 70/255, alpha: 1.0))
			]
			
			// 获得导航栏及状态栏的高度，避免控件被遮住
			let frameY = self.view.safeAreaLayoutGuide.layoutFrame.minY
			
			pageMenu = CAPSPageMenu(viewControllers: controllerArray, frame:CGRect(x: 0, y: frameY, width: self.view.frame.width, height: self.view.frame.height-frameY),pageMenuOptions:params)
			self.addChildViewController(pageMenu!)
			self.view.addSubview((pageMenu?.view)!)
			pageMenu!.didMove(toParentViewController: self)
		}
		
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
