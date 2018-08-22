//
//  ExchangeHistoryTableViewController.swift
//  PointExchange
//
//  Created by yiner on 2018/7/14.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import UIKit
import RxCocoa
import RxSwift
import RxDataSources
import Kingfisher

class ExchangeHistoryViewController: UIViewController {

	@IBOutlet weak var tableView: UITableView!
	var merchantID:String?
	var pointHistories = [PointsHistoryItem]()
	
	var disposeBag = DisposeBag()
    override func viewDidLoad() {
        super.viewDidLoad()
		self.tableView.register(UINib(nibName: "PointHistoryTableViewCell", bundle: nil), forCellReuseIdentifier: "pointCell")
		self.tableView.rowHeight = 60
		ServerConnector.getPointsHistoryByMerchantID(merchantID: merchantID!){(result,pointHistories) in
			if result {
				self.pointHistories = pointHistories!
				self.setDataSource()
			}
			
		}
    }
	private func getDatas()->Observable<[SectionModel<String,PointsHistoryItem>]>{
		let items = (0 ..< self.pointHistories.count).map {i in
			self.pointHistories[i]
		}
		let observable = Observable.just([SectionModel(model: "item", items: items)])
		return observable
	}
	private func setDataSource(){
		let observable = Observable.empty().asObservable()
			.startWith(())
			.flatMapLatest(getDatas).share(replay: 1)
		let dataSource = RxTableViewSectionedReloadDataSource<SectionModel<String,PointsHistoryItem>>(configureCell: { (dataSource,view,indexPath,element) in
			var cell = self.tableView.dequeueReusableCell(withIdentifier: "pointCell") as? PointHistoryTableViewCell
			if cell == nil {
				cell = UITableViewCell(style: .default, reuseIdentifier: "pointCell") as? PointHistoryTableViewCell
			}
			cell?.isUserInteractionEnabled = false
			let formatter = DateFormatter()
			formatter.dateFormat = "MMM dd, yyyy hh:mm:ss a"
			let date = formatter.date(from: element.time!)
			formatter.dateFormat = "MMM dd, yyyy"
			cell?.dateLabel.text = formatter.string(from: date!)
			cell?.pointLabel.text = "-" + String(stringInterpolationSegment: element.pointsCard!)
			cell?.citiPointLabel.text = "+" + String(stringInterpolationSegment: element.pointsCiti!) + "P"
			return cell!
		})
		observable.bind(to: self.tableView.rx.items(dataSource: dataSource)).disposed(by: disposeBag)
	}
	
	
}
