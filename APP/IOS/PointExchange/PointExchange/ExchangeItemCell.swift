//
//  ExchangeItemCell.swift
//  PointExchange
//
//  Created by yiner on 2018/7/7.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import UIKit

class ExchangeItemCell: UITableViewCell{

	// store to bank cell
	@IBOutlet weak var checkbox1: UIButton!
	@IBOutlet weak var storeName: UILabel!
	@IBOutlet weak var sourcePoints: UILabel!
	@IBOutlet weak var editSourcePoints: UITextField!
	@IBOutlet weak var targetPoints: UILabel!
	@IBOutlet weak var editBtn1: UIButton!
	
	//bank to store cell
	@IBOutlet weak var checkbox2: UIButton!
	@IBOutlet weak var editBtn2: UIButton!
	@IBOutlet weak var generalPoints: UILabel!
	@IBOutlet weak var editGeneralPoints: UITextField!

	
	override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
		if self.reuseIdentifier == "store to bank" {
			editSourcePoints.isHidden = true
			sourcePoints.isHidden = false
			checkbox1.addTarget(self, action: #selector(checkboxClick), for: UIControlEvents.touchUpInside)
			editBtn1.addTarget(self, action: #selector(editBtnClick), for: UIControlEvents.touchUpInside)
			editBtn1.isHidden = true
		}
		else { // "bank to store"
			editGeneralPoints.isHidden = true
			generalPoints.isHidden = false
			checkbox2.addTarget(self, action: #selector(checkboxClick), for: UIControlEvents.touchUpInside)
			editBtn2.addTarget(self, action: #selector(editBtnClick), for: UIControlEvents.touchUpInside)
			editBtn2.isHidden = true
		}
		
    }
	
	@objc func checkboxClick(button:UIButton){
		button.isSelected = !button.isSelected
		
		if button.isSelected {
			editBtn1?.isHidden = false
			editBtn2?.isHidden = false
		}
		else {
			editBtn1?.isSelected = false
			editSourcePoints?.isHidden = true
			sourcePoints?.isHidden = false
			editBtn1?.isHidden = true
			
			editBtn2?.isSelected = false
			editGeneralPoints?.isHidden = true
			generalPoints?.isHidden = false
			editBtn2?.isHidden = true
		}
	}
	
	@objc func editBtnClick(button:UIButton){
		button.isSelected = !button.isSelected
		
		if button.isSelected {
			editSourcePoints?.isHidden = false
			sourcePoints?.isHidden = true
			
			editGeneralPoints?.isHidden = false
			generalPoints?.isHidden = true
		}
		else {
			editSourcePoints?.isHidden = true
			sourcePoints?.isHidden = false
			
			editGeneralPoints?.isHidden = true
			generalPoints?.isHidden = false
			
			generalPoints?.text = editGeneralPoints?.text
			sourcePoints?.text = editSourcePoints?.text
		}
	}
	
	/// 让viewController成为textField的delegate来控制键盘收回
	@objc func setTextFieldDelegateWith(_ viewController:UIViewController){
		if self.reuseIdentifier == "store to bank" {
			editSourcePoints.delegate = viewController
		}
		else {
			editGeneralPoints.delegate = viewController
		}
	}
	



}
