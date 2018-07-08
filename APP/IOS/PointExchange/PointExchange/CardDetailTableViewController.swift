//
//  CardDetailTableViewController.swift
//  PointExchange
//
//  Created by yiner on 2018/7/7.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import UIKit

class CardDetailTableViewController: UITableViewController {
	
	enum CardDetail:Int {
		case cardImage = 0, cardDetail, unBind
	}

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

//    override func numberOfSections(in tableView: UITableView) -> Int {
//		//FIXME: 待完善
//        return 3
//    }
//
//    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
//        //FIXME: 待完善
//		if section == CardDetail.cardImage.rawValue {
//			return 1
//		} else if section == CardDetail.cardDetail.rawValue {
//			return 4
//		} else {
//			return 1
//		}
//		
//    }
//
//	
//    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
//		let cell:UITableViewCell!
//		if indexPath.section == CardDetail.cardImage.rawValue {
//			cell = tableView.dequeueReusableCell(withIdentifier: "card image", for: indexPath)
//			
//		}
//		else if indexPath.section == CardDetail.cardDetail.rawValue {
//			switch indexPath.row {
//			case 0 : cell = tableView.dequeueReusableCell(withIdentifier: "card id", for: indexPath)
//			case 1 : cell = tableView.dequeueReusableCell(withIdentifier: "nickname", for: indexPath)
//			case 2 : cell = tableView.dequeueReusableCell(withIdentifier: "binding phone", for: indexPath)
//			default : cell = tableView.dequeueReusableCell(withIdentifier: "available store", for: indexPath)
//			}
//			
//		}
//		else {
//			cell = tableView.dequeueReusableCell(withIdentifier: "unbind", for: indexPath)
//		}
//
//        return cell
//    }
	

    /*
    // Override to support conditional editing of the table view.
    override func tableView(_ tableView: UITableView, canEditRowAt indexPath: IndexPath) -> Bool {
        // Return false if you do not want the specified item to be editable.
        return true
    }
    */

    /*
    // Override to support editing the table view.
    override func tableView(_ tableView: UITableView, commit editingStyle: UITableViewCellEditingStyle, forRowAt indexPath: IndexPath) {
        if editingStyle == .delete {
            // Delete the row from the data source
            tableView.deleteRows(at: [indexPath], with: .fade)
        } else if editingStyle == .insert {
            // Create a new instance of the appropriate class, insert it into the array, and add a new row to the table view
        }    
    }
    */

    /*
    // Override to support rearranging the table view.
    override func tableView(_ tableView: UITableView, moveRowAt fromIndexPath: IndexPath, to: IndexPath) {

    }
    */

    /*
    // Override to support conditional rearranging of the table view.
    override func tableView(_ tableView: UITableView, canMoveRowAt indexPath: IndexPath) -> Bool {
        // Return false if you do not want the item to be re-orderable.
        return true
    }
    */

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

}
