//
//  MerchantDetailViewController.swift
//  PointExchange
//
//  Created by yiner on 2018/7/27.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import UIKit
import SnapKit

class MerchantDetailViewController: UIViewController,UITableViewDelegate,UITableViewDataSource {

	var merchant:Merchant?
	var items = [Item]()
	var offlineActivities = [OfflineActivity]()
	var isFold = true
	@IBOutlet weak var merchantName: UILabel!
	@IBOutlet weak var merchantLogo: UIImageView!
	@IBOutlet weak var offlineScrollView: UIScrollView!
	@IBOutlet weak var moreButton: UIButton!
	@IBOutlet weak var couponTableView: UITableView!
	@IBOutlet weak var merchantDescriptionField: UITextField!
	@IBOutlet weak var merchantPhoneNumLAbel: UILabel!
	
	@IBOutlet weak var offlineView: UIView!
	override func viewDidLoad() {
        super.viewDidLoad()
		merchantLogo.imageFromURL((merchant?.logoURL)!, placeholder: UIImage())
		merchantDescriptionField.text = merchant?.description
		merchantName.text = merchant?.name
		self.couponTableView.register(UINib(nibName: "MerchantCouponTableViewCell", bundle: nil), forCellReuseIdentifier: "couponCell")
		self.couponTableView.delegate = self
		self.couponTableView.dataSource = self
		
		
    }
	
	override func viewWillLayoutSubviews() {
		// 获取优惠信息
		ServerConnector.getMerchantItems(merchantID: (merchant?.id)!, start: 0, n: 2){ (result, items) in
			if result {
				self.items = items!
				self.items.append(items![0])
				self.items.append(items![1])
				self.couponTableView.reloadData()
			}
			
		}
		// 设置线下活动scrollview
		let offlineActivity = OfflineActivity()
		offlineActivities.append(offlineActivity)
		offlineActivities.append(offlineActivity)
		offlineActivities.append(offlineActivity)
		self.setOfflineScrollView()
	}
	
	
	/// 设置scrollView
	private func setOfflineScrollView(){
		
		if offlineActivities.count > 2{
			let width = offlineView.frame.width + CGFloat(196*(offlineActivities.count-2))
			self.offlineView.frame = CGRect(x: offlineView.frame.origin.x, y: offlineView.frame.origin.y, width: width, height: offlineView.frame.height)
			self.offlineScrollView.contentSize = CGSize(width: width, height: self.offlineView.frame.height)
		}
		for i in 0...offlineActivities.count{
			let x = i*196
			let y = self.offlineView.center.y - 65
			
			let view = OfflineCardView(frame: CGRect(x: x, y: Int(y), width: 180, height: 130))
			self.offlineView.addSubview(view)
		}
	}

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

    // MARK: - Table view data source


    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        // #warning Incomplete implementation, return the number of rows
		if isFold {
			if items.count < 2{
				return items.count
			}else{
				return 2
			}
		}
		else{
			return items.count
		}
		
    }
	
	func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
			var cell = self.couponTableView.dequeueReusableCell(withIdentifier: "couponCell") as? MerchantCouponTableViewCell
			if cell == nil {
				cell = UITableViewCell(style: .default, reuseIdentifier: "couponCell") as? MerchantCouponTableViewCell
			}
		cell?.nameView.text = items[indexPath.row].name
			cell?.pointLabel.text = String(stringInterpolationSegment: items[indexPath.row].points!)
			return cell!

	}
	
	@IBAction func ClickMoreBtn(_ sender: Any) {
		self.couponTableView.reloadData()
		self.couponTableView.snp.updateConstraints(){ make in
			make.height.equalTo(70*items.count)
		}
	}
}
