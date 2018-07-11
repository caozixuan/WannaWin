package com.citiexchangeplatform.pointsleague;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class PointsFragment extends Fragment {

    boolean isLogin;

    View view;
    LinearLayout accountInfoLayout;
    ProgressDialog dialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_points, null);
        accountInfoLayout = (LinearLayout)view.findViewById(R.id.linearlayout_account_info_points);

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
        final List<String> descriptions = new ArrayList<>();
        imageUrls.add("http://m.360buyimg.com/mobilecms/s300x98_jfs/t2416/102/20949846/13425/a3027ebc/55e6d1b9Ne6fd6d8f.jpg");
        imageUrls.add("http://m.360buyimg.com/mobilecms/s300x98_jfs/t1507/64/486775407/55927/d72d78cb/558d2fbaNb3c2f349.jpg");
        imageUrls.add("http://m.360buyimg.com/mobilecms/s300x98_jfs/t1363/77/1381395719/60705/ce91ad5c/55dd271aN49efd216.jpg");
        descriptions.add("新品推荐");
        descriptions.add("时尚男装");
        descriptions.add("家电秒杀");

        for (int i = 0; i < imageUrls.size(); i++) {
            //新建三个展示View，并且添加到SliderLayout
            TextSliderView tsv = new TextSliderView(getActivity());
            tsv.image(imageUrls.get(i)).description(descriptions.get(i));
            final int finalI = i;
            tsv.setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                @Override
                public void onSliderClick(BaseSliderView slider) {
                    Toast.makeText(getActivity(), descriptions.get(finalI), Toast.LENGTH_SHORT).show();
                }
            });
            sliderLayout.addSlider(tsv);
        }

        //对SliderLayout自定义配置
        sliderLayout.setCustomAnimation(new DescriptionAnimation());
        sliderLayout.setPresetTransformer(SliderLayout.Transformer.Default);
        sliderLayout.setDuration(3000);
        //      sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        sliderLayout.setCustomIndicator(indicator);
    }


    private void loadAccountInfo(){
        accountInfoLayout.removeAllViewsInLayout();
        isLogin = LogStateInfo.getInstance(getContext()).isLogin();

        if(isLogin){
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
            Button buttonAddCard = (Button) content.findViewById(R.id.button_add_card_main);
            buttonAddCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intentToAddCard = new Intent(getActivity(), AddCardActivity.class);
                    startActivity(intentToAddCard);
                }
            });
            TextView textViewGeneralPoint = (TextView) content.findViewById(R.id.textView_all_points_main);
            textViewGeneralPoint.setText("当前通用积分 : " + LogStateInfo.getInstance(getContext()).getGeneralPoint());
            TextView textViewAvailablePoint = (TextView) content.findViewById(R.id.textView_remain_points_main);
            textViewAvailablePoint.setText("剩余可抵积分 : " + LogStateInfo.getInstance(getContext()).getAvailablePoints());

        }else {

            View content = LayoutInflater.from(getContext()).inflate(R.layout.content_login_button_points, null);
            accountInfoLayout.addView(content);

            Button button = (Button)view.findViewById(R.id.button_login_points);
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
        if(LogStateInfo.getInstance(getContext()).isLogin() != isLogin)
        {
            loadAccountInfo();
        }

        loadCardsInfo();

    }

    private void loadCardsInfo(){
        if(isLogin){
            dialog = ProgressDialog.show(getContext(),"","正在获取卡片信息...");
            new Thread(new getCardsInfo()).start();
        }
    }


    class getCardsInfo implements Runnable {

        @Override
        public void run() {
            boolean getInfoSuccess = false;

            HttpURLConnection connection = null;
            BufferedReader reader = null;
            try {
                URL url = new URL("http://193.112.44.141:80/citi/mscard/infos");
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                DataOutputStream out = new DataOutputStream(connection.getOutputStream());
                out.writeBytes("userId=" + LogStateInfo.getInstance(getContext()).getUserID() +"&n=20");
                connection.setConnectTimeout(5000);
                connection.setReadTimeout(5000);

                InputStream in = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(in));
                String json = reader.readLine();
                System.out.println(json);

                JSONArray jsonArray = new JSONArray(json);
                for(int i = 0; i < jsonArray.length(); i++){
                    JSONObject jobj = jsonArray.getJSONObject(i);
                    String cardID = jobj.getString("cardID");
                    String cardNo = jobj.getString("card_No");
                    int points = jobj.getInt("points");
                    System.out.println(cardID+ " "+cardNo+" "+points);
                }
            } catch (Exception e) {
                e.printStackTrace();

            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (connection != null) {
                    connection.disconnect();
                }
            }
            dialog.dismiss();
        }
    }
}
