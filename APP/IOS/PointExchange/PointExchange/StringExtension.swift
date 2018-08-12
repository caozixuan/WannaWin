//
//  StringExtension.swift
//  PointExchange
//
//  Created by panyy on 2018/7/11.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import Foundation

extension String{
    func transformToPinyin()->String{
        let mutableString = NSMutableString(string:self)
        CFStringTransform(mutableString, nil, kCFStringTransformToLatin, false)
        CFStringTransform(mutableString, nil, kCFStringTransformStripDiacritics, false)
        return String(mutableString)
    }
	
	func dateFormatChange()->String{
		let dateFormatter = DateFormatter()
		dateFormatter.dateFormat = "MMM d, YYYY hh:mm:ss tt"
		print(self)
		print("MMM d, YYYY hh:mm:ss tt")
		let date = dateFormatter.date(from: self)
		let newString = dateFormatter.string(from: date!)
		return newString
	}
}


