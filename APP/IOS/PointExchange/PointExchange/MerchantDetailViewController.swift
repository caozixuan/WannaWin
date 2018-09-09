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
                // 当优惠信息少于三个时，移除“查看更多”按钮
                if self.items.count < 3 && self.moreButton != nil {
                    self.moreButton.removeFromSuperview()
                }
				
				if self.items.count != 0 {
					self.couponTableView.reloadData()
				}
				else { // 当没有优惠信息时，显示提示文字
					self.couponTableView.backgroundView = self.showMessage("暂无优惠劵")
				}
				
			}
			
		}
		ServerConnector.getMerchantActivities(merchantID: (merchant?.id)!){(result,activities) in
			if result {
				self.offlineActivities = activities!
				
				if self.offlineActivities.count != 0 {
					self.setOfflineScrollView()
				}
				else { // 当没有线下活动时，显示提示文字
					let view = self.showMessage("暂无线下活动")
					view.frame = self.offlineScrollView.bounds
					self.offlineView.addSubview(view)
				}
			}
			
		}
		
	}
	
	
	/// 设置scrollView
	private func setOfflineScrollView(){
		
		if offlineActivities.count > 2{
			let width = offlineView.frame.width + CGFloat(196*(offlineActivities.count-2))
			self.offlineView.frame = CGRect(x: offlineView.frame.origin.x, y: offlineView.frame.origin.y, width: width, height: offlineView.frame.height)
			self.offlineScrollView.contentSize = CGSize(width: width, height: self.offlineView.frame.height)
		}
		if offlineActivities.count > 0{
			for i in 0...offlineActivities.count-1{
				let x = i*196
				let y = self.offlineView.center.y - 65
				
				let view = OfflineCardView(frame: CGRect(x: x, y: Int(y), width: 180, height: 130))
				view.image.imageFromURL(offlineActivities[i].imageURL!, placeholder: UIImage())
				let tapGesture = UITapGestureRecognizer(target: self, action: #selector(self.clickActivity(_:)))
				view.addGestureRecognizer(tapGesture)
				view.isUserInteractionEnabled = true
				view.tag = i
				self.offlineView.addSubview(view)
				
			}
		}
	}
	
	/// 无内容时显示提示信息
	func showMessage(_ message:String)-> UIView{
		let messageLabel = UILabel()
		messageLabel.text = message
		messageLabel.font = UIFont(descriptor: .preferredFontDescriptor(withTextStyle: .body), size: 18)
		messageLabel.textColor = UIColor.lightGray
		messageLabel.textAlignment = NSTextAlignment.center
		messageLabel.sizeToFit()
		return messageLabel
	}
	
	@objc func clickActivity(_ sender:UITapGestureRecognizer){
		let sb = UIStoryboard(name: "Discover", bundle: nil)
		let vc = sb.instantiateViewController(withIdentifier: "ActivityDetailViewController") as! ActivityDetailViewController
		vc.activity = offlineActivities[(sender.view?.tag)!]
		self.navigationController?.pushViewController(vc, animated: true)
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
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        let sb = UIStoryboard(name: "Discover", bundle: nil)
        let vc = sb.instantiateViewController(withIdentifier: "CouponDetailViewController") as! CouponDetailViewController
        vc.itemID = items[indexPath.row].ItemID
        self.navigationController?.pushViewController(vc, animated: true)
        
    }

	@IBAction func ClickMoreBtn(_ sender: UIButton) {
        sender.isSelected = !sender.isSelected
		
        
        if sender.isSelected { // tableview变长
            isFold = false
            // 设置约束更改tableview高度
            self.couponTableView.snp.remakeConstraints(){ make in
                make.height.equalTo(70*self.items.count).priority(1000)
            }
            
            // 使动画生效
            UIView.animate(withDuration: 0.5){
                self.view.layoutIfNeeded()
            }
        }
        else { // tableview变短
            isFold = true
            // 更改约束更改tableview高度
            self.couponTableView.snp.remakeConstraints(){ make in
                make.height.equalTo(136).priority(1000)
            }
            
            // 使动画生效
            UIView.animate(withDuration: 0.5){
                self.view.layoutIfNeeded()
            }
        }
		self.couponTableView.reloadData()
    }
    
}
