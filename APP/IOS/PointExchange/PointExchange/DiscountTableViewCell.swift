//
//  DiscountTableViewCell.swift
//  PointExchange
//
//  Created by yiner on 2018/7/27.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import UIKit

class DiscountTableViewCell: UITableViewCell {

	@IBOutlet weak var descriptionLabel: UILabel!
	
	@IBOutlet weak var pointLabel: UILabel!
	@IBOutlet weak var originPriceLabel: UILabel!
	
	@IBOutlet weak var invalidDateLabel: UILabel!
	override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }
    
}
