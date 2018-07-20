//
//  HomepagePartViewController.swift
//  PointExchange
//
//  Created by panyy on 2018/7/20.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import UIKit
import Alamofire

class HomepagePartViewController: UIViewController, LoginViewDelegate, HomepageStackViewDelegate {
    var loginView:LoginView?
    var homepageStackView:HomepageStackView?
    var activityIndicator:UIActivityIndicatorView?
	
    var cards:[Card]?

    override func viewDidLoad() {
        super.viewDidLoad()
        activityIndicator = ActivityIndicator.createWaitIndicator(parentView: self.view)
        // Do any additional setup after loading the view.
    }
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        
        if User.getUser().username != nil {
            if homepageStackView == nil {
                activityIndicator = ActivityIndicator.createWaitIndicator(parentView: self.view)
                activityIndicator?.startAnimating()
                homepageStackView = HomepageStackView(frame: CGRect(x: 0, y: 0, width: view.frame.width, height: view.frame.height))
                homepageStackView?.delegate = self
                view.addSubview(homepageStackView!)
                loginView?.removeFromSuperview()
            }
        }
        else {
            if loginView == nil {
                loginView = LoginView(frame: CGRect(x: 0, y: 0, width: view.frame.width, height: view.frame.height))
                loginView?.delegate = self
                view.addSubview(loginView!)
                homepageStackView?.removeFromSuperview()
            }
            
        }
        
        
        
        
    }
    override func viewWillLayoutSubviews() {
        
        for subview in view.subviews{
            if subview .isKind(of: HomepageStackView.self){
                // 调整卡片滑动视图
                let v = subview as! HomepageStackView
                let width = v.cardImage1.bounds.size.width * 3 + 32
                let height = v.cardImage1.bounds.size.height
                print("width:\(width), height:\(height)")
                v.cardScrollView.contentSize = CGSize(width: width, height: height)
                v.cardScrollView.contentOffset = CGPoint(x: v.cardImage1.bounds.size.width/2+16, y: 0)
                
                //添加积分卡添加点击手势事件
                let cardTap1 = UITapGestureRecognizer(target: self, action: #selector(MainViewController.goToCardDetail(_:)))
                let cardTap2 = UITapGestureRecognizer(target: self, action: #selector(MainViewController.goToCardDetail(_:)))
                let cardTap3 = UITapGestureRecognizer(target: self, action: #selector(MainViewController.goToCardDetail(_:)))
                v.cardImage1.addGestureRecognizer(cardTap1)
                v.cardImage2.addGestureRecognizer(cardTap2)
                v.cardImage3.addGestureRecognizer(cardTap3)
				
				v.exchangeBtn.addTarget(self, action: #selector(HomepagePartViewController.gotoExchangeVC), for: .touchUpInside)
                
                ServerConnector.getMostPointCards(n: 3){(result,cards) in
                    if result {
                        self.cards = cards
                        v.cardImage1.imageFromURL((self.cards?[0].logoURL)!, placeholder: UIImage(named: "Mask")!,fadeIn: true, shouldCacheImage: true)
                        v.cardImage2.imageFromURL((self.cards?[1].logoURL)!, placeholder: UIImage(named: "Mask")!,fadeIn: true, shouldCacheImage: true)
                        v.cardImage1.imageFromURL((self.cards?[2].logoURL)!, placeholder: UIImage(named: "Mask")!,fadeIn: true, shouldCacheImage: true)
                        ServerConnector.getGeneralPoints(){ (result, points) in
                            if result {
                                v.currentCitiPointLabel.text = String(stringInterpolationSegment: points)
                                if (self.activityIndicator?.isAnimating)! {
                                    self.activityIndicator?.stopAnimating()
                                }
                            }
                        }
                        ServerConnector.getAvailablePoints(){(result,points) in
                            if result {
                                v.availablePointsLabel.text = String(stringInterpolationSegment: points)
                                if (self.activityIndicator?.isAnimating)! {
                                    self.activityIndicator?.stopAnimating()
                                }
                            }
                        }
                    }
                    
                    
                }
                
            }
        }
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    func login() {
        activityIndicator?.startAnimating()
        ServerConnector.login(phoneNum: (loginView?.usernameField.text)!, password: (loginView?.passwordField.text)!){ result in
            if result == true {
                User.saveToKeychain()
                self.activityIndicator?.stopAnimating()
                let alert = UIAlertController(title:"登录", message:"登录成功！", preferredStyle:.alert)
                let okAction=UIAlertAction(title:"确定", style:.default, handler:{ action in
                    self.navigationController?.popViewController(animated: true)
                })
                alert.addAction(okAction)
                self.present(alert, animated: true, completion: nil)
            }
            else{
                let alert:UIAlertController!
                let reachable = NetworkReachabilityManager()
                if !(reachable?.isReachable)! {
                    alert = UIAlertController(title:"登录", message:"网络连接失败，请刷新重试！", preferredStyle:.alert)
                }
                else {
                    alert = UIAlertController(title:"登录", message:"登录失败！请检查用户名和密码是否正确", preferredStyle:.alert)
                }
                let okAction=UIAlertAction(title:"确定", style:.default, handler:{ action in
                })
                alert.addAction(okAction)
                self.present(alert, animated: true, completion: nil)
            }
            self.activityIndicator?.stopAnimating()
            
        }
    }
	
	
	@objc func gotoExchangeVC(_ sender: Any) {
		let storyBoard = UIStoryboard(name:"HomePage", bundle:nil)
		let view = storyBoard.instantiateViewController(withIdentifier: "ExchangeViewController")
		self.navigationController!.pushViewController(view, animated: true)
		self.navigationController!.setNavigationBarHidden(false, animated: true)
	}

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */
    
    func checkAllCards() {
        let sb = UIStoryboard(name: "HomePage", bundle: nil)
        let view = sb.instantiateViewController(withIdentifier: "CardInfoTableViewController")
        self.navigationController?.pushViewController(view, animated: true)
        self.navigationController?.setNavigationBarHidden(false, animated: false)
    }

}
