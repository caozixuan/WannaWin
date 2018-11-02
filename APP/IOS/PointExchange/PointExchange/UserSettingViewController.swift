//
//  UserSettingViewController.swift
//  PointExchange
//
//  Created by panyy on 2018/7/7.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import UIKit
import AFImageHelper

class UserSettingViewController: UIViewController, UITableViewDataSource, UITableViewDelegate, UIImagePickerControllerDelegate, UINavigationControllerDelegate {
    
    var picker:UIImagePickerController?
    
    @IBOutlet weak var tableView: UITableView!
    @IBOutlet weak var portraitImage: UIImageView!
    
    // 等待动画
    var activityIndicator:UIActivityIndicatorView?
    
    let storyBoard = UIStoryboard(name: "UserSetting", bundle: nil)
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.tableView.delegate = self
        self.tableView.dataSource = self
    }
    
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        
        activityIndicator = ActivityIndicator.createWaitIndicator(parentView: self.view)
        
        self.tableView.reloadData()
        
        portraitImage.isUserInteractionEnabled = true
        let imageClickGuesture = UITapGestureRecognizer(target: self, action: #selector(UserSettingViewController.changePortrait))
        portraitImage.addGestureRecognizer(imageClickGuesture)
    }
    
    @objc func changePortrait(){
        selectImageFromAlbum()
    }
    
    func selectImageFromAlbum(){
        if UIImagePickerController.isSourceTypeAvailable(.photoLibrary){
            picker = UIImagePickerController()
            picker?.delegate = self
            picker?.sourceType=UIImagePickerControllerSourceType.photoLibrary
            picker?.allowsEditing = true
            self.present(picker!, animated:true, completion:nil)
        }
    }
    
    func imagePickerController(_ picker: UIImagePickerController, didFinishPickingMediaWithInfo info: [String : Any]) {
        var image = UIImage()
        image = (info[UIImagePickerControllerEditedImage] as! UIImage)
        let imagePath = info[UIImagePickerControllerImageURL] as! NSURL
        User.getUser().portraitPath = imagePath.path
        
        // TODO: - 上传头像图片
        
        
        portraitImage.image=image.roundCornersToCircle()
        picker.dismiss(animated: true, completion: nil)
    }

    
    // MARK: - Table view data source
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return 2
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        switch indexPath.row {
        case 0:
            let cell = self.tableView.dequeueReusableCell(withIdentifier: "bindCell")
            cell?.selectionStyle = .none
            return cell!
        case 1:
            let cell = self.tableView.dequeueReusableCell(withIdentifier: "modifyPasswordCell")
            cell?.selectionStyle = .none
            // 去除最后一行的分割线
            cell!.separatorInset = UIEdgeInsetsMake(0,0, 0, cell!.bounds.size.width+50)
            return cell!
        default:
            return UITableViewCell()
        }
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath:IndexPath){
		
        switch indexPath.row{
        // 绑定花旗账户
        case 0:
            bindAccount()
        // 修改密码
        case 1:
            let view = storyBoard.instantiateViewController(withIdentifier: "ModifyPasswordViewController") as! ModifyPasswordViewController
            self.navigationController?.pushViewController(view, animated: true)
			self.navigationController?.setNavigationBarHidden(false, animated: true)
        default:
            break;
        }
    }
    
    /// 绑定花旗账户
    func bindAccount(){
        activityIndicator?.startAnimating()
        ServerConnector.bindCitiCard { (result, url) in
            if result {
                let view = self.storyBoard.instantiateViewController(withIdentifier:"AddBankCardViewController") as! AddBankCardViewController
                view.url = url
                self.navigationController?.pushViewController(view, animated: true)
				self.navigationController?.setNavigationBarHidden(false, animated: true)
                self.activityIndicator?.stopAnimating()
            }
        }
    }
    
    
}
