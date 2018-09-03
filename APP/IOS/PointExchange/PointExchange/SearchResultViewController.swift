//
//  SearchResultViewController.swift
//  PointExchange
//
//  Created by yiner on 2018/8/23.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import UIKit
import PageMenu

class SearchResultViewController: UIViewController {
	
	var currentPage = 0
	var searchBar:UISearchBar?
	var pageMenu:CAPSPageMenu?
	var couponController:SearchResultCouponViewController?
	var merchantController:SearchResultMerchantViewController?
	var offlineController:SearchResultOfflineViewController?
	var searchNavigationVC1: UINavigationController?
	
	override func viewDidAppear(_ animated: Bool) {
		super.viewDidAppear(animated)
		
		if pageMenu == nil {
			var controllerArray:[UIViewController] = []
			couponController = UIStoryboard(name: "Discover", bundle: nil).instantiateViewController(withIdentifier: "SearchResultCouponViewController") as? SearchResultCouponViewController
			couponController?.title = "优惠券"
			merchantController = UIStoryboard(name: "Discover", bundle: nil).instantiateViewController(withIdentifier: "SearchResultMerchantViewController") as? SearchResultMerchantViewController
			merchantController?.title = "商家"
			offlineController = UIStoryboard(name: "Discover", bundle: nil).instantiateViewController(withIdentifier: "SearchResultOfflineViewController") as? SearchResultOfflineViewController
			offlineController?.title = "线下活动"
			controllerArray.append(merchantController!)
			controllerArray.append(couponController!)
			controllerArray.append(offlineController!)
			
			
			let params:[CAPSPageMenuOption] = [
				.useMenuLikeSegmentedControl(true),
				.addBottomMenuHairline (true),
				.scrollMenuBackgroundColor (UIColor.white),
				.menuHeight(35),
				.menuItemFont(UIFont.systemFont(ofSize:18)),
				.unselectedMenuItemLabelColor (UIColor(red: 255/255, green: 149/255, blue: 70/255, alpha: 1.0)),
				.selectedMenuItemLabelColor (UIColor(red: 255/255, green: 149/255, blue: 70/255, alpha: 1.0)),
				.selectionIndicatorColor (UIColor(red: 255/255, green: 149/255, blue: 70/255, alpha: 1.0)),
				.enableHorizontalBounce (false)
				
			]
			
			// 获得导航栏及搜索框的高度，避免控件被遮住
			let frameY = self.view.safeAreaLayoutGuide.layoutFrame.minY 
			
			pageMenu = CAPSPageMenu(viewControllers: controllerArray, frame:CGRect(x: 0, y: frameY, width: self.view.frame.width, height: self.view.safeAreaLayoutGuide.layoutFrame.maxY - frameY),pageMenuOptions:params)
			pageMenu?.delegate = self
			self.addChildViewController(pageMenu!)
			self.view.addSubview((pageMenu?.view)!)
			pageMenu!.didMove(toParentViewController: self)
	
		}
	}
	
}

extension SearchResultViewController:UISearchBarDelegate,UISearchResultsUpdating{
	func updateSearchResults(for searchController: UISearchController) {
		searchController.searchResultsController?.view.isHidden = false;
	}
	func searchBarTextDidBeginEditing(_ searchBar: UISearchBar) {
	}
	
	func searchBarSearchButtonClicked(_ searchBar: UISearchBar) {
		switch pageMenu?.currentPageIndex {
		case 0:
			merchantController?.search(keyword: searchBar.text!)
		case 1:
			couponController?.search(keyword: searchBar.text!)
		case 2:
			offlineController?.search(keyword: searchBar.text!)
		default:
			break
		}
	}
}

extension SearchResultViewController:CAPSPageMenuDelegate{
	func didMoveToPage(_ controller: UIViewController, index: Int) {
		if index != currentPage{
			currentPage = index
			switch index {
			case 0:
				if let keyword = searchBar?.text{
					merchantController?.search(keyword: keyword)
				}
			case 1:
				if let keyword = searchBar?.text{
					couponController?.search(keyword: keyword)
				}
				
			case 2:
				if let keyword = searchBar?.text{
					offlineController?.search(keyword: keyword)
				}
			default:
				break
			}
		}
	}
	
}
