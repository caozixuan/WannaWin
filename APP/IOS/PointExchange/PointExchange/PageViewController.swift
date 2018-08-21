//
//  PageViewController.swift
//  PointExchange
//
//  Created by yiner on 2018/8/21.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import UIKit
import Pageboy

class PageViewController: PageboyViewController, PageboyViewControllerDataSource {
    
    @IBOutlet weak var pageControl: UIPageControl!
    var totalNum: Int!
    
    var pageControllers = [UIViewController]()
   
    override func viewDidLoad() {
        super.viewDidLoad()
        self.dataSource = self
        
        // set up pageControllers
        let storyboard = UIStoryboard(name: "HomePage", bundle: nil)
        //var viewControllers = [UIViewController]()
        for i in 0 ..< totalNum {
            let viewController = storyboard.instantiateViewController(withIdentifier: "ExchangeResultTableViewController") as! ExchangeResultTableViewController
            viewController.index = i + 1
            pageControllers.append(viewController)
        }
        
        // set up page control
        pageControl.numberOfPages = pageControllers.count
        pageControl.currentPage = 0
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
        pageControl.currentPage = index
    }

}
