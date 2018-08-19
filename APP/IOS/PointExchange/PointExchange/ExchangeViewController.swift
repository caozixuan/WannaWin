//
//  ExchangeViewController.swift
//  PointExchange
//
//  Created by yiner on 2018/7/7.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import UIKit
import SwiftyJSON

struct CellData {
    var sourcePoints: String
    var editSourcePoints: String
    var targetPoints: String
    var isSelected: Bool
}

class ExchangeViewController: UIViewController, UITableViewDelegate, UITableViewDataSource, ExchangeItemCellDelegate {
	
	var dataSource: [Card]?
    var selectedList: [Bool]? // 用于记住选中的项，防止cell重用导致的选中错乱
    var cellDataList = [CellData]() // 用于存下所有数据
    var firstTime: [Bool]?
    var allCell = [ExchangeItemCellView]()
    
	@IBOutlet weak var tableView: UITableView!
	@IBOutlet weak var pointsSumLabel: UILabel!
	var pointsSum:Double = 0.0
	
	override func viewDidLoad() {
        super.viewDidLoad()
		self.tableView.delegate = self
		self.tableView.dataSource = self
		if User.getUser().card == nil {
			ServerConnector.getCardCount{ (result,num) in
				if result {
					ServerConnector.getMostPointCards(n: num){(result,cards) in
						if result{
							User.getUser().card = cards
							self.dataSource = cards
							self.tableView.reloadData()
						}
					}
				}
			}
		}else{
			dataSource = User.getUser().card
		}
		
		// 加入“全选”按钮在导航栏右边
		let selectBtn = UIBarButtonItem(title: "全选", style: .plain, target: self, action: #selector(ExchangeViewController.selectAllCell))
		self.navigationItem.rightBarButtonItem = selectBtn
		
		// 设置初始总积分数
		pointsSumLabel.text = String(format:"%.2f", pointsSum)
        
        // 设置键盘弹出收回通知
        NotificationCenter.default.addObserver(self, selector: #selector(ExchangeViewController.keyboardWillShow), name: NSNotification.Name.UIKeyboardWillShow, object: nil)
        NotificationCenter.default.addObserver(self, selector: #selector(ExchangeViewController.keyboardWillHide), name: NSNotification.Name.UIKeyboardWillHide, object: nil)
        
        self.tableView.contentInset.bottom = 60
		
    }

    // MARK: - Table view data source
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
		if dataSource != nil {
            selectedList = Array(repeating: false, count: (dataSource?.count)!)
            firstTime = Array(repeating: true, count: (dataSource?.count)!)
			return (dataSource?.count)!
		}else {
			return 0
		}
    }

    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        var cell:UITableViewCell!
		cell = tableView.dequeueReusableCell(withIdentifier: "store to bank", for: indexPath)
        if cell == nil {
            cell = UITableViewCell(style: .default, reuseIdentifier: "store to bank")
        }
        
		for subview in cell.contentView.subviews{
			if subview .isKind(of: ExchangeItemCellView.self){
				let exchangeItemCellView = subview as! ExchangeItemCellView
				exchangeItemCellView.perform(#selector(ExchangeItemCellView.setTextFieldDelegateWith), with: self)
				exchangeItemCellView.delegate = self
				exchangeItemCellView.editSourcePoints?.tag = indexPath.row
				if let card = dataSource?[indexPath.row]{
                    
                    // 不变的量
                    exchangeItemCellView.storeName.text = card.merchant?.name
                    exchangeItemCellView.editSourcePoints.placeholder = String(format:"%.2f", card.points)
                    exchangeItemCellView.proportion = card.proportion
                    
                    if (firstTime?[indexPath.row])! { // 初始化数据
                        
                        exchangeItemCellView.sourcePoints.text = String(format:"%.2f", card.points)
                        exchangeItemCellView.editSourcePoints.text = String(format:"%.2f", card.points)
                        exchangeItemCellView.targetPoints.text = String(format:"%.2f", card.points * (card.proportion)!)
                        exchangeItemCellView.checkbox.isSelected = false
                        
                        // 保存数据到数组中
                        let cellData = CellData(sourcePoints: String(format:"%.2f", card.points), editSourcePoints: String(format:"%.2f", card.points), targetPoints: String(format:"%.2f", card.points * (card.proportion)!), isSelected: false)
                        cellDataList.append(cellData)
                        firstTime?[indexPath.row] = false
                        
                        // 获得所有cell，用于全选逻辑
                        allCell.append(exchangeItemCellView)
                    }
                    else { // 后续更新数据
                        exchangeItemCellView.sourcePoints.text = cellDataList[indexPath.row].sourcePoints
                        exchangeItemCellView.editSourcePoints.text = cellDataList[indexPath.row].editSourcePoints
                        exchangeItemCellView.targetPoints.text = cellDataList[indexPath.row].targetPoints
                        exchangeItemCellView.checkbox.isSelected = cellDataList[indexPath.row].isSelected
                        allCell[indexPath.row] = exchangeItemCellView
                    }
				}
                break
			}
		}
		return cell
    }
	
	// MARK: - TextField delegate
    /// 检测输入正确性
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
            textField.text = String(format:"%.2f", maxPoints)
			return false
		}
	}
    
