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

	
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
		activityIndicator = ActivityIndicator.createWaitIndicator(parentView: self.view)
		activityIndicator?.startAnimating()
        if User.getUser().username != nil {
			if homepageStackView == nil {
				homepageStackView = HomepageStackView(frame: CGRect(x: 0, y: 0, width: view.frame.width, height: view.frame.height))
				homepageStackView?.delegate = self
				view.addSubview(homepageStackView!)
				loginView?.removeFromSuperview()
				loginView = nil
				
			}
        }
        else {
			if loginView == nil {
				loginView = LoginView(frame: CGRect(x: 0, y: 0, width: view.frame.width, height: view.frame.height))
				loginView?.delegate = self
				view.addSubview(loginView!)
				homepageStackView?.removeFromSuperview()
				homepageStackView = nil
				
			}
			activityIndicator?.stopAnimating()
        }
		
    }
	
    override func viewWillLayoutSubviews() {
        
        for subview in view.subviews{
            if subview .isKind(of: HomepageStackView.self){
                let v = subview as! HomepageStackView
				
				// 设置会员卡偏移量---显示中间卡
				let xOffset = v.cardScrollView.contentSize.width/2 - UIScreen.main.bounds.size.width/2
				v.cardScrollView.contentOffset = CGPoint(x: xOffset, y: 0)
                
                // 添加会员卡点击手势事件
                let cardTap1 = UITapGestureRecognizer(target: self, action: #selector(goToCardDetail(_:)))
                let cardTap2 = UITapGestureRecognizer(target: self, action: #selector(goToCardDetail(_:)))
                let cardTap3 = UITapGestureRecognizer(target: self, action: #selector(goToCardDetail(_:)))
                v.cardImage1.addGestureRecognizer(cardTap1)
                v.cardImage2.addGestureRecognizer(cardTap2)
                v.cardImage3.addGestureRecognizer(cardTap3)
				
				v.exchangeBtn.addTarget(self, action: #selector(HomepagePartViewController.gotoExchangeVC), for: .touchUpInside)
                
                ServerConnector.getMostPointCards(n: 3){(result,cards) in
                    if result {
                        self.cards = cards
						
						if cards.count >= 1 {
							v.cardImage1.kf.indicatorType = .activity
							v.cardImage1.kf.setImage(with: URL(string:(self.cards?[0].logoURL)!),placeholder:UIImage(named: "Mask"))
						}
						if cards.count >= 2 {
							v.cardImage2.kf.indicatorType = .activity
							v.cardImage2.kf.setImage(with: URL(string:(self.cards?[1].logoURL)!),placeholder:UIImage(named: "Mask"))
							
						}
						if cards.count >= 3 {
							v.cardImage3.kf.indicatorType = .activity
							v.cardImage3.kf.setImage(with: URL(string:(self.cards?[2].logoURL)!),placeholder:UIImage(named: "Mask"))
						}
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

	@objc func goToCardDetail(_ tap:UITapGestureRecognizer)->Void{
		let storyBoard:UIStoryboard!
		
		if User.getUser().username != nil {
			storyBoard = UIStoryboard(name:"HomePage", bundle:nil)
			let tag = tap.view?.tag // tag从1开始
			if cards != nil && (cards?.count)! >= tag!{
				let view = storyBoard.instantiateViewController(withIdentifier: "CardDetailTableViewController") as! CardDetailViewController
				view.merchantID = cards?[tag!-1].merchant?.id
				self.navigationController!.pushViewController(view, animated: true)
				self.navigationController?.setNavigationBarHidden(false, animated: true)
				
			}else {
				let view = storyBoard.instantiateViewController(withIdentifier: "MerchantChooseTableViewController")
				self.navigationController!.pushViewController(view, animated: true)
				self.navigationController?.setNavigationBarHidden(false, animated: true)
			}
			
		}
		else {
			storyBoard = UIStoryboard(name:"User", bundle:nil)
			let view = storyBoard.instantiateViewController(withIdentifier: "LoginViewController")
			self.navigationController!.pushViewController(view, animated: true)
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
					self.loginView?.removeFromSuperview()
					self.loginView = nil
					self.homepageStackView = HomepageStackView(frame: CGRect(x: 0, y: 0, width: self.view.frame.width, height: self.view.frame.height))
					self.homepageStackView?.delegate = self
					self.view.addSubview(self.homepageStackView!)
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

    func checkAllCards() {
        let sb = UIStoryboard(name: "HomePage", bundle: nil)
        let view = sb.instantiateViewController(withIdentifier: "CardInfoTableViewController")
        self.navigationController?.pushViewController(view, animated: true)
        self.navigationController?.setNavigationBarHidden(false, animated: false)
    }
	
	func gotoSignUp() {
		let sb = UIStoryboard(name: "User", bundle: nil)
		let vc = sb.instantiateViewController(withIdentifier: "SignUpViewController")
		self.navigationController?.pushViewController(vc, animated: true)
        self.navigationController?.setNavigationBarHidden(false, animated: false)
	}

}
