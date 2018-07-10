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
    var id:String?
    var nickname:String = "default"
    var password:String?
    var card:[Card]?
    var portraitName = "通讯录"
    var portraitPath:String?
    var generalPoints: Int?
    var availablePoints: Int?
}

extension User{
    private static var user:User?
    class func getUser() -> User{
        if let userN = user {
            return userN
        }else{
            let currentUserJSON = KeychainHandler.getInstance().string(forKey: "current_user")
            if let currentUser = currentUserJSON?.data(using: .utf8){
                print(currentUserJSON!)
                let decoder = JSONDecoder()
                user = try! decoder.decode(User.self, from: currentUser)
                return user!
            }
            else{
                user = User()
                return user!
            }
        }
    }
    
    static func logout(){
        user=nil
        KeychainHandler.getInstance().removeObject(forKey: "current_user")
    }
    
    static func login(username:String, password:String)->Bool{
        // TODO: - 登录是否有效
        user?.username = username
        user?.password = password
        return true
    }
    
    func getPortraitImage()->UIImage{
        if let path = self.portraitPath {
            // TODO: - 从服务器获取图片
            let image = UIImage(contentsOfFile: path)
            if let i = image {
                return i
            }else{
                return UIImage(named: self.portraitName)!
            }
        }
        else{
            return UIImage(named: self.portraitName)!
        }
    }
    
    static func saveToKeychain(){
        let encoder = JSONEncoder()
        let jsonData = try! encoder.encode(user)
        KeychainHandler.getInstance().set(object: jsonData, forKey: "current_user")
        print("save: \(jsonData)")
    }
}