    /// 键盘出现视图向上移动
    @objc func keyboardWillShow(notification:NSNotification) {
        
        if let keyboardSize = (notification.userInfo?[UIKeyboardFrameBeginUserInfoKey] as? NSValue)?.cgRectValue {
            self.tableView.contentInset.bottom = keyboardSize.size.height + 60
        }
    }
    
    /// 键盘收回视图向下移动
    @objc func keyboardWillHide(notification:NSNotification) {
        if ((notification.userInfo?[UIKeyboardFrameBeginUserInfoKey] as? NSValue)?.cgRectValue) != nil {
            self.tableView.contentInset.bottom = 60
        }
    }
	
	//MARK: - Target Action
    /// 全选积分项
	@objc func selectAllCell() {
        // 选中所有的单元格
//        let cellNumber = self.tableView(self.tableView, numberOfRowsInSection: 0)
//        var indexPath:IndexPath
//        var cell:UITableViewCell?
//        for row in 0..<cellNumber {
//            indexPath = IndexPath(row: row, section: 0)
//
//            cell = tableView.cellForRow(at: indexPath)
//
//            if cell != nil {
//                //定于到该行cell（此方法可以用于解决cell被遮挡的问题）
//                self.tableView.scrollToRow(at: indexPath, at: .none, animated: false)
//                cell = tableView.cellForRow(at: indexPath)
//            }
//
//            for subview in (cell?.contentView.subviews)!{
//                if subview .isKind(of: ExchangeItemCellView.self){
//                    let exchangeItemCellView = subview as! ExchangeItemCellView
//                    exchangeItemCellView.perform(#selector(ExchangeItemCellView.checkboxClick), with: exchangeItemCellView.checkbox)
//                    break
//                }
//            }
//        }
        
        
        
        // 选中所有可见的单元格
//        for cell in tableView.visibleCells {
//            for subview in cell.contentView.subviews{
//                if subview .isKind(of: ExchangeItemCellView.self){
//                    let exchangeItemCellView = subview as! ExchangeItemCellView
//                    exchangeItemCellView.perform(#selector(ExchangeItemCellView.checkboxClick), with: exchangeItemCellView.checkbox)
//                }
//            }
//        }
        
        // 改变按钮名称
        if self.navigationItem.rightBarButtonItem?.title == "全选" {
            self.navigationItem.rightBarButtonItem?.title = "全不选"
            for cell in allCell {
                if cell.checkbox.isSelected == false {
                    cell.perform(#selector(cell.checkboxClick), with: cell.checkbox)
                }

            }
        }
        else {
            self.navigationItem.rightBarButtonItem?.title = "全选"
            for cell in allCell {
                if cell.checkbox.isSelected == true {
                    cell.perform(#selector(cell.checkboxClick), with: cell.checkbox)
                }

            }
        }
        
	}

	// MARK: - ExchangeItemCell delegate
	/// 获得输入框值并统计积分总数
	func contentDidChanged(text: String, row: Int, type: changeType) {
		switch type {
		case .add:
			pointsSum += Double(text)!
			pointsSumLabel.text = String(format:"%.2f", pointsSum)
			
		default: //minus
			pointsSum -= Double(text)!
            if String(format:"%.2f", pointsSum) == "-0.00" { //排除误差
                pointsSumLabel.text = "0.00"
            }
            else {
                pointsSumLabel.text = String(format:"%.2f", pointsSum)
            }
			
            
            if let card = dataSource?[row]{
                 allCell[row].sourcePoints.text = String(format:"%.2f", card.points)
                 allCell[row].editSourcePoints.placeholder = String(format:"%.2f", card.points)
                 allCell[row].targetPoints.text = String(format:"%.2f", card.points * (card.proportion)!)
            }
            
//            let indexPath = IndexPath(row: row, section: 0)
//            let cell = self.tableView.cellForRow(at: indexPath)
//            for subview in (cell?.contentView.subviews)!{
//                if subview .isKind(of: ExchangeItemCellView.self){
//                    let exchangeItemCellView = subview as! ExchangeItemCellView
//                    if let card = dataSource?[row]{
//                        exchangeItemCellView.sourcePoints.text = String(format:"%.2f", card.points)
//                        exchangeItemCellView.editSourcePoints.placeholder = String(format:"%.2f", card.points)
//                        exchangeItemCellView.targetPoints.text = String(format:"%.2f", card.points * (card.proportion)!)
//                    }
//                    break
//                }
//            }
            
		}
	}
    
    /// 标记选中状态
    func setSelected(tag: Int, isSelected: Bool) {
        //selectedList?[tag] = isSelected
        cellDataList[tag].isSelected = isSelected
        
    }
    
    func setData(_ tag: Int, _ sourcePoints: String, _ editSourcePoints:String, _ targetPoints: String) {
        cellDataList[tag].sourcePoints = sourcePoints
        cellDataList[tag].editSourcePoints = editSourcePoints
        cellDataList[tag].targetPoints = targetPoints
    }
	
    // MARK: - 兑换积分网络请求
    @IBAction func clickExchangeBtn(_ sender: Any) {
        var allUnselected = true
        
        // 判断是否有选择积分项
        for item in cellDataList {
            if item.isSelected {
                allUnselected = false
                break
            }
        }
        
        if allUnselected == true { // 如果没有选择任何积分项则不跳转
            let alert = UIAlertController(title:"提示", message:"尚未选择任何积分项！", preferredStyle:.alert)
            let okAction = UIAlertAction(title:"确定", style:.default, handler:nil)
            alert.addAction(okAction)
            self.present(alert, animated: true, completion: nil)
            return
        }
        
        // 获得选中积分项数据
        //let cellNumber = self.tableView(self.tableView, numberOfRowsInSection: 0)
        //var indexPath:IndexPath
        //var cell:UITableViewCell?
        
        var chosenMerchantList = [ChooseMerchants]()
        var chosenMerchant:ChooseMerchants
        var chosenMerchantNames = [String]()
        
//        for row in 0..<cellNumber {
//            indexPath = IndexPath(row: row, section: 0)
//            cell = tableView.cellForRow(at: indexPath)
//            for subview in (cell?.contentView.subviews)!{
//                if subview .isKind(of: ExchangeItemCellView.self){
//                    let exchangeItemCellView = subview as! ExchangeItemCellView
//                    if exchangeItemCellView.checkbox.isSelected == true {
//                        chosenMerchant = ChooseMerchants(merchantID: exchangeItemCellView.storeName.text!, selectedMSCardPoints: exchangeItemCellView.sourcePoints.text!)
//                        chosenMerchantList.append(chosenMerchant)
//                    }
//                }
//            }
//        }
        
//        if let list = selectedList {
//            for (row,item) in list.enumerated() {
//                if item {
//                    indexPath = IndexPath(row: row, section: 0)
//                    cell = tableView.cellForRow(at: indexPath)
//
//                    if cell == nil {
//                        //定于到该行cell（此方法可以用于解决cell被遮挡的问题）
//                        self.tableView.scrollToRow(at: indexPath, at: .none, animated: false)
//                        cell = tableView.cellForRow(at: indexPath)
//                    }
//
//                    for subview in (cell?.contentView.subviews)!{
//                        if subview .isKind(of: ExchangeItemCellView.self){
//                            let exchangeItemCellView = subview as! ExchangeItemCellView
//
//                            //避免发送“0.00”导致后台出错
//                            if exchangeItemCellView.sourcePoints.text! == "0.00" {
//                                chosenMerchant = ChooseMerchants(merchantID: (dataSource?[row].merchant?.id)!, selectedMSCardPoints: "0")
//                            }
//                            else {
//                                chosenMerchant = ChooseMerchants(merchantID: (dataSource?[row].merchant?.id)!, selectedMSCardPoints: exchangeItemCellView.sourcePoints.text!)
//                            }
//                            chosenMerchantList.append(chosenMerchant)
//                            chosenMerchantNames.append((dataSource?[row].merchant?.name)!)
//                        }
//                    }
//                }
//            }
//        }
        
        for (row,cell) in cellDataList.enumerated() {
            if cell.isSelected {
                //避免发送“0.00”导致后台出错
                if cell.sourcePoints == "0.00" {
                    chosenMerchant = ChooseMerchants(merchantID: (dataSource?[row].merchant?.id)!, selectedMSCardPoints: "0")
                }
                else {
                    chosenMerchant = ChooseMerchants(merchantID: (dataSource?[row].merchant?.id)!, selectedMSCardPoints: cell.sourcePoints)
                }
                chosenMerchantList.append(chosenMerchant)
                chosenMerchantNames.append((dataSource?[row].merchant?.name)!)
            }
            
        }
        
        
        // 进行网络请求和后续跳转的数据准备
        let storyBoard = UIStoryboard(name:"HomePage", bundle:nil)
        let view = storyBoard.instantiateViewController(withIdentifier: "FinishExchangeToGeneralViewController")
        
        let chosenInfo = ChoosePointInfo(userID: User.getUser().id, merchants: chosenMerchantList)
        
        ServerConnector.changePoints(chooseInfo: chosenInfo){ result, failureMerchant  in
            if result { // 准备“兑换成功”数据
                if view .isKind(of: FinishExchangeToGeneralViewController.self){
                    let finishExchangeToGeneralVC = view as! FinishExchangeToGeneralViewController
                    finishExchangeToGeneralVC.status = true
                    finishExchangeToGeneralVC.successMerchants = chosenMerchantList
                    finishExchangeToGeneralVC.successMerchantNames = chosenMerchantNames
                    finishExchangeToGeneralVC.generalPoints = self.pointsSum
                }
            }
            else { // 准备“兑换失败”数据
                if view .isKind(of: FinishExchangeToGeneralViewController.self){
                    let finishExchangeToGeneralVC = view as! FinishExchangeToGeneralViewController
                    finishExchangeToGeneralVC.status = false
                    finishExchangeToGeneralVC.failureMerchants = failureMerchant
                }
            }
            
            self.navigationController!.pushViewController(view, animated: true)
        }
        
    }
    
    
}


