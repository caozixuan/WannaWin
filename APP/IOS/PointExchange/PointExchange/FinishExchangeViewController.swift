//
//  FinishExchangeViewController.swift
//  PointExchange
//
//  Created by yiner on 2018/7/8.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import UIKit

class FinishExchangeViewController: UIViewController{

    override func viewDidLoad() {
        super.viewDidLoad()
		self.navigationItem.hidesBackButton = true
		let finishBtn = UIBarButtonItem(title: "完成", style: .done, target: self, action: #selector(goBackToHomePage))
		self.navigationItem.rightBarButtonItem = finishBtn

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
