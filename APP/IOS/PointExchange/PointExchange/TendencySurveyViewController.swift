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
	var typeBtns = [UIButton]()
	override func viewDidLoad() {
        super.viewDidLoad()
		typeBtns.append(hotelBtn)
		typeBtns.append(movieBtn)
		typeBtns.append(marketBtn)
		typeBtns.append(communicationBtn)
		typeBtns.append(foodBtn)
		typeBtns.append(airlineBtn)
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
		var chooseResult = [String]()
		for i in 0...typeBtns.count-1{
			if typeBtns[i].isSelected{
				chooseResult.append((typeBtns[i].titleLabel?.text)!.replacingOccurrences(of: "\n", with: ""))
				
			}
		}
		print(chooseResult)
	}
	
	
	@IBAction func clickSkip(_ sender: Any) {
		let storyBoard = UIStoryboard(name:"HomePage", bundle:nil)
		let view = storyBoard.instantiateViewController(withIdentifier: "MainViewController")
		self.navigationController!.pushViewController(view, animated: true)
	}
	
	@IBAction func chooseType(_ sender: Any) {
		if (sender as! UIButton).isSelected{
			(sender as! UIButton).isSelected = false
		}else{
			(sender as! UIButton).isSelected = true
		}
	}
}
