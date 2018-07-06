//
//  CardInfoTableViewController.swift
//  PointExchange
//
//  Created by yiner on 2018/7/5.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import UIKit

class CardInfoTableViewController: UITableViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
		// 加入“添加”按钮在导航栏右边
		let addBtn = UIBarButtonItem(barButtonSystemItem:UIBarButtonSystemItem.add , target: self, action: #selector(goAddVC))
		self.navigationItem.rightBarButtonItem = addBtn
		
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
	
	// 跳转到“添加银行卡”页面
	@objc func goAddVC() {
		let storyBoard = UIStoryboard(name:"Main", bundle:nil)
		let view = storyBoard.instantiateViewController(withIdentifier: "AddCardTableViewController")
		self.navigationController!.pushViewController(view, animated: true)
		
	}
	
	//MARK: - Table view data source
	
	override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
		// #warning Incomplete implementation, return the number of rows
		return 3
	}
	
	 override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
		let cell = tableView.dequeueReusableCell(withIdentifier: "card", for: indexPath)
		return cell
		
	}
	
    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

}
