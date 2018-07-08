//
//  User.swift
//  PointExchange
//
//  Created by Pan on 2018/7/5.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import UIKit

class User: NSObject{
    static var user:User = User()
    var username:String?
    var nickname:String = "default"
    var password:String?
    var card:Card?
    var portrait = UIImage(named:"通讯录")
    class func getUser() -> User{
        return user;
    }
    
    static func logout(){
        user=User()
    }
}
