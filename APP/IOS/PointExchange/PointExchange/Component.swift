//
//  Component.swift
//  PointExchange
//
//  Created by panyy on 2018/7/10.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import UIKit

class ActivityIndicator {
    static func createWaitIndicator(parentView:UIView)->UIActivityIndicatorView{
        let activityIndicator = UIActivityIndicatorView(activityIndicatorStyle: UIActivityIndicatorViewStyle.whiteLarge)
        activityIndicator.center = parentView.center
        activityIndicator.backgroundColor = UIColor(red: 0, green: 0, blue: 0, alpha: 0.5)
        activityIndicator.hidesWhenStopped = true
        activityIndicator.frame = CGRect(x:parentView.bounds.width/2-50, y:parentView.bounds.height/2-50, width:100, height:100)
        activityIndicator.layer.cornerRadius =  10
        parentView.addSubview(activityIndicator)
        return activityIndicator
    }
    
}
