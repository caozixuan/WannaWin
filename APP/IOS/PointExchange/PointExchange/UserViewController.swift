//
//  UserViewController.swift
//  PointExchange
//
//  Created by Pan on 2018/7/5.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import UIKit

class UserViewController: UITableViewController {
    
    @IBOutlet weak var portraitImageView: UIImageView!
    @IBOutlet weak var exitButton: UITableViewCell!
    @IBOutlet weak var logoutButton: UIView!
    //登录后头部
    var usernameHeadLabel:UILabel = UILabel()
    var boundingCitiCardHeadLabel:UILabel = UILabel()
    var usernameLabel:UILabel = UILabel()
    
    //未登录时登录按钮
    var loginButton:UIButton = UIButton()
    
    @IBOutlet weak var userTableCell: UITableViewCell?

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
    }
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.tableView.reloadData()
        
    }
    
    @objc func gotoLogin(){
        let storyBoard = UIStoryboard(name:"User", bundle:nil)
        let view = storyBoard.instantiateViewController(withIdentifier: "LoginViewController")
        self.navigationController!.pushViewController(view, animated: true)
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    

    
    override func tableView(_ tableView: UITableView, didSelectRowAt indexPath:IndexPath){
        if (indexPath as NSIndexPath).section == 2 && (indexPath as NSIndexPath).row == 0{
            let alert = UIAlertController(title:"退出登录", message:"是否确认退出登录？", preferredStyle:.alert)
            let okAction=UIAlertAction(title:"确定", style:.default, handler:{ action in
                self.logout()
            })
            let cancelAction=UIAlertAction(title:"取消", style:.cancel, handler:nil)
            
            alert.addAction(cancelAction)
            alert.addAction(okAction)
            self.present(alert, animated:true, completion:nil)
        }
        else if indexPath.section == 0 {
            if let _ = User.getUser().username {
                let storyboard = UIStoryboard(name:"User", bundle:nil)
                let view = storyboard.instantiateViewController(withIdentifier: "UserSettingViewController")
                self.navigationController!.pushViewController(view, animated: true)
            }
        }
        else if indexPath.section == 1 {
            let storyBoard = UIStoryboard(name: "User", bundle: nil)
            switch indexPath.row{
            case 0:
                if let _ = User.getUser().username{
                    let view = storyBoard.instantiateViewController(withIdentifier:"AddBankCardViewController")
                    self.navigationController?.pushViewController(view, animated: true)
                }else{
                    let view = storyBoard.instantiateViewController(withIdentifier:"LoginViewController")
                    self.navigationController?.pushViewController(view, animated: true)
                }
                break
            case 1:
                if let _ = User.getUser().username{
                    let view = storyBoard.instantiateViewController(withIdentifier:"OrdersViewController")
                    self.navigationController?.pushViewController(view, animated: true)
                }else{
                    let view = storyBoard.instantiateViewController(withIdentifier:"LoginViewController")
                    self.navigationController?.pushViewController(view, animated: true)
                }
            default:
                break;
            }
            
        }
    }
    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        if indexPath.section == 0 {
            if let name = User.getUser().username{
                usernameHeadLabel.text="用户名："
                usernameHeadLabel.frame=CGRect(x:143, y:47, width:70, height:21)
                usernameHeadLabel.textColor=UIColor.white
                
                usernameLabel.textColor=UIColor.white
                usernameLabel.frame=CGRect(x:220, y:47, width:100, height:21)
                usernameLabel.text=name
                
                boundingCitiCardHeadLabel.text = {() -> String in
                    if let card = User.getUser().card{
                        return "银行卡："
                    }
                    else{
                        return "您尚未绑定银行卡"
                    }
                }()
                boundingCitiCardHeadLabel.textColor=UIColor.white
                boundingCitiCardHeadLabel.frame=CGRect(x:143, y:70, width:200, height:21)
                
                userTableCell?.addSubview(usernameLabel)
                userTableCell?.addSubview(usernameHeadLabel)
                userTableCell?.addSubview(boundingCitiCardHeadLabel)
                
                userTableCell?.accessoryType = UITableViewCellAccessoryType.disclosureIndicator
                userTableCell?.isUserInteractionEnabled = true
                
                
                
                loginButton.removeFromSuperview()
            }
            else{
                loginButton.frame=CGRect(x:175, y:47, width:133, height:50)
                loginButton.setTitleColor(UIColor.white, for:.normal)
                loginButton.setTitle("登录 / 注册", for: UIControlState.normal)
                loginButton.layer.borderWidth=2
                loginButton.layer.borderColor=UIColor.white.cgColor
                loginButton.layer.cornerRadius=15
                loginButton.addTarget(self, action: #selector(UserViewController.gotoLogin), for: .touchDown)
                userTableCell?.addSubview(loginButton)
                userTableCell?.accessoryType = UITableViewCellAccessoryType.none
                userTableCell?.selectionStyle = .none
                
                
                boundingCitiCardHeadLabel.removeFromSuperview()
                usernameLabel.removeFromSuperview()
                usernameHeadLabel.removeFromSuperview()
                
                
                
            }
            //头像显示
            self.portraitImageView.image = User.getUser().getPortraitImage().roundCornersToCircle()
            return userTableCell!
        }
        else if indexPath.section == 2 {
            if let _ = User.getUser().username{
                exitButton.isUserInteractionEnabled = true
                exitButton.backgroundColor = UIColor.white
                exitButton.textLabel?.textColor = UIColor.black
            }else{
                exitButton.isUserInteractionEnabled = false
                exitButton.backgroundColor = UIColor.lightGray
                exitButton.textLabel?.textColor = UIColor.white
            }
            return super.tableView(self.tableView, cellForRowAt: indexPath)
        }
        else{
            return super.tableView(self.tableView, cellForRowAt: indexPath)
        }
        
    }

    
    func logout(){
        User.logout()
        print("logout")
        self.tableView.reloadData()
        
    }

}
