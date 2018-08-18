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
	override func viewDidLoad() {
        super.viewDidLoad()
		tableView.delegate = self
		tableView.dataSource = self
        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
	
	func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
		switch indexPath.row {
		case 0:
			let cell = tableView.dequeueReusableCell(withIdentifier: "fontSizeCell")
			return cell!
		case 1:
			let cell = tableView.dequeueReusableCell(withIdentifier: "clearCacheCell")
			return cell!
		case 2:
			let cell = tableView.dequeueReusableCell(withIdentifier: "feedbackCell")
			return cell!
		case 3:
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
		return 4
	}
	func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
		switch indexPath.row {
		case 0:
			changeFontSize()
		case 1:
			clearCache()
		case 2:
			feedback()
		case 3:
			about()
		default:
			break
		}
	}
	
	func changeFontSize(){
		
	}
	
	func clearCache(){
		
	}
	func feedback(){
		
	}
	func about(){
		
	}
}
