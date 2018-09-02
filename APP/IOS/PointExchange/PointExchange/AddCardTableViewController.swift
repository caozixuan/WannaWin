//
//  AddCardTableViewController.swift
//  PointExchange
//
//  Created by yiner on 2018/7/6.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import UIKit

class AddCardViewController: UIViewController {

    var merchant:Merchant?
	var merchantID:String?
	
	var activityIndicator:UIActivityIndicatorView?

	@IBOutlet weak var nameLabel: UILabel!
	@IBOutlet weak var passwordField: UITextField!

	@IBOutlet weak var logoView: UIImageView!
	@IBOutlet weak var cardID: UITextField!
	
	@IBOutlet weak var button: UIButton!
	override func viewDidLoad() {
        super.viewDidLoad()
		cardID.delegate = self
		ServerConnector.getMerchantInfoByID(id: merchantID!){(result,merchant) in
			if result{
				self.merchant = merchant
				self.logoView.imageFromURL((self.merchant?.logoURL)!, placeholder: UIImage())
				self.nameLabel.text = self.merchant?.name
			}
		}
		
		activityIndicator = ActivityIndicator.createWaitIndicator(parentView: self.view)
    }

	// MARK: - TextField delegate
	func textFieldShouldEndEditing(_ textField: UITextField) -> Bool {
		
		// FIXME: - 后期需要修改条件
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
	
	@IBAction func clickButton(_ sender: Any) {
		activityIndicator?.startAnimating()
		ServerConnector.addCard(merchantID: (merchant?.id)!, cardNum: cardID.text!, password: passwordField.text!){ result in
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
				let okAction = UIAlertAction(title:"确定", style:.default, handler:{ action in
				})
				alert.addAction(okAction)
			}
			self.activityIndicator?.stopAnimating()
			self.present(alert, animated: true, completion: nil)
		}
	}
	

}
