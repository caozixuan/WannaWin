package com.citiexchangeplatform.pointsleague;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class AccountFragment extends Fragment {

    boolean isLogin;

    View view;
    RelativeLayout accountInfoLayout;
    Button buttonLogout;
    Button buttonLogin;
    TextView accountTextView;
    TextView citiAccountTextView;
    ImageView portrait;

    ListView listViewMenu;
    //ArrayList<String> menuItem = new ArrayList<String>(Arrays.asList("积分兑换记录","查看历史订单","绑定花旗账户","通用","反馈","关于"));
    ArrayList<String> menuItem = new ArrayList<String>(Arrays.asList("积分兑换记录","我的订单","设置"));
    ArrayList<Integer> menuIcon = new ArrayList<Integer>(Arrays.asList(R.drawable.icon_exchange,R.drawable.icon_my_order_history, R.drawable.icon_setting));


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_account, null);
        accountInfoLayout = (RelativeLayout)view.findViewById(R.id.linearlayout_account_info_account);
        accountTextView = view.findViewById(R.id.textview_account);
        citiAccountTextView = view.findViewById(R.id.textview_account_position);
        portrait = view.findViewById(R.id.img_portrait);

        portrait.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(LogStateInfo.getInstance(getContext()).isLogin()){
                    Intent intentToUserSetting = new Intent(getContext(), UserSettingActivity.class);
                    startActivity(intentToUserSetting);
                }
                else
                    Toast.makeText(getContext(), "请先登录", Toast.LENGTH_SHORT).show();

            }
        });

        //状态栏文字图标暗色
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ) {//android6.0以后可以对状态栏文字颜色和图标进行修改
            getActivity().getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        buttonLogin = (Button)view.findViewById(R.id.button_login);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(LogStateInfo.getInstance(getContext()).isLogin() != isLogin) {
                    Toast.makeText(getContext(), "请先登录", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }

            }
        });

        buttonLogout = (Button)view.findViewById(R.id.button_logout_account);

        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                alertDialog.setTitle("确定要退出登录吗").setPositiveButton("退出", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        LogStateInfo.getInstance(getContext()).logout();
                        loadAccountInfo();
                    }
                }).setNegativeButton("取消", null).show();
            }
        });

        loadAccountInfo();



        listViewMenu = (ListView)view.findViewById(R.id.listView_menu_account);
        MenuListAdapter menuListAdapter = new MenuListAdapter(getContext(),menuItem,menuIcon);
        listViewMenu.setAdapter(menuListAdapter);

        listViewMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(!LogStateInfo.getInstance(getContext()).isLogin()) {
                    Toast.makeText(getContext(), "请先登录", Toast.LENGTH_SHORT).show();
                }
                else{
                    switch (position){
                        case 0:
                            Intent intentToRecord = new Intent(getContext(), PointsExchangeExpandListActivity.class);
                            startActivity(intentToRecord);
                            break;
                        case 1:
                            Intent intentToOrder = new Intent(getContext(), MyOrderActivity.class);
                            startActivity(intentToOrder);
                            break;
                        case 2:
                            Intent intentToGeneral = new Intent(getContext(), SettingActivity.class);
                            startActivity(intentToGeneral);
                            break;

                    }
                }

            }
        });




        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();
        if(LogStateInfo.getInstance(getContext()).isLogin() != isLogin)
        {
            loadAccountInfo();
        }
    }

    private void loadAccountInfo(){
        //accountInfoLayout.removeAllViewsInLayout();

        isLogin = LogStateInfo.getInstance(getContext()).isLogin();

        if(isLogin){
            buttonLogin.setVisibility(View.INVISIBLE);
            buttonLogout.setVisibility(View.VISIBLE);
            accountTextView.setVisibility(View.VISIBLE);
            citiAccountTextView.setVisibility(View.VISIBLE);

            //TextView accountTextView = view.findViewById(R.id.textview_account);
            accountTextView.setText(LogStateInfo.getInstance(getContext()).getAccount());


            //TextView citiAccountTextView = view.findViewById(R.id.textview_account_position);
            //citiAccountTextView.setText("尚未绑定花旗账户");


            //accountInfoLayout.addView(accountTextView);
            //accountInfoLayout.addView(citiAccountTextView);
        }else {
            buttonLogout.setVisibility(View.INVISIBLE);
            accountTextView.setVisibility(View.INVISIBLE);
            citiAccountTextView.setVisibility(View.INVISIBLE);
            buttonLogin.setVisibility(View.VISIBLE);
            //Button button = new Button(getActivity());
            //button.setText("登录");
            //button.setTextColor(getResources().getColor(R.color.colorWhite));
            //button.setTextSize(18);
            //button.setBackgroundColor(getResources().getColor(R.color.colorLightOrange));
            //button.setBackground(getResources().getDrawable(R.drawable.pic_logout));
            //button.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
            //        LinearLayout.LayoutParams.WRAP_CONTENT));
            //button.setOnClickListener(new View.OnClickListener() {
            //    @Override
            //    public void onClick(View v) {
            //        Intent intent = new Intent(getActivity(), LoginActivity.class);
            //        startActivity(intent);
            //    }
            //});
            //accountInfoLayout.addView(button);
        }
    }
}
