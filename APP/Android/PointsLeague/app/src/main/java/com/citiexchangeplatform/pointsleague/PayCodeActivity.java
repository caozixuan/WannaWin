package com.citiexchangeplatform.pointsleague;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class PayCodeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_code);

        ImageView iv_bar = (ImageView)findViewById(R.id.imageview_barcode);
        ImageView iv_qr = (ImageView)findViewById(R.id.imageview_qr);
        String content = "display";

        /*生成二维码、条形码*/
        /*生成条形码*/

        //Bitmap bitmap_bar = ZXingUtils.creatBarcode(getApplicationContext(), content, iv_bar.getWidth(),  iv_bar.getHeight(),true);
        //iv_bar.setImageBitmap(bitmap_bar);

        Bitmap bitmap_bar = ZXingUtils.creatBarcode(getApplicationContext(), content,400,  200,false);
        iv_bar.setImageBitmap(bitmap_bar);
        /*生成二维码*/
        //Bitmap bitmap_qr = ZXingUtils.createQRImage(content, iv_qr.getWidth(),  iv_qr.getHeight());
        //iv_qr.setImageBitmap(bitmap_qr);

        Bitmap bitmap_qr = ZXingUtils.createQRImage(content, 300,  300);
        iv_qr.setImageBitmap(bitmap_qr);

    }

}
