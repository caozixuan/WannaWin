//
//  MerchantDetailTableViewController.swift
//  PointExchange
//
//  Created by yiner on 2018/7/27.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import UIKit

class MerchantDetailTableViewController: UITableViewController {

	var merchant:Merchant?
	var items:[Item]?
	@IBOutlet weak var merchantLogo: UIImageView!
	@IBOutlet weak var merchantDescriptionField: UITextView!
	
	override func viewDidLoad() {
        super.viewDidLoad()
		merchantLogo.imageFromURL((merchant?.logoURL)!, placeholder: UIImage())
		merchantDescriptionField.text = merchant?.description
		
		self.tableView.register(UINib(nibName: "DiscountTableViewCell", bundle: nil), forCellReuseIdentifier: "discountCell")
		
		// 获取优惠信息
		ServerConnector.getMerchantItems(merchantID: (merchant?.id)!, start: 0, n: 2){ (result, items) in
			if result {
				self.items = items
				self.tableView.reloadData()
			}
			
		}
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

    // MARK: - Table view data source


    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        // #warning Incomplete implementation, return the number of rows
		if section == 2 {
			if items != nil {
				return (items?.count)!
			}
		}
        return super.tableView(tableView, numberOfRowsInSection: section)
    }
	
	override func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
		if indexPath.section == 2 {
			return 80
		}
		return super.tableView(tableView, heightForRowAt: indexPath)
	}
	
	override func tableView(_ tableView: UITableView, heightForHeaderInSection section: Int) -> CGFloat{
		return 20
	}
	
	override func tableView(_ tableView: UITableView, viewForHeaderInSection section: Int) -> UIView? {
		return nil
	}
	
	override func tableView(_ tableView: UITableView, indentationLevelForRowAt indexPath: IndexPath) -> Int {
		if indexPath.section == 2 {
			let newIndexPath = IndexPath(row: 0, section: indexPath.section)
			return super.tableView(tableView, indentationLevelForRowAt: newIndexPath)
		}
		return super.tableView(tableView, indentationLevelForRowAt: indexPath)
	}
	
	


	override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
	
		if indexPath.section == 2 && items != nil && items?.count != 0 {
			
			var cell = self.tableView.dequeueReusableCell(withIdentifier: "discountCell") as? DiscountTableViewCell
			if cell == nil {
				cell = UITableViewCell(style: .default, reuseIdentifier: "discountCell") as? DiscountTableViewCell
			}
			cell?.descriptionLabel.text = items![indexPath.row].description!
			cell?.originPriceLabel.text = String(stringInterpolationSegment: items![indexPath.row].originalPrice!)
			cell?.pointLabel.text = String(stringInterpolationSegment: items![indexPath.row].points!)
			cell?.invalidDateLabel.text = items![indexPath.row].overdueTime
			return cell!
		}
		else{
			return super.tableView(self.tableView, cellForRowAt: indexPath)
		}
		
	}
}
