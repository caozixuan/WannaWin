//
//  User.swift
//  PointExchange
//
//  Created by Pan on 2018/7/5.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import UIKit

class User: NSObject {
    static let user:User = User()
    var username:String?
    var nickname:String = "default"
    var password:String?
    var card:Card?
    class func getUser() -> User{
        return user;
    }
}
