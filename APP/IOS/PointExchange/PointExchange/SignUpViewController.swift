//
//  SignUpViewController.swift
//  PointExchange
//
//  Created by yiner on 2018/7/5.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import UIKit

class SignUpViewController: UITableViewController {
    
    
    var phoneNumberValid = false, passwordValid = false, identifyValid = false, passwordIdentifyValid = false

    @IBOutlet weak var passwordIdentifyField: UITextField!
    @IBOutlet weak var signUpButton: UITableViewCell!
    @IBOutlet weak var passwordField: UITextField!
    @IBOutlet weak var identifyField: UITextField!
    @IBOutlet weak var phoneNumberField: UITextField!
    override func viewDidLoad() {
        super.viewDidLoad()
        passwordField.delegate=self
        identifyField.delegate=self
        phoneNumberField.delegate=self
        passwordIdentifyField.delegate=self
        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */
    
    override func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        //若点击的是注册按钮
        if indexPath.section == 2 {
            if self.isSignUpValid(){
                let alert = UIAlertController(title:"注册", message:"注册成功！", preferredStyle:.alert)
                let okAction=UIAlertAction(title:"确定", style:.default, handler:{ action in
                    self.navigationController?.popViewController(animated: true)
                    self.navigationController?.popViewController(animated: true)
                })
                
                alert.addAction(okAction)
                self.present(alert, animated:true, completion:nil)
            }
        }
    }
    
    private func isSignUpValid()->Bool{
        // TODO: - 注册是否有效
        if let username = phoneNumberField.text {
            User.getUser().username=username
        }
        else{
            let alert = UIAlertController(title:"注册失败", message:"请检查信息是否填写正确", preferredStyle:.alert)
            let okAction=UIAlertAction(title:"确定", style:.default, handler:{ action in
                self.navigationController!.popViewController(animated: true)
                self.navigationController?.popViewController(animated: true)
            })
            let cancelAction=UIAlertAction(title:"取消", style:.cancel, handler:nil)
            
            alert.addAction(okAction)
            alert.addAction(cancelAction)
            self.present(alert, animated: true, completion: nil)
            return false
        }
        if let password=passwordField.text {
            User.getUser().password=password
        }else{
            let alert = UIAlertController(title:"注册失败", message:"请检查信息是否填写正确", preferredStyle:.alert)
            let okAction=UIAlertAction(title:"确定", style:.default, handler:{ action in
                self.navigationController!.popViewController(animated: true)
                self.navigationController?.popViewController(animated: true)
            })
            let cancelAction=UIAlertAction(title:"取消", style:.cancel, handler:nil)
            
            alert.addAction(okAction)
            alert.addAction(cancelAction)
            self.present(alert, animated: true, completion: nil)
            return false
        }
        return true
    }
    
    /// 检查phoneNumber输入是否合法
    func checkPhoneNumberInput()->Bool{
        if phoneNumberField.text?.count != 11 {
            phoneNumberField.layer.borderWidth = 0.5
            phoneNumberField.layer.cornerRadius = 5
            phoneNumberField.layer.borderColor=UIColor.red.cgColor
            // TODO: - 验证手机号是否注册过
            
            phoneNumberField.shake(direction: .horizontal, times: 5, duration: 0.05, delta: 2, completion: nil)
            return false
        }
        else{
            phoneNumberField.layer.borderColor = UIColor.gray.cgColor
            return true
        }
    }
    
    /// 检查验证码输入是否合法
    func checkIdentifyCodeInput()->Bool{
        // TODO: - 检查验证码
        
        return true
    }
    
    /// 检查密码输入是否合法
    func checkPasswordInput()->Bool{
        // TODO: - 检查密码
        return true
    }
    
    /// 检查确认密码输入是否合法
    func checkPasswordIdentifyInput()->Bool{
        if passwordIdentifyField.text != passwordField.text {
            passwordIdentifyField.layer.borderWidth = 0.5
            passwordIdentifyField.layer.cornerRadius = 5
            passwordIdentifyField.layer.borderColor=UIColor.red.cgColor
            
            passwordIdentifyField.shake(direction: .horizontal, times: 5, duration: 0.05, delta: 2, completion: nil)
            return false
        }else{
            passwordIdentifyField.layer.borderColor = UIColor.gray.cgColor
            return true
        }
    }
    
    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        //使除了“注册”按钮以外的cell都不可选择
        if indexPath.section == 2 && indexPath.row == 1 {
            return super.tableView(self.tableView, cellForRowAt: indexPath)
        }
        else{
            let cell = super.tableView(self.tableView, cellForRowAt: indexPath)
            cell.selectionStyle = .none
            return cell
        }
    }
    
    //各区域输入完毕后的检查
    @IBAction func checkInputValid(_ sender: Any) {
        
        switch (sender as! UITextField).restorationIdentifier{
        case "phoneNumber field":
            phoneNumberValid=checkPhoneNumberInput()
        case "identifyCode field":
            identifyValid=checkIdentifyCodeInput()
        case "password field":
            passwordValid=checkPasswordIdentifyInput()
            passwordIdentifyValid=checkPasswordIdentifyInput()
        case "passwordIdentify field":
            passwordIdentifyValid=checkPasswordIdentifyInput()
        default:
            break;
        }
        
        if phoneNumberValid && passwordIdentifyValid && passwordIdentifyValid && phoneNumberField.text != "" && identifyField.text != "" && passwordField.text != "" && passwordIdentifyField.text != ""{
            signUpButton.isUserInteractionEnabled=true
            signUpButton.backgroundColor=UIColor.blue
        }
        else{
            signUpButton.isUserInteractionEnabled=false
            signUpButton.backgroundColor=UIColor.gray
        }
    }
    
}
