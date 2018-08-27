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
	
	func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
		return UITableViewCell()
	}
	
	func search(keyword:String){
		ServerConnector.getMerchantCount(){(result,count) in
			if result{
				ServerConnector.getMerchantsInfos(start: 0, n: count){(result,merchants) in
					if result{
						self.merchants = merchants
						self.searchMerchant(keyword: keyword)
					}
					
				}
			}
		}
	}
	private func searchMerchant(keyword:String){
		var searchResults = [Merchant]()
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
		return merchants.count
	}

}
