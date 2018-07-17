//
//  UserSettingViewController.swift
//  PointExchange
//
//  Created by panyy on 2018/7/7.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import UIKit
import AFImageHelper

class UserSettingViewController: UITableViewController, UIImagePickerControllerDelegate, UINavigationControllerDelegate {
    
    var picker:UIImagePickerController?

    @IBOutlet weak var portraitImage: UIImageView!
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }
    
    override func viewWillAppear(_ animated: Bool) {
        self.tableView.reloadData()
        let imageClickGuesture = UITapGestureRecognizer(target: self, action: #selector(UserSettingViewController.changePortrait))
        portraitImage.addGestureRecognizer(imageClickGuesture)
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
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

    
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
        if segue.destination.isKind(of: EditTableViewController.self){
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
        
    }
    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = super.tableView(self.tableView, cellForRowAt: indexPath)
        portraitImage.image=User.getUser().getPortraitImage().roundCornersToCircle()
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
