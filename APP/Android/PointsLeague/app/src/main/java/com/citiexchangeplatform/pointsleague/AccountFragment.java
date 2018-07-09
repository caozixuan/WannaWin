package com.citiexchangeplatform.pointsleague;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class AccountFragment extends Fragment {

    View view;
    LinearLayout accountInfoLayout;

    boolean isLogin;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_account, null);
        accountInfoLayout = (LinearLayout)view.findViewById(R.id.account_info_area_account);

        LogStateInfo.getInstance(getContext()).logout();    //each time open. logout

        loadAccountInfoArea();

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
            loadAccountInfoArea();
        }
    }

    private void loadAccountInfoArea(){
        accountInfoLayout.removeAllViewsInLayout();

        isLogin = LogStateInfo.getInstance(getContext()).isLogin();

        if(isLogin){

            TextView accountTextView = new TextView(getActivity());
            accountTextView.setText("账户：" + LogStateInfo.getInstance(getContext()).getAccount());
            accountTextView.setTextSize(24);

            TextView citiAccountTextView = new TextView(getActivity());
            citiAccountTextView.setText("尚未绑定花旗账户");
            citiAccountTextView.setTextSize(16);

            accountInfoLayout.addView(accountTextView);
            accountInfoLayout.addView(citiAccountTextView);
        }else {

            Button button = new Button(getActivity());
            button.setText("登录");
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
