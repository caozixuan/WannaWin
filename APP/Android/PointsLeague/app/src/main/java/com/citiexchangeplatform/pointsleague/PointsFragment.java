package com.citiexchangeplatform.pointsleague;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

import java.util.ArrayList;
import java.util.List;

public class PointsFragment extends Fragment {

    View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_points, null);

        initImageSlider();

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    /**
     * 初始化首页的商品广告条
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

        //对SliderLayout进行一些自定义的配置
        sliderLayout.setCustomAnimation(new DescriptionAnimation());
        sliderLayout.setPresetTransformer(SliderLayout.Transformer.Default);
        sliderLayout.setDuration(3000);
        //      sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        sliderLayout.setCustomIndicator(indicator);
    }
}
