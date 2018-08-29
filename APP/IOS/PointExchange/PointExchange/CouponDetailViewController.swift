//
//  CouponDetailViewController.swift
//  PointExchange
//
//  Created by yiner on 2018/8/15.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import UIKit
import Kingfisher

class CouponDetailViewController: UIViewController {

	var itemID:String?
	var item:Item?
	var count = 1
	
	
	@IBOutlet weak var validDate: UILabel!
	@IBOutlet weak var countSelectedText: UITextField!
	@IBOutlet weak var descriptionText: UITextView!
	@IBOutlet weak var pointLabel: UILabel!
	@IBOutlet weak var logoImage: UIImageView!
	override func viewDidLoad() {
        super.viewDidLoad()
		ServerConnector.getSingleItemDetail(itemID: itemID!){(result,item) in
			if result{
				self.item = item
				let imageURL = URL(string: (self.item?.logoURL?.addingPercentEncoding(withAllowedCharacters: .urlQueryAllowed))!)
				self.logoImage.kf.indicatorType = .activity
				self.logoImage.kf.setImage(with: imageURL)
				self.descriptionText.text = self.item?.description
				// TODO: 日期
//				let formatter = DateFormatter()
//				formatter.dateFormat = "MMM dd, yyyy hh:mm:ss a"
//				let overdueTime = self.item?.overdueTime
//				print(self.item!.overdueTime!)
//				let d = formatter.date(from: overdueTime!)
//
//				formatter.dateFormat = "yyyy-MM-dd"
//				let dateString = formatter.string(from: d!)
//				self.validDate.text = dateString
				var str = "Jul 29, 2018 1:00:00 PM"
				let formatter = DateFormatter()
				formatter.dateFormat = "M dd, yyyy hh:mm:ss a"
				print(str)
				let date = formatter.date(from: str)
				formatter.dateFormat = "yyyy-MM-dd"
				let string = formatter.string(from: date!)
				print(string)
				self.pointLabel.text = String(stringInterpolationSegment: self.item!.points!)+"P"
			}
		}
		self.logoImage.contentMode = .scaleAspectFit
		
		
        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
	
	@IBAction func clickMinus(_ sender: Any) {
		if count > 0 {
			count -= 1
		}
		self.countSelectedText.text = String(count)
	}
	
	@IBAction func clickAdd(_ sender: Any) {
		count += 1
		self.countSelectedText.text = String(count)
	}
	@IBAction func countChange(_ sender: Any) {
		let newCount = Int(countSelectedText.text!)
		if newCount! < 0 {
			count = 0
			countSelectedText.text = String(0)
		}else{
			count = Int(countSelectedText.text!)!
		}
	}
	
	@IBAction func clickExchange(_ sender: Any) {
		ServerConnector.buyCoupons(itemID: (item?.ItemID)!, count: self.count){result in
			if result {
				let alert = UIAlertController(title:"兑换", message:"兑换成功！", preferredStyle:.alert)
				let okAction = UIAlertAction(title:"确定", style:.default, handler:{ action in
					let sb = UIStoryboard(name: "User", bundle: nil)
					let vc = sb.instantiateViewController(withIdentifier: "OrdersViewController") as! OrdersViewController
					
					self.navigationController?.pushViewController(vc, animated: true)
				})
				alert.addAction(okAction)
				self.present(alert, animated: true, completion: nil)
			}
			else{
				let alert = UIAlertController(title:"兑换", message:"兑换失败！", preferredStyle:.alert)
				let okAction = UIAlertAction(title:"确定", style:.default, handler:{ action in
				})
				alert.addAction(okAction)
				self.present(alert, animated: true, completion: nil)
			}
		}
	}
}
