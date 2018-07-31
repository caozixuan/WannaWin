//
//  MainViewController.swift
//  PointExchange
//
//  Created by yiner on 2018/7/5.
//  Copyright © 2018年 WannaWin. All rights reserved.

import UIKit
import ESPullToRefresh

class MainViewController: UIViewController,ImageScrollerControllerDelegate {
    
    @IBOutlet weak var container: UIView!
	@IBOutlet weak var scrollView: UIScrollView!
	@IBOutlet weak var imageScrollerContainer: UIView!
	
    var activityIndicator:UIActivityIndicatorView?
	
	//图片轮播组件
	var imageScroller : ImageScrollerViewController!

    override func viewWillAppear(_ animated: Bool) {
		super.viewWillAppear(animated)
		
		//隐藏导航栏，设置滑动偏移量来隐藏刷新的白边
		self.navigationController?.setNavigationBarHidden(true, animated: true)
		scrollView.contentOffset = CGPoint(x: 0, y: 20)
    }

    /// 获得商户信息后的回调函数
    func gotMerchantsCallback(result:Bool, merchants:[Merchant]){
        if result {
            MerchantList.list = merchants
            var merchantName = [String]()
            for m in merchants{
                merchantName.append(m.name)
            }
            let storyBoard = UIStoryboard(name:"HomePage", bundle:nil)
            let view = storyBoard.instantiateViewController(withIdentifier: "MerchantChooseTableViewController") as! MerchantChooseTableViewController
            view.merchantNames = merchantName
            self.navigationController!.pushViewController(view, animated: true)
        }
        else {
            print("商户信息获取失败")
        }
        activityIndicator?.stopAnimating()
    }
    
	// MARK: - 图片轮播组件协议
	//图片轮播组件协议方法：获取数据集合
	func scrollerDataSource() -> [String] {
		return ["https://photo.tuchong.com/3505293/ft640/165347608.jpg"]
	}
	
	// MARK: - 所有点击事件的响应动作
	// TODO: - 点击图片响应事件
	@objc func handleTapAction(_ tap:UITapGestureRecognizer)->Void{
		//获取图片索引值
		let index = imageScroller.currentIndex
		//弹出索引信息
		let alertController = UIAlertController(title: "您点击的图片索引是：",
												message: "\(index)", preferredStyle: .alert)
		let cancelAction = UIAlertAction(title: "确定", style: .cancel, handler: nil)
		alertController.addAction(cancelAction)
		self.present(alertController, animated: true, completion: nil)
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
