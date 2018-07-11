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
}
