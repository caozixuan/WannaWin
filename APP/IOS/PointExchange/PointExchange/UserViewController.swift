//
//  UserViewController.swift
//  PointExchange
//
//  Created by Pan on 2018/7/5.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import UIKit

class UserViewController: UIViewController, UITableViewDelegate, UITableViewDataSource {
    
    @IBOutlet weak var portrait: UIImageView!
    @IBOutlet weak var portraitBg: UIView!
    @IBOutlet weak var logoutBtn: UIButton!
	@IBOutlet weak var locationLabel: UILabel!
	@IBOutlet weak var loginBtn: UIButton!
	@IBOutlet weak var nameLabel: UILabel!
	@IBOutlet weak var quit: UIButton!
	@IBOutlet weak var tableView: UITableView!
    // 等待动画
    var activityIndicator:UIActivityIndicatorView?
    
    let storyBoard = UIStoryboard(name: "User", bundle: nil)
    

    override func viewDidLoad() {
        super.viewDidLoad()
		self.tableView.delegate = self
		self.tableView.dataSource = self
		self.tableView.rowHeight = 60
    }
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
		activityIndicator = ActivityIndicator.createWaitIndicator(parentView: self.view)
		refreshView()
        
    }
	
	func refreshView(){
		if User.getUser().username != nil {
			self.nameLabel.text = User.getUser().username
			self.loginBtn.isHidden = true
			self.locationLabel.isHidden = false
			self.logoutBtn.isHidden = false
			self.nameLabel.isHidden = false
            
            self.portrait.isUserInteractionEnabled = true
            let tap1 = UITapGestureRecognizer(target: self, action: #selector(goToUserSetting(_:)))
            let tap2 = UITapGestureRecognizer(target: self, action: #selector(goToUserSetting(_:)))
            self.portrait.addGestureRecognizer(tap1)
            self.portraitBg.addGestureRecognizer(tap2)
		}
		else{
			self.nameLabel.text = "未登录"
			self.loginBtn.isHidden = false
			self.locationLabel.isHidden = true
			self.logoutBtn.isHidden = true
			self.nameLabel.isHidden = true
			
		}
	}
    
    //MARK: - Table view data source
	func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
		return 3
	}
	
	func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
		switch indexPath.row {
		case 0:
			let cell = self.tableView.dequeueReusableCell(withIdentifier: "orderCell")
			return cell!
		case 1:
			let cell = self.tableView.dequeueReusableCell(withIdentifier: "recordCell")
			return cell!
		case 2:
			let cell = self.tableView.dequeueReusableCell(withIdentifier: "settingCell")
            // 去除最后一行的分割线
            cell!.separatorInset = UIEdgeInsetsMake(0,0, 0, cell!.bounds.size.width)
			return cell!
		default:
			return UITableViewCell()
		}
	}
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath:IndexPath){
            switch indexPath.row{
            // 查看我的订单
            case 0:
                checkHistory()
            // 查看积分兑换记录
            case 1:
                checkPointRecord()
			// 通用设置
			case 2:
                let storyBoard = UIStoryboard(name:"GeneralSetting", bundle:nil)
				let view = storyBoard.instantiateViewController(withIdentifier: "SettingViewController")
				self.navigationController?.pushViewController(view, animated: true)
            default:
                break;
            }
    }
    
    

    /// 退出登录
    func logout(){
        User.logout()
        print("logout")
        self.tableView.reloadData()
        
    }

    /// 查看积分兑换记录
    func checkPointRecord(){
        if let _ = User.getUser().username{
			let view = self.storyBoard.instantiateViewController(withIdentifier:"PointHistoryViewController") as! PointHistoryViewController
			self.navigationController?.pushViewController(view, animated: true)
            
            
        }else{
            let view = storyBoard.instantiateViewController(withIdentifier:"LoginViewController")
            self.navigationController?.pushViewController(view, animated: true)
        }
    }
    
    /// 查看历史订单
    func checkHistory(){
        if let _ = User.getUser().username{
            let view = self.storyBoard.instantiateViewController(withIdentifier:"OrdersViewController") as! OrdersViewController
			self.navigationController?.pushViewController(view, animated: true)
            
        }else{
            let view = storyBoard.instantiateViewController(withIdentifier:"LoginViewController")
            self.navigationController?.pushViewController(view, animated: true)
        }
    }
    
	@IBAction func logoutBtnClick(_ sender: Any) {
		let alert = UIAlertController(title:"退出登录", message:"是否确认退出登录？", preferredStyle:.alert)
		let okAction=UIAlertAction(title:"确定", style:.default, handler:{ action in
			self.logout()
			self.refreshView()
		})
		let cancelAction=UIAlertAction(title:"取消", style:.cancel, handler:nil)
		
		alert.addAction(cancelAction)
		alert.addAction(okAction)
		self.present(alert, animated:true, completion:nil)
		
	}
	
	@IBAction func clickLogin(_ sender: Any) {
		let storyBoard = UIStoryboard(name:"User", bundle:nil)
		let view = storyBoard.instantiateViewController(withIdentifier: "LoginViewController")
		self.navigationController!.pushViewController(view, animated: true)
	}
    
    @objc func goToUserSetting(_ tap:UITapGestureRecognizer){
        let storyBoard = UIStoryboard(name:"UserSetting", bundle:nil)
        let view = storyBoard.instantiateViewController(withIdentifier: "UserSettingViewController")
        self.navigationController!.pushViewController(view, animated: true)
    }
    
}
