 
 //
//  CardInfoTableViewController.swift
//  PointExchange
//
//  Created by yiner on 2018/7/5.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import UIKit
import AFImageHelper

class CardInfoTableViewController: UITableViewController {

	var activityIndicator:UIActivityIndicatorView?
    
    var searchController:UISearchController?
    
    var cardArray:[Card]?
    
    var searchResult:[Card]?
    
    var isSearch = false
	
	override func viewDidLoad() {
		super.viewDidLoad()
		searchController = UISearchController(searchResultsController: nil)
		searchController?.searchBar.delegate = self
		searchController?.searchResultsUpdater = self
		searchController?.searchBar.searchBarStyle = .minimal
		searchController?.dimsBackgroundDuringPresentation = false
		self.definesPresentationContext = true
		searchController?.hidesNavigationBarDuringPresentation = false
		self.tableView.tableHeaderView = searchController?.searchBar
		self.tableView.tableHeaderView?.backgroundColor = UIColor.white
		
		// 加入“添加”按钮在导航栏右边
		let addBtn = UIBarButtonItem(barButtonSystemItem:UIBarButtonSystemItem.add , target: self, action: #selector(goAddVC))
		self.navigationItem.rightBarButtonItem = addBtn
	}
	
	override func viewWillAppear(_ animated: Bool) {
		super.viewWillAppear(animated)
		
		ServerConnector.getCardCount(){ (result,num) in
			if result{
				ServerConnector.getMostPointCards(n: num, callback: self.getCardCallback)
			}
		}
		
		activityIndicator = ActivityIndicator.createWaitIndicator(parentView: self.view)
		activityIndicator?.startAnimating()
	}
	
	func getCardCallback(result:Bool,cards:[Card]){
        if result {
            cardArray = cards
			User.getUser().card = cards
            tableView.reloadData()
        }
        activityIndicator?.stopAnimating()
    }
	
	/// 跳转到“添加银行卡”页面
	@objc func goAddVC() {
		// 加载动画
		self.activityIndicator = ActivityIndicator.createWaitIndicator(parentView: self.view)
		self.activityIndicator?.startAnimating()
		
		// 获得商家信息
		let storyBoard = UIStoryboard(name:"HomePage", bundle:nil)
		let view = storyBoard.instantiateViewController(withIdentifier: "MerchantChooseTableViewController") as! MerchantChooseTableViewController
		self.navigationController!.pushViewController(view, animated: true)
        self.activityIndicator?.stopAnimating()
	}
	
	
	
	//MARK: - Table view data source
	
	override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        if searchResult == nil || searchResult?.count == 0 {
            if let array = cardArray {
                return array.count
            }
            else{
                return 0
            }
        }else{
            return (searchResult?.count)!
        }
        
	}
	
	 override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        
		let cell  = tableView.dequeueReusableCell(withIdentifier: "card", for: indexPath)
		if (searchController?.isActive)! && searchResult != nil && searchResult?.count != 0{
            (cell.viewWithTag(1) as! UIImageView).imageFromURL((searchResult![indexPath.row].merchant?.logoURL)!, placeholder: UIImage())
            (cell.viewWithTag(2) as! UILabel).text = String(stringInterpolationSegment: searchResult![indexPath.row].points)
            (cell.viewWithTag(3) as! UILabel).text = String(stringInterpolationSegment: searchResult![indexPath.row].points*searchResult![indexPath.row].proportion!)
            (cell.viewWithTag(4) as! UILabel).text = searchResult![indexPath.row].merchant?.name
            (cell.viewWithTag(5) as! UIImageView).image = UIImage(named: "bg2_\(searchResult![indexPath.row].cardStyle!)")
		
        }else{
            if cardArray != nil {
                (cell.viewWithTag(1) as! UIImageView).imageFromURL((cardArray![indexPath.row].merchant?.logoURL)!, placeholder: UIImage())
                (cell.viewWithTag(2) as! UILabel).text = String(stringInterpolationSegment: cardArray![indexPath.row].points)
                (cell.viewWithTag(3) as! UILabel).text = String(stringInterpolationSegment: cardArray![indexPath.row].points*cardArray![indexPath.row].proportion!)
                (cell.viewWithTag(4) as! UILabel).text = cardArray![indexPath.row].merchant?.name
                (cell.viewWithTag(5) as! UIImageView).image = UIImage(named: "bg2_\(cardArray![indexPath.row].cardStyle!)")
                
            }
        }
        cell.selectionStyle = .none
        
		return cell
		
	}
	
	override func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        let sb = UIStoryboard(name: "HomePage", bundle: nil)
        let vc = sb.instantiateViewController(withIdentifier: "CardDetailTableViewController") as! CardDetailViewController
        if (searchController?.isActive)! && searchResult != nil && searchResult?.count != 0{
            vc.merchantID = searchResult?[indexPath.row].merchant?.id
        }else{
            vc.merchantID = cardArray?[indexPath.row].merchant?.id
        }
        self.navigationController?.pushViewController(vc, animated: true)
		// 取消搜索栏
		searchController?.isActive = false
	}
}
 
 extension CardInfoTableViewController:UISearchBarDelegate,UISearchResultsUpdating{
    //点击搜索按钮，触发该代理方法，如果已经显示搜索结果，那么直接去除键盘，否则刷新列表
    func searchBarSearchButtonClicked(_ searchBar: UISearchBar) {
        if searchResult != nil && searchResult?.count != 0{
            tableView.reloadData()
            searchController?.searchBar.resignFirstResponder()
        }
    }
    func scrollViewWillBeginDragging(scrollView: UIScrollView) {
        searchController?.searchBar.resignFirstResponder()
    }
    //这个updateSearchResultsForSearchController(_:)方法是UISearchResultsUpdating中唯一一个我们必须实现的方法。当search bar 成为第一响应者，或者search bar中的内容被改变将触发该方法.不管用户输入还是删除search bar的text，UISearchController都会被通知到并执行上述方法。
    func updateSearchResults(for searchController: UISearchController) {
        let searchString = searchController.searchBar.text
        isSearch = true
        //过滤数据源，存储匹配的数据
        searchResult = cardArray?.filter({ (card) -> Bool in
            let name: NSString = card.merchant?.name as! NSString
            return   (name.range(of: searchString!, options: .caseInsensitive).location) != NSNotFound
        })
        //刷新表格
        tableView.reloadData()
    }

    
    
 }
