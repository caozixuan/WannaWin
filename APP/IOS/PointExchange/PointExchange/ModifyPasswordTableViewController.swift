//
//  ModifyPasswordTableViewController.swift
//  
//
//  Created by panyy on 2018/7/16.
//

import UIKit

class ModifyPasswordTableViewController: UITableViewController {

    @IBOutlet weak var button: UITableViewCell!
    @IBOutlet weak var confirmPswField: UITextField!
    @IBOutlet weak var newPswField: UITextField!
    @IBOutlet weak var oldPswField: UITextField!
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


    override func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        if indexPath.section == 1{
            ServerConnector.changePassword(oldPassword: oldPswField.text!, newPassword: newPswField.text!, callback: changedCallback)
        }
    }
    func changedCallback(result:Bool){
        if result {
            let alert = UIAlertController(title:"修改", message:"密码修改成功！", preferredStyle:.alert)
            let okAction=UIAlertAction(title:"确定", style:.default, handler:{ action in
                self.navigationController!.popViewController(animated: true)
            })
            alert.addAction(okAction)
            self.present(alert, animated: true, completion: nil)
        }
        else{
            let alert = UIAlertController(title:"修改", message:"密码修改失败！", preferredStyle:.alert)
            let okAction=UIAlertAction(title:"确定", style:.default, handler:{ action in
            })
            alert.addAction(okAction)
            self.present(alert, animated: true, completion: nil)
        }
    }
    @IBAction func changed(_ sender: Any) {
        if self.oldPswField.text != "" && self.newPswField.text != "" && self.confirmPswField.text != "" {
            button.backgroundColor = UIColor.white
            button.textLabel?.textColor = UIColor.black
        }
    }
    
}
