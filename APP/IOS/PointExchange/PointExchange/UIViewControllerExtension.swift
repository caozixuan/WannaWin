//
//  UIViewControllerExtension.swift
//  PointExchange
//
//  Created by yiner on 2018/7/7.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import UIKit

extension UIViewController: UITextFieldDelegate{
	//点击return 收回键盘
	public func textFieldShouldReturn(_ textField: UITextField) -> Bool {
		textField.resignFirstResponder()
		return true
	}
	//点击屏幕其他地方  收回键盘
	override open func touchesBegan(_ touches: Set<UITouch>, with event: UIEvent?) {
		self.view.endEditing(true)
	}
	

}

// UIScrollView 与 touchesBegan 会冲突，因此要重写touchesBegan方法
extension UIScrollView{
	override open func touchesBegan(_ touches: Set<UITouch>, with event: UIEvent?) {
		self.next?.touchesBegan(touches, with: event)
		super.touchesBegan(touches, with: event)
	}
}

