//
//  LoginView.swift
//  PointExchange
//
//  Created by yiner on 2018/7/19.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import UIKit

class LoginView: UIView{

    @IBOutlet var view: UIView!
    @IBOutlet weak var usernameField: UITextField!
    @IBOutlet weak var signUpButton: UILabel!
    @IBOutlet weak var loginButton: UIButton!
    @IBOutlet weak var passwordField: UITextField!
    
    var delegate:LoginViewDelegate?
    
    
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
        let nib = UINib(nibName: "LoginView", bundle: bundle)
        self.view = nib.instantiate(withOwner: self, options: nil)[0] as! UIView
        self.view.frame = bounds
        self.addSubview(view)
    }
    
    
    @IBAction func usernameInputCheck(_ sender: Any) {
        if usernameField.text?.count != 11 {
            usernameField.shake()
        }
        else{
            if passwordField.text != "" {
                loginButton.isEnabled = true
            }
        }
    }
    
    @IBAction func passwordInputCheck(_ sender: Any) {
        if passwordField.text != "" && usernameField.text != ""{
            loginButton.isEnabled = true
        }
    }
    @IBAction func login(_ sender: Any) {
        delegate?.login()
    }
	
	@IBAction func gotoSignUp(_ sender: Any) {
		delegate?.gotoSignUp()
	}
}

protocol LoginViewDelegate {
    func login()
	func gotoSignUp()
}
