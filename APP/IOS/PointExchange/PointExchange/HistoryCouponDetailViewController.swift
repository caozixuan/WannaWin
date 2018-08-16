//
//  HistoryCouponDetailViewController.swift
//  PointExchange
//
//  Created by yiner on 2018/8/15.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import UIKit

class HistoryCouponDetailViewController: UIViewController {

	var items:Item?
	@IBOutlet weak var qrBarImage: UIImageView!
	@IBOutlet weak var tagLabel: UILabel!
	@IBOutlet weak var descriptionText: UITextView!
	@IBOutlet weak var pointLabel: UILabel!
	@IBOutlet weak var logoImage: UIImageView!
	override func viewDidLoad() {
        super.viewDidLoad()
		descriptionText.text = items?.description
		pointLabel.text = String(stringInterpolationSegment: items?.points!)
		logoImage.imageFromURL((items?.logoURL)!, placeholder: UIImage())
		qrBarImage.image = ScanCodeManager().createQRCode(url:(items?.ItemID)!)
		switch items?.state {
		case "UNUSED":
			tagLabel.text = "未使用"
		default:
			break
			
		}
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
