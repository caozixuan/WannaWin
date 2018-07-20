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
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        timeStamp = String(Int(Date().timeIntervalSince1970))
        let codeInfo = "{\"userID\":\"\(String(describing: User.getUser().id!))\",\"timeStamp\":\"\(timeStamp)\"}"
        print(codeInfo)
        barCodeView.image=ScanCodeManager().createBarCode(url: codeInfo)
        qrCodeView.image=ScanCodeManager().createQRCode(url: codeInfo)
        let timer = Timer.scheduledTimer(timeInterval: 10, target: self, selector: #selector(refreshCode), userInfo: nil, repeats: true)
        timer.fire()
        
        // Do any additional setup after loading the view.
    }
    @objc func refreshCode(){
        ServerConnector.pollizngQR(timestamp: timeStamp, callback: refreshCallback)
    }
    func refreshCallback(result:String, order:Order?){
        if result == "invalid" {
            timeStamp = String(Int(Date().timeIntervalSince1970))
            let codeInfo = "{\"userID\":\"\(String(describing: User.getUser().id))\",\"timeStamp\":\"\(timeStamp)\"}"
            barCodeView.image=ScanCodeManager().createBarCode(url: codeInfo)
            qrCodeView.image=ScanCodeManager().createQRCode(url: codeInfo)
        }
        else if result == "success" {
            let sb = UIStoryboard(name: "Exchange", bundle: nil)
            let view = sb.instantiateViewController(withIdentifier: "FinishExchangeViewController") as! FinishExchangeViewController
            view.order = order!
            self.navigationController?.pushViewController(view, animated: true)
        }
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    

	
    // MARK: - Navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
		// TODO: - 网络请求
		let isSuccess = true
		// ...
		
		if isSuccess {
			// 准备“抵扣成功”数据
		}
		else {
			// 准备“抵扣失败”数据
		}
		
		
    }
    

}
