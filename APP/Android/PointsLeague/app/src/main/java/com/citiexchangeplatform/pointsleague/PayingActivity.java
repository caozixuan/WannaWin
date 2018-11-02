package com.citiexchangeplatform.pointsleague;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Build;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.text.method.KeyListener;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.citiexchangeplatform.pointsleague.models.ExchangeModel;
import com.citiexchangeplatform.pointsleague.models.ExchangeResultModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PayingActivity extends AppCompatActivity {

    private RecyclerView msCardRecyclerView;
    private PayingAdapter mAdapter;
    ProgressDialog dialog;

    TextView Choose_Points;
    Boolean state = true;

    //存放抵扣结果
    ArrayList<String> used = new ArrayList<>();
    //ArrayList<String> exchanged = new ArrayList<>();
    //ArrayList<String> logos = new ArrayList<>();
    ArrayList<String> names = new ArrayList<>();
    ArrayList<String> reasons = new ArrayList<>();

    KeyListener storedKeylistener;

    // 存储勾选框状态的map集合
    //private HashMap<Integer, Double> map = new HashMap<>();

    //接口实例
    //private RecyclerViewOnItemClickListener onItemClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paying);


        //设置toolbar
        //Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar_paying);
        //setSupportActionBar(mToolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        //获取页面中的元素
        initView();

        //设置RecyclerView管理器
        setRecyclerView();

        //获得初始化数据
        initData();

        toolBar();

        setSearchView();


    }

    public void setSearchView(){

        SearchView search = this.findViewById(R.id.editText_search_paying);

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mAdapter.getFilter().filter(newText);
                return true;
            }
        });

        search.setIconified(false);
        //搜索图标是否显示在搜索框内
        search.setIconifiedByDefault(false);

        //设置搜索框展开时是否显示提交按钮，可不显示
        search.setSubmitButtonEnabled(false);
        search.clearFocus();
        search.clearFocus();

        //让键盘的回车键设置成搜索
        //search.setImeOptions(EditorInfo.IME_ACTION_SEARCH);

        //隐藏下划线
        try {        //--拿到字节码
            Class<?> argClass = search.getClass();
            //--指定某个私有属性,mSearchPlate是搜索框父布局的名字
            Field ownField = argClass.getDeclaredField("mSearchPlate");
            //--暴力反射,只有暴力反射才能拿到私有属性
            ownField.setAccessible(true);
            View mView = (View) ownField.get(search);
            //--设置背景
            mView.setBackgroundColor(Color.TRANSPARENT);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void toolBar(){
        boolean isImmersive = false;
        //if (hasKitKat() && !hasLollipop()) {
        //    isImmersive = true;
        //    //透明状态栏
        //    getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //    //透明导航栏
        //    //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        //}
        //else if (hasLollipop()) {
        //    Window window = getWindow();
        //    window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
        //            | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        //    window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        //            //| View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
        //            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        //    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        //    window.setStatusBarColor(Color.TRANSPARENT);
        //    isImmersive = true;
        //}
        //if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ) {//android6.0以后可以对状态栏文字颜色和图标进行修改
        //    getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        //}

        int toolBarHeight = UnitConverter.px2dip(getApplicationContext(),132);
        int actionTitleSize = UnitConverter.px2sp(getApplicationContext(),51);
        int titleSize = UnitConverter.px2sp(getApplicationContext(),51);

        final TitleBar titleBar = (TitleBar) findViewById(R.id.title_bar);
        titleBar.setDividerColor(Color.parseColor("#d3d3d3"));
        titleBar.setBackgroundColor(Color.WHITE);
        //设置字体大小
        //titleBar.setHeight(toolBarHeight*2);
        //titleBar.setTitleSize(titleSize);
        //titleBar.setLeftTextSize(actionTitleSize);
        //titleBar.setSubTitleSize(actionTitleSize);

        //左侧
        titleBar.setLeftImageResource(R.drawable.ic_left_orange_24dp);
        titleBar.setLeftText("首页");
        titleBar.setLeftTextColor(getResources().getColor(R.color.colorLightOrange));

        titleBar.setLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        titleBar.setTitle("兑换积分");
        titleBar.setTitleColor(Color.BLACK);

        titleBar.setActionTextColor(Color.BLACK);

        //右侧
        titleBar.addAction(new TitleBar.TextAction("全选") {
            @Override
            public void performAction(View view) {
                //for(int i = 0;i<mAdapter.getSourceItems().size();i++){
                //    mAdapter.getSourceItems().get(i).setChoose(true);
                //}

                mAdapter.notifyCheckBoxChange();

                Choose_Points.setText(mAdapter.getTotalPoints());

            }
        });

        //沉浸式
        //titleBar.setImmersive(true);
    }

    public static boolean hasKitKat() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
    }

    public static boolean hasLollipop() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }

    private void initView(){
        //会员卡列表
        msCardRecyclerView = (RecyclerView) findViewById(R.id.rv_points);
        //用户已选择积分
        Choose_Points = (TextView) findViewById(R.id.textview_points_choose);
    }

    /*获得各项积分卡数据：logo merchantName posses_points rate generalPoints*/
    protected void initData()
    {
        dialog = ProgressDialog.show(PayingActivity.this, "", "正在加载积分信息...");
        getMSCardInfoRequest();
        //mAdapter.addData("1000", "100","0.1","qwe","中国移动","http://www.never-give-it-up.top/wp-content/uploads/2018/07/apple_logo.png");
        //mAdapter.addData("2000","20","0.01","qwe","中国联通","http://www.never-give-it-up.top/wp-content/uploads/2018/07/yidong_logo.png");
        //mAdapter.addData("3000","30","0.01","qwe","Nike","http://www.never-give-it-up.top/wp-content/uploads/2018/07/nike_logo.png");
        //mAdapter.addData("1000", "100","0.1","qwe","中国移动","http://www.never-give-it-up.top/wp-content/uploads/2018/07/apple_logo.png");
        //mAdapter.addData("2000","20","0.01","qwe","中国联通","http://www.never-give-it-up.top/wp-content/uploads/2018/07/yidong_logo.png");
        //mAdapter.addData("3000","30","0.01","qwe","Nike","http://www.never-give-it-up.top/wp-content/uploads/2018/07/nike_logo.png");
        //mAdapter.addData("1000", "100","0.1","qwe","中国移动","http://www.never-give-it-up.top/wp-content/uploads/2018/07/apple_logo.png");
        //mAdapter.addData("2000","20","0.01","qwe","中国联通","http://www.never-give-it-up.top/wp-content/uploads/2018/07/yidong_logo.png");
        //mAdapter.addData("3000","30","0.01","qwe","Nike","http://www.never-give-it-up.top/wp-content/uploads/2018/07/nike_logo.png");
        //mAdapter.addData("1000", "100","0.1","qwe","中国移动","http://www.never-give-it-up.top/wp-content/uploads/2018/07/apple_logo.png");
        //mAdapter.addData("2000","20","0.01","qwe","中国联通","http://www.never-give-it-up.top/wp-content/uploads/2018/07/yidong_logo.png");
        //mAdapter.addData("3000","30","0.01","qwe","Nike","http://www.never-give-it-up.top/wp-content/uploads/2018/07/nike_logo.png");
        //mAdapter.addData("3000","30","0.01","qwe","Nike","http://www.never-give-it-up.top/wp-content/uploads/2018/07/nike_logo.png");
        //getMSCardInfoRequest();



    }

    protected void setRecyclerView(){
        msCardRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new PayingAdapter(PayingActivity.this);

        msCardRecyclerView.setAdapter(mAdapter);
        //添加Android自带的分割线
        //msCardRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

        mAdapter.buttonSetOnclick(new PayingAdapter.ButtonInterface() {
            @Override
            public void onclick(View view, int position) {
                //修改合计价格
                Choose_Points.setText(mAdapter.getTotalPoints());
                //Toast.makeText(getApplicationContext(), "点击条目上的按钮"+position, Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), "修改", Toast.LENGTH_SHORT).show();
            }
        });

        mAdapter.checkBoxSetOnclick(new PayingAdapter.CheckBoxInterface() {
            @Override
            public void onclick(View view, int position) {
                Choose_Points.setText(mAdapter.getTotalPoints());

            }
        });

        mAdapter.editTextComplete(new PayingAdapter.EditTextInterface(){
            @Override
            public void onComplete(View view, int position) {
                //修改合计价格
                Choose_Points.setText(mAdapter.getTotalPoints());
            }


        });

    }



    /*判断是否显示软键盘*/
    private boolean isSoftShowing() {
        //获取当前屏幕内容的高度
        int screenHeight = getWindow().getDecorView().getHeight();
        //获取View可见区域的bottom
        Rect rect = new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);

        return screenHeight - rect.bottom != 0;
    }


    /*确认抵扣按钮点击事件*/
    public void click_finish(View view){
        if(mAdapter.getTotalPoints().equals("0")){
            Toast.makeText(getApplicationContext(), "兑换积分为0，请重新选择", Toast.LENGTH_SHORT).show();
        }
        else{
            exchangePostJSON();
            List<ExchangeResultModel> list = new ArrayList<>();
            Intent intent = new Intent(PayingActivity.this, PaymentFinishActivity.class);
            Bundle bundle = new Bundle();
            if(state){

                //map = mAdapter.getMap();
                for (int i = 0;i<mAdapter.getSourceItems().size();i++){
                    if (mAdapter.getSourceItems().get(i).getChoose()){
                        //map.keySet()返回的是所有key的值
                        int usePoints = mAdapter.getSourceItems().get(i).getExchangePoint();
                        String merchantName = mAdapter.getSourceItems().get(i).getName();
                        ExchangeResultModel model = new ExchangeResultModel(null,merchantName,usePoints);
                        list.add(model);
                    }
                }

                bundle.putString("total",mAdapter.getTotalPoints());
                bundle.putParcelableArrayList("resultList", (ArrayList<? extends Parcelable>) list);
            }
            else {
                if (names!=null&&reasons!=null){

                    for (int i = 0;i<names.size();i++){
                        ExchangeResultModel model = new ExchangeResultModel(reasons.get(i),names.get(i),0);
                        list.add(model);

                    }
                }

                bundle.putParcelableArrayList("resultList", (ArrayList<? extends Parcelable>) list);
                //intent.putExtra("resultList", (Serializable) list);

            /*Intent intent = new Intent(PayingActivity.this, PaymentFinishActivity.class);
            Bundle bundle = new Bundle();

            bundle.putStringArrayList("reasons",reasons);
            bundle.putStringArrayList("logo_urls",logos);
            bundle.putStringArrayList("business_names",names);

            intent.putExtras(bundle);
            //
            startActivity(intent);*/

            }


            bundle.putBoolean("state",state);

            intent.putExtras(bundle);
            startActivity(intent);
            finish();
        }

    }

    public void exchangePostJSON() {
        //定义一个JSON，用于向服务器提交数据
        final JSONArray jsonArray = new JSONArray();
        JSONObject jsonObj = new JSONObject();
        try {
            for (int i = 0;i<mAdapter.getSourceItems().size();i++){
                if (mAdapter.getSourceItems().get(i).getChoose()){
                    JSONObject item = new JSONObject();
                    item.put("merchantID",mAdapter.getSourceItems().get(i).getMerchantID());
                    item.put("selectedMSCardPoints",mAdapter.getSourceItems().get(i).getExchangePoint());
                    jsonArray.put(item);
                }
            }

            jsonObj.put("userID", LogStateInfo.getInstance(PayingActivity.this).getUserID())
                    .put("merchants", jsonArray);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        final String jsonString = jsonObj.toString();
        System.out.println(jsonString);
        String url="http://193.112.44.141:80/citi/points/changePoints";
        RequestQueue queue = MyApplication.getHttpQueues();

        JsonArrayRequest jsonRequest = new JsonArrayRequest(Request.Method.POST, url, jsonObj,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.e("success",response.toString());
                        System.out.println("jsonRequest"+response.toString());

                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("#JsonObject...:Error#", error.toString());
                state = false;

                try {
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jObj = jsonArray.getJSONObject(i);

                        String merchantName = String.valueOf(jObj.getInt("merchantName"));
                        //String merchantLogoURL = String.valueOf(jObj.getInt("merchantLogoURL"));
                        String reason = String.valueOf(jObj.getString("reason"));
                        names.add(merchantName);
                        //logos.add(merchantLogoURL);
                        reasons.add(reason);

                    }

                }
                catch (JSONException e1) {
                    e1.printStackTrace();
                }
            }
        }){
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<String, String>();

                headers.put("Content-Type", "application/json");

                return headers;
            }
        };

        queue.add(jsonRequest);

    }

    private void getMSCardInfoRequest() {

        String url="http://193.112.44.141:80/citi/mscard/infos";
        RequestQueue queue = MyApplication.getHttpQueues();
        //RequestQueue queue=Volley.newRequestQueue(this);
        StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Log.e("success",s);
                System.out.println(s);
                try {
                    JSONArray jsonArray = new JSONArray(s);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jObj = jsonArray.getJSONObject(i);

                        int generalPoints = jObj.getInt("points");
                        double availablePoints = jObj.getInt("points")*jObj.getDouble("proportion");

                        //保留两位小数
                        NumberFormat nf = NumberFormat.getNumberInstance();
                        // 保留两位小数
                        nf.setMaximumFractionDigits(2);
                        // 如果不需要四舍五入，可以使用RoundingMode.DOWN
                        nf.setRoundingMode(RoundingMode.UP);
                        String result = nf.format(availablePoints);

                        if(jObj.getInt("points") > 0)
                            mAdapter.addData(generalPoints,availablePoints,jObj.getString("merchantID"),jObj.getDouble("proportion"),jObj.getString("merchantName"),jObj.getString("merchantLogoURL"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                dialog.dismiss();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                dialog.dismiss();
                Toast.makeText(PayingActivity.this, "服务器连接失败", Toast.LENGTH_LONG).show();

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map=new HashMap<>();
                map.put("userID",LogStateInfo.getInstance(PayingActivity.this).getUserID());
                map.put("n", "20");
                return map;
            }
        };
        queue.add(request);
    }

    private void postStringRequest() {
        String url="http://193.112.44.141:80/citi/merchant/getInfos";
        RequestQueue queue = MyApplication.getHttpQueues();
        //RequestQueue queue= Volley.newRequestQueue(this);
        StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Log.e("success",s);
                System.out.println(s);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map=new HashMap<>();
                map.put("start","0");
                map.put("n", "1");
                return map;
            }
        };
        queue.add(request);
    }



    private void postRequest() {
        //RequestQueue queue = VolleySingleton.getVolleySingleton(this.getApplicationContext()).getRequestQueue();
        String url="http://193.112.44.141:80/citi/merchant/getInfos";
        //RequestQueue queue= Volley.newRequestQueue(this);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,
                url, (String) null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println("json data===" + response.toString());
                JSONArray jsonArray = null;
                try {
                    jsonArray = new JSONArray(response.toString());
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String name= jsonObject.getString("totalPoints");
                        String merchantID= jsonObject.getString("merchantID");
                        String logoURL= jsonObject.getString("logoURL");
                        String description= jsonObject.getString("description");
                        String address= jsonObject.getString("address");

                        System.out.println(name);
                        MerchantInfo.getInstance(PayingActivity.this).setMerchantID(merchantID)
                                .setName(name)
                                .setLogoURL(logoURL)
                                .setDescription(description)
                                .setAddress(address);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map=new HashMap<>();

                map.put("start","0");
                map.put("n", "1");

                return map;
            }
        };

        //queue.add(request);
        //VolleySingleton.getVolleySingleton(this).addToRequestQueue(request);
        //设置请求标签用于加入全局队列后，方便找到
        request.setTag("postsr");
        //添加到全局的请求队列
        MyApplication.getHttpQueues().add(request);
    }




    //@Override
    //public boolean onCreateOptionsMenu(Menu menu) {
    //    getMenuInflater().inflate(R.menu.menu_main, menu);
    //
    //    final MenuItem searchItem = menu.findItem(R.id.action_search);
    //    final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
    //    searchView.setOnQueryTextListener(this);
    //
    //    return true;
    //}

    //@Override
    //public boolean onQueryTextChange(String query) {
    //    mAdapter.getFilter().filter(query);
    //    return true;
    //
    //}
    //
    //@Override
    //public boolean onQueryTextSubmit(String query) {
    //    return false;
    //}





}

