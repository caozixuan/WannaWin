//
//  AddCardTableViewController.swift
//  PointExchange
//
//  Created by yiner on 2018/7/6.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import UIKit

class AddCardTableViewController: UITableViewController {

    var merchant:Merchant?
	
	var activityIndicator:UIActivityIndicatorView?

	@IBOutlet weak var passwordField: UITextField!
	
	@IBOutlet weak var cardId: UITextField!
	
	@IBOutlet weak var finishBtn: UITableViewCell!
	
	override func viewDidLoad() {
        super.viewDidLoad()
		cardId.delegate = self
		finishBtn.isUserInteractionEnabled = false
		
		activityIndicator = ActivityIndicator.createWaitIndicator(parentView: self.view)
    }

	// MARK: - TextField delegate
	func textFieldShouldEndEditing(_ textField: UITextField) -> Bool {
		let text2 = cardId.text
		// FIXME: - 后期需要修改条件
		if text2?.count != 0 && isCardIdValid()
		{
			finishBtn.isUserInteractionEnabled = true
			finishBtn.backgroundColor = UIColor.blue
		}
		return true
	}
	
	func isCardIdValid() -> Bool {
		// FIXME: - 后期完善
		return true
	}
	
	func checkBindVaild() -> Bool {
		// FIXME: - 后期完善
		return true
	}
	
	override func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
		if indexPath.section == 1 && indexPath.row == 0 {
			activityIndicator?.startAnimating()
			ServerConnector.addCard(merchantID: (merchant?.id)!, cardNum: cardId.text!, password: passwordField.text!){ result in
				let alert:UIAlertController!
				if result {
					alert = UIAlertController(title:"绑定成功", message:nil, preferredStyle:.alert)
					let okAction = UIAlertAction(title:"确定", style:.default, handler:{ action in
						self.navigationController!.popViewController(animated: true)
						self.navigationController?.popViewController(animated: true)
					})
					alert.addAction(okAction)
				}
				else {
					alert = UIAlertController(title:"绑定失败", message:nil, preferredStyle:.alert)
					let cancelAction = UIAlertAction(title:"取消", style:.cancel, handler:nil)
					alert.addAction(cancelAction)
				}
				self.present(alert, animated: true, completion: nil)
			}
			
		}
	}

}
