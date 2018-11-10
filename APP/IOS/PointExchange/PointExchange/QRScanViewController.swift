//
//  QRScanViewController.swift
//  PointExchange
//
//  Created by Pan on 2018/7/5.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import UIKit

class QRScanViewController: UIViewController {

    @IBOutlet weak var barCodeView: UIImageView!
    @IBOutlet weak var qrCodeView: UIImageView!
    
    var timeStamp = ""
	var timer:Timer?
    
    
	override func viewWillAppear(_ animated: Bool) {
		super.viewWillAppear(animated)
		if User.getUser().username == nil{
			let storyBoard = UIStoryboard(name:"User", bundle:nil)
			let view = storyBoard.instantiateViewController(withIdentifier: "LoginViewController")
			self.navigationController!.pushViewController(view, animated: true)
			self.navigationController?.isNavigationBarHidden = false
			return
		}
		changeCode()
		timer = Timer.scheduledTimer(timeInterval: 1, target: self, selector: #selector(refreshCode), userInfo: nil, repeats: true)
		timer?.fire()
	}
	override func viewWillDisappear(_ animated: Bool) {
		timer?.invalidate()
	}
    @objc func refreshCode(){
        ServerConnector.pollizngQR(timestamp: timeStamp, callback: refreshCallback)
    }
    func refreshCallback(result:String, order:Order?){
        if result == "invalid" {
            changeCode()
        }
        else if result == "used" {
			if order?.state == OrderState.SUCCESS{
				timer?.invalidate()
				let sb = UIStoryboard(name: "Exchange", bundle: nil)
				let view = sb.instantiateViewController(withIdentifier: "FinishExchangeViewController") as! FinishExchangeViewController
				view.order = order!
				self.navigationController?.pushViewController(view, animated: true)
			}
			else{
				self.timer?.fireDate = Date.distantFuture
				let alert = UIAlertController(title:"支付", message:"支付失败！请重新支付", preferredStyle:.alert)
				let okAction = UIAlertAction(title:"确定", style:.default, handler:{ action in
					self.changeCode()
					self.timer?.fireDate = Date()
					
				})
				alert.addAction(okAction)
				self.present(alert, animated: true, completion: nil)
			}
			
        }
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
    

	
    // MARK: - Navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
		let isSuccess = true
		// ...
		
		if isSuccess {
			// 准备“抵扣成功”数据
		}
		else {
			// 准备“抵扣失败”数据
		}
		
		
    }
    

	func changeCode(){
		timeStamp = String(Int(Date().timeIntervalSince1970))
		let codeInfo = "{\"userID\":\"\(String(describing: User.getUser().id!))\",\"timeStamp\":\"\(timeStamp)\"}"
		barCodeView.image=ScanCodeManager().createBarCode(url: codeInfo)
		qrCodeView.image=ScanCodeManager().createQRCode(url: codeInfo)
	}
}
