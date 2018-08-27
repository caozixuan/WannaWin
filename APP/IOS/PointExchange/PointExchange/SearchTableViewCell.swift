//
//  SearchTableViewCell.swift
//  PointExchange
//
//  Created by yiner on 2018/8/27.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import UIKit

class SearchTableViewCell: UITableViewCell {

	
	@IBOutlet weak var title: UILabel!
	
	@IBOutlet weak var descriptionLabel: UILabel!
	
	@IBOutlet weak var logoImageView: UIImageView!
	override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }
    
}
