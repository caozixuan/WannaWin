//
//  EditTableViewController.swift
//  PointExchange
//
//  Created by panyy on 2018/7/7.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import UIKit

class EditTableViewController: UITableViewController {

    var changedType:String?
    var textPlaceholder:String?
    @IBOutlet weak var textField: UITextField!
    override func viewDidLoad() {
        super.viewDidLoad()
        
        textField.placeholder = textPlaceholder!
        
        textField.delegate=self

    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }

   
    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return 1;
    }
    override func numberOfSections(in tableView: UITableView) -> Int {
        return 1;
    }
    
    @IBAction func finishButtonClick(_ sender: Any) {
        if isInputValid() {
            self.changedUserInfo()
            self.navigationController!.popViewController(animated: true)
        }
    }
    
    func isInputValid()->Bool{
        return true
    }
    
    func changedUserInfo(){
        if self.textField.text != ""{
            switch changedType {
            case "username":
                User.getUser().username = self.textField.text
            case "nickname":
                User.getUser().nickname = self.textField.text!
            default:
                break;
            }
        }
        User.saveToKeychain()
    }
}
