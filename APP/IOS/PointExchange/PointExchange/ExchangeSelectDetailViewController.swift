//
//  ExchangeSelectDetailViewController.swift
//  PointExchange
//
//  Created by yiner on 2018/7/8.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import UIKit

class ExchangeSelectDetailViewController: ExchangeViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
		self.navigationItem.hidesBackButton = true
        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
	
	
    
	@IBAction func pressFoldBtn(_ sender: Any) {
		self.navigationController?.popViewController(animated: true)
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
