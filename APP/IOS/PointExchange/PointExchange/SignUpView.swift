//
//  SignUpView.swift
//  PointExchange
//
//  Created by yiner on 2018/8/20.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import UIKit

class SignUpView: UIView {
	
	@IBOutlet weak var nextBtn: UIButton!
	@IBOutlet weak var signUpBtn: UIButton!
	@IBOutlet weak var vcodeBtn: UIButton!
	@IBOutlet weak var passwordAgainField: UITextField!
	@IBOutlet weak var passwordField: UITextField!
	
	@IBOutlet weak var vcodeField: UITextField!
	@IBOutlet weak var phoneNumField: UITextField!
	@IBOutlet var view2: UIView!
	@IBOutlet var view1: UIView!
	
	
	var phoneNumberValid = true
	var passwordValid = true
	var passwordAgainValid = true
	var vcodeValid = true
	var second = 30
	/*
    // Only override draw() if you perform custom drawing.
    // An empty implementation adversely affects performance during animation.
    override func draw(_ rect: CGRect) {
        // Drawing code
    }
    */
	override init(frame: CGRect) {
		super.init(frame: frame)
		initViewFromNib()
	}
	
	required init?(coder aDecoder: NSCoder) {
		super.init(coder: aDecoder)
		initViewFromNib()
	}
	
	private func initViewFromNib(){
		let bundle = Bundle(for: type(of: self))
		let nib = UINib(nibName: "SignUpView", bundle: bundle)
		self.view1 = nib.instantiate(withOwner: self, options: nil)[0] as! UIView
		self.view1.frame = bounds
		self.addSubview(view1)
		self.view2 = nib.instantiate(withOwner: self, options: nil)[1] as! UIView
		self.view2.frame = bounds
	}

	@IBAction func clickSignUp(_ sender: Any) {
		
	}
	
	@IBAction func clickNextBtn(_ sender: Any) {
		view1.removeFromSuperview()
		self.addSubview(view2)
	}
	@IBAction func clickGetVCode(_ sender: Any) {
		ServerConnector.getVCode(phoneNumber: phoneNumField.text!)
		self.vcodeBtn.isEnabled = false
		let timer = Timer.scheduledTimer(timeInterval: 1, target: self, selector: #selector(SignUpViewController.refreshVCodeTime), userInfo: sender, repeats: true)
		timer.fire()
	}
	
	@IBAction func clickLogin(_ sender: Any) {
		
	}
	
	/// 检查phoneNumber输入是否合法
	func checkPhoneNumberInput()->Bool{
		if phoneNumField.text?.count != 11 {
			phoneNumField.shake(direction: .horizontal, times: 5, duration: 0.05, delta: 2, completion: nil)
			return false
		}
		else{
			
			return true
		}
	}
	
	/// 检查验证码输入是否合法
	func checkIdentifyCodeInput()->Bool{
		// TODO: - 检查验证码
		if let _ = self.vcodeField.text {
			return true
		}
		return false
	}
	
	/// 检查密码输入是否合法
	func checkPasswordInput()->Bool{
		// TODO: - 检查密码
		return true
	}
	
	/// 检查确认密码输入是否合法
	func checkPasswordIdentifyInput()->Bool{
		if passwordAgainField.text != passwordField.text {
			passwordAgainField.shake(direction: .horizontal, times: 5, duration: 0.05, delta: 2, completion: nil)
			return false
		}else{
			
			return true
		}
	}
	
	
	//各区域输入完毕后的检查
	@IBAction func checkInputValid(_ sender: Any) {
		
		switch (sender as! UITextField).restorationIdentifier{
		case "phoneNumber field":
			phoneNumberValid=checkPhoneNumberInput()
			vcodeBtn.isEnabled = true
		case "vcode field":
			vcodeValid=checkIdentifyCodeInput()
		case "password field":
			passwordValid=checkPasswordIdentifyInput()
			passwordAgainValid=checkPasswordIdentifyInput()
		case "passwordAgain field":
			passwordAgainValid=checkPasswordIdentifyInput()
		default:
			break;
		}
		
		if phoneNumberValid && passwordAgainValid && passwordAgainValid && phoneNumField.text != "" && vcodeField.text != "" && passwordField.text != "" && passwordAgainField.text != ""{
			signUpBtn.isUserInteractionEnabled=true
			signUpBtn.backgroundColor=UIColor.blue
		}
		else{
			signUpBtn.isUserInteractionEnabled=false
			signUpBtn.backgroundColor=UIColor.gray
		}
	}
	
	
	// 重新获取验证码倒计时
	@objc func refreshVCodeTime(timer:Timer){
		self.vcodeBtn.titleLabel?.text = "\(second)秒后重新发送"
		self.vcodeBtn.setTitle("\(second)秒后重新发送", for: .disabled)
		if second <= 0 {
			second = 30
			self.vcodeBtn.isEnabled = true
			self.vcodeBtn.titleLabel?.text = "获取验证码"
			self.vcodeBtn.setTitle("获取验证码", for: .normal)
			timer.invalidate()
		}
		second -= 1
	}

}
