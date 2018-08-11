//
//  DsMerchantTableViewCell.swift
//  PointExchange
//
//  Created by yiner on 2018/8/11.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import UIKit

class DsMerchantTableViewCell: UITableViewCell {

	@IBOutlet weak var typeLabel: UILabel!
	@IBOutlet weak var addressLabel: UILabel!
	@IBOutlet weak var nameLabel: UILabel!
	@IBOutlet weak var logoView: UIImageView!
	override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }
    
}
