//
//  SettingViewController.swift
//  PointExchange
//
//  Created by yiner on 2018/7/5.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import UIKit

class SettingViewController: UIViewController,UITableViewDataSource,UITableViewDelegate {

	@IBOutlet weak var tableView: UITableView!
    
    let storyBoard = UIStoryboard(name:"GeneralSetting", bundle:nil)
    
	override func viewDidLoad() {
        super.viewDidLoad()
		tableView.delegate = self
		tableView.dataSource = self
        
        // 隐藏tableView最后一个cell的分割线
        let view = UIView(frame: CGRect(x: 0, y: 0, width: 0, height: 0.1))
        tableView.tableFooterView = view
    }
	
	func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
		switch indexPath.row {
		case 0:
			let cell = tableView.dequeueReusableCell(withIdentifier: "clearCacheCell")
			return cell!
		case 1:
			let cell = tableView.dequeueReusableCell(withIdentifier: "feedbackCell")
			return cell!
		case 2:
			let cell = tableView.dequeueReusableCell(withIdentifier: "aboutCell")
			return cell!
		default:
			return UITableViewCell()
		}
	}
	func numberOfSections(in tableView: UITableView) -> Int {
		return 1
	}
	func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
		return 3
	}
	func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
		switch indexPath.row {
		case 0:
			clearCache()
		case 1:
			feedback()
		case 2:
			about()
		default:
			break
		}
	}

	func clearCache(){
        // 计算缓存大小
        let cacheSize = String(fileSizeOfCache())
        
        // 弹出提示框
        let alertController = UIAlertController(title: "提示", message: "已缓存\(cacheSize)MB",preferredStyle: .actionSheet)
        let confirmAction = UIAlertAction(title: "清除缓存", style: .default){ UIAlertAction in
            self.deleteCacheFiles() //清除缓存
        }
        let cancelAction = UIAlertAction(title: "取消", style: .cancel, handler: nil)
        alertController.addAction(confirmAction)
        alertController.addAction(cancelAction)
        self.present(alertController, animated: true, completion: nil)
    
	}
    
	func feedback(){
        let view = storyBoard.instantiateViewController(withIdentifier: "FeedbackViewController")
        view.navigationItem.title = "反馈"
        self.navigationController!.pushViewController(view, animated: true)
	}
    
	func about(){
        let view = storyBoard.instantiateViewController(withIdentifier: "AboutViewController")
        view.navigationItem.title = "关于"
        self.navigationController!.pushViewController(view, animated: true)
	}
    
    /// 计算缓存文件大小
    func fileSizeOfCache()-> Int {
        
        // 取出cache文件夹目录 缓存文件都在这个目录下
        let cachePath = NSSearchPathForDirectoriesInDomains(FileManager.SearchPathDirectory.cachesDirectory, FileManager.SearchPathDomainMask.userDomainMask, true).first
        
        // 取出文件夹下所有文件数组
        let fileArr = FileManager.default.subpaths(atPath: cachePath!)
        
        //快速枚举出所有文件名 计算文件大小
        var size = 0
        for file in fileArr! {
            
            // 把文件名拼接到路径中
            let path = cachePath! + "/\(file)"
            // 取出文件属性
            let floder = try! FileManager.default.attributesOfItem(atPath: path)
            // 用元组取出文件大小属性
            for (abc, bcd) in floder {
                // 累加文件大小
                if abc == FileAttributeKey.size {
                    size += (bcd as AnyObject).integerValue
                }
            }
        }
        
        let mb = size / 1024 / 1024
        
        return mb
    }
    
    /// 删除缓存文件
    func deleteCacheFiles() {
        
        // 取出cache文件夹目录 缓存文件都在这个目录下
        let cachePath = NSSearchPathForDirectoriesInDomains(FileManager.SearchPathDirectory.cachesDirectory, FileManager.SearchPathDomainMask.userDomainMask, true).first
        
        // 取出文件夹下所有文件数组
        let fileArr = FileManager.default.subpaths(atPath: cachePath!)
        
        // 遍历删除
        for file in fileArr! {
            
            let path = cachePath! + "/\(file)"
            if FileManager.default.fileExists(atPath: path) {
                do {
                    try FileManager.default.removeItem(atPath: path)
                } catch {
                    
                }
            }
        }
    }

    

    
}
