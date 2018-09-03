//
//  SearchResultOfflineViewController.swift
//  PointExchange
//
//  Created by yiner on 2018/8/23.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import UIKit

class SearchResultOfflineViewController: UIViewController, UITableViewDelegate,UITableViewDataSource {

	@IBOutlet var tableView: UITableView!
	var activity = [OfflineActivity]()
	var keyword = ""
	var start = 0
	var end = 9
	
	override func  viewDidAppear(_ animated: Bool) {
		self.tableView.frame = self.view.bounds
	}
	
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
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
	
	func search(keyword:String){
		ServerConnector.searchActivity(start: start, end: end, keyword: keyword){(result, activity) in
			if result {
				if self.activity.count == 0{
					self.activity = activity!
				}else{
					self.activity += activity!
				}
				if (activity?.count)! < 6{
					self.tableView.es.noticeNoMoreData()
				}else{
					self.tableView.es.stopLoadingMore()
				}
				self.tableView.reloadData()
			}
		}
	}
	
	func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
		var cell = tableView.dequeueReusableCell(withIdentifier: "searchCell") as? SearchTableViewCell
		if cell == nil {
			cell = UITableViewCell(style:.default, reuseIdentifier:"searchCell") as? SearchTableViewCell
		}
		let imageURL = URL(string: (activity[indexPath.row].imageURL?.addingPercentEncoding(withAllowedCharacters: .urlQueryAllowed))!)
		cell?.logoImageView.kf.indicatorType = .activity
		cell?.logoImageView.kf.setImage(with: imageURL)
		cell?.title.text = activity[indexPath.row].name
		cell?.descriptionLabel.text = activity[indexPath.row].description
		return cell!
	}
	
	func numberOfSections(in tableView: UITableView) -> Int {
		return 1
	}
	func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
		return activity.count
	}

}
