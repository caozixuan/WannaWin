//
//  HistoryTableViewCell.swift
//  PointExchange
//
//  Created by yiner on 2018/8/12.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import UIKit

class HistoryTableViewCell: UITableViewCell {

	@IBOutlet weak var backgroundImage: UIImageView!
	@IBOutlet weak var dateTitleLabel: UILabel!
	@IBOutlet weak var descriptionField: UITextField!
	@IBOutlet weak var pointLabel: UILabel!
	@IBOutlet weak var logoImage: UIImageView!
	@IBOutlet weak var dateLabel: UILabel!
	@IBOutlet weak var invalidLabel: UILabel!
	override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }
    
}
