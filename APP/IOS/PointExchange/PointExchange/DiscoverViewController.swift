//
//  DiscoverViewController.swift
//  PointExchange
//
//  Created by yiner on 2018/7/5.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import UIKit
import RxSwift
import RxCocoa

class DiscoverViewController: UIViewController, UITableViewDelegate, UITableViewDataSource, YDMenuDataSource,YDMenuDelegate {
    @IBOutlet weak var searchBar: UISearchBar!
    
    var rowCount = 4;
    var merchantArray = ["东方航空","南方航空","北京烤鸭","宫保鸡丁"]
    @IBOutlet weak var tableView: UITableView!
    
    var tableCellIdentifier:String = "local discount"
    
    var menu = YDMenu(origin:CGPoint(x:0, y:120),menuheight:50)
    var data = [String: AnyObject]()

    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.tableView.delegate=self
        self.tableView.dataSource=self
        
        self.tableView.rowHeight = 185
        let dataUrl = Bundle.main.url(forResource: "MenuData", withExtension: "plist")
        
        if dataUrl != nil {
            data =  NSDictionary(contentsOf: dataUrl!)! as! [String : AnyObject]
        }
        // 下拉菜单
        self.menu.delegate = self
        self.menu.dataSource = self
        view.addSubview(self.menu)
        // Do any additional setup after loading the view.
    }

    // 下拉菜单
    @IBAction func selectedBtnClick(_ sender: Any) {
        menu.selectedAtIndex(YDMenu.Index(column: 1, row: 2))
    }
    @IBAction func selectedDefaultBtnClick(_ sender: Any) {
        
        menu.selectDeafult()
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    // MARK: - YDMenuDataSource / Delegate
    
    func numberOfColumnsInMenu(_ menu: YDMenu) -> Int {
        return data.count
    }
    
    func menu(_ menu: YDMenu, numberOfRowsInColumn column: Int) -> Int {
        
        switch column {
        case 0:
            return (data["Area"] as! [[String: AnyObject]]).count
        case 1:
            return 1
        default:
            return 0
        }
    }
    
    func menu(_ menu: YDMenu, numberOfItemsInRow row: Int, inColumn column: Int) -> Int {
        if column == 0 {
            return ((data["Area"] as! [[String: AnyObject]])[row]["distance"] as! [String]).count
        }
        return 0
    }
    
    func menu(_ menu: YDMenu, titleForItemsInRowAtIndexPath indexPath: YDMenu.Index) -> String {
        
        
        switch indexPath.column {
            
        case 0:
            return ((data["Area"] as! [[String: AnyObject]])[indexPath.row]["distance"] as! [String])[indexPath.item]
            
        default:
            return ""
        }
        
        
    }
    
    func menu(_ menu: YDMenu, titleForRowAtIndexPath indexPath: YDMenu.Index) -> String {
        
        switch indexPath.column {
        case 0:
            return (data["Area"] as! [[String: AnyObject]])[indexPath.row]["name"] as! String
        case 1:
            return data["filter"] as! String
        default:
            return ""
        }
    }
    
    func menu(_ menu: YDMenu, imageNameForRowAtIndexPath indexPath: YDMenu.Index) -> String? {
        if indexPath.column == 0 || indexPath.column == 2 {
            return (arc4random() % 10).description
        }
        return nil
    }
    
    func menu(_ menu: YDMenu, detailTextForRowAtIndexPath indexPath: YDMenu.Index) -> String? {
        
        let random = arc4random() % 100
        return random.description
    }
    
    func menu(_ menu: YDMenu, detailTextForItemsInRowAtIndexPath indexPath: YDMenu.Index) -> String? {
        let random = arc4random() % 1000
        return random.description
    }
    
    
    func menu(_ menu: YDMenu, didSelectRowAtIndexPath indexPath: YDMenu.Index) {
        
        print("选中了第\(indexPath.column)列, 一级列表的第\(indexPath.row)行\(indexPath.haveItem ? ", 二级列表的第\(indexPath.item)行" : ", 没有选择二级列表")")
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
		// TODO: - 设置发现页行数
        return rowCount
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = self.tableView.dequeueReusableCell(withIdentifier:self.tableCellIdentifier, for: indexPath)
        (cell.viewWithTag(2) as! UILabel).text = merchantArray[indexPath.row]
        
        return cell
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        // TODO: - 点击发现页折扣活动后跳转
    }

    
}
