//
//  OrderDetailTableViewController.swift
//  PointExchange
//
//  Created by panyy on 2018/7/8.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import UIKit

class OrderDetailTableViewController: UITableViewController {

    @IBOutlet weak var QRCodeImageView: UIImageView!
    @IBOutlet weak var barCodeImageView: UIImageView!
    override func viewDidLoad() {
        super.viewDidLoad()
       
    }
    
    override func viewWillAppear(_ animated: Bool) {
        let codeManager = ScanCodeManager()
        QRCodeImageView.image = codeManager.createQRCode(url: "null")
        barCodeImageView.image = codeManager.createBarCode(url: "null")
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

    // MARK: - Table view data source

    override func numberOfSections(in tableView: UITableView) -> Int {
        // #warning Incomplete implementation, return the number of sections
        return 3
    }


}
