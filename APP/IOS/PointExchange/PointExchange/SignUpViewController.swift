//
//  SignUpViewController.swift
//  PointExchange
//
//  Created by yiner on 2018/7/5.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import UIKit

class SignUpViewController: UIViewController,SignUpViewDelegate {

	@IBOutlet var signUpView: SignUpView!
	override func viewDidLoad() {
		
        super.viewDidLoad()
		signUpView.delegate = self
        // Do any additional setup after loading the view.
    }

    
    // 向服务器进行注册操作
    func signUp(){
        ServerConnector.sendPassword(phoneNumber: signUpView.phoneNumField.text!, vcode: signUpView.vcodeField.text!, password: signUpView.passwordField.text!, callback: signUpFinish)
    }
    
    // 注册完成后的处理
    func signUpFinish(result:Bool){
        if result == true{
            let alert = UIAlertController(title:"注册", message:"注册成功！", preferredStyle:.alert)
            let okAction=UIAlertAction(title:"确定", style:.default, handler:{ action in
				ServerConnector.login(phoneNum: self.signUpView.phoneNumField.text!, password: self.signUpView.passwordField.text!){result in
					if result {
						User.getUser().username = self.signUpView.phoneNumField.text
						User.getUser().password = self.signUpView.passwordField.text
						User.saveToKeychain()
						let sb = UIStoryboard(name: "User", bundle: nil)
						let vc = sb.instantiateViewController(withIdentifier: "TendencySurveyViewController")
						self.navigationController?.pushViewController(vc, animated: true)
					}
				}
            })
            alert.addAction(okAction)
            self.present(alert, animated: true, completion: nil)
			
        }else{
            let alert = UIAlertController(title:"注册失败", message:"请检查信息是否填写正确", preferredStyle:.alert)
            let okAction=UIAlertAction(title:"确定", style:.default, handler:{ action in
            })
            let cancelAction=UIAlertAction(title:"取消", style:.cancel, handler:nil)
            
            alert.addAction(okAction)
            alert.addAction(cancelAction)
            self.present(alert, animated: true, completion: nil)
        }
    }
}
