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
