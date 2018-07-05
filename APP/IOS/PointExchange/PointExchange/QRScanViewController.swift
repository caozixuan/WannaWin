//
//  QRScanViewController.swift
//  PointExchange
//
//  Created by yiner on 2018/7/5.
//  Copyright © 2018年 WannaWin. All rights reserved.
//

import UIKit

class QRScanViewController: UIViewController {

    @IBOutlet weak var barCodeView: UIImageView!
    @IBOutlet weak var qrCodeView: UIImageView!
    override func viewDidLoad() {
        super.viewDidLoad()
        
        barCodeView.image=createBarCode(url: "hello")
        qrCodeView.image=createQRCode(url: "hello")

        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */
    func createQRCode(url:String) -> UIImage?{
        let filter=CIFilter(name:"CIQRCodeGenerator")
        filter?.setDefaults()
        let text = url
        filter?.setValue(text.data(using: String.Encoding.utf8), forKey: "inputMessage")
        //取出二维码
        if let outputImage = filter?.outputImage{
            let qrCodeImage = createHighQRCode(outputImage, size:300)
            return qrCodeImage
        }
        return nil
    }
    
    func createBarCode(url:String) ->UIImage?{
        let filter=CIFilter(name:"CICode128BarcodeGenerator")
        filter?.setDefaults()
        let text=url
        filter?.setValue(text.data(using: String.Encoding.utf8), forKey: "inputMessage")
        //取出条形码
        let width = CGFloat(300)
        let height = CGFloat(50)
        if var outputImage = filter?.outputImage{
            let scaleX = width/outputImage.extent.size.width
            let scaleY = height/outputImage.extent.size.height
            outputImage = outputImage.transformed(by: (CGAffineTransform(scaleX:scaleX, y:scaleY)))
            return UIImage(ciImage: outputImage)
        }
        return nil
    }
    
    func createHighQRCode(_ image: CIImage, size:CGFloat)->UIImage{
        let integral:CGRect = image.extent.integral
        let proportion: CGFloat = min(size/integral.width, size/integral.height)
        
        let width = integral.width * proportion
        let height = integral.height*proportion
        let colorSpace: CGColorSpace = CGColorSpaceCreateDeviceGray()
        let bitmapRef = CGContext(data:nil, width:Int(width), height:Int(height),bitsPerComponent:8, bytesPerRow:0, space:colorSpace, bitmapInfo:0)!
        let context=CIContext(options:nil)
        let bitmapImage:CGImage = context.createCGImage(image,from:integral)!
        
        bitmapRef.interpolationQuality = CGInterpolationQuality.none
        bitmapRef.scaleBy(x: proportion, y:proportion);
        bitmapRef.draw(bitmapImage, in: integral);
        let image: CGImage = bitmapRef.makeImage()!
        return UIImage(cgImage: image);
        
    }

}
