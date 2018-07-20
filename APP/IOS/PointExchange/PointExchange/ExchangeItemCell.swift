//
//  ExchangeItemCell.swift
//  PointExchange
//
//  Created by yiner on 2018/7/7.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import UIKit

enum changeType {
	case add
	case minus
}

protocol ExchangeItemCellDelegate {
	func contentDidChanged(text: String, row: Int, type: changeType)
}

class ExchangeItemCell: UITableViewCell{
	
	// store to bank cell
	@IBOutlet weak var checkbox1: UIButton!
	@IBOutlet weak var storeName: UILabel!
	@IBOutlet weak var sourcePoints: UILabel!
	@IBOutlet weak var editSourcePoints: UITextField!
	@IBOutlet weak var targetPoints: UILabel!
	@IBOutlet weak var editBtn1: UIButton!

	// 换算和总计相关
	var proportion:Double?
	
	// 代理用来获得textfield更新的值
	var delegate:ExchangeItemCellDelegate?
	
	override func awakeFromNib() {
        super.awakeFromNib()
		
		editSourcePoints.isHidden = true
		sourcePoints.isHidden = false
		checkbox1.addTarget(self, action: #selector(checkboxClick), for: .touchUpInside)
		//editBtn1.addTarget(self, action: #selector(editBtnClick), for: .touchUpInside)
		//editBtn1.isHidden = true
    }

	/// 点击选中按钮的触发动作
	@objc func checkboxClick(button:UIButton){
		button.isSelected = !button.isSelected
		
		if button.isSelected {
			editBtn1?.isHidden = false

			// 触发代理获得转换后的通用积分，统计积分总数
			if let text = targetPoints?.text {
				self.delegate?.contentDidChanged(text: text, row: editSourcePoints.tag, type: .add)
			}
		}
		else {
			
			if editBtn1 != nil {
				if editBtn1?.isSelected == true {
					editBtnClick(button: editBtn1)
				}
				
				editBtn1?.isSelected = false
				editSourcePoints?.isHidden = true
				sourcePoints?.isHidden = false
				editBtn1?.isHidden = true
			}
			
			// 触发代理获得转换后的通用积分，统计积分总数
			if let text = targetPoints?.text {
				self.delegate?.contentDidChanged(text: text, row: editSourcePoints.tag, type: .minus)
			}
		}
	}
	
	/// 点击确认修改按钮的触发动作
	@objc func editBtnClick(button:UIButton){
		
		button.isSelected = !button.isSelected
		
		if button.isSelected {
			editSourcePoints?.isHidden = false
			sourcePoints?.isHidden = true
			
			// 触发代理获得textfield数据，统计积分总数
			if let text = targetPoints?.text {
				self.delegate?.contentDidChanged(text: text, row: editSourcePoints.tag, type: .minus)
			}
			
		}
		else {
			editSourcePoints?.isHidden = true
			sourcePoints?.isHidden = false
			
			if editSourcePoints.isFirstResponder == true {
				editSourcePoints.resignFirstResponder()
			}
			if editSourcePoints?.text != nil && (editSourcePoints?.text?.count)! > 0 {
				sourcePoints?.text = editSourcePoints?.text
				
				// TODO: - 积分换算
				if sourcePoints?.text != nil {
					if (sourcePoints?.text?.count)! > 0 {
						targetPoints?.text = String(Double(sourcePoints.text!)! * proportion!)
					}
				}
			}
			
			// 触发代理获得转换后的通用积分，统计积分总数
			if let text = targetPoints?.text {
				self.delegate?.contentDidChanged(text: text, row: editSourcePoints.tag, type: .add)
			}
			
		}
	}
	
	
	/// 让viewController成为textField的delegate来控制键盘收回
	@objc func setTextFieldDelegateWith(_ viewController:UIViewController){
		editSourcePoints.delegate = viewController
	}

}
