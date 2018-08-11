//
//  UIViewExtension.swift
//  PointExchange
//
//  Created by Pan on 2018/7/7.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import UIKit

public enum ShakeDirection{
    case horizontal
    case vertical
}

extension UIView{
    // MARK: - 抖动效果
    ///
    /// - Parameters:
    ///     - direction: 抖动方向
    ///     - times: 抖动次数
    ///     - duration：持续时长
    ///     - delta：偏移量
    ///     - completion：抖动结束后的回调函数
    public func shake(direction:ShakeDirection = .horizontal, times:Int = 3, duration:TimeInterval = 0.1, delta:CGFloat = 2, completion:(()->Void)! = nil){
        UIView.animate(withDuration: duration, animations: {
            switch direction{
            case .horizontal:
                self.layer.setAffineTransform(CGAffineTransform(translationX: delta, y: 0))
            case .vertical:
                self.layer.setAffineTransform(CGAffineTransform(translationX: 0, y: delta))
            }
        }){ finish in
            if times == 0 {
                UIView.animate(withDuration: duration, animations: {
                    self.layer.setAffineTransform(CGAffineTransform.identity)
                }, completion: { (finish) in
                    completion?()
                })
            }
            else
            {
                self.shake(direction: direction, times: times - 1, duration: duration, delta: -delta, completion: completion)
            }
        }
        
    }
    
    
}



