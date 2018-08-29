//
//  LoginViewController.swift
//  PointExchange
//
//  Created by Pan on 2018/7/5.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import UIKit
import Alamofire

class LoginViewController: UIViewController,LoginViewDelegate{
	@IBOutlet weak var loginView: LoginView!
	var activityIndicator:UIActivityIndicatorView?
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
		loginView.delegate = self
    }
    
    override func viewDidAppear(_ animated: Bool) {
        //调整以适配屏幕
        self.loginView.view.frame = self.loginView.bounds
    }

	func login() {
		ServerConnector.login(phoneNum: loginView.usernameField.text!, password: loginView.passwordField.text!, callback: afterLogin)
		// 加载动画
		self.activityIndicator = ActivityIndicator.createWaitIndicator(parentView: self.view)
		self.activityIndicator?.startAnimating()
	}
	
	func gotoSignUp() {
		let sb = UIStoryboard(name: "User", bundle: nil)
		let vc = sb.instantiateViewController(withIdentifier: "SignUpViewController")
		self.navigationController?.pushViewController(vc, animated: true)
	}
	
    
    // 登录完成后的操作
    func afterLogin(result:Bool){
        if result == true {
            self.saveUserInfo()
            
            self.activityIndicator?.stopAnimating()
            
            let alert = UIAlertController(title:"登录", message:"登录成功！", preferredStyle:.alert)
            let okAction=UIAlertAction(title:"确定", style:.default, handler:{ action in
                self.navigationController?.popViewController(animated: true)
				
            })
            alert.addAction(okAction)
            self.present(alert, animated: true, completion: nil)
        }
        else{
			let alert:UIAlertController!
			let reachable = NetworkReachabilityManager()
			if !(reachable?.isReachable)! {
				alert = UIAlertController(title:"登录", message:"网络连接失败，请刷新重试！", preferredStyle:.alert)
			}
			else {
				alert = UIAlertController(title:"登录", message:"登录失败！请检查用户名和密码是否正确", preferredStyle:.alert)
			}
            let okAction=UIAlertAction(title:"确定", style:.default, handler:{ action in
            })
            alert.addAction(okAction)
            self.present(alert, animated: true, completion: nil)
        }
        self.activityIndicator?.stopAnimating()
		
    }
    
    // 设置当前用户的用户名和密码，并存储到keychain
    func saveUserInfo(){
        User.saveToKeychain()
    }
    
	
    
}
