//
//  KeychainHandler.swift
//  PointExchange
//
//  Created by panyy on 2018/7/7.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import UIKit
import Valet

class KeychainHandler: NSObject {
    static let myValet = Valet.valet(with: Identifier(nonEmpty: "userInfo")!, accessibility: .whenUnlocked)
    
    public static func getInstance()->Valet{
        return myValet
    }
    
}
