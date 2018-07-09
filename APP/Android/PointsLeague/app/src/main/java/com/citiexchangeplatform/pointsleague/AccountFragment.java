package com.citiexchangeplatform.pointsleague;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class AccountFragment extends Fragment {

    boolean isLogin;

    View view;
    LinearLayout accountInfoLayout;
    Button buttonLogout;

    ListView listViewMenu;
    ArrayList<String> menuItem = new ArrayList<String>(Arrays.asList("查看历史订单","绑定花旗账户","通用","反馈","关于"));
    ArrayList<Integer> menuIcon = new ArrayList<Integer>(Arrays.asList(R.drawable.ic_mall_black_24dp, R.drawable.ic_account_black_24dp, R.drawable.ic_points_black_24dp, R.drawable.ic_search_black_24dp, R.drawable.ic_points_black_24dp));


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_account, null);
        accountInfoLayout = (LinearLayout)view.findViewById(R.id.linearlayout_account_info_account);

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
                switch (position){
                    case 0:
                        Toast.makeText(getContext(),"历史订单",Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(getContext(),"绑定",Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Intent intentToGeneral = new Intent(getContext(), GeneralSettingActivity.class);
                        startActivity(intentToGeneral);
                        break;
                    case 3:
                        Intent intentToFeedback = new Intent(getContext(), FeedbackActivity.class);
                        startActivity(intentToFeedback);
                        break;
                    case 4:
                        Intent intentToAbout = new Intent(getContext(), AboutUsActivity.class);
                        startActivity(intentToAbout);
                        break;
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
        accountInfoLayout.removeAllViewsInLayout();

        isLogin = LogStateInfo.getInstance(getContext()).isLogin();

        if(isLogin){
            buttonLogout.setVisibility(View.VISIBLE);


            TextView accountTextView = new TextView(getActivity());
            accountTextView.setText("账户：" + LogStateInfo.getInstance(getContext()).getAccount());
            accountTextView.setTextSize(24);

            TextView citiAccountTextView = new TextView(getActivity());
            citiAccountTextView.setText("尚未绑定花旗账户");
            citiAccountTextView.setTextSize(16);

            accountInfoLayout.addView(accountTextView);
            accountInfoLayout.addView(citiAccountTextView);
        }else {
            buttonLogout.setVisibility(View.INVISIBLE);

            Button button = new Button(getActivity());
            button.setText("登录");
            button.setTextSize(18);
            button.setTextColor(getResources().getColor(R.color.colorWhite));
            button.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            button.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }
            });
            accountInfoLayout.addView(button);
        }
    }
}