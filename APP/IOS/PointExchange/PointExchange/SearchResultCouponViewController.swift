//
//  SearchResultCouponViewController.swift
//  PointExchange
//
//  Created by yiner on 2018/8/23.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import UIKit
import Kingfisher
import ESPullToRefresh

class SearchResultCouponViewController: UIViewController,UITableViewDataSource,UITableViewDelegate {

	@IBOutlet var tableView: UITableView!
	var items = [Item]()
	var start = 0
	var end = 6
	var keyword = ""
	override func viewDidLoad() {
        super.viewDidLoad()
		self.tableView.delegate = self
		self.tableView.dataSource = self
		self.tableView.register(UINib(nibName: "SearchTableViewCell", bundle: nil), forCellReuseIdentifier: "searchCell")
		self.tableView.es.addPullToRefresh { [weak self] in
			self?.search(keyword: (self?.keyword)!)
			self?.tableView.es.stopPullToRefresh()
			self?.start = 0
			self?.end = 6
		}
		
		self.tableView.es.addInfiniteScrolling {
			self.start = self.start + 6
			self.end = self.end + 6
			self.search(keyword: self.keyword)
			
		}
        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
	
	func search(keyword:String){
		self.keyword = keyword
		ServerConnector.searchItem(start: start, end: end, keyword: keyword){(result,items) in
			if result {
				if self.items.count == 0 {
					self.items = items!
				}else{
					self.items += items!
				}
				if (items?.count)! < 6{
					self.tableView.es.noticeNoMoreData()
				}else{
					self.tableView.es.stopLoadingMore()
				}
				
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
		var cell = tableView.dequeueReusableCell(withIdentifier: "searchCell") as? SearchTableViewCell
		if cell == nil {
			cell = UITableViewCell(style:.default, reuseIdentifier:"searchCell") as? SearchTableViewCell
		}
		let imageURL = URL(string: (items[indexPath.row].logoURL?.addingPercentEncoding(withAllowedCharacters: .urlQueryAllowed))!)
		cell?.logoImageView.kf.indicatorType = .activity
		cell?.logoImageView.kf.setImage(with: imageURL)
		cell?.title.text = items[indexPath.row].name
		cell?.descriptionLabel.text = items[indexPath.row].description
		return cell!
	}
}
