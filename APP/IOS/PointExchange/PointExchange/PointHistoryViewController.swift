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
    
    var pointsHistoryArray = [PointsHistory]()
    @IBOutlet weak var tableView: ExpyTableView!
    override func viewDidLoad() {
        super.viewDidLoad()
		
		self.tableView.register(UINib(nibName: "PointHistoryTableViewCell", bundle: nil), forCellReuseIdentifier: "pointCell")
		self.tableView.register(UINib(nibName: "PointHistoryDetailTableViewCell", bundle: nil), forCellReuseIdentifier: "detailCell")
		self.tableView.rowHeight = 44

		
		self.tableView.dataSource = self
		ServerConnector.getAllPointsHistory{ (result, pointsHistory) in
			if result {
				self.pointsHistoryArray = pointsHistory
				self.tableView.reloadData()
			}

		}
        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    // 可展开的cell
    func tableView(_ tableView: ExpyTableView, expandableCellForSection section: Int) -> UITableViewCell {
		
		var cell = tableView.dequeueReusableCell(withIdentifier: String(describing: "pointCell")) as? PointHistoryTableViewCell
		if cell == nil {
			cell = UITableViewCell(style: .default, reuseIdentifier: "pointCell") as? PointHistoryTableViewCell
		}
		if section == 0 {
			cell?.line.frame = CGRect(x: (cell?.oval.center.x)!-1, y: (cell?.oval.center.y)!, width: 2, height: 50)
		}else{
			cell?.line.frame = CGRect(x: (cell?.oval.center.x)!-1, y: -2, width: 2, height: 50)
		}
		switch section%5 {
		case 0:
			cell?.oval.image = UIImage(named: "Oval_blue")
		case 1:
			cell?.oval.image = UIImage(named: "Oval_green")
		case 2:
			cell?.oval.image = UIImage(named: "Oval_lightGreen")
		case 3:
			cell?.oval.image = UIImage(named: "Oval_purple")
		default:
			cell?.oval.image = UIImage(named: "Oval_red")
		}
		
        cell?.pointLabel.text = String(stringInterpolationSegment: pointsHistoryArray[section].totalPoints!) + "P"
		let formatter = DateFormatter()
		formatter.dateFormat = "MMM dd, yyyy hh:mm:ss a"
		let date = formatter.date(from: pointsHistoryArray[section].historyMerchants![0].time!)
		formatter.dateFormat = "MMM dd, yyyy"
        cell?.dateLabel.text = formatter.string(from: date!)
        return cell!
    }
    
    func numberOfSections(in tableView: UITableView) -> Int {
        return pointsHistoryArray.count
    }
    
    // 点击展开后的单元格
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
		var cell = self.tableView.dequeueReusableCell(withIdentifier: "detailCell") as? PointHistoryDetailTableViewCell
		if cell == nil {
			cell = UITableViewCell(style: .default, reuseIdentifier: "detailCell") as? PointHistoryDetailTableViewCell
		}
        cell?.hideSeparator()
        // 商家名
        cell?.merchantName.text = pointsHistoryArray[indexPath.section].historyMerchants![indexPath.row-1].merchantName
        // 商家积分
        cell?.merchantPointLabel.text = "-" + String(stringInterpolationSegment: pointsHistoryArray[indexPath.section].historyMerchants![indexPath.row-1].pointsCard!)
        // 通用点
        cell?.pointLabel.text = "+" + String(stringInterpolationSegment: pointsHistoryArray[indexPath.section].historyMerchants![indexPath.row-1].pointsCiti!) + "P"
        
        return cell!
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath){
        tableView.deselectRow(at: indexPath, animated: false)
        print("DID SELECT row: \(indexPath.row), section: \(indexPath.section)")
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return pointsHistoryArray[section].historyMerchants!.count+1
    }
    

}
