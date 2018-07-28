//
//  CardDetailTableViewController.swift
//  PointExchange
//
//  Created by yiner on 2018/7/7.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import UIKit

class CardDetailTableViewController: UITableViewController {
	
	var merchantID:String?
	var indicator:UIActivityIndicatorView?
	
	@IBOutlet weak var cardImageView: UIImageView!
	@IBOutlet weak var pointLabel: UILabel!
	@IBOutlet weak var numberLabel: UILabel!
	@IBOutlet weak var citiPointLabelView: UILabel!
	override func viewDidLoad() {
        super.viewDidLoad()
		
		// 加入“历史积分兑换记录”按钮在导航栏右边
		let historyBtn = UIBarButtonItem(title: "兑换记录", style: .plain, target: self, action: #selector(goExchangeHistoryVC))
		self.navigationItem.rightBarButtonItem = historyBtn
		indicator = ActivityIndicator.createWaitIndicator(parentView: self.view)
		indicator?.startAnimating()
		ServerConnector.getCardDetail(merchantID: self.merchantID!){(result,card) in
			if result {
				self.cardImageView.imageFromURL(card.logoURL!, placeholder: UIImage())
				self.pointLabel.text = String(stringInterpolationSegment: card.points)
				self.citiPointLabelView.text = String(stringInterpolationSegment: card.points * card.proportion!)
				self.numberLabel.text = card.number
			}
			self.indicator?.stopAnimating()
		}
    }

    // MARK: - Navigations
	@objc func goExchangeHistoryVC() {
		let storyBoard = UIStoryboard(name:"HomePage", bundle:nil)
		let view = storyBoard.instantiateViewController(withIdentifier: "ExchangeHistoryTableViewController")
		self.navigationController!.pushViewController(view, animated: true)
	}
	
	// MARK: - Unbind
	override func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
		if indexPath.section == 1 && indexPath.row == 0 {
			// TODO: - 网络请求
			let isSuccess = true
			// ...
			let alert:UIAlertController!
			let okAction:UIAlertAction!
			if isSuccess {
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
	}

}
