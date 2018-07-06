//
//  MainViewController.swift
//  PointExchange
//
//  Created by yiner on 2018/7/5.
//  Copyright © 2018年 WannaWin. All rights reserved.
//  reference: http://www.hangge.com/blog/cache/detail_1314.html

import UIKit

class MainViewController: UIViewController,ImageScrollerControllerDelegate {
	
	var user = User.getUser()
	
	@IBOutlet weak var imageScrollerView: UIImageView!
	
	//获取屏幕宽度
	let screenWidth =  UIScreen.main.bounds.size.width
	
	//图片轮播组件
	var imageScroller : ImageScrollerViewController!
	
    override func viewDidLoad() {
        super.viewDidLoad()
		// Do any additional setup after loading the view.
		
		//初始化图片轮播组件
		imageScroller = ImageScrollerViewController()
		imageScroller.delegate = self
		imageScroller.view.frame = CGRect(x: 10, y: 70, width: screenWidth-20, height: (screenWidth-20)/4*2);
		imageScroller.view.layer.cornerRadius = 20;
		imageScroller.view.layer.masksToBounds = true;
		//imageScroller.view = imageScrollerView
		
		//将图片轮播组件添加到当前视图
		self.addChildViewController(imageScroller)
		self.view.addSubview(imageScroller.view)
		
		//添加组件的点击事件
		let tap = UITapGestureRecognizer(target: self,
										 action: #selector(MainViewController.handleTapAction(_:)))
		imageScroller.view.addGestureRecognizer(tap)
		
    }
	
	//图片轮播组件协议方法：获取内部scrollView尺寸
	func scrollerViewSize() -> CGSize {
		return CGSize(width: screenWidth-20, height: (screenWidth-20)/4*2)
	}
	
	//图片轮播组件协议方法：获取数据集合
	func scrollerDataSource() -> [String] {
		return ["https://images.pexels.com/photos/1203705/pexels-photo-1203705.jpeg?cs=srgb&dl=adorable-animal-breed-1203705.jpg&fm=jpg",
				"https://images.pexels.com/photos/977736/pexels-photo-977736.jpeg?cs=srgb&dl=beach-cliffs-evening-977736.jpg&fm=jpg",
				"https://images.pexels.com/photos/459554/pexels-photo-459554.jpeg?cs=srgb&dl=4th-of-july-berries-berry-459554.jpg&fm=jpg",
				"https://images.pexels.com/photos/968245/pexels-photo-968245.jpeg?cs=srgb&dl=beverage-brew-citrus-968245.jpg&fm=jpg",
				"https://images.pexels.com/photos/297755/pexels-photo-297755.jpeg?cs=srgb&dl=adult-book-business-297755.jpg&fm=jpg"]
	}
	
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
	
	@IBAction func showCardInfo(_ sender: AnyObject){
		user.username = "yiner"
		let storyBoard = UIStoryboard(name:"Main", bundle:nil)
		if user.username != nil {
			let view = storyBoard.instantiateViewController(withIdentifier: "CardInfoTableViewController")
			self.navigationController!.pushViewController(view, animated: true)
		}
		else{
			let view = storyBoard.instantiateViewController(withIdentifier: "LoginViewController")
			self.navigationController!.pushViewController(view, animated: true)
		}
	}
	
	@IBAction func addCard(_ sender: AnyObject){
		user.username = "yiner"
		let storyBoard = UIStoryboard(name:"Main", bundle:nil)
		if user.username != nil {
			let view = storyBoard.instantiateViewController(withIdentifier: "AddCardTableViewController")
			self.navigationController!.pushViewController(view, animated: true)
		}
		else{
			let view = storyBoard.instantiateViewController(withIdentifier: "LoginViewController")
			self.navigationController!.pushViewController(view, animated: true)
		}
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
