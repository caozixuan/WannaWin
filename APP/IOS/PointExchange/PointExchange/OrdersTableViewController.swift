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
    @IBOutlet weak var container: UIView!
    var willUseOrders:[Order]?
    var usedOrders:[Order]?
    var expireOrders:[Order]?
    
    // tableView相关
    var willUseDataSource:RxTableViewSectionedReloadDataSource<SectionModel<String,Order>>?
    var disposeBag = DisposeBag()
    /**
    override func viewDidLoad() {
        super.viewDidLoad()
        self.willUseTableView.rowHeight = 85
        self.usedTableView.rowHeight = 85
        self.expireTableView.rowHeight = 85
    }
    */
    override func viewWillAppear(_ animated: Bool) {
        //setDataSource()
        //bindDataSource()
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
/**
    /// 设置数据源
    func setDataSource(){
        dataSource = RxTableViewSectionedReloadDataSource<SectionModel<String,Order>>(configureCell: {(dataSource, view, indexPath, element) in
            var cell = UITableViewCell()
            switch self.segmentControl.selectedSegmentIndex{
            // 创建未使用页的列表
            case 0:
                cell = view.dequeueReusableCell(withIdentifier: "coupon")!
                // 简介
                (cell.viewWithTag(1) as! UITextView).text = element.description
                // 商家名
                (cell.viewWithTag(2) as! UILabel).text = element.merchantName
                // 时间
                (cell.viewWithTag(3) as! UILabel).text = element.date
            // 创建已使用的列表
            case 1:
                // TODO: 根据订单类型来选择显示
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
                    let cell = view.dequeueReusableCell(withIdentifier: "point")
                    // 使用的积分数
                    (cell?.viewWithTag(1) as! UITextView).text = String(format: "%f 通用积分", arguments: [element.points!])
                    // 商家名
                    (cell?.viewWithTag(2) as! UILabel).text = element.merchantName
                    // 时间
                    (cell?.viewWithTag(3) as! UILabel).text = element.date
                }
            // 创建过期页的列表
            case 2:
                // TODO: 根据订单类型来选择显示
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
                    (cell.viewWithTag(1) as! UITextView).text = String(format: "%f 通用积分", arguments: [element.points!])
                    // 商家名
                    (cell.viewWithTag(2) as! UILabel).text = element.merchantName
                    // 时间
                    (cell.viewWithTag(3) as! UILabel).text = element.date
                }
            default:
                break;
            }
            return cell
        })
        
    }
    
    /// 绑定数据源
    func bindDataSource(){
        
        switch self.segmentControl.selectedSegmentIndex {
        case 0:
            if let orders = willUseOrders{
                let obs = Observable.just([
                    SectionModel(model:"",items:orders)
                    ])
                obs.bind(to: self.tableView.rx.items(dataSource: dataSource!))
                    .disposed(by:disposeBag)
            }
        case 1:
            if let orders = usedOrders{
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
                dataSource?.titleForHeaderInSection = { ds, index in
                    return ds.sectionModels[index].model
                }
            }
        case 2:
            if let orders = usedOrders{
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
                dataSource?.titleForHeaderInSection = { ds, index in
                    return ds.sectionModels[index].model
                }
            }
        default:
            break;
        }
    }
    
    @IBAction func segmentControlChange(_ sender: Any) {
        bindDataSource()
        self.tableView.reloadData()
    }
*/
 }
