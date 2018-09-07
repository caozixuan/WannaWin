//
//  FinishExchangeToGeneralViewController.swift
//  PointExchange
//
//  Created by yiner on 2018/8/2.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import UIKit

class FinishExchangeToGeneralViewController: UIViewController, UITableViewDelegate, UITableViewDataSource, Connector {

    @IBOutlet weak var statusLogo: UIImageView!
    @IBOutlet weak var statusText: UILabel!
    @IBOutlet weak var addedGeneralPoints: UILabel!
    @IBOutlet weak var pageControl: UIPageControl!
    
    var pageViewController : PageViewController!
    
    var status: Bool = false
    var generalPoints: Double?
    var successMerchants: [ChooseMerchants]?
    var successMerchantNames : [String]?
    var failureMerchants: Dictionary<String,String>?
    var failureName: [String]?
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        // 设置导航栏按钮
        self.navigationItem.hidesBackButton = true
        let finishBtn = UIBarButtonItem(title: "完成", style: .done, target: self, action: #selector(goBackToHomePage))
        self.navigationItem.rightBarButtonItem = finishBtn
        
        if status {
            statusText.text = "兑换成功"
            statusLogo.image = UIImage(named: "success")
            addedGeneralPoints.isHidden = false
			
			if String(format:"%.2f", generalPoints!) == "-0.00" { //排除误差
				addedGeneralPoints.text = "+0.00P"
				
			}
			else {
				addedGeneralPoints.text = "+" + String(format:"%.2f", generalPoints!) + "P"
			}
			
        }
        else {
            statusText.text = "兑换失败"
            statusLogo.image = UIImage(named: "fail")
            failureName = failureMerchants?.keys.sorted()
            addedGeneralPoints.isHidden = true
        }
    
    }
    
    // MARK: - Table view data source
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        var count: Int! = 0
        
        if (tableView.delegate? .isKind(of: ExchangeResultTableViewController.self))!{
            let viewController = tableView.delegate as! ExchangeResultTableViewController
            count = viewController.index! * 3
        }
        
        if status {
            if count > (successMerchants?.count)!{
                let num = (successMerchants?.count)! - (count - 3)
                return num
            }
            return 3
        }
        else {
            if count > (failureMerchants?.count)!{
                let num = (failureMerchants?.count)! - (count - 3)
                return num
            }
            return 3
        }
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell  = tableView.dequeueReusableCell(withIdentifier: "exchangedPoints", for: indexPath)
        
        var page: Int! = 0
        var index: Int! = 0
        
        if (tableView.delegate? .isKind(of: ExchangeResultTableViewController.self))!{
            let viewController = tableView.delegate as! ExchangeResultTableViewController
            page = viewController.index!
            index = (page-1) * 3 + indexPath.row
        }
        
        
        if cell .isKind(of: ExchangedPointsCell.self){
            let exchangedPointsCell = cell as! ExchangedPointsCell
            if status {
                exchangedPointsCell.merchantName.text = successMerchantNames?[index]
                let points = Double((successMerchants?[index].selectedMSCardPoints)!)!
                exchangedPointsCell.exchangedPoints.text = String(format:"%.2f", points)
            }
            else {
                let merchant = failureName?[index]
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
    
    // 为嵌入PageVC做准备
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "show page VC" {
            if segue.destination .isKind(of: PageViewController.self){
                pageViewController = segue.destination as! PageViewController
                pageViewController.connector = self
                if status {
                    pageViewController.totalNum = (successMerchants?.count)!
                }
                else {
                    pageViewController.totalNum = (failureMerchants?.count)!
                }
                
                pageViewController.pageNum = (pageViewController.totalNum % 3 == 0) ?(pageViewController.totalNum/3) : (pageViewController.totalNum/3 + 1)
                
                // set up page control
                self.pageControl.numberOfPages = pageViewController.pageNum
                self.pageControl.currentPage = 0
            }
        }
    }
    
    func setPageIndex(index: Int){
        self.pageControl.currentPage = index
    }

}
