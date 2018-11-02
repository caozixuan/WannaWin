//
//  CardDetailTableViewController.swift
//  PointExchange
//
//  Created by yiner on 2018/7/7.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import UIKit
import SnapKit

class CardDetailViewController: UIViewController,UITableViewDataSource,UITableViewDelegate {
	
	
	@IBOutlet weak var logoBackgroundView: GradientView!
	@IBOutlet weak var barView: GradientView!
	@IBOutlet weak var barImage: UIImageView!
	@IBOutlet weak var backgroundView: UIView!
	@IBOutlet weak var cardInfoBackgroundImage: UIImageView!
	@IBOutlet weak var barButton: UIButton!
	
	@IBOutlet weak var merchantLogoView: UIImageView!
	var merchantID:String?
	var indicator:UIActivityIndicatorView?
	var card:Card?
	var isFold = true
    var gestureOriginPoint:CGPoint?
    var topConstraint:Double = 0
	
	@IBOutlet weak var merchantNameLabel: UILabel!
	@IBOutlet weak var cardImageView: UIImageView!
	
	@IBOutlet weak var tableView: UITableView!
	
    @IBOutlet weak var bgConstraint: NSLayoutConstraint!
    
    override func viewDidLoad() {
        super.viewDidLoad()
		self.tableView.dataSource = self
		self.tableView.delegate = self
		self.tableView.rowHeight = 56
		// TODO: 条形码内容
		self.barImage.image = ScanCodeManager().createBarCode(url:"welcome to use our app")
		// 加入“历史积分兑换记录”按钮在导航栏右边
		let historyBtn = UIBarButtonItem(title: "兑换记录", style: .plain, target: self, action: #selector(goExchangeHistoryVC))
		self.navigationItem.rightBarButtonItem = historyBtn
		
		self.tableView.layer.zPosition = 10.0
		self.barButton.layer.zPosition = 10.0
		self.cardInfoBackgroundImage.layer.zPosition = 10.0
		
		// 添加滑动手势
        let panGesturer = UIPanGestureRecognizer(target: self, action: #selector(panGesture(_:)))
        self.backgroundView.addGestureRecognizer(panGesturer)
		
		// logo背景图层
		logoBackgroundView.cornerRadius = UIScreen.main.bounds.size.width*0.11
    }
	
	override func viewDidAppear(_ animated: Bool) {
		
		
		indicator = ActivityIndicator.createWaitIndicator(parentView: self.view)
		indicator?.startAnimating()
		ServerConnector.getCardDetail(merchantID: self.merchantID!){(result,card) in
			if result {
				self.card = card
				self.cardImageView.image = UIImage(named: "bg3_\(self.card!.cardStyle!)")
				self.merchantLogoView.imageFromURL((card.merchant?.logoURL)!, placeholder: UIImage())
				self.merchantNameLabel.text = card.merchant?.name
				self.tableView.reloadData()
			}
			self.indicator?.stopAnimating()
		}
	}
	
    @objc func panGesture(_ gesture:UIPanGestureRecognizer){
        let point = gesture.location(in: self.view)
        if gesture.state == .began{
            self.gestureOriginPoint = point
        }else if gesture.state == .changed{
            var offset = Double(point.y-(self.gestureOriginPoint?.y)!)
            self.gestureOriginPoint = point
            if offset+self.topConstraint > 169{
                offset = 169 - self.topConstraint
            }else if offset+self.topConstraint < -100{
                offset = -100 - self.topConstraint
            }
//            if isFold{
//                //折叠状态时，若处在上部（即遮挡卡logo位置）时，下滑至-24
//                if offset > 0 && self.topConstraint+offset > -24 && self.topConstraint+offset < 169 {
//                    offset = -24 - self.topConstraint
//                }
//                // 折叠状态时，当下滑太多时，固定到169位置
//                else if self.topConstraint + offset > 169{
//                    offset = 169 - self.topConstraint
//                }
//                // 折叠状态时，当上滑太多时，固定到-100
//                else if self.topConstraint + offset < -100{
//                    offset = -100 - self.topConstraint
//                }
//            }else{
//                // 非折叠状态时，当上滑太多，只恢复到最初的样子（-24）
//                if self.topConstraint + offset < -23.5{
//                    offset = -23.5 - self.topConstraint
//                }
//                if self.topConstraint + offset > 169{
//                    offset = 0
//                }
//            }
            print("constraint:\(self.topConstraint) offset: \(offset) new:\(self.topConstraint+offset)")
            backgroundViewSwipe(offset: offset)
        }else if gesture.state == .ended{
            var offset = Double(point.y-(self.gestureOriginPoint?.y)!)
            if self.topConstraint + offset > 90{
                offset = 169 - self.topConstraint
            }else if isFold && self.topConstraint + offset < -23.5 {
                offset = -100 - self.topConstraint
            }else if self.topConstraint + offset < 90{
                offset = -23.5 - self.topConstraint
            }
            
            self.backgroundViewSwipe(offset: offset)
            if self.topConstraint > 0 {
                isFold = false
            }else{
                isFold = true
            }
        }
    }
	
    // MARK: - Navigations
	@objc func goExchangeHistoryVC() {
		let storyBoard = UIStoryboard(name:"HomePage", bundle:nil)
		let view = storyBoard.instantiateViewController(withIdentifier: "ExchangeHistoryViewController") as! ExchangeHistoryViewController
		view.merchantID = self.merchantID
		
		self.navigationController!.pushViewController(view, animated: true)
	}
	
	func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
		return 5
	}
	
	func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
		var cell = UITableViewCell()
		switch indexPath.row {
		case 0:
			cell = self.tableView.dequeueReusableCell(withIdentifier: "pointCell")!
			if card != nil {
				(cell.viewWithTag(1) as! UILabel).text = String(stringInterpolationSegment: (card?.points)!)
			}
		case 1:
			cell = self.tableView.dequeueReusableCell(withIdentifier: "citiPointCell")!
			if card != nil {
				(cell.viewWithTag(1) as! UILabel).text = String(stringInterpolationSegment: (card?.points)!*(card?.proportion)!)
			}
			
		case 2:
			cell = self.tableView.dequeueReusableCell(withIdentifier: "cardNumCell")!
			if card != nil {
				(cell.viewWithTag(1) as! UILabel).text = card?.number
			}
		case 3:
			cell = self.tableView.dequeueReusableCell(withIdentifier: "ruleCell")!
		default:
			cell = self.tableView.dequeueReusableCell(withIdentifier: "unbindCell")!
            // 去除最后一行的分割线
            cell.separatorInset = UIEdgeInsetsMake(0,0, 0, cell.bounds.size.width)
		}
		cell.selectionStyle = UITableViewCellSelectionStyle.none
		return cell
	}
	@IBAction func clickBarBtn(_ sender: Any) {
        if isFold{
            backgroundViewSwipe(offset: 169-self.topConstraint)
        }
        else{
            backgroundViewSwipe(offset: -23.5-self.topConstraint)
        }
        isFold = !isFold
	}
    func backgroundViewSwipe(offset:Double){
        if self.topConstraint+offset<0{
            self.cardInfoBackgroundImage.image = UIImage(named: "cardInfo_fold")
        }else{
            self.cardInfoBackgroundImage.image = UIImage(named: "cardInfo_unfold")
        }
        
        // 移除原有约束
        if bgConstraint != nil {
            self.view.removeConstraint(bgConstraint)
        }
        
        // 设置约束更改backgroundView位置
        self.backgroundView.snp.remakeConstraints(){ make in
            self.topConstraint += offset
            make.top.equalTo(self.cardImageView.snp.bottom).offset(self.topConstraint)
            
        }
        // 使动画生效
        UIView.animate(withDuration: 0.5){
            self.view.layoutIfNeeded()
        }
	}
	@IBAction func clickUnbind(_ sender: Any) {
		let alert = UIAlertController(title:"解绑会员卡", message:"您确定要解绑该会员卡吗？", preferredStyle:.alert)
		let ok = UIAlertAction(title:"确定", style:.default, handler:{ action in
			ServerConnector.unbindCard(merchantID: self.merchantID!, cardNum: (self.card?.number)!){ result in
				let alert:UIAlertController!
				let okAction:UIAlertAction!
				if result {
					alert = UIAlertController(title:"提示", message:"会员卡解绑成功！", preferredStyle:.alert)
					okAction = UIAlertAction(title:"确定", style:.default, handler:{ action in
						self.navigationController!.popViewController(animated: true)
					})
				}
				else {
					alert = UIAlertController(title:"提示", message:"会员卡解绑失败！", preferredStyle:.alert)
					okAction = UIAlertAction(title:"确定", style:.default, handler:nil)
				}
				
				alert.addAction(okAction)
				self.present(alert, animated: true, completion: nil)
			}
		})
		let cancel = UIAlertAction(title:"取消", style:.cancel, handler:{ action in
		})
		alert.addAction(ok)
		alert.addAction(cancel)
		self.present(alert, animated: true, completion: nil)
		// ...
	}
}
