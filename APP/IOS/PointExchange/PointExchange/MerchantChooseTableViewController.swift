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
                (cell.viewWithTag(2) as? UIImageView)?.imageFromURL(MerchantList.list[indexPath.row].logoURL!, placeholder: UIImage())
                return cell
            })
        result.bind(to: self.tableView.rx.items(dataSource: dataSource))
            .disposed(by:disposeBag)
		self.tableView.rx.itemSelected.map{ indexPath in
			return (indexPath,dataSource[indexPath])
		}.subscribe(onNext: { indexPath, model in
			let sb = UIStoryboard(name: "HomePage", bundle: nil)
			let view = sb.instantiateViewController(withIdentifier: "AddCardTableView") as! AddCardTableViewController
			view.merchant = MerchantList.list[indexPath.row]
			self.navigationController?.pushViewController(view, animated: true)
		}).disposed(by:disposeBag)
        
    }
    
    /// 初始化tableView显示的数据
    func getDatas()->Observable<[SectionModel<String, String>]> {
        let items = (0 ..< marchantCount).map {i in
            merchantNames[i]
        }
        let observable = Observable.just([SectionModel(model: "S", items: items)])
        return observable
    }
    
    /// 搜索时结果过滤
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
    
    /// 创建索引
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

    
    /// 获得商户卡信息的回调函数
    func gotCardTypeCallback(result:Bool,cardTypes:[CardType]){
        if result {
            if cardTypes.count != 0 {
                MerchantList.get(merchantID: cardTypes[0].merchantID!)?.cardTypes=cardTypes
                
                let storyboard = UIStoryboard(name: "HomePage", bundle: nil)
                let view = storyboard.instantiateViewController(withIdentifier: "AddCardTableView") as? AddCardTableViewController
                view?.merchant = MerchantList.get(merchantID: cardTypes[0].merchantID!)
                self.navigationController?.pushViewController(view!, animated: true)
            }
        }
    }
	
	

}
