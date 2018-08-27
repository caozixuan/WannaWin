//
//  SearchResultMerchantViewController.swift
//  PointExchange
//
//  Created by yiner on 2018/8/23.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import UIKit

class SearchResultMerchantViewController: UIViewController,UITableViewDelegate,UITableViewDataSource {

	@IBOutlet var tableView: UITableView!
	var merchants = [Merchant]()
	var searchResults = [Merchant]()
	var start = 0
	var end = 9
	override func viewDidLoad() {
        super.viewDidLoad()
		self.tableView.delegate = self
		self.tableView.dataSource = self
		self.tableView.register(UINib(nibName: "SearchTableViewCell", bundle: nil), forCellReuseIdentifier: "searchCell")
        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
	
	func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
		var cell = tableView.dequeueReusableCell(withIdentifier: "searchCell") as? SearchTableViewCell
		if cell == nil {
			cell = UITableViewCell(style:.default, reuseIdentifier:"searchCell") as? SearchTableViewCell
		}
		let imageURL = URL(string: (searchResults[indexPath.row].logoURL?.addingPercentEncoding(withAllowedCharacters: .urlQueryAllowed))!)
		cell?.logoImageView.kf.indicatorType = .activity
		cell?.logoImageView.kf.setImage(with: imageURL)
		cell?.title.text = searchResults[indexPath.row].name
		cell?.descriptionLabel.text = searchResults[indexPath.row].description
		return cell!
	}
	
	func search(keyword:String){
		ServerConnector.getMerchantCount(){(result,count) in
			if result{
				ServerConnector.getMerchantsInfos(start: 0, n: count){(result,merchants) in
					if result{
						self.merchants = merchants
						self.searchMerchant(keyword: keyword)
						self.tableView.reloadData()
					}
					
				}
			}
		}
	}
	private func searchMerchant(keyword:String){
		searchResults.removeAll()
		for merchant in self.merchants{
			if merchant.name.lowercased().contains(keyword.lowercased()){
				searchResults.append(merchant)
			}
		}
		for merchant in self.merchants{
			if (merchant.description?.contains(keyword))!{
				searchResults.append(merchant)
			}
		}

		
	}
	
	func numberOfSections(in tableView: UITableView) -> Int {
		return 1
	}
	func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
		return searchResults.count
	}

}
