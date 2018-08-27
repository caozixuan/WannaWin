//
//  SearchResultCouponViewController.swift
//  PointExchange
//
//  Created by yiner on 2018/8/23.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import UIKit

class SearchResultCouponViewController: UIViewController,UITableViewDataSource,UITableViewDelegate {

	@IBOutlet var tableView: UITableView!
	var items = [Item]()
	var start = 0
	var end = 9
	override func viewDidLoad() {
        super.viewDidLoad()
		self.tableView.delegate = self
		self.tableView.dataSource = self
        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
	
	func search(keyword:String){
		ServerConnector.searchItem(start: start, end: end, keyword: keyword){(result,items) in
			if result {
				self.items = items!
				self.tableView.reloadData()
			}
		}
	}
	
	func numberOfSections(in tableView: UITableView) -> Int {
		return 1
	}
	func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
		return items.count
	}
	
	func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
		return UITableViewCell()
	}
}
