//
//  ModifyPasswordView.swift
//  PointExchange
//
//  Created by yiner on 2018/8/20.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import UIKit

protocol ModifyPasswordViewDelegate {
    func modify()
}

class ModifyPasswordView: UIView {

    @IBOutlet var view: UIView!

    @IBOutlet weak var button: UIButton!
    @IBOutlet weak var confirmPswField: UITextField!
    @IBOutlet weak var newPswField: UITextField!
    @IBOutlet weak var oldPswField: UITextField!
    
    var delegate: ModifyPasswordViewDelegate?
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        initViewFromNib()
    }
    
    required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
        initViewFromNib()
    }
    
    private func initViewFromNib(){
        let bundle = Bundle(for: type(of: self))
        let nib = UINib(nibName: "ModifyPasswordView", bundle: bundle)
        self.view = nib.instantiate(withOwner: self, options: nil)[0] as! UIView
        self.view.frame = bounds
        self.addSubview(view)
        button.isEnabled = false
    }
    
    @IBAction func modify(_ sender: Any) {
        delegate?.modify()
    }
    
    @IBAction func changed(_ sender: Any) {
        if self.oldPswField.text != "" && self.newPswField.text != "" && self.confirmPswField.text != "" {
            button.isEnabled = true
        }
    }

}
