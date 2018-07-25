//
//  PointHistoryViewController.swift
//  PointExchange
//
//  Created by panyy on 2018/7/14.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import UIKit
import ExpyTableView

class PointHistoryViewController: UIViewController, ExpyTableViewDataSource {
    
    var pointsHistoryArray:[PointsHistory]?
    @IBOutlet weak var tableView: ExpyTableView!
    override func viewDidLoad() {
        super.viewDidLoad()
        tableView.dataSource = self
        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    // 可展开的cell
    func tableView(_ tableView: ExpyTableView, expandableCellForSection section: Int) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: String(describing: PointHistoryTableViewCell.self)) as! PointHistoryTableViewCell
        cell.pointLabel.text = String(stringInterpolationSegment: pointsHistoryArray![section].totalPoints!)
        cell.dateLabel.text = pointsHistoryArray![section].historyMerchants![0].time!
        return cell
    }
    
    func numberOfSections(in tableView: UITableView) -> Int {
        return pointsHistoryArray!.count
    }
    
    // 点击展开后的单元格
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = self.tableView.dequeueReusableCell(withIdentifier: "detailCell")
        cell?.hideSeparator()
        // 商家名
        (cell?.viewWithTag(1) as! UILabel).text = pointsHistoryArray![indexPath.section].historyMerchants![indexPath.row-1].merchantName
        // 商家积分
        (cell?.viewWithTag(2) as! UILabel).text = String(stringInterpolationSegment: pointsHistoryArray![indexPath.section].historyMerchants![indexPath.row-1].pointsCard!)
        // 通用点
        (cell?.viewWithTag(3) as! UILabel).text = String(stringInterpolationSegment: pointsHistoryArray![indexPath.section].historyMerchants![indexPath.row-1].pointsCiti!)
        
        return cell!
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath){
        tableView.deselectRow(at: indexPath, animated: false)
        print("DID SELECT row: \(indexPath.row), section: \(indexPath.section)")
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return pointsHistoryArray![section].historyMerchants!.count+1
    }
    

}
