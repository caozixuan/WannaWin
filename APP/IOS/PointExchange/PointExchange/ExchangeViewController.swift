//
//  ExchangeViewController.swift
//  PointExchange
//
//  Created by yiner on 2018/7/7.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import UIKit

class ExchangeViewController: UIViewController, UITableViewDelegate, UITableViewDataSource {

	@IBOutlet weak var tableView: UITableView!
	let maxPoints:Int = 2000
	
	override func viewDidLoad() {
        super.viewDidLoad()
		self.tableView.delegate = self
		self.tableView.dataSource = self
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

    // MARK: - Table view data source
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        // #warning Incomplete implementation, return the number of rows
        return 2
    }

    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
		
		let cell:UITableViewCell!
		// TODO: - 测试使用，后面再修改
		
		if indexPath.row == 0 {
			cell = tableView.dequeueReusableCell(withIdentifier: "store to bank", for: indexPath)
			if let cell1 = cell as? ExchangeItemCell {
				cell1.perform(#selector(ExchangeItemCell.setTextFieldDelegateWith), with: self)
			}
		}
		else {
			cell = tableView.dequeueReusableCell(withIdentifier: "bank to store", for: indexPath)
			if let cell1 = cell as? ExchangeItemCell {
				cell1.perform(#selector(ExchangeItemCell.setTextFieldDelegateWith), with: self)
			}
		}

        // Configure the cell...

        return cell
    }
	
	// MARK: - TextField delegate
	func textFieldShouldEndEditing(_ textField: UITextField) -> Bool{
		let number = Int(textField.text!)
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
//	
//	@IBAction func pressVerifyBtn(_ sender: Any) {
//		
//		if self.restorationIdentifier == "ExchangeSelectViewController" {
//			self.navigationController?.popViewController(animated: false)
//		}
//		else if self.restorationIdentifier == "ExchangeSelectDetailViewController"  {
//			self.navigationController?.popViewController(animated: false)
//			self.navigationController?.popViewController(animated: false)
//		}
//		let storyBoard = UIStoryboard(name:"Exchange", bundle:nil)
//		let view = storyBoard.instantiateViewController(withIdentifier: "FinishExchangeViewController")
//		self.navigationController?.pushViewController(view, animated: true)
//	}
//
//	

}
