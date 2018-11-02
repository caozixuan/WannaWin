//
//  AddBankCardTableViewController.swift
//  PointExchange
//
//  Created by panyy on 2018/7/7.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import UIKit
import WebKit

class AddBankCardViewController: UIViewController, WKNavigationDelegate, WKUIDelegate,UIWebViewDelegate{

	
	@IBOutlet weak var webView: WKWebView!
    var closeBtn: UIBarButtonItem!
	var backBtn: UIBarButtonItem!
	var forwardBtn: UIBarButtonItem!
	var refreshBtn: UIBarButtonItem!
	var url:String?
//    var activityIndicator:UIActivityIndicatorView? // 用于初次加载网页的时候显示
	
    lazy private var progressView: UIProgressView = {
        self.progressView = UIProgressView.init(frame: CGRect(x: CGFloat(0), y: CGFloat(65), width: UIScreen.main.bounds.width, height: 2))
        self.progressView.tintColor = UIColor(red: 255/255, green: 149/255, blue: 70/255, alpha: 1.0)
        self.progressView.trackTintColor = UIColor.white
        return self.progressView
    }()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
		backBtn = UIBarButtonItem(title: "后退", style: .plain, target: self, action: #selector(AddBankCardViewController.onButtonsClicked(_:)))
		backBtn.tag = 1
		
		forwardBtn = UIBarButtonItem(title: "前进", style: .plain, target: self, action: #selector(AddBankCardViewController.onButtonsClicked(_:)))
		forwardBtn.tag = 2
		
		refreshBtn = UIBarButtonItem(title: "刷新", style: .plain, target: self, action: #selector(AddBankCardViewController.onButtonsClicked(_:)))
		refreshBtn.tag = 3
		
        closeBtn = UIBarButtonItem(title: "X", style: .plain, target: self, action: #selector(AddBankCardViewController.onButtonsClicked(_:)))
        closeBtn.tag = 4
        
		self.navigationItem.leftBarButtonItems = [closeBtn,backBtn,forwardBtn]
		self.navigationItem.rightBarButtonItem = refreshBtn
        
//        // 初次加载网页的时候显示
//        activityIndicator = ActivityIndicator.createWaitIndicator(parentView: self.view)
//        activityIndicator?.startAnimating()

    }

	override func viewWillAppear(_ animated: Bool) {
		super.viewWillAppear(animated)
        
        // 添加进度条
        self.refreshButtonState()
        let pageUrl = NSURL(string: (self.url?.addingPercentEncoding(withAllowedCharacters: .urlQueryAllowed))!)
        let request = NSURLRequest(url: pageUrl! as URL)
        self.webView.addObserver(self, forKeyPath: "estimatedProgress", options: .new, context: nil)
        self.webView.load(request as URLRequest)
        self.webView?.uiDelegate = self
        self.webView?.navigationDelegate = self
        
        self.setProgressView()

	}
    
    func setProgressView(){
        self.view.addSubview(progressView)
        
    }
    
    override func observeValue(forKeyPath keyPath: String?, of object: Any?, change: [NSKeyValueChangeKey : Any]?, context: UnsafeMutableRawPointer?) {
        if keyPath == "estimatedProgress"{
            progressView.alpha = 1.0
            progressView.setProgress(Float((self.webView.estimatedProgress) ), animated: true)
            if (self.webView?.estimatedProgress ?? 0.0)  >= 1.0 {
                UIView.animate(withDuration: 0.3, delay: 0.1, options: .curveEaseOut, animations: {
                    self.progressView.alpha = 0
                }, completion: { (finish) in
                    self.progressView.setProgress(0.0, animated: false)
                })
            }
        }
    }
    deinit {
        self.webView?.removeObserver(self, forKeyPath: "estimatedProgress")
        self.webView?.uiDelegate = nil
        self.webView?.navigationDelegate = nil
    }
    
    func webView(_ webView: WKWebView, didFinish navigation: WKNavigation!) {
//        if (activityIndicator?.isAnimating)! {
//            activityIndicator?.stopAnimating()
//        }
        
        self.refreshButtonState()
    }

	func refreshButtonState() {
		if (self.webView?.canGoBack)! {
			self.backBtn.isEnabled = true
		}
		else {
			self.backBtn.isEnabled = false
		}
		
		if (self.webView?.canGoForward)! {
			self.forwardBtn.isEnabled = true
		}
		else {
			self.forwardBtn.isEnabled = false
		}
	}
    
    @objc func onButtonsClicked(_ sender:UIBarButtonItem) {
        switch (sender.tag) {
        case 1:
            self.webView.goBack()
            self.refreshButtonState()
        case 2:
            self.webView.goForward()
            self.refreshButtonState()
        case 4:
            self.navigationController?.popViewController(animated: true)
        default:
            self.webView.reload()
        }
    }

}
