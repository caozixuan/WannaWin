//
//  AddCardTableViewController.swift
//  PointExchange
//
//  Created by yiner on 2018/7/6.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import UIKit

class AddCardTableViewController: UITableViewController,UIPickerViewDelegate,UIPickerViewDataSource {

    
    var cardTypeCount:Int?
    var merchant:Merchant?

    @IBOutlet weak var pickerView: UIPickerView!
	@IBOutlet weak var cardId: UITextField!
	
	@IBOutlet weak var finishBtn: UITableViewCell!
	
	override func viewDidLoad() {
        super.viewDidLoad()
		cardId.delegate = self
		finishBtn.isUserInteractionEnabled = false
        
        pickerView.delegate = self
        pickerView.dataSource = self
        
        
        
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
			let alert:UIAlertController!
			if checkBindVaild() {
				alert = UIAlertController(title:"绑定成功", message:nil, preferredStyle:.alert)
				let okAction = UIAlertAction(title:"确定", style:.default, handler:{ action in
					self.navigationController!.popViewController(animated: true)
					self.navigationController?.popViewController(animated: true)
				})
				alert.addAction(okAction)
			}
			else {
				// TODO: - message后期完善
				alert = UIAlertController(title:"绑定失败", message:nil, preferredStyle:.alert)
				let cancelAction = UIAlertAction(title:"取消", style:.cancel, handler:nil)
				alert.addAction(cancelAction)
			}
			self.present(alert, animated: true, completion: nil)
		}
	}
    
    // pickerView相关
    func numberOfComponents(in pickerView: UIPickerView) -> Int {
        if let number = cardTypeCount {
            return number
        }
        else{
            return 1
        }
    }
    func pickerView(_ pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int {
        if let count = cardTypeCount {
            return count
        }
        return 0
    }
    
    func pickerView(_ pickerView: UIPickerView, titleForRow row: Int, forComponent component: Int) -> String? {
        if let m = merchant?.cardTypes {
            return m[row].cardType
        }
        return "null"
    }
	
}
