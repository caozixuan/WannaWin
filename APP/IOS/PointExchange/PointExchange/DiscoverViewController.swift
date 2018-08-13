//
//  DiscoverViewController.swift
//  PointExchange
//
//  Created by yiner on 2018/7/5.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import UIKit
import RxSwift
import RxCocoa

class DiscoverViewController: UIViewController, UITableViewDelegate, UITableViewDataSource{
    @IBOutlet weak var searchBar: UISearchBar!
    
	@IBOutlet weak var couponView: DiscoverCouponView!
	var rowCount = 4;
    var merchantArray:[Merchant]?
    @IBOutlet weak var tableView: UITableView!
    
    var tableCellIdentifier:String = "local discount"
    
    var activityIndicator:UIActivityIndicatorView?
	

    override func viewWillAppear(_ animated:Bool) {
        super.viewWillAppear(animated)
        
        self.tableView.delegate=self
        self.tableView.dataSource=self
        
        self.tableView.rowHeight = 68
		
        // Do any additional setup after loading the view.
        
        activityIndicator = ActivityIndicator.createWaitIndicator(parentView: self.view)
        activityIndicator?.startAnimating()
		ServerConnector.getMerchantCount(){(result,count) in
			if result {
				ServerConnector.getMerchantsInfos(start: 0, n: count){ (result,merchants) in
					if result {
						self.merchantArray = merchants
						self.tableView.reloadData()
					}
					self.activityIndicator?.stopAnimating()
				}
			}
		}
		
		self.tableView.register(UINib(nibName: "DsMerchantTableViewCell", bundle: nil), forCellReuseIdentifier: "merchantCell")
		self.tableView.rowHeight = 95
		
    }

	
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    // MARK: - YDMenuDataSource / Delegate

    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
		// TODO: - 设置发现页行数
        if merchantArray != nil {
            return (merchantArray?.count)!
        }else{
            return 0
        }
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
		var cell = self.tableView.dequeueReusableCell(withIdentifier: "merchantCell") as? DsMerchantTableViewCell
		if cell == nil {
			cell = UITableViewCell(style: .default, reuseIdentifier: "merchantCell") as? DsMerchantTableViewCell
		}
        cell?.logoView.imageFromURL((merchantArray?[indexPath.row].logoURL)!, placeholder: UIImage())
		cell?.nameLabel.text = merchantArray![indexPath.row].name
		
		return cell!
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        // TODO: - 点击发现页折扣活动后跳转
		let sb = UIStoryboard(name: "Discover", bundle: nil)
		let view = sb.instantiateViewController(withIdentifier: "MerchantDetailTableView") as! MerchantDetailTableViewController
		view.merchant = merchantArray?[indexPath.row]
		self.navigationController?.pushViewController(view, animated: true)
    }

    
}
