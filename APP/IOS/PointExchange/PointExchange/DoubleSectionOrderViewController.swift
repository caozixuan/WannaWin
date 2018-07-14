//
//  DoubleSectionOrderViewController.swift
//  PointExchange
//
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
            var cell = UITableViewCell()
            if element.type == "coupon"{
                cell = view.dequeueReusableCell(withIdentifier: "coupon")!
                // 简介
                (cell.viewWithTag(1) as! UITextView).text = element.description
                // 商家名
                (cell.viewWithTag(2) as! UILabel).text = element.merchantName
                // 时间
                (cell.viewWithTag(3) as! UILabel).text = element.date
            }
            else{
                cell = view.dequeueReusableCell(withIdentifier: "point")!
                // 使用的积分数
//                (cell.viewWithTag(1) as! UITextView).text = String(stringInterpolationSegment: element.points)+"通用积分"
                // 商家名
                (cell.viewWithTag(2) as! UILabel).text = element.merchantName
                // 时间
                (cell.viewWithTag(3) as! UILabel).text = element.date
            }
            return cell
        })
        //设置分区头标题
        dataSource?.titleForHeaderInSection = { ds, index in
            return ds.sectionModels[index].model
        }
        
        // 绑定数据源
        if let orders = orders{
            var coupons=[Order]()
            var points = [Order]()
            for o in orders{
                if o.type == "coupon"{
                    coupons.append(o)
                }
                else{
                    points.append(o)
                }
            }
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

