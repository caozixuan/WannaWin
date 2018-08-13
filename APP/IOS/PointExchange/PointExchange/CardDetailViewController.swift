//
//  CardDetailTableViewController.swift
//  PointExchange
//
//  Created by yiner on 2018/7/7.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import UIKit

class CardDetailViewController: UIViewController,UITableViewDataSource {
	
	var merchantID:String?
	var indicator:UIActivityIndicatorView?
	var card:Card?
	
	@IBOutlet weak var cardImageView: UIImageView!
	
	@IBOutlet weak var tableView: UITableView!
	
	override func viewDidLoad() {
        super.viewDidLoad()
		self.tableView.dataSource = self
		self.tableView.layer.zPosition = 10
		// 加入“历史积分兑换记录”按钮在导航栏右边
		let historyBtn = UIBarButtonItem(title: "兑换记录", style: .plain, target: self, action: #selector(goExchangeHistoryVC))
		self.navigationItem.rightBarButtonItem = historyBtn
		indicator = ActivityIndicator.createWaitIndicator(parentView: self.view)
		indicator?.startAnimating()
		ServerConnector.getCardDetail(merchantID: self.merchantID!){(result,card) in
			if result {
				self.card = card
			self.cardImageView.imageFromURL(card.logoURL!, placeholder: UIImage())
				self.tableView.reloadData()
				// - TODO: 会员卡条形码
			}
			self.indicator?.stopAnimating()
		}
    }
	
    // MARK: - Navigations
	@objc func goExchangeHistoryVC() {
		let storyBoard = UIStoryboard(name:"HomePage", bundle:nil)
		let view = storyBoard.instantiateViewController(withIdentifier: "ExchangeHistoryViewController") as! ExchangeHistoryViewController
		view.merchantID = self.merchantID
		
		self.navigationController!.pushViewController(view, animated: true)
	}
	
	// MARK: - Unbind
	func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
		if indexPath.section == 2 && indexPath.row == 0 {
			// TODO: - 网络请求
			let alert = UIAlertController(title:"解绑会员卡", message:"您确定要解绑该会员卡吗？", preferredStyle:.alert)
			let ok = UIAlertAction(title:"确定", style:.default, handler:{ action in
				ServerConnector.unbindCard(merchantID: self.merchantID!, cardNum: (self.card?.number)!){ result in
					let alert:UIAlertController!
					let okAction:UIAlertAction!
					if result {
						alert = UIAlertController(title:"提示", message:"会员卡解绑成功！", preferredStyle:.alert)
						okAction = UIAlertAction(title:"确定", style:.default, handler:{ action in
							self.navigationController!.popViewController(animated: true)
						})
					}
					else {
						alert = UIAlertController(title:"提示", message:"会员卡解绑失败！", preferredStyle:.alert)
						okAction = UIAlertAction(title:"确定", style:.default, handler:nil)
					}
					
					alert.addAction(okAction)
					self.present(alert, animated: true, completion: nil)
				}
			})
			let cancel = UIAlertAction(title:"取消", style:.cancel, handler:{ action in
			})
			alert.addAction(ok)
			alert.addAction(cancel)
			self.present(alert, animated: true, completion: nil)
			// ...
			
		}
	}

	func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
		return 4
	}
	
	func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
		var cell = UITableViewCell()
		switch indexPath.row {
		case 0:
			cell = self.tableView.dequeueReusableCell(withIdentifier: "pointCell")!
			if card != nil {
				(cell.viewWithTag(1) as! UILabel).text = String(stringInterpolationSegment: (card?.points)!)
			}
		case 1:
			cell = self.tableView.dequeueReusableCell(withIdentifier: "citiPointCell")!
			if card != nil {
				(cell.viewWithTag(1) as! UILabel).text = String(stringInterpolationSegment: (card?.points)!*(card?.proportion)!)
			}
			
		case 2:
			cell = self.tableView.dequeueReusableCell(withIdentifier: "cardNumCell")!
			if card != nil {
				(cell.viewWithTag(1) as! UILabel).text = card?.number
			}
		default:
			cell = self.tableView.dequeueReusableCell(withIdentifier: "ruleCell")!
		}
		return cell
	}
}
