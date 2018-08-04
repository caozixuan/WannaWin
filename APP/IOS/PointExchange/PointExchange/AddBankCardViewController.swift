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
	var backBtn: UIBarButtonItem!
	var forwardBtn: UIBarButtonItem!
	var refreshBtn: UIBarButtonItem!
	var url:String?
	
	
    override func viewDidLoad() {
        super.viewDidLoad()
		backBtn = UIBarButtonItem(title: "后退", style: .plain, target: self, action: #selector(HuaQiStoreWebViewController.onButtonsClicked(_:)))
		backBtn.tag = 1
		
		forwardBtn = UIBarButtonItem(title: "前进", style: .plain, target: self, action: #selector(HuaQiStoreWebViewController.onButtonsClicked(_:)))
		forwardBtn.tag = 2
		
		refreshBtn = UIBarButtonItem(title: "刷新", style: .plain, target: self, action: #selector(HuaQiStoreWebViewController.onButtonsClicked(_:)))
		refreshBtn.tag = 3
		
		self.navigationItem.leftBarButtonItems = [backBtn,forwardBtn]
		self.navigationItem.rightBarButtonItem = refreshBtn
		
		
    }

	override func viewWillAppear(_ animated: Bool) {
		super.viewWillAppear(animated)
		self.refreshButtonState()
		let pageUrl = NSURL(string: (self.url?.addingPercentEncoding(withAllowedCharacters: .urlQueryAllowed))!)
		let request = NSURLRequest(url: pageUrl! as URL)
		self.webView.load(request as URLRequest)
		self.webView?.uiDelegate = self
		self.webView?.navigationDelegate = self
	}
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
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
}
