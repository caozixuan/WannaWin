//
//  MyTabBarController.swift
//  PointExchange
//
//  Created by yiner on 2018/11/2.
//  Copyright © 2018 WannaWin. All rights reserved.
//

import UIKit

class MyTabBarController:UITabBarController{
	override func tabBar(_ tabBar: UITabBar, didSelect item: UITabBarItem) {
		if item.title == "我的"{
			(self.childViewControllers[4] as! UINavigationController).popToRootViewController(animated: true)
		}
	}
}
