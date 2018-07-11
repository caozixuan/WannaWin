//
//  MerchantChooseTableViewController.swift
//  PointExchange
//
//  Created by panyy on 2018/7/10.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import UIKit
import RxSwift
import RxCocoa
import RxDataSources

class MerchantChooseTableViewController: UIViewController {
    
    @IBOutlet weak var tableView: UITableView!
    @IBOutlet weak var searchBar: UISearchBar!
    var marchantCount = MerchantList.list.count
    
    var merchantNames = [String]()
    
    var disposeBag = DisposeBag()
    
    var indexController:UILocalizedIndexedCollation?

    override func viewDidLoad() {
        super.viewDidLoad()

        let result = Observable.empty().asObservable()
            .startWith(())
            .flatMapLatest(getDatas)
            .flatMap(filter)
            .share(replay: 1)
        let dataSource = RxTableViewSectionedReloadDataSource
            <SectionModel<String, String>>(configureCell: {
                (dataSource, tv, indexPath, element) in
                let cell = tv.dequeueReusableCell(withIdentifier: "cell")!
                (cell.viewWithTag(1) as? UILabel)?.text = element
                let data = try? Data(contentsOf: URL(string:MerchantList.list[indexPath.row].logoURL!)!)
                cell.imageView?.imageFromURL(MerchantList.list[indexPath.row].logoURL!, placeholder: UIImage(named: "周黑鸭")!)
                return cell
            })
        result.bind(to: self.tableView.rx.items(dataSource: dataSource))
            .disposed(by:disposeBag)
        // Uncomment the following line to preserve selection between presentations
        // self.clearsSelectionOnViewWillAppear = false

        // Uncomment the following line to display an Edit button in the navigation bar for this view controller.
        // self.navigationItem.rightBarButtonItem = self.editButtonItem
        
        
    }
    func getDatas()->Observable<[SectionModel<String, String>]> {
        print("正在请求数据......")
        let items = (0 ..< marchantCount).map {i in
            merchantNames[i]
        }
        let observable = Observable.just([SectionModel(model: "S", items: items)])
        return observable
    }
    func filter(data:[SectionModel<String, String>])-> Observable<[SectionModel<String, String>]> {
        return searchBar.rx.text.orEmpty
            .flatMapLatest{ query -> Observable<[SectionModel<String, String>]> in
                if query.isEmpty{
                    return Observable.just(data)
                }
                else{
                    var newData:[SectionModel<String, String>] = []
                    for sectionModel in data {
                        let items = sectionModel.items.filter{ "\($0)".transformToPinyin().contains(query.lowercased()) }
                        newData.append(SectionModel(model: sectionModel.model, items: items))
                    }
                    return Observable.just(newData)
                }
        }
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
