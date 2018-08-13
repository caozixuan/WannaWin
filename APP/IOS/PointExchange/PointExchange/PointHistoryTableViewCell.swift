//
//  PointHistoryTableViewCell.swift
//  PointExchange
//
//  Created by panyy on 2018/7/14.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import UIKit
import ExpyTableView

class PointHistoryTableViewCell: UITableViewCell, ExpyTableViewHeaderCell{
    
	@IBOutlet weak var line: UIImageView!
	@IBOutlet weak var oval: UIImageView!
    @IBOutlet weak var pointLabel: UILabel!
    @IBOutlet weak var dateLabel: UILabel!
    func changeState(_ state: ExpyState, cellReuseStatus cellReuse: Bool) {
        switch state {
        case .willExpand:
            print("WILL EXPAND")
            hideSeparator()
            
        case .willCollapse:
            print("WILL COLLAPSE")
            
        case .didExpand:
            print("DID EXPAND")
            
        case .didCollapse:
            showSeparator()
            print("DID COLLAPSE")
        }
    }

    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
		self.oval.layer.zPosition = 2.0
		self.line.layer.zPosition = 1.0
		
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }
}

extension UITableViewCell {
    
    func showSeparator() {
        DispatchQueue.main.async {
            self.separatorInset = UIEdgeInsets(top: 0, left: 0, bottom: 0, right: 0)
        }
    }
    
    func hideSeparator() {
        DispatchQueue.main.async {
            self.separatorInset = UIEdgeInsets(top: 0, left: self.bounds.size.width, bottom: 0, right: 0)
        }
    }
}
