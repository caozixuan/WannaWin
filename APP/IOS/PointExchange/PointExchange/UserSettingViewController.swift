//
//  UserSettingViewController.swift
//  PointExchange
//
//  Created by yiner on 2018/7/5.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import UIKit

class UserSettingViewController: UITableViewController {
    
    var user = User.getUser()
    
    @IBOutlet weak var logoutButton: UIView!
    //登录后头部
    var usernameHeadLabel:UILabel = UILabel()
    var boundingCitiCardHeadLabel:UILabel = UILabel()
    var usernameLabel:UILabel = UILabel()
    
    //未登录时登录按钮
    var loginButton:UIButton?
    
    @IBOutlet weak var userTableCell: UITableViewCell?

    override func viewDidLoad() {
        super.viewDidLoad()
        if let name = user.username{
            usernameHeadLabel.text="用户名："
            
            usernameHeadLabel.frame=CGRect(x:143, y:47, width:70, height:21)
            usernameHeadLabel.textColor=UIColor.white
            
            usernameLabel.textColor=UIColor.white
            usernameLabel.frame=CGRect(x:220, y:47, width:50, height:21)
            usernameLabel.text=name
            userTableCell?.addSubview(usernameLabel)
            userTableCell?.addSubview(usernameHeadLabel)
        }
        else{
            loginButton=UIButton()
            loginButton?.frame=CGRect(x:175, y:47, width:133, height:50)
            loginButton?.setTitleColor(UIColor.white, for:.normal)
            loginButton?.setTitle("登录 / 注册", for: UIControlState.normal)
            loginButton?.layer.borderWidth=2
            loginButton?.layer.borderColor=UIColor.white.cgColor
            loginButton?.layer.cornerRadius=15
            loginButton?.addTarget(self, action: #selector(UserSettingViewController.gotoLogin), for: .touchDown)
            userTableCell?.addSubview(loginButton!)
            
        }
        

        // Do any additional setup after loading the view.
    }
    
    @objc func gotoLogin(){
        let storyBoard = UIStoryboard(name:"Main", bundle:nil)
        let view = storyBoard.instantiateViewController(withIdentifier: "LoginViewController")
        self.navigationController!.pushViewController(view, animated: true)
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    

    
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
        if let name=user.username{
            
        }
        
    }
    
    @IBAction func logout(_ sender: AnyObject){
        
    }

}
