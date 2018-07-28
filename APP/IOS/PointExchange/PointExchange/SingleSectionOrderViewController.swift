//
//  SingleSectionOrderViewController.swift
//  PointExchange
//  未使用和过期订单的tableview
//  Created by panyy on 2018/7/14.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import UIKit
import RxCocoa
import RxSwift
import RxDataSources

class SingleSectionOrderViewController: UIViewController {
    @IBOutlet weak var tableView: UITableView!
    
    var items = [Item]()
	
	var tag:String?
    
    // tableView设置相关
    var dataSource:RxTableViewSectionedReloadDataSource<SectionModel<String,Order>>?
    var disposeBag = DisposeBag()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        tableView.rowHeight = 85
        // Do any additional setup after loading the view.
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
		if tag == "unuse" {
			ServerConnector.getUnusedCoupons(){(result, items) in
				if result {
					self.items = items
					self.tableView.reloadData()
				}
			}
		}else{
			ServerConnector.getOverdueCoupons(){(result, items) in
				if result {
					self.items = items
					self.tableView.reloadData()
				}
			}
		}
            let obs = Observable.just([
                SectionModel(model:"",items:items)
                ])
			obs.bind(to:tableView.rx.items(cellIdentifier: "coupon")){(row, element, cell) in
				if self.items.count != 0 {
					(cell.viewWithTag(1) as! UITextView).text = self.items[row].description!
					(cell.viewWithTag(2) as! UILabel).text = self.items[row].itemName!
					(cell.viewWithTag(3) as! UILabel).text = self.items[row].getTime!
				}
			}.disposed(by: disposeBag)
        
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    

}
