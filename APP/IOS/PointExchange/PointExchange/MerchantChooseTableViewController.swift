//
//  MerchantChooseTableViewController.swift
//  PointExchange
//
//  Created by panyy on 2018/7/10.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import UIKit

class MerchantChooseTableViewController: UITableViewController {
    
    var marchantCount = MerchantList.list.count

    override func viewDidLoad() {
        super.viewDidLoad()

        
        // Uncomment the following line to preserve selection between presentations
        // self.clearsSelectionOnViewWillAppear = false

        // Uncomment the following line to display an Edit button in the navigation bar for this view controller.
        // self.navigationItem.rightBarButtonItem = self.editButtonItem
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

    // MARK: - Table view data source

    override func numberOfSections(in tableView: UITableView) -> Int {
        // #warning Incomplete implementation, return the number of sections
        return 1
    }

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        // #warning Incomplete implementation, return the number of rows
        return self.marchantCount
    }
    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "merchant")
        cell?.textLabel?.text = MerchantList.list[indexPath.row].name
        return cell!
    }
    override func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        
        // 获得商家卡类型
        ServerConnector.getCardTypeByUserID(merchantID: MerchantList.list[indexPath.row].id, callback: gotCardTypeCallback)
//        ServerConnector.getCardTypeByUserID(merchantID:"00001", callback: gotCardTypeCallback)
        
    }
    /// 获得商户卡信息的回调函数
    func gotCardTypeCallback(result:Bool,cardTypes:[CardType]){
        if result {
            if cardTypes.count != 0 {
                MerchantList.get(merchantID: cardTypes[0].merchantID!)?.cardTypes=cardTypes
                
                let storyboard = UIStoryboard(name: "HomePage", bundle: nil)
                let view = storyboard.instantiateViewController(withIdentifier: "AddCardTableView") as? AddCardTableViewController
                view?.merchant = MerchantList.get(merchantID: cardTypes[0].merchantID!)
                view?.cardTypeCount = cardTypes.count
                self.navigationController?.pushViewController(view!, animated: true)
            }
        }
    }

}
