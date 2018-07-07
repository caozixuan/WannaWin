//
//  UserSettingViewController.swift
//  PointExchange
//
//  Created by panyy on 2018/7/7.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import UIKit

class UserSettingViewController: UITableViewController {

    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }
    
    override func viewWillAppear(_ animated: Bool) {
        self.tableView.reloadData()
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
        let view = segue.destination as! EditTableViewController
        switch segue.identifier {
        case "edit username":
            view.textPlaceholder = User.getUser().username
            view.title = "设置用户名"
            view.changedType = "username"
        case "edit nickname":
            view.textPlaceholder = User.getUser().nickname
            view.title = "设置昵称"
            view.changedType = "nickname"
        default:
            break;
        }
    }
    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = super.tableView(self.tableView, cellForRowAt: indexPath)
        if indexPath.section == 1 {
            switch indexPath.row{
            case 0:
                cell.detailTextLabel?.text = User.getUser().nickname
            case 1:
                cell.detailTextLabel?.text = User.getUser().username!
            default:
                break;
            }
        }
        return cell
        
    }
    

}
