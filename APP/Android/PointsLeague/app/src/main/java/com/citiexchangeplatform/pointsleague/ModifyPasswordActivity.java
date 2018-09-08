package com.citiexchangeplatform.pointsleague;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ModifyPasswordActivity extends AppCompatActivity {

    private ProgressDialog dialog;

    private EditText editTextOldPassword;
    private EditText editTextNewPassword;
    private String strOldPassword;
    private String strNewPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_password);
        //设置toolbar
        toolBar();

        editTextOldPassword = findViewById(R.id.editText_old_password);
        editTextNewPassword = findViewById(R.id.editText_new_password);

        Button buttonLogin = (Button) findViewById(R.id.button_modify_password);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextOldPassword.getText().length() == 0) {
                    showAlert("请输入旧密码");
                } else if (editTextNewPassword.getText().length() == 0) {
                    showAlert("请输入新密码");
                } else {

                    strOldPassword = editTextOldPassword.getText().toString();
                    strNewPassword = editTextNewPassword.getText().toString();

                    tryModifyPassword();
                }
            }
        });
    }

    private void showAlert(String msg) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(ModifyPasswordActivity.this);
        alertDialog.setTitle(msg).setPositiveButton("OK", null).show();
    }

    private void tryModifyPassword(){
        String url="http://193.112.44.141:80/citi/account/changePassword";
        RequestQueue queue = MyApplication.getHttpQueues();
        dialog = ProgressDialog.show(ModifyPasswordActivity.this, "", "正在修改密码...");
        //RequestQueue queue=Volley.newRequestQueue(this);
        final StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                dialog.dismiss();

                Log.e("success",s);
                System.out.println(s);
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    Boolean status = jsonObject.getBoolean("status");
                    if(status){
                        Toast.makeText(ModifyPasswordActivity.this, "密码修改成功", Toast.LENGTH_LONG).show();
                        finish();
                    }
                    else
                        Toast.makeText(ModifyPasswordActivity.this, "密码修改失败,请检查输入", Toast.LENGTH_LONG).show();


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                dialog.dismiss();

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map=new HashMap<>();

                //userID,oldPassword,newPassword
                map.put("userID",LogStateInfo.getInstance(ModifyPasswordActivity.this).getUserID());
                map.put("oldPassword",strOldPassword);
                map.put("newPassword",strNewPassword);

                return map;
            }
        };

        queue.add(request);
    }




    public void toolBar(){
        boolean isImmersive = false;
        if (hasKitKat() && !hasLollipop()) {
            isImmersive = true;
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        } else if (hasLollipop()) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    //| View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            isImmersive = true;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ) {//android6.0以后可以对状态栏文字颜色和图标进行修改
            getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        final TitleBar titleBar = (TitleBar) findViewById(R.id.title_bar);
        titleBar.setBackgroundColor(getResources().getColor(R.color.colorWhite));
        titleBar.setLeftImageResource(R.drawable.ic_left_orange_24dp);
        titleBar.setLeftText("返回");
        titleBar.setLeftTextSize(18);
        titleBar.setLeftTextColor(getResources().getColor(R.color.colorLightOrange));
        titleBar.setLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

        titleBar.setTitleColor(Color.BLACK);

        //沉浸式
        titleBar.setImmersive(isImmersive);
    }

    public static boolean hasKitKat() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
    }

    public static boolean hasLollipop() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }
}
