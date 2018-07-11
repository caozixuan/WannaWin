//
//  MainViewController.swift
//  PointExchange
//
//  Created by yiner on 2018/7/5.
//  Copyright © 2018年 WannaWin. All rights reserved.
//  reference: http://www.hangge.com/blog/cache/detail_1314.html

import UIKit

class MainViewController: UIViewController,ImageScrollerControllerDelegate {
	
	
	
	@IBOutlet weak var cardImage1: UIImageView!
	
	@IBOutlet weak var cardImage2: UIImageView!
	
	@IBOutlet weak var cardImage3: UIImageView!
	
	@IBOutlet weak var imageScrollerContainer: UIView!
	
	//获取屏幕宽度
	let screenWidth =  UIScreen.main.bounds.size.width

	
	//图片轮播组件
	var imageScroller : ImageScrollerViewController!
	
    override func viewDidLoad() {
        super.viewDidLoad()
		
		//添加积分卡添加点击手势事件
		let cardTap1 = UITapGestureRecognizer(target: self, action: #selector(MainViewController.goToCardDetail(_:)))
		let cardTap2 = UITapGestureRecognizer(target: self, action: #selector(MainViewController.goToCardDetail(_:)))
		let cardTap3 = UITapGestureRecognizer(target: self, action: #selector(MainViewController.goToCardDetail(_:)))
		cardImage1.addGestureRecognizer(cardTap1)
		cardImage2.addGestureRecognizer(cardTap2)
		cardImage3.addGestureRecognizer(cardTap3)
        
        // 获得商家信息
        ServerConnector.getMerchantsInfos(start: 0, n: 10, callback: gotMerchantsCallback)
        
		
    }
	
    /// 获得商户信息后的回调函数
    func gotMerchantsCallback(result:Bool, merchants:[Merchant]){
        if result {
            MerchantList.list = merchants
        }
        else {
            print("商户信息获取失败")
        }
    }
    
    
    
	// MARK: - 图片轮播组件协议
	//图片轮播组件协议方法：获取内部scrollView尺寸
	func scrollerViewSize() -> CGSize {
		let height = imageScrollerContainer.bounds.size.height
		let width = imageScrollerContainer.bounds.size.width
		return CGSize(width: width, height:height)

	}

	
	//图片轮播组件协议方法：获取数据集合
	func scrollerDataSource() -> [String] {
		return ["https://images.pexels.com/photos/1203705/pexels-photo-1203705.jpeg?cs=srgb&dl=adorable-animal-breed-1203705.jpg&fm=jpg",
				"https://images.pexels.com/photos/977736/pexels-photo-977736.jpeg?cs=srgb&dl=beach-cliffs-evening-977736.jpg&fm=jpg",
				"https://images.pexels.com/photos/459554/pexels-photo-459554.jpeg?cs=srgb&dl=4th-of-july-berries-berry-459554.jpg&fm=jpg",
				"https://images.pexels.com/photos/968245/pexels-photo-968245.jpeg?cs=srgb&dl=beverage-brew-citrus-968245.jpg&fm=jpg",
				"https://images.pexels.com/photos/297755/pexels-photo-297755.jpeg?cs=srgb&dl=adult-book-business-297755.jpg&fm=jpg"]
	}
	
	// MARK: - 所有的点击事件响应动作
	//点击事件响应
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
	
	@objc func goToCardDetail(_ tap:UITapGestureRecognizer)->Void{
		let storyBoard:UIStoryboard!
		let view:UIViewController!
		if User.getUser().username != nil {
			storyBoard = UIStoryboard(name:"HomePage", bundle:nil)
			view = storyBoard.instantiateViewController(withIdentifier: "CardDetailTableViewController")
			switch tap.view {
			case cardImage1:
				// FIXME: - 后期用来判断点击了那张卡，用来传递数据
				let a = 1
			case cardImage2:
				// FIXME: - 后期用来判断点击了那张卡，用来传递数据
				let a = 1
			default:
				// FIXME: - 后期用来判断点击了那张卡，用来传递数据
				let a = 1
			}
		}
		else {
			storyBoard = UIStoryboard(name:"User", bundle:nil)
			view = storyBoard.instantiateViewController(withIdentifier: "LoginViewController")
		}
		
		self.navigationController!.pushViewController(view, animated: true)
	}
	
	@IBAction func showCardInfo(_ sender: AnyObject){
		let storyBoard = UIStoryboard(name:"Main", bundle:nil)
		if User.getUser().username != nil {
			let view = storyBoard.instantiateViewController(withIdentifier: "CardInfoTableViewController")
			self.navigationController!.pushViewController(view, animated: true)
		}
		else{
			let view = storyBoard.instantiateViewController(withIdentifier: "LoginViewController")
			self.navigationController!.pushViewController(view, animated: true)
		}
	}
	
	@IBAction func addCard(_ sender: AnyObject){
		
		if User.getUser().username != nil {
            let storyBoard = UIStoryboard(name:"HomePage", bundle:nil)
			let view = storyBoard.instantiateViewController(withIdentifier: "MerchantChooseTableViewController")
			self.navigationController!.pushViewController(view, animated: true)
		}
		else{
            let storyBoard = UIStoryboard(name:"User", bundle:nil)
			let view = storyBoard.instantiateViewController(withIdentifier: "LoginViewController")
			self.navigationController!.pushViewController(view, animated: true)
		}
	}

    

	
    // MARK: - Navigation
    // 初始化图片轮播组件，为嵌入的图片轮播VC做数据准备
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
		if segue.identifier == "show scroller" {
			if segue.destination .isKind(of: ImageScrollerViewController.self){
				imageScroller = segue.destination as! ImageScrollerViewController
				imageScroller.delegate = self
				imageScroller.view.layer.cornerRadius = 20;
				imageScroller.view.layer.masksToBounds = true;
				
				//为图片添加点击手势事件
				let tap = UITapGestureRecognizer(target: self,
												 action: #selector(MainViewController.handleTapAction(_:)))
				imageScroller.view.addGestureRecognizer(tap)
				
			}
		}
    }
	

}
