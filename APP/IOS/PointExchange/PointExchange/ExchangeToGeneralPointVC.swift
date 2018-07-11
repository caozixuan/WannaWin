//
//  ExchangeToGeneralPointVC.swift
//  PointExchange
//
//  Created by yiner on 2018/7/11.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import UIKit

class ExchangeToGeneralPointVC: ExchangeViewController {

	@IBOutlet weak var pointsSum: UILabel!
	
	
	override func viewDidLoad() {
        super.viewDidLoad()
		let card1 = Card(merchant: Merchant(name: "星巴克"), point: 200, proportion: 0.5)
		let card2 = Card(merchant: Merchant(name: "南方航空"), point: 400, proportion: 0.2)
		let card3 = Card(merchant: Merchant(name: "耐克"), point: 300, proportion: 0.4)
		dataSource = [card1,card2,card3]
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()

    }
	
	override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
		
		let cell:UITableViewCell!
		// TODO: - 测试使用，后面再修改
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
		return cell
	}
	
	// MARK: - TextField delegate
	override func textFieldShouldEndEditing(_ textField: UITextField) -> Bool{
		let number = Double(textField.text!)
		var maxPoints:Double!
		if let card = self.dataSource?[textField.tag] as? Card {
			maxPoints = card.point
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
	
	

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

}
