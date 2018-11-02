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

class DiscoverTableViewController: UITableViewController{
//    @IBOutlet weak var searchBarView: UIView!
	
	@IBOutlet weak var mainTableView: UITableView!
	@IBOutlet weak var couponView: DiscoverCouponView!
	var rowCount = 4;
    var merchantArray:[Merchant]?
	var items = [Item]()
	
        
    var activityIndicator:UIActivityIndicatorView?
	var searchController:UISearchController?
	var searchResultVC:SearchResultViewController!
	var searchNavigationVC:UINavigationController!
	
	override func viewDidLoad() {
		// searchController
		searchResultVC = UIStoryboard(name: "Discover", bundle: nil).instantiateViewController(withIdentifier: "SearchResultViewController") as! SearchResultViewController
		
		self.searchController = UISearchController(searchResultsController: searchResultVC)
		searchController?.searchBar.searchBarStyle = .minimal
		searchController?.searchBar.delegate = searchResultVC
		searchController?.searchResultsUpdater = searchResultVC
		definesPresentationContext = true
		searchController?.dimsBackgroundDuringPresentation = false
		searchController?.searchBar.tintColor = UIColor(red: 255/255, green: 149/255, blue: 70/255, alpha: 1.0)
		searchResultVC.searchBar = searchController?.searchBar
		if #available(iOS 11, *) {
			navigationItem.searchController = searchController
			searchController?.hidesNavigationBarDuringPresentation = true
			navigationItem.hidesSearchBarWhenScrolling = false
		} else {
			navigationItem.titleView = searchController?.searchBar
			searchController?.hidesNavigationBarDuringPresentation = false
		}
		
		// mainTableView
		mainTableView.delegate = self
		mainTableView.dataSource = self
	}

    override func viewWillAppear(_ animated:Bool) {
        super.viewWillAppear(animated)
		
//        self.couponView.viewDelegate = self
		
//        self.tableView.rowHeight = 68
		
		// 加载数据
        activityIndicator = ActivityIndicator.createWaitIndicator(parentView: self.view)
        activityIndicator?.startAnimating()
		ServerConnector.getMerchantCount(){(result,count) in
			if result {
				ServerConnector.getMerchantsInfos(start: 0, n: count){ (result,merchants) in
					if result {
						self.merchantArray = merchants
						self.mainTableView.reloadData()
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
					couponNameLabel.font = UIFont.boldSystemFont(ofSize: 14)
					couponNameLabel.textColor = UIColor.black
					couponNameLabel.frame = CGRect(x: 10, y: 120, width: 150, height: 28)
					self.couponView.imageViews[i].addSubview(couponNameLabel)
				}
			}
		}
		
		// 商家tableView
		self.mainTableView.register(UINib(nibName: "DsMerchantTableViewCell", bundle: nil), forCellReuseIdentifier: "merchantCell")
//		self.mainTableView.rowHeight = 95
		
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

	override func numberOfSections(in tableView: UITableView) -> Int {
		return 2
	}
    
    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
		
		if section == 1{
			if merchantArray != nil {
				return (merchantArray?.count)!
			}else{
				return 0
			}
		}
		return 1
    }
	
	//调整section字体
	override func tableView(_ tableView: UITableView, viewForHeaderInSection section: Int) -> UIView? {
		let headerView = UIView()
		headerView.backgroundColor = mainTableView.backgroundColor
		let titleLabel = UILabel()
		if section == 0{
			titleLabel.text = "推荐优惠券"
			
		}
		else{
			titleLabel.text = "推荐商家"
		}
		titleLabel.frame = CGRect(x: 5, y: 5, width: 500, height: 40)
		
		titleLabel.textColor = UIColor.black
//		titleLabel.sizeToFit()
		titleLabel.font = UIFont.systemFont(ofSize: 23)
		headerView.addSubview(titleLabel)
		
		
		return headerView
	}
	
	//返回分区头部高度
	override func tableView(_ tableView: UITableView, heightForHeaderInSection section: Int) -> CGFloat {
		return 45
	}
    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
		if indexPath.section == 1{
			var cell = self.mainTableView.dequeueReusableCell(withIdentifier: "merchantCell") as? DsMerchantTableViewCell
			if cell == nil {
				cell = UITableViewCell(style: .default, reuseIdentifier: "merchantCell") as? DsMerchantTableViewCell
			}
			cell?.logoView.imageFromURL((merchantArray?[indexPath.row].logoURL)!, placeholder: UIImage())
			cell?.nameLabel.text = merchantArray![indexPath.row].name
			cell?.addressLabel.text = merchantArray![indexPath.row].description
			cell?.typeLabel.text = transBussinessType(type: merchantArray![indexPath.row].businessType!)
			
			return cell!
		}
		
		let cell = super.tableView(mainTableView, cellForRowAt: indexPath)
		cell.selectionStyle = .none
		return cell
    }
	
	override func tableView(_ tableView: UITableView,
							heightForRowAt indexPath: IndexPath) -> CGFloat {
		if indexPath.section == 1{
			return 90
		}else{
			return super.tableView(tableView, heightForRowAt: indexPath)
		}
	}
	override func tableView(_ tableView: UITableView,
							indentationLevelForRowAt indexPath: IndexPath) -> Int {
		if indexPath.section == 1{
			let newIndexPath = IndexPath(row: 0, section: indexPath.section)
			return super.tableView(tableView, indentationLevelForRowAt: newIndexPath)
		}else{
			return super.tableView(tableView, indentationLevelForRowAt: indexPath)
		}
	}
    
    override func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
		if indexPath.section == 1{
			let sb = UIStoryboard(name: "Discover", bundle: nil)
			let view = sb.instantiateViewController(withIdentifier: "MerchantDetailViewController") as! MerchantDetailViewController
			view.merchant = merchantArray?[indexPath.row]
			self.navigationController?.pushViewController(view, animated: true)
		}
		
    }
    
    func transBussinessType(type:String)->String{
        var result = ""
        switch type {
        case "catering":
            result = "餐饮"
        case "operator":
            result = "运营商"
        case "aviation":
            result = "航空"
        case "hotel":
            result = "酒店"
        case "supermarket":
            result = "超市便利店"
        case "movie":
            result = "电影"
        default:
            result = "一般"
        }
        return result
    }

	
}


