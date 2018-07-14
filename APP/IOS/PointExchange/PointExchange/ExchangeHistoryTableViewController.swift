//
//  ExchangeHistoryTableViewController.swift
//  PointExchange
//
//  Created by yiner on 2018/7/14.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import UIKit

class ExchangeHistoryTableViewController: UITableViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
    }

    // MARK: - Table view data source
    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
		// TODO: - Incomplete implementation, return the number of rows
        return 5
    }

    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "exchange item", for: indexPath)

        // TODO: - Configure the cell

        return cell
    }
	
}
