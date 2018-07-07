package com.citiexchangeplatform.pointsleague;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.method.KeyListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class PayingAdapter extends RecyclerView.Adapter<PayingAdapter.MyViewHolder> {
    //数据源
    private List<String> list;
    private Context context;
    //是否显示单选框,默认false
    private boolean isshowBox = false;

    private HashMap<Integer, Boolean> map = new HashMap<>();
    private ButtonInterface buttonInterface;

    KeyListener storedKeylistener;



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

    /**
     *按钮点击事件需要的方法
     */
    public void buttonSetOnclick(ButtonInterface buttonInterface){
        this.buttonInterface=buttonInterface;
    }

    /**
     * 按钮点击事件对应的接口
     */
    public interface ButtonInterface{
        public void onclick( View view,int position);
    }


    @Override
    public PayingAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        /*列表布局*/
        PayingAdapter.MyViewHolder holder = new PayingAdapter.MyViewHolder(LayoutInflater.from(
                context).inflate(R.layout.item_paying, parent, false));
        return holder;
    }

    /*为列表内容配置数据*/
    @Override
    public void onBindViewHolder(final PayingAdapter.MyViewHolder holder, final int position) {
        holder.Points_Possession.setText(list.get(position));

        holder.Button_Modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(buttonInterface!=null) {
                  //接口实例化后的而对象，调用重写后的方法
                    buttonInterface.onclick(v,position);
                }

            }
        });

        //设置checkBox改变监听
        holder.Checkbox_Choose.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //用map集合保存
                map.put(position, isChecked);
                if (map.get(position)) {
                    holder.Button_Modify.setVisibility(View.VISIBLE);
                } else {
                    holder.Button_Modify.setVisibility(View.INVISIBLE);
                }
            }
        });


        // 设置CheckBox的状态
        if (map.get(position) == null) {
            map.put(position, false);
        }
        holder.Checkbox_Choose.setChecked(map.get(position));
        if (map.get(position)) {
            holder.Button_Modify.setVisibility(View.VISIBLE);
        } else {
            holder.Button_Modify.setVisibility(View.INVISIBLE);
        }


    }

    /*返回列表长度*/
    @Override
    public int getItemCount() {
        return list.size();
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
    public HashMap<Integer, Boolean> getMap() {
        return map;
    }


    /**
     * ViewHolder类，用于缓存控件
     */
    class MyViewHolder extends RecyclerView.ViewHolder {
        View view;
        TextView Points_Possession;
        ImageView Business_Image;
        CheckBox Checkbox_Choose;
        Button Button_Modify;
        EditText EditText_Points_Posses;



        public MyViewHolder(View view) {
            super(view);
            Points_Possession = (TextView) view.findViewById(R.id.textview_points_transfer);
            Business_Image = (ImageView) view.findViewById(R.id.image_business);
            Checkbox_Choose = (CheckBox) view.findViewById(R.id.checkbox_choose);
            Button_Modify = (Button) view.findViewById(R.id.button_modify);
            EditText_Points_Posses = (EditText) view.findViewById(R.id.editText_points_posses);

            // 保存默认的KeyListener以便恢复
            storedKeylistener = EditText_Points_Posses.getKeyListener();
        }


    }
}