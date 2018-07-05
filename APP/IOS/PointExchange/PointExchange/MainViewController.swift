//
//  MainViewController.swift
//  PointExchange
//
//  Created by yiner on 2018/7/5.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import UIKit

class MainViewController: UIViewController,ImageScrollerControllerDelegate {

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
		imageScroller.view.frame = CGRect(x: 10, y: 40, width: screenWidth-20,
										  height: (screenWidth-20)/4);
		
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
		return CGSize(width: screenWidth-20, height: (screenWidth-20)/4)
	}
	
	//图片轮播组件协议方法：获取数据集合
	func scrollerDataSource() -> [String] {
		return ["http://bizhi.zhuoku.com/bizhi2008/0516/3d/3d_desktop_13.jpg",
				"http://tupian.enterdesk.com/2012/1015/zyz/03/5.jpg",
				"http://img.web07.cn/UpImg/Desk/201301/12/desk230393121053551.jpg",
				"http://wallpaper.160.com/Wallpaper/Image/1280_960/1280_960_37227.jpg",
				"http://bizhi.zhuoku.com/wall/jie/20061124/cartoon2/cartoon014.jpg"]
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
