//
//  User.swift
//  PointExchange
//
//  Created by Pan on 2018/7/5.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import UIKit

class User: Codable{
    var username:String?
    var nickname:String = "default"
    var password:String?
    var card:Card?
    var portraitName = "通讯录"
    var portraitPath:String?
}

extension User{
    static var user:User?
    class func getUser() -> User{
        if let userN = user {
            return userN
        }else{
            let currentUserJSON = KeychainHandler.getInstance().string(forKey: "current_user")
            if let currentUser = currentUserJSON?.data(using: .utf8){
                print(currentUserJSON!)
                let decoder = JSONDecoder()
                return try! decoder.decode(User.self, from: currentUser)
            }
            else{
                return User()
            }
        }
    }
    
    static func logout(){
        user=nil
        KeychainHandler.getInstance().removeObject(forKey: "current_user")
    }
    
    func getPortraitImage()->UIImage{
        if let path = self.portraitPath {
            // TODO: - 从服务器获取图片
            let image = UIImage(contentsOfFile: path)
            return image!
        }
        else{
            return UIImage(named: self.portraitName)!
        }
    }
}
