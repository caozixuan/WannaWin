//
//  FinishExchangeToGeneralViewController.swift
//  PointExchange
//
//  Created by yiner on 2018/8/2.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import UIKit

class FinishExchangeToGeneralViewController: UIViewController, UITableViewDelegate, UITableViewDataSource {

    @IBOutlet weak var statusLogo: UIImageView!
    @IBOutlet weak var statusText: UILabel!
    @IBOutlet weak var addedGeneralPoints: UILabel!
    @IBOutlet weak var tableView: UITableView!
    
    var status: Bool = false
    var generalPoints: Double?
    var successMerchants: [ChooseMerchants]?
    var failureMerchants: Dictionary<String,String>?
    var failureName: [String]?
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        // 设置导航栏按钮
        self.navigationItem.hidesBackButton = true
        let finishBtn = UIBarButtonItem(title: "完成", style: .done, target: self, action: #selector(goBackToHomePage))
        self.navigationItem.rightBarButtonItem = finishBtn
        
        // 设置显示数据
        tableView.delegate = self
        tableView.dataSource = self
        
        if status {
            statusText.text = "兑换成功"
            statusLogo.image = UIImage(named: "success")
        }
        else {
            statusText.text = "兑换失败"
            statusLogo.image = UIImage(named: "fail")
            failureName = failureMerchants?.keys.sorted()
        }
    
    }
    
    // MARK: - Table view data source
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        if status {
            return (successMerchants?.count)!
        }
        else {
            return (failureMerchants?.count)!
        }
        
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        var cell  = tableView.dequeueReusableCell(withIdentifier: "exchangedPoints", for: indexPath)
        if cell == nil {
            cell = UITableViewCell(style: .default, reuseIdentifier: "exchangedPoints")
        }
        if cell .isKind(of: ExchangedPointsCell.self){
            let exchangedPointsCell = cell as! ExchangedPointsCell
            if status {
                addedGeneralPoints.isHidden = false
                exchangedPointsCell.merchantName.text = successMerchants?[indexPath.row].merchantID
                exchangedPointsCell.exchangedPoints.text = successMerchants?[indexPath.row].selectedMSCardPoints
            }
            else {
                let merchant = failureName?[indexPath.row]
                addedGeneralPoints.isHidden = true
                exchangedPointsCell.merchantName.text = merchant
                exchangedPointsCell.exchangedPoints.text = failureMerchants?[merchant!]
            }
            
        }
        return cell
    }
    
    
    
    // MARK: - Navigation
    @objc func goBackToHomePage(){
        let rootVC = self.navigationController
        self.navigationController?.popToRootViewController(animated: false)
        rootVC?.tabBarController?.selectedIndex = 0
    }

}
