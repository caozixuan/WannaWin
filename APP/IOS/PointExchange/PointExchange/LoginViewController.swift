//
//  LoginViewController.swift
//  PointExchange
//
//  Created by Pan on 2018/7/5.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import UIKit

class LoginViewController: UITableViewController{
    @IBOutlet weak var usernameField: UITextField!
    @IBOutlet weak var passwordField: UITextField!
    @IBOutlet weak var loginButton: UITableViewCell!
    
    
    
    var user:User = User.getUser()
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
    override func tableView(_ tableView: UITableView, didSelectRowAt indexPath:IndexPath){
        if (indexPath as NSIndexPath).section == 2 && (indexPath as NSIndexPath).row == 0{
            if isLoginValid() {
                user.username = usernameField.text
                user.password = passwordField.text
                self.navigationController!.popViewController(animated: true)
            }
        }
    }
    
    func isLoginValid()->Bool{
        return true
    }
    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = super.tableView(self.tableView, cellForRowAt: indexPath)
        cell.selectionStyle = UITableViewCellSelectionStyle.none
        return cell
        
    }
    
    @IBAction func isInputValid(_ sender: Any) {
        if usernameField.text != "" && passwordField.text != "" {
//            loginButton.textLabel?.backgroundColor = UIColor.blue
            loginButton.contentView.backgroundColor = UIColor.blue
            loginButton.isUserInteractionEnabled = true
        }
        else{
//            loginButton.textLabel?.backgroundColor = UIColor.lightGray
            loginButton.contentView.backgroundColor = UIColor.lightGray
            loginButton.isUserInteractionEnabled = false
        }
    }
    
    
}
