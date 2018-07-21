 
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
	
    override func viewDidLoad() {
        super.viewDidLoad()
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
		ServerConnector.getMerchantsInfos(start: 0, n: 10, callback: gotMerchantsCallback)
		
	}
	
	/// 获得商户信息后的回调函数
	func gotMerchantsCallback(result:Bool, merchants:[Merchant]){
		if result {
			MerchantList.list = merchants
			var merchantName = [String]()
			for m in merchants{
				merchantName.append(m.name)
			}
			let storyBoard = UIStoryboard(name:"HomePage", bundle:nil)
			let view = storyBoard.instantiateViewController(withIdentifier: "MerchantChooseTableViewController") as! MerchantChooseTableViewController
			view.merchantNames = merchantName
			self.navigationController!.pushViewController(view, animated: true)
		}
		else {
			print("商户信息获取失败")
		}
		activityIndicator?.stopAnimating()
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
            (cell.viewWithTag(1) as! UIImageView).imageFromURL(cardArray![indexPath.row].logoURL!, placeholder: UIImage())
            (cell.viewWithTag(2) as! UILabel).text = String(stringInterpolationSegment: cardArray![indexPath.row].points)
            (cell.viewWithTag(3) as! UILabel).text = String(stringInterpolationSegment: cardArray![indexPath.row].points*cardArray![indexPath.row].proportion!)
            
        }
		return cell
		
	}
	
//    override func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
//        let storyBoard = UIStoryboard(name:"HomePage", bundle:nil)
//        let view = storyBoard.instantiateViewController(withIdentifier: "CardDetailTableViewController")
//        self.navigationController!.pushViewController(view, animated: true)
//    }

}
