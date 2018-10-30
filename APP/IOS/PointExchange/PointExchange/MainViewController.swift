//
//  MainViewController.swift
//  PointExchange
//
//  Created by yiner on 2018/7/5.
//  Copyright © 2018年 WannaWin. All rights reserved.

import UIKit
import ESPullToRefresh

class MainViewController: UIViewController,ImageScrollerControllerDelegate {
    
	@IBOutlet weak var maskHeightCons: NSLayoutConstraint!
	@IBOutlet weak var bgView: UIView!
	@IBOutlet weak var maskConstraint: NSLayoutConstraint!
	@IBOutlet weak var maskView: MaskView!
	@IBOutlet weak var container: UIView!
	@IBOutlet weak var scrollView: UIScrollView!
	@IBOutlet weak var imageScrollerContainer: UIView!
	
    var activities:[OfflineActivity]?
    var coupons:[Item]?
    var isLoaded = false
	
	var containerOriginTop:Double?
	
	//图片轮播组件
	var imageScroller : ImageScrollerViewController!
    
    override func viewDidLoad() {
		
//		if maskHeightCons != nil{
//			maskView.removeConstraint(maskHeightCons)
//		}
//		maskView.snp.makeConstraints{ make in
//			let height = UIScreen.main.bounds.size.height*0.7
//			make.height.equalTo(height)
//		}
		
//        ServerConnector.getAds(){(result,activities) in
//            if result {
//                self.activities = activities
//                if self.isLoaded{
//                    // TODO: 刷新轮播
//					self.imageScroller.refresh()
//
//                }else{
//                    self.isLoaded = true
//                }
//            }
//        }
		
		refresh()
		
		let pan = UIPanGestureRecognizer(target: self, action: #selector(MainViewController.panGesture(_:)))
		maskView.addGestureRecognizer(pan)
		
    }
	
	func refresh(){
		ServerConnector.getAds(){(result,activities) in
			if result {
				self.activities = activities
				if self.isLoaded{
					// TODO: 刷新轮播
					self.imageScroller.refresh()
					
				}else{
					self.isLoaded = true
				}
			}
		}
	}
	
	@objc func panGesture(_ gesture:UIPanGestureRecognizer){
		let offset = gesture.translation(in: self.view).y
		if maskConstraint != nil{
			self.bgView.removeConstraint(maskConstraint)
		}
		if gesture.state == .began{
		}else if gesture.state == .changed{
			maskView.snp.remakeConstraints{ make in
				if offset < 40 && offset > 0{
					make.top.equalTo(self.imageScrollerContainer.snp.top).offset(160+offset)
				}
			}
		}else if gesture.state == .ended{
			refresh()
			maskView.snp.remakeConstraints{ make in
				make.top.equalTo(self.imageScrollerContainer.snp.top).offset(160)
			}
		}
		
		// 使动画生效
		UIView.animate(withDuration: 0.5){
			self.view.layoutIfNeeded()
		}
		
	}

    override func viewWillAppear(_ animated: Bool) {
		super.viewWillAppear(animated)
		
		// 隐藏导航栏
		self.navigationController?.setNavigationBarHidden(true, animated: true)
		// 下拉加载
//		scrollView.es.addPullToRefresh{ [unowned self] in
//
//			self.scrollView.es.stopPullToRefresh(ignoreDate: true)
//			self.scrollView.es.stopPullToRefresh(ignoreDate: true, ignoreFooter: false)
//		}
		
    }
	
	// 设置滑动偏移量来隐藏刷新的白边
	override func viewWillLayoutSubviews() {
		scrollView.contentOffset = CGPoint(x: 0, y: 20)
	}
	
	// MARK: - 图片轮播组件协议
	//图片轮播组件协议方法：获取数据集合
	func scrollerDataSource() -> [String] {
		self.isLoaded = true
		if self.activities != nil || self.coupons != nil {
			var items = [String]()
			if(self.activities != nil){
				for i in 0 ... (self.activities?.count)!-1{
					items.append(self.activities![i].imageURL!)
				}
				
				
			}
			if(self.coupons != nil){
				
				for i in 0 ... (self.coupons?.count)!-1{
					items.append(self.coupons![i].logoURL!)
				}
			}
			
			return items
		}
		else {
			return [String]()
		}
		
	}
	
	// MARK: - 所有点击事件的响应动作
	// TODO: - 点击图片响应事件
	@objc func handleTapAction(_ tap:UITapGestureRecognizer)->Void{
		//获取图片索引值
		let index = imageScroller.currentIndex
//		//弹出索引信息
//		let alertController = UIAlertController(title: "您点击的图片索引是：",
//												message: "\(index)", preferredStyle: .alert)
//		let cancelAction = UIAlertAction(title: "确定", style: .cancel, handler: nil)
//		alertController.addAction(cancelAction)
//		self.present(alertController, animated: true, completion: nil)
		
		let sb = UIStoryboard(name: "Discover", bundle: nil)
		let vc = sb.instantiateViewController(withIdentifier: "ActivityDetailViewController") as! ActivityDetailViewController
		vc.activity = activities?[index]
		self.navigationController!.pushViewController(vc, animated: true)
		self.navigationController?.setNavigationBarHidden(false, animated: true)
	}
	
    // MARK: - Navigation
    // 初始化图片轮播组件，为嵌入的图片轮播VC做数据准备
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
		if segue.identifier == "show scroller" {
			if segue.destination .isKind(of: ImageScrollerViewController.self){
				imageScroller = segue.destination as! ImageScrollerViewController
				imageScroller.delegate = self
				
				//为图片添加点击手势事件
				let tap = UITapGestureRecognizer(target: self, action: #selector(MainViewController.handleTapAction(_:)))
				imageScroller.view.addGestureRecognizer(tap)
			}
		}
        else if segue.identifier == "container" {
            

        }
    }

}
