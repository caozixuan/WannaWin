//
//  SignUpViewController.swift
//  PointExchange
//
//  Created by yiner on 2018/7/5.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import UIKit

class SignUpViewController: UITableViewController {
    
    var user = User.getUser()

    @IBOutlet weak var passwordField: UITextField!
    @IBOutlet weak var identifyField: UITextField!
    @IBOutlet weak var phoneNumberField: UITextField!
    override func viewDidLoad() {
        super.viewDidLoad()

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
                    self.navigationController!.popViewController(animated: true)
                    self.navigationController?.popViewController(animated: true)
                })
                
                alert.addAction(okAction)
                self.present(alert, animated:true, completion:nil)
            }
        }
    }
    
    private func isSignUpValid()->Bool{
        // TODO: - 注册是否有效
        user.username=phoneNumberField.text
        user.password=passwordField.text
        return true
    }
    
    @IBAction func inputedIdentifyCode(_ sender: Any) {
        if self.isIdentifyCodeValid() {
            
        }
    }
    
    private func isIdentifyCodeValid()->Bool{
        // TODO: - 验证码是否正确
        return true
    }
    
}
