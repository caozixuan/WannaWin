//
//  CardDetailTableViewController.swift
//  PointExchange
//
//  Created by yiner on 2018/7/7.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import UIKit

class CardDetailTableViewController: UITableViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
		
		// 加入“历史积分兑换记录”按钮在导航栏右边
		let historyBtn = UIBarButtonItem(title: "兑换记录", style: .plain, target: self, action: #selector(goExchangeHistoryVC))
		self.navigationItem.rightBarButtonItem = historyBtn
    }

    // MARK: - Navigation
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
