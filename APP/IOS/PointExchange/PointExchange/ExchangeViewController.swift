//
//  ExchangeViewController.swift
//  PointExchange
//
//  Created by yiner on 2018/7/7.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import UIKit

class ExchangeViewController: UIViewController, UITableViewDelegate, UITableViewDataSource, ExchangeItemCellDelegate {

	@IBOutlet weak var tableView: UITableView!
	//let maxPoints:Int = 2000
	
	//var dataSource:[Card]? = User.getUser().card
	var dataSource:[Any]?
	
	
	@IBOutlet weak var shouldSelectPointsLabel: UILabel!
	@IBOutlet weak var selectedPointsLabel: UILabel!
	
	
	override func viewDidLoad() {
        super.viewDidLoad()
		self.tableView.delegate = self
		self.tableView.dataSource = self
		
		// test data
		let user = User.getUser()
		user.generalPoints = 1000
		let card1 = Card(merchant: Merchant(name: "星巴克"), point: 200, proportion: 0.5)
		let card2 = Card(merchant: Merchant(name: "南方航空"), point: 400, proportion: 0.2)
		let card3 = Card(merchant: Merchant(name: "耐克"), point: 300, proportion: 0.4)
		dataSource = [user.generalPoints as Any,card1,card2,card3]
		
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
		
		if indexPath.row != 0 {
			cell = tableView.dequeueReusableCell(withIdentifier: "store to bank", for: indexPath)
			if let cell1 = cell as? ExchangeItemCell {
				cell1.perform(#selector(ExchangeItemCell.setTextFieldDelegateWith), with: self)
				cell1.delegate = self
				cell1.editSourcePoints?.tag = indexPath.row
				if let card = dataSource?[indexPath.row] as? Card{
					cell1.storeName.text = card.merchant?.name
					cell1.sourcePoints.text = String(card.point)
					cell1.editSourcePoints.placeholder = String(card.point)
					cell1.targetPoints.text = String(card.point * (card.proportion)!)
					cell1.proportion = card.proportion
				}
			}
			
			
		}
		else {
			cell = tableView.dequeueReusableCell(withIdentifier: "bank to store", for: indexPath)
			if let cell1 = cell as? ExchangeItemCell {
				cell1.perform(#selector(ExchangeItemCell.setTextFieldDelegateWith), with: self)
				cell1.delegate = self
				cell1.generalPoints?.tag = indexPath.row
				if let generalPoints = dataSource?[indexPath.row] as? Double{
					cell1.generalPoints.text = String(generalPoints)
					cell1.editGeneralPoints.placeholder = String(generalPoints)
				}
			}
		}
		
		
        return cell
    }
	
	// MARK: - TextField delegate
	func textFieldShouldEndEditing(_ textField: UITextField) -> Bool{
		let number = Double(textField.text!)
		var maxPoints:Double!
		if textField.tag == 0 {
			if let generalPoints = self.dataSource?[textField.tag] as? Double {
				maxPoints = generalPoints
			}
		}
		else {
			if let card = self.dataSource?[textField.tag] as? Card {
				maxPoints = card.point
			}
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
	func contentDidChanged(text: String, row: Int) {
		if row == 0 {
			if let card = dataSource?[row] as? Card {
				
			}
		}
		else {
			
		}
	}
	
	

    /*
    // Override to support conditional editing of the table view.
    override func tableView(_ tableView: UITableView, canEditRowAt indexPath: IndexPath) -> Bool {
        // Return false if you do not want the specified item to be editable.
        return true
    }
    */

    /*
    // Override to support editing the table view.
    override func tableView(_ tableView: UITableView, commit editingStyle: UITableViewCellEditingStyle, forRowAt indexPath: IndexPath) {
        if editingStyle == .delete {
            // Delete the row from the data source
            tableView.deleteRows(at: [indexPath], with: .fade)
        } else if editingStyle == .insert {
            // Create a new instance of the appropriate class, insert it into the array, and add a new row to the table view
        }    
    }
    */

    /*
    // Override to support rearranging the table view.
    override func tableView(_ tableView: UITableView, moveRowAt fromIndexPath: IndexPath, to: IndexPath) {

    }
    */

    /*
    // Override to support conditional rearranging of the table view.
    override func tableView(_ tableView: UITableView, canMoveRowAt indexPath: IndexPath) -> Bool {
        // Return false if you do not want the item to be re-orderable.
        return true
    }
    */

	
    // MARK: - Navigation
	// FIXME: - 后期发请求，加判断条件
//	@IBAction func pressVerifyBtn(_ sender: Any) {
//		
//
//		let storyBoard = UIStoryboard(name:"Exchange", bundle:nil)
//		let view = storyBoard.instantiateViewController(withIdentifier: "FinishExchangeViewController")
//		self.navigationController?.pushViewController(view, animated: true)
//	}
//
//	

}
