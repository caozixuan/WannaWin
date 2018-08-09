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
	
	var merchants = [Merchant]()
    
    var disposeBag = DisposeBag()
	var dataSource: RxTableViewSectionedReloadDataSource<SectionModel<String,Merchant>>?
    var indexController:UILocalizedIndexedCollation?
	
	var activityIndicator:UIActivityIndicatorView?

    override func viewDidLoad() {
        super.viewDidLoad()
		
		activityIndicator = ActivityIndicator.createWaitIndicator(parentView: self.view)
		activityIndicator?.startAnimating()
		requestMerchantsInfo()
    }
	
	/// 请求数据
	func requestMerchantsInfo(){
		ServerConnector.getMerchantCount(){(result, count) in
			if result {
				ServerConnector.getMerchantsInfos(start: 0, n: count){(result,merchants) in
					if result {
						self.merchants = merchants
						let observable = Observable.empty().asObservable()
							.startWith(())
							.flatMapLatest(self.getDatas)
							.flatMap(self.filter)
							.share(replay:1)
						self.dataSource = RxTableViewSectionedReloadDataSource<SectionModel<String,Merchant>>(configureCell: { (dataSource,view,indexPath,element) in
							let cell = view.dequeueReusableCell(withIdentifier: "cell")!
							(cell.viewWithTag(1) as? UILabel)?.text = element.name
							(cell.viewWithTag(2) as? UIImageView)?.imageFromURL(element.logoURL!, placeholder: UIImage())
							return cell
						})
						observable.bind(to: self.tableView.rx.items(dataSource: self.dataSource!)).disposed(by: self.disposeBag)
						// 点击事件
						self.tableView.rx.itemSelected.map{ indexPath in
							return (indexPath,self.dataSource![indexPath])
							}.subscribe(onNext: { indexPath, model in
								let sb = UIStoryboard(name: "HomePage", bundle: nil)
								let view = sb.instantiateViewController(withIdentifier: "AddCardTableView") as! AddCardTableViewController
								view.merchant = self.merchants[indexPath.row]
								self.navigationController?.pushViewController(view, animated: true)
							}).disposed(by:self.disposeBag)
					}
					self.activityIndicator?.stopAnimating()
				}
			}
		}
	}

	
	/// 获得商户信息后的回调函数
	func gotMerchantsCallback(result:Bool, merchants:[Merchant]){
		if result {
			self.merchants = merchants
			self.tableView.reloadData()
		}
		else {
			print("商户信息获取失败")
		}
		activityIndicator?.stopAnimating()
	}
	
    /// 初始化tableView显示的数据
    func getDatas()->Observable<[SectionModel<String, Merchant>]> {
        let items = (0 ..< merchants.count).map {i in
			merchants[i]
        }
        let observable = Observable.just([SectionModel(model: "S", items: items)])
        return observable
    }
    
    /// 搜索时结果过滤
    func filter(data:[SectionModel<String, Merchant>])-> Observable<[SectionModel<String, Merchant>]> {
        return searchBar.rx.text.orEmpty
            .flatMapLatest{ query -> Observable<[SectionModel<String, Merchant>]> in
                if query.isEmpty{
                    return Observable.just(data)
                }
                else{
                    var newData:[SectionModel<String, Merchant>] = []
                    for sectionModel in data {
                        let items = sectionModel.items.filter{ "\($0.name)".transformToPinyin().contains(query.lowercased()) }
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
