//
//  ModifyPasswordViewController.swift
//  PointExchange
//
//  Created by 黄小英 on 2018/8/20.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import UIKit

class ModifyPasswordViewController: UIViewController, ModifyPasswordViewDelegate {
    
    @IBOutlet weak var modifyPasswordView: ModifyPasswordView!
    var activityIndicator:UIActivityIndicatorView?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        modifyPasswordView.delegate = self
    }

    func modify() {
        ServerConnector.changePassword(oldPassword: modifyPasswordView.oldPswField.text!, newPassword: modifyPasswordView.newPswField.text!, callback: changedCallback)
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

}
