//
//  CouponHistoryViewController.swift
//  PointExchange
//
//  Created by yiner on 2018/8/12.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import UIKit

class CouponHistoryViewController: UIViewController {

	@IBOutlet weak var unuseContainer: UIView!
	@IBOutlet weak var usedContainer: UIView!
	@IBOutlet weak var overdueContainer: UIView!
	@IBOutlet weak var segmentControl: UISegmentedControl!

	override func viewDidLoad() {
        super.viewDidLoad()
		segmentControl.tintColor = UIColor.clear
		let unselectedSegmentStyle = [NSAttributedStringKey.font:UIFont.systemFont(ofSize: 18),NSAttributedStringKey.foregroundColor:UIColor(red: 110/255, green: 110/255, blue: 110/255, alpha: 1.0)] as [AnyHashable : Any]
		self.segmentControl.setTitleTextAttributes(unselectedSegmentStyle , for: UIControlState.normal)
		let selectedSegmentStyle = [NSAttributedStringKey.font:UIFont.systemFont(ofSize: 24),NSAttributedStringKey.foregroundColor:UIColor(red: 0/255, green: 0/255, blue: 0/255, alpha: 1.0)] as [AnyHashable : Any]
		segmentControl.setTitleTextAttributes(selectedSegmentStyle as [AnyHashable : Any], for: UIControlState.selected)
		
		

        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
	
	@IBAction func segmentChange(_ sender: Any) {
		switch segmentControl.selectedSegmentIndex {
		// 未使用
		case 0:
			unuseContainer.isHidden = false
			usedContainer.isHidden = true
			overdueContainer.isHidden = true
		// 已使用
		case 1:
			unuseContainer.isHidden = true
			usedContainer.isHidden = false
			overdueContainer.isHidden = true
		// 已过期
		case 2:
			unuseContainer.isHidden = true
			usedContainer.isHidden = true
			overdueContainer.isHidden = false
		default:
			break
		}
	}
	override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
		switch segue.identifier {
		case "unuseView":
			if let view = segue.destination as? CouponHistoryContainViewContainer{
				view.tag = "unuse"
			}
		case "usedView":
			if let view = segue.destination as? CouponHistoryContainViewContainer{
				view.tag = "used"
			}
		case "expireView":
			if let view = segue.destination as? CouponHistoryContainViewContainer{
				view.tag = "overdue"
			}
		default:
			break
		}
	}

}
