//
//  ResetPasswordTableViewController.swift
//  PointExchange
//
//  Created by panyy on 2018/7/16.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import UIKit

class ResetPasswordTableViewController: UITableViewController {
    @IBOutlet weak var getVCodeBtn: UIButton!
    
    @IBOutlet weak var vcodeField: UITextField!
    @IBOutlet weak var phoneNumField: UITextField!
    override func viewDidLoad() {
        super.viewDidLoad()
        
        
    }
    
    

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

    // MARK: - Table view data source
    
    
    @IBAction func clickGetVCode(_ sender: Any) {
        ServerConnector.checkVCode(phoneNum: phoneNumField.text!, vcode: vcodeField.text!, callback: getVCodeCallbak)
    }
    func getVCodeCallbak(result:Bool){
        if !result {
            let alert = UIAlertController(title:"获取验证码", message:"获取验证码失败，请检查手机号是否正确", preferredStyle:.alert)
            let okAction=UIAlertAction(title:"确定", style:.default, handler:{ action in
            })
            
            alert.addAction(okAction)
            self.present(alert, animated: true, completion: nil)
        }
    }
    
    override func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        if indexPath.section == 1 {
            
        }
    }
    
}
