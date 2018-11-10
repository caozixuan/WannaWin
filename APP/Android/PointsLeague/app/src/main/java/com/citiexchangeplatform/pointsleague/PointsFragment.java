package com.citiexchangeplatform.pointsleague;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.citiexchangeplatform.pointsleague.adapter.CardPointsAdapter;
import com.citiexchangeplatform.pointsleague.models.CardPointsModel;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.leochuan.CenterSnapHelper;
import com.leochuan.ScaleLayoutManager;
import com.study.xuan.xvolleyutil.base.XVolley;
import com.study.xuan.xvolleyutil.build.PostFormBuilder;
import com.study.xuan.xvolleyutil.callback.CallBack;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PointsFragment extends Fragment {

    boolean wasLogin;
    String newAccount;

    int remainSecond;
    final int WAITING_TIME_FOR_EACH_MSG = 30;

    View view;
    LinearLayout accountInfoLayout;
    View content;
    ProgressDialog dialog;

    RecyclerView recyclerView;
    CardPointsAdapter cardPointsAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_points, null);
        accountInfoLayout = (LinearLayout) view.findViewById(R.id.linearlayout_account_info_points);

        initImageSlider();

        //状态栏文字图标暗色
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//android6.0以后可以对状态栏文字颜色和图标进行修改
            getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        return view;
    }


    private void initImageSlider() {

        final SliderLayout sliderLayout = (SliderLayout) view.findViewById(R.id.slider_main);
        PagerIndicator indicator = (PagerIndicator) view.findViewById(R.id.custom_indicator_main);

        //对SliderLayout自定义配置
        sliderLayout.setCustomAnimation(new DescriptionAnimation());
        sliderLayout.setPresetTransformer(SliderLayout.Transformer.Default);
        sliderLayout.setDuration(3000);
        sliderLayout.setCustomIndicator(indicator);

        PostFormBuilder url =
                XVolley.getInstance()
                        .doPost()
                        .url("http://193.112.44.141:80/citi//recommend/getAds");

        if (LogStateInfo.getInstance(getContext()).isLogin()) {
            url = url.addParam("userID", LogStateInfo.getInstance(getContext()).getUserID());
        }

        url.build().execute(getContext(), new CallBack<String>() {
                    @Override
                    public void onSuccess(Context context, String response) {
                        System.out.println(response);
                        boolean logSuccess = false;
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String name = jsonObject.getString("name");
                                String imageURL = jsonObject.getString("imageURL");
                                final String activityID = jsonObject.getString("activityID");

                                DefaultSliderView sv = new DefaultSliderView(getActivity());
                                sv.image(imageURL);
                                sv.setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                                    @Override
                                    public void onSliderClick(BaseSliderView slider) {
                                        Intent intentToDetailActivity = new Intent(getActivity(), DetailActivityActivity.class);
                                        intentToDetailActivity.putExtra("activityID",activityID);
                                        getContext().startActivity(intentToDetailActivity);
                                    }
                                });
                                sliderLayout.addSlider(sv);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(VolleyError error) {
                        super.onError(error);
                        Toast.makeText(getContext(), "服务器连接失败", Toast.LENGTH_LONG).show();
                    }

                });


    }


    private void loadMainContent() {
        if (LogStateInfo.getInstance(getContext()).isLogin()) {
            content = LayoutInflater.from(getContext()).inflate(R.layout.content_cards_points, null);
            accountInfoLayout.addView(content);
            Button buttonAllCard = (Button) content.findViewById(R.id.button_all_card_main);
            buttonAllCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intentToAllCard = new Intent(getActivity(), AllCardActivity.class);
                    startActivity(intentToAllCard);
                }
            });
            Button buttonExchangePoint = (Button) content.findViewById(R.id.button_exchange_points_main);
            buttonExchangePoint.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intentToExchangePoint = new Intent(getActivity(), PayingActivity.class);
                    startActivity(intentToExchangePoint);
                }
            });

            recyclerView = (RecyclerView) content.findViewById(R.id.recyclerView_cards_points);
            recyclerView.setLayoutManager(
                    new ScaleLayoutManager
                            .Builder(getContext(), 2)
                            .setOrientation(OrientationHelper.HORIZONTAL)
                            .build());
            new CenterSnapHelper().attachToRecyclerView(recyclerView);
            cardPointsAdapter = new CardPointsAdapter(getContext());
            recyclerView.setAdapter(cardPointsAdapter);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
        } else {
            loadLoginContent();
        }
    }

    private void loadLoginContent() {
        content = LayoutInflater.from(getContext()).inflate(R.layout.content_login_points, null);
        accountInfoLayout.addView(content);

        final EditText editTextAccount = (EditText) content.findViewById(R.id.editText_account_card_points);
        final EditText editTextPassword = (EditText) content.findViewById(R.id.editText_password_card_points);

        Button buttonLogin = (Button) content.findViewById(R.id.button_login_card_points);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextAccount.getText().length() == 0) {
                    Toast.makeText(getContext(), "请输入账号", Toast.LENGTH_SHORT).show();
                } else if (editTextPassword.getText().length() == 0) {
                    Toast.makeText(getContext(), "请输入密码", Toast.LENGTH_SHORT).show();
                } else {

                    String strAccount = editTextAccount.getText().toString();
                    String strPassword = editTextPassword.getText().toString();

                    tryLogin(strAccount, strPassword);
                }
            }
        });

        TextView buttonRegister = (TextView) content.findViewById(R.id.textView_register_card_points);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                accountInfoLayout.removeAllViewsInLayout();
                loadRegisterContent1();
            }
        });
    }

    private void loadRegisterContent1() {
        content = LayoutInflater.from(getContext()).inflate(R.layout.content_register1_points, null);
        accountInfoLayout.addView(content);

        final EditText editTextPhone = (EditText) content.findViewById(R.id.editText_phone_card_points);
        final EditText editTextMsg = (EditText) content.findViewById(R.id.editText_msg_card_points);

        final Button buttonMsg = (Button) content.findViewById(R.id.button_msg_card_points);
        buttonMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getMsgVerification(editTextPhone.getText().toString(), buttonMsg);
            }
        });

        Button buttonNext = (Button) content.findViewById(R.id.button_next_card_points);
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextPhone.getText().length() == 0) {
                    Toast.makeText(getContext(), "请输入手机号码", Toast.LENGTH_SHORT).show();
                } else if (editTextMsg.getText().length() == 0) {
                    Toast.makeText(getContext(), "请输入短信验证码", Toast.LENGTH_SHORT).show();
                } else {

                    newAccount = editTextPhone.getText().toString();
                    String strMsg = editTextMsg.getText().toString();

                    checkMsgVerification(newAccount, strMsg);
                }
            }
        });

        Button buttonLogin = (Button) content.findViewById(R.id.button_back_login_card_points);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                accountInfoLayout.removeAllViewsInLayout();
                loadLoginContent();
            }
        });
    }

    private void loadRegisterContent2() {
        content = LayoutInflater.from(getContext()).inflate(R.layout.content_register2_points, null);
        accountInfoLayout.addView(content);

        final EditText editTextPassword1 = (EditText) content.findViewById(R.id.editText_password1_card_points);
        final EditText editTextPassword2 = (EditText) content.findViewById(R.id.editText_password2_card_points);


        Button buttonRegister = (Button) content.findViewById(R.id.button_register_card_points);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextPassword1.getText().length() == 0 || editTextPassword2.getText().length() == 0) {
                    Toast.makeText(getContext(), "请输入密码", Toast.LENGTH_SHORT).show();
                } else if (!editTextPassword1.getText().toString().equals(editTextPassword2.getText().toString())) {
                    Toast.makeText(getContext(), "两次密码输入不一致", Toast.LENGTH_SHORT).show();
                } else {
                    tryRegister(newAccount, editTextPassword1.getText().toString());
                }
            }
        });

        Button buttonLogin = (Button) content.findViewById(R.id.button_back_login_card_points);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                accountInfoLayout.removeAllViewsInLayout();
                loadLoginContent();
            }
        });
    }


    @Override
    public void onStart() {
        super.onStart();
        loadMainContent();
        wasLogin = LogStateInfo.getInstance(getContext()).isLogin();
    }

    @Override
    public void onResume() {
        super.onResume();
        //if (wasLogin != LogStateInfo.getInstance(getContext()).isLogin()) {
            accountInfoLayout.removeAllViewsInLayout();
            loadMainContent();
        //}

        if (LogStateInfo.getInstance(getContext()).isLogin()) {
            cardPointsAdapter.clearAll();
            showCardInfo();
            showPoints(content);
        }

    }

    private void showCardInfo() {
        XVolley.getInstance()
                .doPost()
                .url("http://193.112.44.141:80/citi/mscard/infos")
                .addParam("userID", LogStateInfo.getInstance(getContext()).getUserID())
                .addParam("n", "3")
                .build()
                .execute(getContext(), new CallBack<String>() {
                    @Override
                    public void onSuccess(Context context, String response) {
                        System.out.println(response);
                        boolean logSuccess = false;
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            ArrayList<CardPointsModel> items = new ArrayList<CardPointsModel>();
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String merchantName = jsonObject.getString("merchantName");
                                String merchantID = jsonObject.getString("merchantID");
                                int points = jsonObject.getInt("points");
                                double proportion = jsonObject.getDouble("proportion");
                                int cardStyle = jsonObject.getInt("cardStyle");
                                String logoURL = jsonObject.getString("merchantLogoURL");
                                CardPointsModel item = new CardPointsModel(merchantName, merchantID, points, proportion, cardStyle, logoURL);
                                items.add(item);
                            }

                            switch (items.size()) {
                                case 1:
                                    cardPointsAdapter.addData(items.get(0));
                                    break;
                                case 2:
                                    cardPointsAdapter.addData(items.get(1));
                                    cardPointsAdapter.addData(items.get(0));
                                    break;
                                case 3:
                                    cardPointsAdapter.addData(items.get(1));
                                    cardPointsAdapter.addData(items.get(0));
                                    cardPointsAdapter.addData(items.get(2));
                                    break;
                                default:
                            }

                            recyclerView.scrollToPosition(cardPointsAdapter.getItemCount() / 2);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        dialog.dismiss();
                    }

                    @Override
                    public void onError(VolleyError error) {
                        super.onError(error);
                        dialog.dismiss();
                        Toast.makeText(getContext(), "服务器连接失败", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onBefore() {
                        super.onBefore();
                        dialog = ProgressDialog.show(getContext(), "", "正在获取卡片信息...");
                    }

                });
    }

    private void showPoints(final View view) {
        XVolley.getInstance()
                .doPost()
                .url("http://193.112.44.141:80/citi/points/generalPoints")
                .addParam("userID", LogStateInfo.getInstance(getContext()).getUserID())
                .build()
                .execute(getContext(), new CallBack<String>() {
                    @Override
                    public void onSuccess(Context context, String response) {
                        System.out.println(response);
                        boolean logSuccess = false;
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            double generalPoints = jsonObject.getDouble("generalPoints");
                            TextView textView = (TextView) view.findViewById(R.id.textView_generalPoints_main);
                            textView.setText(String.format("%.1f", generalPoints));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(VolleyError error) {
                        super.onError(error);
                        Toast.makeText(getContext(), "服务器连接失败", Toast.LENGTH_LONG).show();
                    }
                });

        XVolley.getInstance()
                .doPost()
                .url("http://193.112.44.141:80/citi/points/availablePoints")
                .addParam("userID", LogStateInfo.getInstance(getContext()).getUserID())
                .build()
                .execute(getContext(), new CallBack<String>() {
                    @Override
                    public void onSuccess(Context context, String response) {
                        System.out.println(response);
                        boolean logSuccess = false;
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            double availablePoints = jsonObject.getDouble("availablePoints");
                            TextView textView = (TextView) view.findViewById(R.id.textView_availablePoints_main);
                            textView.setText(String.format("%.1f", availablePoints));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(VolleyError error) {
                        super.onError(error);
                        Toast.makeText(getContext(), "服务器连接失败", Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void tryLogin(final String account, String password) {
        XVolley.getInstance()
                .doPost()
                .url("http://193.112.44.141:80/citi/account/login")
                .addParam("phoneNum", account)
                .addParam("password", password)
                .build()
                .execute(getContext(), new CallBack<String>() {
                    @Override
                    public void onSuccess(Context context, String response) {
                        System.out.println(response);
                        boolean logSuccess = false;
                        try {
                            if (response.length() > 2) {
                                JSONObject jsonObject = new JSONObject(response);

                                String accountPhoneNum = jsonObject.getString("phoneNum");
                                String userID = jsonObject.getString("userID");

                                LogStateInfo.getInstance(getContext())
                                        .setAccount(account)
                                        .setUserID(userID);

                                logSuccess = true;
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        dialog.dismiss();

                        if (logSuccess) {
                            LogStateInfo.getInstance(getContext()).login();
                            Toast.makeText(getContext(), "登录成功", Toast.LENGTH_SHORT).show();
                            onResume();
                            getQuestionnaireInfo();
                        } else {
                            Toast.makeText(getContext(), "账号或密码错误", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(VolleyError error) {
                        super.onError(error);
                        dialog.dismiss();
                        Toast.makeText(getContext(), "服务器连接失败", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onBefore() {
                        super.onBefore();
                        dialog = ProgressDialog.show(getContext(), "", "登录中...");
                    }
                });
    }

    private void getQuestionnaireInfo() {
        XVolley.getInstance()
                .doPost()
                .url("http://193.112.44.141:80/citi/recommend/isInvestigated")
                .addParam("userID", LogStateInfo.getInstance(getContext()).getUserID())
                .build()
                .execute(getContext(), new CallBack<String>() {
                    @Override
                    public void onSuccess(Context context, String response) {
                        System.out.println(response);

                        boolean haveDone = false;
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            haveDone = jsonObject.getBoolean("status");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        if (!haveDone) {
                            Intent intentToQuestionnaire = new Intent(getContext(), QuestionnaireActivity.class);
                            startActivity(intentToQuestionnaire);
                        }
                    }

                    @Override
                    public void onError(VolleyError error) {
                        super.onError(error);
                        Toast.makeText(getContext(), "服务器连接失败", Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void getMsgVerification(String phone, final Button buttonMsg) {
        XVolley.getInstance()
                .doPost()
                .url("http://193.112.44.141:80/citi/account/getVCode")
                .addParam("phoneNum", phone)
                .build()
                .execute(getContext(), new CallBack<String>() {
                    @Override
                    public void onSuccess(Context context, String response) {
                        System.out.println(response);

                        boolean getMSg = false;
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            getMSg = jsonObject.getBoolean("status");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        if (getMSg) {
                            Toast.makeText(getActivity(), "验证码已发送", Toast.LENGTH_SHORT).show();

                            remainSecond = WAITING_TIME_FOR_EACH_MSG;
                            Message messageChangeSecond = new Message();
                            messageChangeSecond.what = 0;
                            new Handler() {
                                @Override
                                public void handleMessage(Message message) {
                                    switch (message.what) {
                                        case 0:
                                            if (remainSecond == WAITING_TIME_FOR_EACH_MSG) {
                                                buttonMsg.setEnabled(false);
                                            }
                                            remainSecond--;
                                            buttonMsg.setText(remainSecond + "秒后重试");
                                            if (remainSecond != 0) {
                                                Message message1 = new Message();
                                                message1.what = 0;
                                                this.sendMessageDelayed(message1, 1000);
                                            } else {
                                                buttonMsg.setText("获取验证码");
                                                buttonMsg.setEnabled(true);
                                            }
                                    }
                                }
                            }.sendMessage(messageChangeSecond);
                        } else {
                            Toast.makeText(getContext(), "获取验证码失败", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(VolleyError error) {
                        super.onError(error);
                        dialog.dismiss();
                        Toast.makeText(getContext(), "服务器连接失败", Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void checkMsgVerification(String strPhone, String strMsg){
        verifyVCode(strPhone,strMsg);

    }

    private void verifyVCode(final String strAccount, final String vCode){
        XVolley.getInstance()
                .doPost()
                .url("http://193.112.44.141:80/citi/account/vfcode")
                .addParam("phoneNum", strAccount)
                .addParam("vcode", vCode)
                .build()
                .execute(getContext(), new CallBack<String>() {
                    @Override
                    public void onSuccess(Context context, String response) {
                        System.out.println(response);
                        boolean registerSuccess = false;
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            registerSuccess = jsonObject.getBoolean("status");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        if (registerSuccess) {
                            //Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                            //tryLogin(strAccount, strPassword);
                            accountInfoLayout.removeAllViewsInLayout();
                            loadRegisterContent2();
                        }else {
                            Toast.makeText(getContext(), "验证码错误，请重新输入", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(VolleyError error) {
                        super.onError(error);
                        Toast.makeText(getContext(), "服务器连接失败", Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void tryRegister(final String phoneNum, final String password){
        XVolley.getInstance()
                .doPost()
                .url("http://193.112.44.141:80/citi/account/resetPassword")
                .addParam("phoneNum", phoneNum)
                .addParam("newPassword", password)
                .build()
                .execute(getContext(), new CallBack<String>() {
                    @Override
                    public void onSuccess(Context context, String response) {
                        System.out.println(response);
                        boolean registerSuccess = false;
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            registerSuccess = jsonObject.getBoolean("status");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        if (registerSuccess) {
                            Toast.makeText(getContext(), "注册成功", Toast.LENGTH_SHORT).show();
                            tryLogin(newAccount, password);

                        }else {
                            Toast.makeText(getContext(), "注册失败", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(VolleyError error) {
                        super.onError(error);
                        Toast.makeText(getContext(), "服务器连接失败", Toast.LENGTH_LONG).show();
                    }
                });
    }
}
