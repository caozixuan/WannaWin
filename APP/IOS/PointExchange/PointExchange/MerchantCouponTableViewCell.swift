//
//  MerchantCouponTableViewCell.swift
//  PointExchange
//
//  Created by yiner on 2018/8/14.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import UIKit

class MerchantCouponTableViewCell: UITableViewCell {

	@IBOutlet weak var pointLabel: UILabel!
	@IBOutlet weak var nameView: UITextView!
	
	
	
	override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }
    
}
