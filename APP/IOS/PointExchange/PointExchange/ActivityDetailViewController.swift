//
//  ActivityDetailViewController.swift
//  PointExchange
//
//  Created by yiner on 2018/8/17.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import UIKit

class ActivityDetailViewController: UIViewController {

	@IBOutlet weak var validDateLabel: UILabel!
	@IBOutlet weak var descriptionText: UITextView!
	@IBOutlet weak var nameLabel: UILabel!
	@IBOutlet weak var imageView: UIImageView!
	
	var activity:OfflineActivity?
	
	override func viewDidLoad() {
        super.viewDidLoad()
		validDateLabel.text = (activity?.startDate)! + " - " + (activity?.endDate)!
		descriptionText.text = activity?.description
		nameLabel.text = activity?.name
		imageView.imageFromURL((activity?.imageURL)!, placeholder: UIImage())
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
