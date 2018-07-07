package com.citiexchangeplatform.pointsleague;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PayingActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<String> data_posses_point;
    private PayingAdapter mAdapter;
    private TextView Text_NeedPoints;
    // 存储勾选框状态的map集合
    private HashMap<Integer, Boolean> map = new HashMap<>();


    //接口实例
    //private RecyclerViewOnItemClickListener onItemClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paying);


        Text_NeedPoints = (TextView)findViewById(R.id.textView_points_need);
        //通过findViewById拿到RecyclerView实例
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_points);

        //初始化数据
        initData();

        //设置RecyclerView管理器
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter = new PayingAdapter(data_posses_point,getApplicationContext()));


    }




    /*按钮点击事件*/
    public void modify_click(View view){
        Button Modify_Button = (Button) findViewById(R.id.button_modify);


    }

    /*按钮点击事件*/
    public void click_finish(View view){
        Button Finish_Button = (Button) findViewById(R.id.button_finish);
        Intent intent = new Intent(this, PaymentFinishActivity.class);

        Bundle bundle = new Bundle();


        //intent.putExtra("value", (Serializable)map);

        SerializableHashMap myMap=new SerializableHashMap();
        myMap.setMap(map);//将hashmap数据添加到封装的myMap中

        bundle.putSerializable("checkbox_map", myMap);
        intent.putExtras(bundle);

        startActivity(intent);

    }

    /*获得各项积分数据：商家图标、积分数*/
    protected void initData()
    {
        //设置需要的积分数
        Text_NeedPoints.setText("120p");

        //设置列表项中的文字（用户拥有的积分数）
        data_posses_point = new ArrayList<String>();
        for (int i = 'A'; i < 'z'; i++)
        {
            data_posses_point.add("" + (char) i);
        }
    }


    class PayingAdapter extends RecyclerView.Adapter<PayingAdapter.MyViewHolder>
    {
        //数据源
        private List<String> list;
        private Context context;
        //是否显示单选框,默认false
        private boolean isshowBox = false;


        //构造方法
        public PayingAdapter(List<String> list, Context context) {
            this.list = list;
            this.context = context;
            initMap();
        }

        //初始化map集合,默认为不选中
        private void initMap() {
            for (int i = 0; i < list.size(); i++) {
                map.put(i, false);
            }
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            /*列表布局*/
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                    PayingActivity.this).inflate(R.layout.item_paying, parent,
                    false));
            return holder;
        }

        /*为列表内容配置数据*/
        @Override
        public void onBindViewHolder(MyViewHolder holder, final int position)
        {
            holder.Points_Possession.setText(data_posses_point.get(position));
            //holder.Business_Image.setImageBitmap();

            /*Animation animation = AnimationUtils.loadAnimation(context, R.anim.list_anim);
            //设置checkBox显示的动画
            if (isshowBox)
                holder.Checkbox_Choose.startAnimation(animation);*/
            //设置Tag
            //holder.view.setTag(position);
            //设置checkBox改变监听
            holder.Checkbox_Choose.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    //用map集合保存
                    map.put(position, isChecked);
                }
            });
            // 设置CheckBox的状态
            if (map.get(position) == null) {
                map.put(position, false);
            }
            holder.Checkbox_Choose.setChecked(map.get(position));
        }

        /*返回列表长度*/
        @Override
        public int getItemCount()
        {
            return data_posses_point.size();
        }

        //设置是否显示CheckBox
        public void setShowBox() {
            //取反
            isshowBox = !isshowBox;
        }

        //点击item选中CheckBox
        public void setSelectItem(int position) {
            //对当前状态取反
            if (map.get(position)) {
                map.put(position, false);
            } else {
                map.put(position, true);
            }
            notifyItemChanged(position);
        }

        //返回集合给MainActivity
        public Map<Integer, Boolean> getMap() {
            return map;
        }



        class MyViewHolder extends RecyclerView.ViewHolder
        {
            View view;
            TextView Points_Possession;
            ImageView Business_Image;
            CheckBox Checkbox_Choose;

            public MyViewHolder(View view)
            {
                super(view);
                Points_Possession = (TextView) view.findViewById(R.id.textview_points_posses);
                Business_Image = (ImageView) view.findViewById(R.id.image_business);
                Checkbox_Choose = (CheckBox) view.findViewById(R.id.checkbox_choose);
            }
        }
    }
}
