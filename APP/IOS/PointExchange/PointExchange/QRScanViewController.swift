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
    override func viewDidLoad() {
        super.viewDidLoad()
        let codeManager = ScanCodeManager()
        barCodeView.image=codeManager.createBarCode(url: "hello")
        qrCodeView.image=codeManager.createQRCode(url: "hello")

        // Do any additional setup after loading the view.
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
