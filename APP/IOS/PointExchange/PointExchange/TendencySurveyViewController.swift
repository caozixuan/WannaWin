//
//  TendencySurveyViewController.swift
//  PointExchange
//
//  Created by yiner on 2018/8/22.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import UIKit

class TendencySurveyViewController: UIViewController {

	@IBOutlet var hotelBtn: UIButton!
	@IBOutlet var movieBtn: UIButton!
	@IBOutlet var marketBtn: UIButton!
	@IBOutlet var communicationBtn: UIButton!
	@IBOutlet var foodBtn: UIButton!
	@IBOutlet var airlineBtn: UIButton!
	override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
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

	@IBAction func clickFinish(_ sender: Any) {
	}
	
	
	@IBAction func clickSkip(_ sender: Any) {
	}
	
	@IBAction func chooseType(_ sender: Any) {
		if (sender as! UIButton).isSelected{
			(sender as! UIButton).isSelected = false
		}else{
			(sender as! UIButton).isSelected = true
		}
	}
}
