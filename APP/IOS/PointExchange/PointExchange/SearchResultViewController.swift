//
//  SearchResultViewController.swift
//  PointExchange
//
//  Created by yiner on 2018/8/23.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import UIKit
import PageMenu

class SearchResultViewController: UIViewController {

	var pageMenu:CAPSPageMenu?
    override func viewDidLoad() {
        super.viewDidLoad()
		var controllerArray:[UIViewController] = []
		let allController = UIStoryboard(name: "Discover", bundle: nil).instantiateViewController(withIdentifier: "SearchResultAllViewController")
		allController.title = "全部"
		let couponController = UIStoryboard(name: "Discover", bundle: nil).instantiateViewController(withIdentifier: "SearchResultCouponViewController")
		couponController.title = "优惠券"
		let merchantController = UIStoryboard(name: "Discover", bundle: nil).instantiateViewController(withIdentifier: "SearchResultMerchantViewController")
		merchantController.title = "商家"
		let offlineController = UIStoryboard(name: "Discover", bundle: nil).instantiateViewController(withIdentifier: "SearchResultOfflineViewController")
		offlineController.title = "商家"
		controllerArray.append(allController)
		controllerArray.append(couponController)
		controllerArray.append(merchantController)
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
		pageMenu = CAPSPageMenu(viewControllers: controllerArray, frame:CGRect(x: 0, y: 64, width: self.view.frame.width, height: self.view.frame.height-64),pageMenuOptions:params)
		self.addChildViewController(pageMenu!)
		self.view.addSubview((pageMenu?.view)!)
		pageMenu!.didMove(toParentViewController: self)
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

}
