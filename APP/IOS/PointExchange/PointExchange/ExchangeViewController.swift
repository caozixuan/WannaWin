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
		
		// 加入“全选”按钮在导航栏右边
		let selectBtn = UIBarButtonItem(title: "全选", style: .plain, target: view, action: #selector(ExchangeViewController.selectAllCell))
		self.navigationItem.rightBarButtonItem = selectBtn
		 //test data
		let card1 = Card(merchant: Merchant(name: "星巴克"), points: 200, proportion: 0.5)
		let card2 = Card(merchant: Merchant(name: "南方航空"), points: 400, proportion: 0.2)
		let card3 = Card(merchant: Merchant(name: "耐克"), points: 300, proportion: 0.4)
        dataSource = [card1,card2,card3]
		
    }

    // MARK: - Table view data source
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return (dataSource?.count)!
    }

    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
		let cell:UITableViewCell!
		// TODO: - 测试使用，后面再修改
		cell = tableView.dequeueReusableCell(withIdentifier: "store to bank", for: indexPath)
		for subview in cell.contentView.subviews{
			if subview .isKind(of: ExchangeItemCellView.self){
				let exchangeItemCellView = subview as! ExchangeItemCellView
				exchangeItemCellView.perform(#selector(ExchangeItemCellView.setTextFieldDelegateWith), with: self)
				exchangeItemCellView.delegate = self
				exchangeItemCellView.editSourcePoints?.tag = indexPath.row
				if let card = dataSource?[indexPath.row]{
					exchangeItemCellView.storeName.text = card.merchant?.name
					exchangeItemCellView.sourcePoints.text = String(card.points)
					exchangeItemCellView.editSourcePoints.placeholder = String(card.points)
					exchangeItemCellView.targetPoints.text = String(card.points * (card.proportion)!)
					exchangeItemCellView.proportion = card.proportion
				}
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
	
	//MARK: - Target Action
	@objc func selectAllCell() {
		//TODO: - 全选逻辑
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
			let cell = self.tableView.cellForRow(at: indexPath)
			for subview in (cell?.contentView.subviews)!{
				if subview .isKind(of: ExchangeItemCellView.self){
					let exchangeItemCellView = subview as! ExchangeItemCellView
					if let card = dataSource?[row]{
						exchangeItemCellView.sourcePoints.text = String(card.points)
						exchangeItemCellView.editSourcePoints.placeholder = String(card.points)
						exchangeItemCellView.targetPoints.text = String(card.points * (card.proportion)!)
					}
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
