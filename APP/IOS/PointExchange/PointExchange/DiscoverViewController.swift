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

class DiscoverViewController: UIViewController, UITableViewDelegate, UITableViewDataSource {
    @IBOutlet weak var searchBar: UISearchBar!
    
    var rowCount = 4;
    var discountArray = ["东方航空","南方航空","北京烤鸭","宫保鸡丁"]
    let disposeBag = DisposeBag()
    @IBOutlet weak var tableView: UITableView!
    
    @IBOutlet weak var segmentedControl: UISegmentedControl!
    
    var tableCellIdentifier:String = "local discount"
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.tableView.delegate=self
        self.tableView.dataSource=self
        
        self.tableView.rowHeight = 185
        
        // TODO: - 搜索rx定义
//        let searchResult = self.searchBar.rx.text.orEmpty.flatMapFirst{  query -> Observable<[String]> in
//            if query.isEmpty {
//                return .just([])
//            }
//            else{
//                let results = self.discountArray.filter{ $0.hasPrefix(query)}
//                return .just(results)
//            }
//            }
//            .observeOn(MainScheduler.instance)
//
//        searchResult.bind(to: tableView.rx.items(cellIdentifier: tableCellIdentifier)){
//            (index, label: String, cell) in
//            (cell.viewWithTag(2) as! UILabel).text = label
//            }.disposed(by: disposeBag)
        

        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
		// TODO: - 设置发现页行数
        return rowCount
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = self.tableView.dequeueReusableCell(withIdentifier:self.tableCellIdentifier, for: indexPath)
        (cell.viewWithTag(2) as! UILabel).text = discountArray[indexPath.row]
        
        return cell
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        // TODO: - 点击发现页折扣活动后跳转
    }

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */
    @IBAction func segmentedChange(_ sender: Any) {
        switch segmentedControl.selectedSegmentIndex{
        case 0:
            tableCellIdentifier="local discount"
            tableView.reloadData()
        case 1:
            tableCellIdentifier="online discount"
            tableView.reloadData()
        default:
            break;
        }
        
    }
    
}
