//
//  HistoryOfflineDetailViewController.swift
//  PointExchange
//
//  Created by yiner on 2018/8/17.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import UIKit

class HistoryOfflineDetailViewController: UIViewController {

	@IBOutlet weak var imageView: UIImageView!
	@IBOutlet weak var useDateLabel: UILabel!
	@IBOutlet weak var AfterPointLabel: UILabel!
	@IBOutlet weak var originPriceLabel: UILabel!
	@IBOutlet weak var merchantNameLabel: UILabel!
	@IBOutlet weak var pointLabel: UILabel!
	
	var order:Order?
	
	override func viewDidLoad() {
        super.viewDidLoad()
		let imageURL = URL(string: (order?.merchantLogoURL?.addingPercentEncoding(withAllowedCharacters: .urlQueryAllowed))!)
		self.imageView.kf.indicatorType = .activity
		self.imageView.kf.setImage(with: imageURL)
		self.useDateLabel.text = order?.time
		self.pointLabel.text = String(stringInterpolationSegment: order!.pointsNeeded!)
		self.AfterPointLabel.text = String(stringInterpolationSegment: order!.priceAfter!)
		self.originPriceLabel.text = String(stringInterpolationSegment: order!.originalPrice!)
		self.merchantNameLabel.text = order?.merchantName
        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
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
