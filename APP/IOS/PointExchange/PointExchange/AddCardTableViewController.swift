//
//  AddCardTableViewController.swift
//  PointExchange
//
//  Created by yiner on 2018/7/6.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import UIKit

class AddCardTableViewController: UITableViewController {

	@IBOutlet weak var cardUser: UITextField!
	
	@IBOutlet weak var cardId: UITextField!
	
	@IBOutlet weak var finishBtn: UITableViewCell!
	
	override func viewDidLoad() {
        super.viewDidLoad()
		cardUser.delegate = self
		cardId.delegate = self
		finishBtn.isUserInteractionEnabled = false
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

	// MARK: - TextField delegate
	func textFieldShouldEndEditing(_ textField: UITextField) -> Bool {
		let text1 = cardUser.text
		let text2 = cardId.text
		// FIXME: - 后期需要修改条件
		if text1?.count != 0 &&
			text2?.count != 0 && isCardIdValid()
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
				alert = UIAlertController(title:"完成绑定", message:nil, preferredStyle:.alert)
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
	
    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

}
