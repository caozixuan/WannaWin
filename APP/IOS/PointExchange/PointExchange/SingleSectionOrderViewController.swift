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
    
    var orders:[Order]?
    
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
        dataSource = RxTableViewSectionedReloadDataSource<SectionModel<String,Order>>(configureCell: {(dataSource, view, indexPath, element) in
            var cell = UITableViewCell()
            return cell
        })
        if let order = orders{
            let obs = Observable.just([
                SectionModel(model:"",items:order)
                ])
            obs.bind(to: self.tableView.rx.items(dataSource: dataSource!))
                .disposed(by:disposeBag)
        }
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    

}
