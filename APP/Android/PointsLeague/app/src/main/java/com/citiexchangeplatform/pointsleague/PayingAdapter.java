package com.citiexchangeplatform.pointsleague;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.KeyListener;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import com.citiexchangeplatform.pointsleague.models.ExchangeModel;

import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class PayingAdapter extends RecyclerView.Adapter<PayingAdapter.MyViewHolder>implements Filterable {
    //数据源
    //private List<String> maxExchangePoints;
    //private List<String> exchangePoints;
    //private List<String> targetPoints;
    //private List<Double> rates;
    //private List<String> names;
    //private List<String> logos;
    //private List<Integer> img_list;

    private List<ExchangeModel> sourceItems;
    private List<ExchangeModel> filteredItems;
    private Context context;
    private PayingAdapter.MyViewHolder viewHolder;
    //是否显示单选框,默认false
    //private boolean isshowBox = false;
    private double total;
    private String totalPoints;

    private HashMap<Integer, Boolean> map = new HashMap<>();
    private HashMap<Integer, Double> totals = new HashMap<>();
    private ButtonInterface buttonInterface;
    private CheckBoxInterface checkBoxInterface;
    private EditTextInterface editTextInterface;

    KeyListener storedKeylistener;



    //构造方法
    public PayingAdapter(Context context) {

        //maxExchangePoints = new ArrayList<String>();
        //exchangePoints = new ArrayList<String>();
        //targetPoints = new ArrayList<String>();
        //rates = new ArrayList<Double>();
        //names = new ArrayList<String>();
        //logos = new ArrayList<String>();
        //img_list = new ArrayList<Integer>();

        sourceItems = new ArrayList<ExchangeModel>();
        filteredItems = sourceItems;
        this.context = context;
        this.total = 0;
        initMap();
    }

    public void addData(int posses, double target,String merchantID, double rate, String name, String logoURL) {
        //maxExchangePoints.add(posses);
        //exchangePoints.add(posses);
        //targetPoints.add(target);
        //rates.add(Double.parseDouble(rate));
        //names.add(totalPoints);
        //logos.add(logoURL);

        ExchangeModel newItem = new ExchangeModel(false, posses, target , name, merchantID, rate, logoURL);
        sourceItems.add(newItem);
        notifyDataSetChanged();
    }

    //初始化map集合,默认为不选中
    private void initMap() {
        for (int i = 0; i < 100; i++) {
            map.put(i, false);
        }
    }




    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String partName = charSequence.toString();
                if (partName.isEmpty()) {
                    filteredItems = sourceItems;
                } else {
                    List<ExchangeModel> newFilterCards = new ArrayList<ExchangeModel>();
                    for (ExchangeModel item:sourceItems) {
                        System.out.println(item.getName().toLowerCase() + "    " + partName.toLowerCase());
                        if(item.getName().toLowerCase().contains(partName.toLowerCase()))
                            newFilterCards.add(item);
                    }
                    filteredItems = newFilterCards;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredItems;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filteredItems = (ArrayList<ExchangeModel>) filterResults.values;
                //刷新数据
                notifyDataSetChanged();
            }
        };
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

    /**
     *editText监听接口
     */
    public void editTextComplete(EditTextInterface editTextInterface){
        this.editTextInterface = editTextInterface;
    }

    /**
     * checkbox点击事件对应的接口
     */
    public interface EditTextInterface{
        public void onComplete(View view, int position);
    }


    /**
     *checkbox点击事件需要的方法
     */
    public void checkBoxSetOnclick(CheckBoxInterface checkBoxInterface){
        this.checkBoxInterface=checkBoxInterface;
    }

    /**
     * checkbox点击事件对应的接口
     */
    public interface CheckBoxInterface{
        public void onclick( View view,int position);
    }





    @NonNull
    @Override
    public PayingAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        /*列表布局*/
        viewHolder = new MyViewHolder(LayoutInflater.from(
                context).inflate(R.layout.item_paying, parent, false));
        return new MyViewHolder(LayoutInflater.from(
                context).inflate(R.layout.item_paying, parent, false));
    }

    /*为列表内容配置数据*/
    @Override
    public void onBindViewHolder(@NonNull final PayingAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        viewHolder = holder;
        holder.setIsRecyclable(false);
        //设置列表中积分信息
        holder.editPoint.setText(String.valueOf(filteredItems.get(position).getExchangePoint()));
        //holder.editPoint.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        holder.editPoint.setInputType(InputType.TYPE_CLASS_NUMBER);
        holder.exchangePoint.setText(String.valueOf(filteredItems.get(position).getTargetPoint()));
        //设置商家图片
        /*Glide.with(context)
                .load(filteredItems.get(position).getLogo())
                .placeholder(R.drawable.ic_points_black_24dp)
                .error(R.drawable.ic_mall_black_24dp)
                .override(60,60)
                .into(holder.logo);*/
        //holder.logo.setImageResource(img_list.get(position));
        //设置商户名
        holder.name.setText(filteredItems.get(position).getName());
        //初始编辑框为不可编辑状态
        holder.editPoint.setFocusable(false);
        holder.editPoint.setFocusableInTouchMode(false);

        /*holder.modifyButton.setOnClickListener(new View.OnClickListener() {
            Boolean button_status = false;
            @Override
            public void onClick(View v) {
                if(!button_status){
                    holder.modifyButton.setBackgroundResource(R.drawable.ic_check_24dp);
                    //编辑框可修改
                    holder.editPoint.setFocusableInTouchMode(true);
                    holder.editPoint.setFocusable(true);
                    holder.editPoint.requestFocus();
                    button_status = true;
                }

                else{
                    holder.modifyButton.setBackgroundResource(R.drawable.ic_modify_24dp);
                    holder.editPoint.setFocusable(false);
                    holder.editPoint.setFocusableInTouchMode(false);
                    button_status = false;
                }

                if(buttonInterface!=null) {
                    //接口实例化后的而对象，调用重写后的方法
                    buttonInterface.onclick(v,position);
                }

            }
        });*/

        holder.editPoint.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                /*判断是否是“GO”键*/
                if(actionId == EditorInfo.IME_ACTION_DONE){

                    /*隐藏软键盘*/

                    InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    if(imm.isActive()) {
                        imm.hideSoftInputFromWindow(v.getApplicationWindowToken(),0);
                    }
                    if (editTextInterface != null) {
                        //接口实例化后的而对象，调用重写后的方法
                        editTextInterface.onComplete(v, position);
                    }

                    return true;
                }
                return false;
            }
        });

        holder.Checkbox_Choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(checkBoxInterface!=null) {
                    //接口实例化后的而对象，调用重写后的方法
                    checkBoxInterface.onclick(v,position);
                }

            }
        });

        //设置checkBox改变监听
        holder.Checkbox_Choose.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //Boolean previous_status = map.get(position);
                //Boolean previous_status = filteredItems.get(position).getChoose();
                //用map集合保存
                map.put(position, isChecked);
                filteredItems.get(position).setChoose(isChecked);

                //计算选择的积分可转换成的通用积分
                //double add_point = Double.parseDouble(holder.exchangePoint.getText().toString());

                //if(map.get(position)){
                //if(filteredItems.get(position).getChoose()){
                //    if(!totals.containsKey(position))
                //     totals.put(position,add_point);
                //    total = 0;
                //    for (Integer key : totals.keySet()) {
                //        total += totals.get(key);
                //    }
                //}
                //else if(previous_status&&!filteredItems.get(position).getChoose()&&totals.containsKey(position)){
                //    totals.remove(position);
                //    total = 0;
                //    for (Integer key : totals.keySet()) {
                //        total += totals.get(key);
                //    }
                //    //total -= add_point;
                //}

                total = 0;
                for(int i = 0;i<sourceItems.size();i++){
                    if(sourceItems.get(i).getChoose()){
                        total += sourceItems.get(i).getTargetPoint();
                    }
                }

                if (filteredItems.get(position).getChoose()) {
                    //holder.modifyButton.setVisibility(View.VISIBLE);
                    holder.editPoint.setBackgroundResource(R.drawable.bg_edit_points_focused);
                    //编辑框可修改
                    holder.editPoint.setFocusableInTouchMode(true);
                    holder.editPoint.setFocusable(true);
                    holder.editPoint.requestFocus();


                } else {
                    //holder.modifyButton.setVisibility(View.INVISIBLE);
                    holder.editPoint.setBackgroundResource(R.drawable.bg_edit_points);
                    //取消选择后编辑框为不可编辑状态
                    holder.editPoint.setFocusable(false);
                    holder.editPoint.setFocusableInTouchMode(false);
                    holder.editPoint.setText(String.valueOf(sourceItems.get(position).getMaxExchangePoint()));

                }

            }
        });


        // 设置CheckBox的状态
        if (filteredItems.get(position).getChoose() == null) {
            map.put(position, false);
            filteredItems.get(position).setChoose(false);
        }
        holder.Checkbox_Choose.setChecked(filteredItems.get(position).getChoose());
        if (filteredItems.get(position).getChoose()) {
            //holder.modifyButton.setVisibility(View.VISIBLE);
            //编辑框可修改
            holder.editPoint.setFocusableInTouchMode(true);
            holder.editPoint.setFocusable(true);
            holder.editPoint.requestFocus();

        } else {
            //holder.modifyButton.setVisibility(View.INVISIBLE);
            //编辑框可修改
            holder.editPoint.setFocusableInTouchMode(false);
            holder.editPoint.setFocusable(false);
            holder.editPoint.requestFocus();

        }


        if (holder.editPoint.getTag() instanceof TextWatcher) {
            holder.editPoint.removeTextChangedListener((TextWatcher) holder.editPoint.getTag());
        }

        //编辑框中输入文字监听
        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                double rate = filteredItems.get(position).getRate();
                double exchangedPoint = 0;
                if(s.length()!=0){
                    if(Double.parseDouble(s.toString())<=0){
                        exchangedPoint = 0;
                        filteredItems.get(position).setExchangePoint(0);
                        Toast.makeText(context, "输入不得小于0", Toast.LENGTH_SHORT).show();

                    }
                    else {
                        exchangedPoint = Double.parseDouble(s.toString())*rate;
                        filteredItems.get(position).setExchangePoint(Integer.parseInt(s.toString()));

                        //超出最大值，自动更新为最大值
                        double max = filteredItems.get(position).getMaxExchangePoint();
                        if(Double.parseDouble(s.toString()) > max){

                            exchangedPoint = max * rate;

                            filteredItems.get(position).setExchangePoint(filteredItems.get(position).getMaxExchangePoint());
                            holder.editPoint.setText(String.valueOf(filteredItems.get(position).getMaxExchangePoint()));
                            Toast.makeText(context, "超出最大值，已自动更新为最大值", Toast.LENGTH_SHORT).show();
                        }

                    }

                }
                if(s.length()==0){
                    filteredItems.get(position).setExchangePoint(0);
                }


                //保留两位小数
                NumberFormat nf = NumberFormat.getNumberInstance();
                // 保留两位小数
                nf.setMaximumFractionDigits(2);
                // 如果不需要四舍五入，可以使用RoundingMode.DOWN
                nf.setRoundingMode(RoundingMode.UP);
                String result = nf.format(exchangedPoint);

                filteredItems.get(position).setTargetPoint(exchangedPoint);
                //targetPoints.set(position,String.valueOf(exchangedPoint));
                holder.exchangePoint.setText(result);
                //notifyItemChanged(position);


            }

            @Override
            public void afterTextChanged(Editable s) {
                holder.editPoint.removeTextChangedListener(this);
                double rate = filteredItems.get(position).getRate();
                double exchangedPoint = 0;
                if(s.length()!=0){
                    if(Double.parseDouble(s.toString())<0){
                        exchangedPoint = 0;
                        filteredItems.get(position).setExchangePoint(0);
                        Toast.makeText(context, "输入不得小于0", Toast.LENGTH_SHORT).show();

                    }
                    else {
                        exchangedPoint = Double.parseDouble(s.toString())*rate;
                        filteredItems.get(position).setExchangePoint(Integer.parseInt(s.toString()));

                        //超出最大值，自动更新为最大值
                        double max = filteredItems.get(position).getMaxExchangePoint();
                        if(Double.parseDouble(s.toString()) > max){

                            exchangedPoint = max * rate;
                            filteredItems.get(position).setExchangePoint(filteredItems.get(position).getMaxExchangePoint());
                            holder.editPoint.setText(String.valueOf(filteredItems.get(position).getMaxExchangePoint()));
                            Toast.makeText(context, "超出最大值，已自动更新为最大值", Toast.LENGTH_SHORT).show();
                        }
                    }

                }

                else{
                    filteredItems.get(position).setExchangePoint(0);
                    holder.editPoint.setText("0");
                }


                //保留两位小数
                NumberFormat nf = NumberFormat.getNumberInstance();
                // 保留两位小数
                nf.setMaximumFractionDigits(2);
                // 如果不需要四舍五入，可以使用RoundingMode.DOWN
                nf.setRoundingMode(RoundingMode.UP);
                String result = nf.format(exchangedPoint);

                filteredItems.get(position).setTargetPoint(exchangedPoint);
                //filteredItems.get(position).setTargetPoint(Double.parseDouble(result));
                //targetPoints.set(position,String.valueOf(exchangedPoint));
                holder.exchangePoint.setText(result);


                //方法1
                //修改total值
                //double add_point = Double.parseDouble(holder.exchangePoint.getText().toString());
                //
                //if(totals.get(position)!=null){
                //    totals.put(position,add_point);
                //}
                //
                //total = 0;
                //for (Integer key : totals.keySet()) {
                //    total += totals.get(key);
                //}

                //方法2
                //total = 0;
                //for(int i = 0;i<sourceItems.size();i++){
                //    if(sourceItems.get(i).getChoose()){
                //        total += Double.parseDouble(sourceItems.get(i).getTargetPoint());
                //    }
                //}

                //notifyItemChanged(position);
                holder.editPoint.addTextChangedListener(this);
            }
        };

        holder.editPoint.addTextChangedListener(watcher);
        holder.editPoint.setTag(watcher);

        //编辑框中输入文字监听
        //holder.editPoint.addTextChangedListener(new TextWatcher() {
        //    @Override
        //    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        //        //double rate = rates.get(position);
        //        //double exchangedPoint = 0;
        //        //if(s.length()!=0){
        //        //    exchangedPoint = Double.parseDouble(s.toString())*rate;
        //        //}
        //        //
        //        //holder.exchangePoint.setText(String.valueOf(exchangedPoint));
        //
        //    }
        //
        //    @Override
        //    public void onTextChanged(CharSequence s, int start, int before, int count) {
        //        double rate = rates.get(position);
        //        double exchangedPoint = 0;
        //        if(s.length()!=0){
        //            exchangedPoint = Double.parseDouble(s.toString())*rate;
        //        }
        //
        //
        //        holder.exchangePoint.setText(String.valueOf(exchangedPoint));
        //
        //    }
        //
        //    @Override
        //    public void afterTextChanged(Editable s) {
        //        holder.editPoint.removeTextChangedListener(this);
        //        double rate = rates.get(position);
        //        double exchangedPoint = 0;
        //        if(s.length()!=0){
        //            exchangedPoint = Double.parseDouble(s.toString())*rate;
        //        }
        //        holder.exchangePoint.setText(String.valueOf(exchangedPoint));
        //        exchangePoints.set(position,s.toString());
        //        targetPoints.set(position,String.valueOf(exchangedPoint));
        //        //修改total值
        //        double add_point = Double.parseDouble(holder.exchangePoint.getText().toString());
        //
        //        if(totals.get(position)!=null){
        //            totals.put(position,add_point);
        //        }
        //
        //        total = 0;
        //        for (Integer key : totals.keySet()) {
        //            total += totals.get(key);
        //        }
        //
        //
        //        holder.editPoint.addTextChangedListener(this);
        //
        //    }
        //});


    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public List<ExchangeModel> getSourceItems() {
        return sourceItems;
    }

    public MyViewHolder getHolder() {
        return viewHolder;
    }

    //private void specialUpdate(final int item_position) {
    //    Handler handler = new Handler();
    //    final Runnable r = new Runnable() {
    //        public void run() {
    //            notifyItemChanged(item_position);
    //        }
    //    };
    //    handler.post(r);
    //}


    /*返回列表长度*/
    @Override
    public int getItemCount() {
        return filteredItems.size();
    }

    //设置是否显示CheckBox
    //public void setShowBox() {
    //    //取反
    //    isshowBox = !isshowBox;
    //}

    /*返回总计价格*/
    public double getTotal() {
        total = 0;
        for (int i = 0;i<sourceItems.size();i++){
            if(sourceItems.get(i).getChoose()){
                total += sourceItems.get(i).getTargetPoint();
            }
        }

        return total;
    }


    public String getTotalPoints() {
        getTotal();
        //保留两位小数
        NumberFormat nf = NumberFormat.getNumberInstance();
        // 保留两位小数
        nf.setMaximumFractionDigits(2);
        // 如果不需要四舍五入，可以使用RoundingMode.DOWN
        nf.setRoundingMode(RoundingMode.UP);
        String result = nf.format(total);

        return result;
    }


    public void notifyCheckBoxChange(){
        for (ExchangeModel model:sourceItems){
            model.setChoose(true);
        }
        notifyDataSetChanged();

    }

    //返回集合给MainActivity
    public HashMap<Integer, Double> getMap() {
        return totals;
    }


    /**
     * ViewHolder类，用于缓存控件
     */
    class MyViewHolder extends RecyclerView.ViewHolder {
        View view;
        //转换的通用积分
        TextView exchangePoint;
        //会员卡商家logo
        //ImageView logo;
        //选择框
        CheckBox Checkbox_Choose;
        //修改按钮
        //Button modifyButton;
        //输入使用的会员卡积分数
        EditText editPoint;
        //会员卡商户名
        TextView name;



        public MyViewHolder(View view) {
            super(view);
            exchangePoint = view.findViewById(R.id.textview_points_transfer);
            //logo = view.findViewById(R.id.image_business);
            Checkbox_Choose = view.findViewById(R.id.checkbox_choose);
            //modifyButton = view.findViewById(R.id.button_modify);
            editPoint = view.findViewById(R.id.editText_points_posses);
            name = view.findViewById(R.id.textview_business_name);

            // 保存默认的KeyListener以便恢复
            storedKeylistener = editPoint.getKeyListener();
        }

        public EditText getEditPoint() {
            return editPoint;
        }
    }
}