//
//  OrdersTableViewController.swift
//  PointExchange
//
//  Created by panyy on 2018/7/7.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import UIKit
import RxSwift
import RxCocoa
import RxDataSources

class OrdersTableViewController: UIViewController {
    
    @IBOutlet weak var segmentControl: UISegmentedControl!
    
    @IBOutlet weak var willUseContainer: UIView!
    
    @IBOutlet weak var expireContainer: UIView!
    @IBOutlet weak var usedContainer: UIView!
    
    // 数据数组
    var willUseOrders:[Order]?
    var usedOrders:[Order]?
    var expireOrders:[Order]?
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    @IBAction func segmentChange(_ sender: Any) {
        switch segmentControl.selectedSegmentIndex {
        case 0:
            willUseContainer.isHidden = false
            expireContainer.isHidden = true
            usedContainer.isHidden = true
        case 1:
            willUseContainer.isHidden = true
            expireContainer.isHidden = true
            usedContainer.isHidden = false
        case 2:
            willUseContainer.isHidden = true
            expireContainer.isHidden = false
            usedContainer.isHidden = true
        default:
            break
        }
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        switch segue.identifier {
        case "willUseView":
            if let view = segue.destination as? SingleSectionOrderViewController{
                view.orders = self.willUseOrders
            }
        case "usedView":
            if let view = segue.destination as? DoubleSectionOrderViewController{
                view.orders = self.usedOrders
            }
        case "expireView":
            if let view = segue.destination as? SingleSectionOrderViewController{
                view.orders = self.expireOrders
            }
        default:
            break
        }
    }

 }
