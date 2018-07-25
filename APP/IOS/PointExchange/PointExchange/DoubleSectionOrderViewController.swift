//
//  DoubleSectionOrderViewController.swift
//  PointExchange
//  已使用订单的tableView
//  Created by panyy on 2018/7/14.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import UIKit
import RxDataSources
import RxSwift
import RxCocoa

class DoubleSectionOrderViewController: UIViewController {
    @IBOutlet weak var tableView: UITableView!
    
    var orders:[Order]?
    
    // tableView相关
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
            // TODO: 根据订单类型来选择显示
            let cell = UITableViewCell()
//
            return cell
        })
        //设置分区头标题
        dataSource?.titleForHeaderInSection = { ds, index in
            return ds.sectionModels[index].model
        }
        
        // 绑定数据源
        if let orders = orders{
            let coupons=[Order]()
            let points = [Order]()
            let obs = Observable.just([
                SectionModel(model:"使用优惠券",items:coupons),
                SectionModel(model:"使用积分",items:points)
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

