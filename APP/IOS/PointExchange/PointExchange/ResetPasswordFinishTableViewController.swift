//
//  ResetPasswordFinishTableViewController.swift
//  PointExchange
//
//  Created by panyy on 2018/7/16.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import UIKit

class ResetPasswordFinishTableViewController: UITableViewController {

    @IBOutlet weak var newPswField: UITextField!
    
    @IBOutlet weak var confirmPswField: UITextField!
    
    var phoneNum:String?
    
    @IBOutlet weak var button: UITableViewCell!
    override func viewDidLoad() {
        super.viewDidLoad()

        // Uncomment the following line to preserve selection between presentations
        // self.clearsSelectionOnViewWillAppear = false

        // Uncomment the following line to display an Edit button in the navigation bar for this view controller.
        // self.navigationItem.rightBarButtonItem = self.editButtonItem
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

    // MARK: - Table view data source

    override func numberOfSections(in tableView: UITableView) -> Int {
        // #warning Incomplete implementation, return the number of sections
        return 0
    }

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        // #warning Incomplete implementation, return the number of rows
        return 0
    }
    
    override func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        if indexPath.section == 1 {
            ServerConnector.resetPassword(phoneNum: phoneNum!, newPassword: newPswField.text!, callback: resetCallback)
        }
    }
    
    private func resetCallback(result:Bool){
        if result {
            let alert = UIAlertController(title:"重置密码", message:"重置密码成功", preferredStyle:.alert)
            let okAction=UIAlertAction(title:"确定", style:.default, handler:{ action in
                self.navigationController!.popViewController(animated: true)
                self.navigationController?.popViewController(animated: true)
            })
            
            alert.addAction(okAction)
            self.present(alert, animated: true, completion: nil)
        }
        else{
            let alert = UIAlertController(title:"重置密码", message:"重置密码失败", preferredStyle:.alert)
            let okAction=UIAlertAction(title:"确定", style:.default, handler:{ action in
            })
            
            alert.addAction(okAction)
            self.present(alert, animated: true, completion: nil)
        }
    }

    
}
