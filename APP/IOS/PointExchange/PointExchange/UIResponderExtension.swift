//
//  UIResponderExtension.swift
//  PointExchange
//  get current first responder
//
//  Created by yiner on 2018/9/7.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import UIKit

extension UIResponder {
	
	private static weak var _currentFirstResponder: UIResponder?
	
	static var currentFirstResponder: UIResponder? {
		_currentFirstResponder = nil
		UIApplication.shared.sendAction(#selector(UIResponder.findFirstResponder(_:)), to: nil, from: nil, for: nil)
		
		return _currentFirstResponder
	}
	
	@objc func findFirstResponder(_ sender: Any) {
		UIResponder._currentFirstResponder = self
	}
}
