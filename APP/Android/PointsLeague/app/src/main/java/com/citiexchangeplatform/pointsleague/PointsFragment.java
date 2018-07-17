package com.citiexchangeplatform.pointsleague;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.leochuan.ScaleLayoutManager;
import com.study.xuan.xvolleyutil.base.XVolley;
import com.study.xuan.xvolleyutil.callback.CallBack;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class PointsFragment extends Fragment {

    boolean isLogin;

    View view;
    LinearLayout accountInfoLayout;
    ProgressDialog dialog;

    RecyclerView recyclerView;
    CardPointsAdapter cardPointsAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_points, null);
        accountInfoLayout = (LinearLayout) view.findViewById(R.id.linearlayout_account_info_points);

        initImageSlider();
        loadAccountInfo();

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    /**
     * 初始化推广轮播内容
     */
    private void initImageSlider() {

        SliderLayout sliderLayout = (SliderLayout) view.findViewById(R.id.slider_main);
        PagerIndicator indicator = (PagerIndicator) view.findViewById(R.id.custom_indicator_main);

        //准备好要显示的数据
        List<String> imageUrls = new ArrayList<>();
        imageUrls.add("http://www.never-give-it-up.top/wp-content/uploads/2018/07/pic1.png");
        imageUrls.add("http://www.never-give-it-up.top/wp-content/uploads/2018/07/pic2.png");
        imageUrls.add("http://www.never-give-it-up.top/wp-content/uploads/2018/07/pic3.png");


        for (int i = 0; i < imageUrls.size(); i++) {
            DefaultSliderView sv = new DefaultSliderView(getActivity());
            sv.image(imageUrls.get(i));
            sv.setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                @Override
                public void onSliderClick(BaseSliderView slider) {
                    Toast.makeText(getActivity(), "~", Toast.LENGTH_SHORT).show();
                }
            });
            sliderLayout.addSlider(sv);
        }

        //对SliderLayout自定义配置
        sliderLayout.setCustomAnimation(new DescriptionAnimation());
        sliderLayout.setPresetTransformer(SliderLayout.Transformer.Default);
        sliderLayout.setDuration(3000);
        sliderLayout.setCustomIndicator(indicator);
    }


    private void loadAccountInfo() {
        accountInfoLayout.removeAllViewsInLayout();
        isLogin = LogStateInfo.getInstance(getContext()).isLogin();

        if (isLogin) {
            View content = LayoutInflater.from(getContext()).inflate(R.layout.content_cards_points, null);
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

            cardPointsAdapter = new CardPointsAdapter(getContext());
            recyclerView = (RecyclerView) content.findViewById(R.id.recyclerView_cards_points);
            //LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(
                    new ScaleLayoutManager
                    .Builder(getContext(),2)
                    .setOrientation(OrientationHelper. HORIZONTAL)
                    .build());
            //layoutManager.setOrientation(OrientationHelper. HORIZONTAL);
            recyclerView.setAdapter(cardPointsAdapter);
            recyclerView.setItemAnimator( new DefaultItemAnimator());

            cardPointsAdapter.addData("nike", 99, 2, "123");
            cardPointsAdapter.addData("apple", 129, 1, "123");
            cardPointsAdapter.addData("中国移动", 200, 3, "123");
            cardPointsAdapter.addData("中国电信", 77, 0.5, "123");
            cardPointsAdapter.addData("Anta", 233, 1, "123");

            recyclerView.scrollToPosition(cardPointsAdapter.getItemCount()/2);

        } else {

            View content = LayoutInflater.from(getContext()).inflate(R.layout.content_login_button_points, null);
            accountInfoLayout.addView(content);

            Button button = (Button) view.findViewById(R.id.button_login_points);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }
            });
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (LogStateInfo.getInstance(getContext()).isLogin() != isLogin) {
            loadAccountInfo();
        }

        loadCardsInfo();

    }

    private void loadCardsInfo() {
        if (isLogin) {
            getCardInfo();
        }
    }

    private void getCardInfo() {
        XVolley.getInstance()
                .doPost()
                .url("http://193.112.44.141:80/citi/mscard/infos")
                .addParam("userId", LogStateInfo.getInstance(getContext()).getUserID())
                .addParam("n", "3")
                .build()
                .execute(getContext(), new CallBack<String>() {
                    @Override
                    public void onSuccess(Context context, String response) {
                        System.out.println(response);
                        boolean logSuccess = false;
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jobj = jsonArray.getJSONObject(i);
                                String cardID = jobj.getString("cardID");
                                String merchantId = jobj.getString("merchantId");
                                int points = jobj.getInt("points");
                                System.out.println(cardID + " " + merchantId + " " + points);
                            }
                            //显示前n

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
}
