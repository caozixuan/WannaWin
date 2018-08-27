 
 //
//  CardInfoTableViewController.swift
//  PointExchange
//
//  Created by yiner on 2018/7/5.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import UIKit
import AFImageHelper

class CardInfoTableViewController: UITableViewController {

	var activityIndicator:UIActivityIndicatorView?
    
    var cardArray:[Card]?
	
	override func viewWillAppear(_ animated: Bool) {
		super.viewWillAppear(animated)
		// 加入“添加”按钮在导航栏右边
		let addBtn = UIBarButtonItem(barButtonSystemItem:UIBarButtonSystemItem.add , target: self, action: #selector(goAddVC))
		self.navigationItem.rightBarButtonItem = addBtn
		
		ServerConnector.getCardCount(){ (result,num) in
			if result{
				ServerConnector.getMostPointCards(n: num, callback: self.getCardCallback)
			}
		}
		
		activityIndicator = ActivityIndicator.createWaitIndicator(parentView: self.view)
		activityIndicator?.startAnimating()
	}
	
	func getCardCallback(result:Bool,cards:[Card]){
        if result {
            cardArray = cards
			User.getUser().card = cards
            tableView.reloadData()
        }
        activityIndicator?.stopAnimating()
    }
	
	/// 跳转到“添加银行卡”页面
	@objc func goAddVC() {
		// 加载动画
		self.activityIndicator = ActivityIndicator.createWaitIndicator(parentView: self.view)
		self.activityIndicator?.startAnimating()
		
		// 获得商家信息
//		ServerConnector.getMerchantsInfos(start: 0, n: 10, callback: gotMerchantsCallback)
		let storyBoard = UIStoryboard(name:"HomePage", bundle:nil)
		let view = storyBoard.instantiateViewController(withIdentifier: "MerchantChooseTableViewController") as! MerchantChooseTableViewController
		self.navigationController!.pushViewController(view, animated: true)
        self.activityIndicator?.stopAnimating()
	}
	
	
	
	//MARK: - Table view data source
	
	override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
		// FIXME: - Incomplete implementation, return the number of rows
        if let array = cardArray {
            return array.count
        }
        else{
            return 0
        }
	}
	
	 override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
		let cell  = tableView.dequeueReusableCell(withIdentifier: "card", for: indexPath)
        if cardArray != nil {
			(cell.viewWithTag(1) as! UIImageView).imageFromURL((cardArray![indexPath.row].merchant?.logoURL)!, placeholder: UIImage())
            (cell.viewWithTag(2) as! UILabel).text = String(stringInterpolationSegment: cardArray![indexPath.row].points)
            (cell.viewWithTag(3) as! UILabel).text = String(stringInterpolationSegment: cardArray![indexPath.row].points*cardArray![indexPath.row].proportion!)
			(cell.viewWithTag(4) as! UILabel).text = cardArray![indexPath.row].merchant?.name
			(cell.viewWithTag(5) as! UIImageView).image = UIImage(named: "bg2_\(cardArray![indexPath.row].cardStyle!)")
            
        }
		return cell
		
	}
	
	override func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
		let sb = UIStoryboard(name: "HomePage", bundle: nil)
		let vc = sb.instantiateViewController(withIdentifier: "CardDetailTableViewController") as! CardDetailViewController
		vc.merchantID = cardArray?[indexPath.row].merchant?.id
		
		self.navigationController?.pushViewController(vc, animated: true)
	}
}
