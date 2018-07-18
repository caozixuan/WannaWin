//
//  ExchangeViewController.swift
//  PointExchange
//
//  Created by yiner on 2018/7/7.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import UIKit

class ExchangeViewController: UIViewController, UITableViewDelegate, UITableViewDataSource, ExchangeItemCellDelegate {
	
	//var dataSource:[Card]? = User.getUser().card
	var dataSource:[Card]?
	
	@IBOutlet weak var tableView: UITableView!
	@IBOutlet weak var pointsSumLabel: UILabel!
	var pointsSum:Double = 0
	
	override func viewDidLoad() {
        super.viewDidLoad()
		self.tableView.delegate = self
		self.tableView.dataSource = self
		
		// test data
//        let card1 = Card(merchant: Merchant(name: "星巴克"), point: 200, proportion: 0.5)
//        let card2 = Card(merchant: Merchant(name: "南方航空"), point: 400, proportion: 0.2)
//        let card3 = Card(merchant: Merchant(name: "耐克"), point: 300, proportion: 0.4)
//        dataSource = [card1,card2,card3]
		
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

    // MARK: - Table view data source
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        // #warning Incomplete implementation, return the number of rows
        return (dataSource?.count)!
    }

    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
		let cell:UITableViewCell!
		// TODO: - 测试使用，后面再修改
		cell = tableView.dequeueReusableCell(withIdentifier: "store to bank", for: indexPath)
		if let cell1 = cell as? ExchangeItemCell {
			cell1.perform(#selector(ExchangeItemCell.setTextFieldDelegateWith), with: self)
			cell1.delegate = self
			cell1.editSourcePoints?.tag = indexPath.row
			if let card = dataSource?[indexPath.row]{
				cell1.storeName.text = card.merchant?.name
				cell1.sourcePoints.text = String(card.points)
				cell1.editSourcePoints.placeholder = String(card.points)
				cell1.targetPoints.text = String(card.points * (card.proportion)!)
				cell1.proportion = card.proportion
			}
		}
		return cell
    }
	
	// MARK: - TextField delegate
	func textFieldShouldEndEditing(_ textField: UITextField) -> Bool{
		let number = Double(textField.text!)
		var maxPoints:Double!
		if let card = self.dataSource?[textField.tag] {
			maxPoints = card.points
		}
		
		if number != nil && number! <= maxPoints {
			return true
		}
		else {
			textField.shake(direction: .horizontal, times: 5, duration: 0.05, delta: 2, completion: nil)
			textField.text = String(maxPoints)
			return false
		}
	}
	
	
	
	// MARK: - ExchangeItemCell delegate
	/// 获得输入框值并统计积分总数
	func contentDidChanged(text: String, row: Int, type: changeType) {
		switch type {
		case .add:
			pointsSum += Double(text)!
			pointsSumLabel.text = String(pointsSum)
			
		default: //minus
			pointsSum -= Double(text)!
			pointsSumLabel.text = String(pointsSum)
			let indexPath = IndexPath(row: row, section: 0)
			if let cell = self.tableView.cellForRow(at: indexPath) as? ExchangeItemCell{
				if let card = dataSource?[row]{
					cell.sourcePoints.text = String(card.points)
					cell.editSourcePoints.placeholder = String(card.points)
					cell.targetPoints.text = String(card.points * (card.proportion)!)
				}
			}
		}
	}
	
	// MARK: - navigation
	
	
	// TODO: - 进行网络请求
	override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
		let isSuccess = true
		// ...
		if isSuccess {
			//准备“兑换成功”数据
		}
		else {
			//准备“兑换失败”数据
		}
		
	}
	
}
