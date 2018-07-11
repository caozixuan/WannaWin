//
//  MerchantChooseTableViewController.swift
//  PointExchange
//
//  Created by panyy on 2018/7/10.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import UIKit

class MerchantChooseTableViewController: UIViewController {
    
    @IBOutlet weak var tableView: UITableView!
    @IBOutlet weak var searchBar: UISearchBar!
    var marchantCount = MerchantList.list.count
    
    var merchantNames = [String]()
    
    var indexController:UILocalizedIndexedCollation?

    override func viewDidLoad() {
        super.viewDidLoad()

        
        // Uncomment the following line to preserve selection between presentations
        // self.clearsSelectionOnViewWillAppear = false

        // Uncomment the following line to display an Edit button in the navigation bar for this view controller.
        // self.navigationItem.rightBarButtonItem = self.editButtonItem
        
        
    }
    
    func createIndex(){
        indexController = UILocalizedIndexedCollation.current()
        let sectionTitileCount = indexController?.sectionTitles.count
        // TODO: 侧边栏索引
        var sectionDataArray = [[String]]()
        
        for _ in 0..<sectionTitileCount! {
            let array = [String]()
            sectionDataArray.append(array)
        }
        // TODO: 中文索引
        // 数据分类
//        for name in merchantNames {
//            let sectionNum = indexController?.section(for: name, collationStringSelector: #selector(getter:name))
//
//
//
//        }
        
    }
    

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

    // MARK: - Table view data source

    
    
//    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
//        let cell = tableView.dequeueReusableCell(withIdentifier: "merchant")
//
//        let data = try? Data(contentsOf: URL(string:MerchantList.list[indexPath.row].logoURL!)!)
//        if let d = data {
//             cell?.imageView?.image = UIImage(data: d)
//        }
//
//        (cell?.viewWithTag(1) as! UILabel).text = MerchantList.list[indexPath.row].name
//        return cell!
//    }
//    override func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
//
//        // 获得商家卡类型
//        ServerConnector.getCardTypeByUserID(merchantID: MerchantList.list[indexPath.row].id, callback: gotCardTypeCallback)
////        ServerConnector.getCardTypeByUserID(merchantID:"00001", callback: gotCardTypeCallback)
//
//    }
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
