//
//  PageViewController.swift
//  PointExchange
//
//  Created by yiner on 2018/8/21.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import UIKit
import Pageboy

protocol Connector {
    func setPageIndex(index: Int)
}

class PageViewController: PageboyViewController, PageboyViewControllerDataSource {
   
    var totalNum: Int!
    var pageNum: Int!
    
    var pageControllers = [UIViewController]()
    
    var connector: Connector?
   
    override func viewDidLoad() {
        super.viewDidLoad()
        
        // set up pageControllers
        let storyboard = UIStoryboard(name: "HomePage", bundle: nil)
        //var viewControllers = [UIViewController]()
        for i in 0 ..< pageNum {
            let viewController = storyboard.instantiateViewController(withIdentifier: "ExchangeResultTableViewController") as! ExchangeResultTableViewController
            viewController.index = i + 1
            viewController.tableView.dataSource = self.connector as? UITableViewDataSource
            pageControllers.append(viewController)
        }
        
        self.dataSource = self

    }

    func numberOfViewControllers(in pageboyViewController: PageboyViewController) -> Int {
        return pageControllers.count
    }
    
    func viewController(for pageboyViewController: PageboyViewController, at index: PageboyViewController.PageIndex) -> UIViewController? {
        return pageControllers[index]
    }
    
    func defaultPage(for pageboyViewController: PageboyViewController) -> PageboyViewController.Page? {
        return nil
    }
    
    func pageboyViewController(_ pageboyViewController: PageboyViewController,
                               didScrollToPageAt index: Int,
                               direction: PageboyViewController.NavigationDirection,
                               animated: Bool) {
        self.connector?.setPageIndex(index: index)
    }

}
