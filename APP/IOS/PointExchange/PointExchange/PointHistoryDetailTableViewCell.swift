//
//  PointHistoryDetailTableViewCell.swift
//  PointExchange
//
//  Created by yiner on 2018/8/13.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import UIKit

class PointHistoryDetailTableViewCell: UITableViewCell {

	@IBOutlet weak var merchantName: UILabel!
	@IBOutlet weak var merchantPointLabel: UILabel!
	@IBOutlet weak var pointLabel: UILabel!
	override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }
    
}
