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
import Kingfisher

class DiscoverViewController: UIViewController, UITableViewDelegate, UITableViewDataSource{
    @IBOutlet weak var searchBarView: UIView!
    
	@IBOutlet weak var couponView: DiscoverCouponView!
	var rowCount = 4;
    var merchantArray:[Merchant]?
	var items = [Item]()
	
    @IBOutlet weak var tableView: UITableView!
    
    var activityIndicator:UIActivityIndicatorView?
	var searchController:UISearchController?

    override func viewWillAppear(_ animated:Bool) {
        super.viewWillAppear(animated)
        
        self.tableView.delegate=self
        self.tableView.dataSource=self
//        self.couponView.viewDelegate = self
		
        self.tableView.rowHeight = 68
		
		// searchController
		let searchResultVC = UIStoryboard(name: "Discover", bundle: nil).instantiateViewController(withIdentifier: "SearchResultViewController") as! SearchResultViewController
		self.searchController = UISearchController(searchResultsController: searchResultVC)
        // Do any additional setup after loading the view.
		searchController?.searchBar.frame = CGRect(x: 0, y: 0, width: 375, height: 56)
		searchController?.searchBar.searchBarStyle = .minimal
		searchController?.searchBar.delegate = searchResultVC
		searchController?.searchResultsUpdater = searchResultVC
		searchController?.definesPresentationContext = true
		searchController?.searchBar.tintColor = UIColor(red: 255/255, green: 149/255, blue: 70/255, alpha: 1.0)
		searchController?.searchBar.setBackgroundImage(UIImage(), for: .any, barMetrics: .default)
		self.searchBarView.addSubview((searchController?.searchBar)!)
		
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
		ServerConnector.getRecommendedItems(){(result,items) in
			if result{
				self.items = items
				for i in 0...2{
					self.couponView.images[i].tag = i
					let tapGesture = UITapGestureRecognizer(target: self, action: #selector(self.clickImage(_:)))
					self.couponView.images[i].addGestureRecognizer(tapGesture)
					self.couponView.images[i].isUserInteractionEnabled = true
					let imageURL = URL(string: (items[i].logoURL?.addingPercentEncoding(withAllowedCharacters: .urlQueryAllowed))!)
					self.couponView.images[i].kf.indicatorType = .activity
					self.couponView.images[i].kf.setImage(with: imageURL){(image, error, cacheType, imageUrl) in
						self.couponView.images[i].viewWithTag(1)?.isHidden = false
					}
					let couponNameLabel = UILabel()
					couponNameLabel.text = items[i].name
					couponNameLabel.font = UIFont.systemFont(ofSize: 12)
					couponNameLabel.textColor = UIColor.white
					couponNameLabel.frame = CGRect(x: 10, y: 100, width: 150, height: 28)
					self.couponView.imageViews[i].addSubview(couponNameLabel)
				}
			}
		}
		
		self.tableView.register(UINib(nibName: "DsMerchantTableViewCell", bundle: nil), forCellReuseIdentifier: "merchantCell")
		self.tableView.rowHeight = 95
		
    }
	
	@objc func clickImage(_ sender:UITapGestureRecognizer){
		let sb = UIStoryboard(name: "Discover", bundle: nil)
		let vc = sb.instantiateViewController(withIdentifier: "CouponDetailViewController") as! CouponDetailViewController
		vc.itemID = self.items[(sender.view?.tag)!].ItemID
		self.navigationController?.pushViewController(vc, animated: true)
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
		let sb = UIStoryboard(name: "Discover", bundle: nil)
		let view = sb.instantiateViewController(withIdentifier: "MerchantDetailViewController") as! MerchantDetailViewController
		view.merchant = merchantArray?[indexPath.row]
		self.navigationController?.pushViewController(view, animated: true)
    }
	
//	func addTapImageAction() {
//		for i in 0 ... 2{
//			couponView.images[i].tag = i
//			let gesture = UITapGestureRecognizer(target: self, action: #selector(self.tapImageAction(_:)))
//			couponView.images[i].addGestureRecognizer(gesture)
//
//		}
//	}
//
//	@objc func tapImageAction(_ sender:UITapGestureRecognizer){
//		let sb = UIStoryboard(name: "Discover", bundle: nil)
//		let vc = sb.instantiateViewController(withIdentifier: "CouponDetailViewController") as! CouponDetailViewController
//		vc.itemID = items[(sender.view?.tag)!].ItemID
//		self.navigationController?.pushViewController(vc, animated: true)
//
//	}
	
}
extension DiscoverViewController:UISearchBarDelegate{
}
