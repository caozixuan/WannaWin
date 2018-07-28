//
//  ExchangeItemCellView.swift
//  PointExchange
//
//  Created by yiner on 2018/7/20.
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

class ExchangeItemCellView: UIView {
	
	// 换算和总计相关
	var proportion:Double?
	
	// 代理用来获得textfield更新的值
	var delegate:ExchangeItemCellDelegate?
    
	@IBOutlet var view: UIView!
	@IBOutlet weak var middleView: UIView!
	@IBOutlet weak var sourcePoints: UILabel!
	@IBOutlet weak var editSourcePoints: UITextField!
	@IBOutlet weak var targetPoints: UILabel!
	@IBOutlet weak var storeName: UILabel!
	@IBOutlet weak var checkbox: UIButton!
	
	@IBOutlet weak var barView: UIView!
	
	override init(frame: CGRect) {
		super.init(frame: frame)
		initViewFromNib()
	}
	
	required init?(coder aDecoder: NSCoder) {
		super.init(coder: aDecoder)
		self.initViewFromNib()
		self.setUp()
	}
	
	private func initViewFromNib(){
		let bundle = Bundle(for: type(of: self))
		let nib = UINib(nibName: "ExchangeItemCellView", bundle: bundle)
		self.view = nib.instantiate(withOwner: self, options: nil)[0] as! UIView
		self.view.frame = bounds
		self.addSubview(view)
	}
	
	func setUp() {
		setupShadow()
		
		//setup status for points
		editSourcePoints.isHidden = true
		sourcePoints.isHidden = false
		checkbox.addTarget(self, action: #selector(checkboxClick), for: .touchUpInside)
		editSourcePoints.addTarget(self, action: #selector(pointBeginEdit), for: .editingDidBegin)
		editSourcePoints.addTarget(self, action: #selector(pointEndEdit), for: .editingDidEnd)
	}
	
	func setupShadow() {
		barView.layer.cornerRadius = 14
		barView.layer.shadowColor = UIColor(red: CGFloat(132/255.0), green: 143/255.0, blue: 161/255.0, alpha: 0.5/1.0).cgColor
		barView.layer.shadowOffset = CGSize(width:0, height:2)
		barView.layer.shadowOpacity = 1;
		barView.layer.shadowRadius = 5;
		
		middleView.layer.shadowColor = UIColor(red: CGFloat(119/255.0), green: 110/255.0, blue: 110/255.0, alpha: 0.5/1.0).cgColor
		middleView.layer.shadowOffset = CGSize(width:0, height:0)
		middleView.layer.shadowOpacity = 0.5;
		middleView.layer.shadowRadius = 4;
	}
	
	/// 点击选中按钮的触发动作
	@objc func checkboxClick(button:UIButton){
		button.isSelected = !button.isSelected
		
		if button.isSelected {
			editSourcePoints?.isHidden = false
			sourcePoints?.isHidden = true
			
			// 触发代理获得转换后的通用积分，统计积分总数
			if let text = targetPoints?.text {
				self.delegate?.contentDidChanged(text: text, row: editSourcePoints.tag, type: .add)
			}
		}
		else {
			editSourcePoints?.isHidden = true
			sourcePoints?.isHidden = false
			
			// 触发代理获得转换后的通用积分，统计积分总数
			if let text = targetPoints?.text {
				self.delegate?.contentDidChanged(text: text, row: editSourcePoints.tag, type: .minus)
			}
		}
	}
	
	@objc func pointBeginEdit(){
		// 触发代理获得textfield数据，统计积分总数
		if let text = targetPoints?.text {
			self.delegate?.contentDidChanged(text: text, row: editSourcePoints.tag, type: .minus)
		}
	}
	
	/// 点击确认修改按钮的触发动作
	@objc func pointEndEdit(field:UITextField){
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
	
	/// 让viewController成为textField的delegate来控制键盘收回
	@objc func setTextFieldDelegateWith(_ viewController:UIViewController){
		editSourcePoints.delegate = viewController
	}
	
}
