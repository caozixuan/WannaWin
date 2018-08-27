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
	
	
	var searchBar:UISearchBar?
	var pageMenu:CAPSPageMenu?
	var couponController:SearchResultCouponViewController?
	var merchantController:SearchResultMerchantViewController?
	var offlineController:SearchResultOfflineViewController?
    override func viewDidLoad() {
        super.viewDidLoad()
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
			.menuHeight(40),
			.menuItemFont(UIFont.systemFont(ofSize:18)),
			.unselectedMenuItemLabelColor (UIColor(red: 255/255, green: 149/255, blue: 70/255, alpha: 1.0)),
			.selectedMenuItemLabelColor (UIColor(red: 255/255, green: 149/255, blue: 70/255, alpha: 1.0)),
			.selectionIndicatorColor (UIColor(red: 255/255, green: 149/255, blue: 70/255, alpha: 1.0))
			
		]
		pageMenu = CAPSPageMenu(viewControllers: controllerArray, frame:CGRect(x: 0, y: 64, width: self.view.frame.width, height: self.view.frame.height-64),pageMenuOptions:params)
		self.addChildViewController(pageMenu!)
		self.view.addSubview((pageMenu?.view)!)
		pageMenu!.didMove(toParentViewController: self)
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

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
