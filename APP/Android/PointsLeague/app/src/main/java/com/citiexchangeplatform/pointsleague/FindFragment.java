package com.citiexchangeplatform.pointsleague;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.citiexchangeplatform.pointsleague.adapter.AllCardAdapter;
import com.citiexchangeplatform.pointsleague.adapter.FindActivityAdapter;
import com.citiexchangeplatform.pointsleague.adapter.FindAdapter;
import com.leochuan.CenterSnapHelper;
import com.leochuan.ScaleLayoutManager;
import com.study.xuan.xvolleyutil.base.XVolley;
import com.study.xuan.xvolleyutil.callback.CallBack;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;

public class FindFragment extends Fragment{

    View view;
    RecyclerView recyclerView1;
    RecyclerView recyclerView2;
    FindAdapter findAdapter;
    FindActivityAdapter findActivityAdapter;

    ProgressDialog dialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_find, null);

        recyclerView1 = (RecyclerView)view.findViewById(R.id.recyclerView_activity_find);
        recyclerView1.setLayoutManager(
                new ScaleLayoutManager
                        .Builder(getContext(),2)
                        .setMinScale(1.0f)
                        .setOrientation(OrientationHelper. HORIZONTAL)
                        .build());
        new CenterSnapHelper().attachToRecyclerView(recyclerView1);
        findActivityAdapter = new FindActivityAdapter(getContext());
        recyclerView1.setAdapter(findActivityAdapter);
        recyclerView1.setItemAnimator(new DefaultItemAnimator());

        findActivityAdapter.addData("123123","!23","123","123","123");
        findActivityAdapter.addData("123123","!23","123","123","123");
        findActivityAdapter.addData("123123","!23","123","123","123");
        recyclerView1.scrollToPosition(findActivityAdapter.getItemCount()/2);

        recyclerView2 = (RecyclerView)view.findViewById(R.id.recyclerView_find);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getContext());
        recyclerView2.setLayoutManager(layoutManager2);
        findAdapter = new FindAdapter(getContext());
        recyclerView2.setAdapter(findAdapter);
        recyclerView2.setItemAnimator(new DefaultItemAnimator());

        TitleBar titleBar = (TitleBar) view.findViewById(R.id.toolbar_find);
        titleBar.setTitle("发现");
        titleBar.setTitleColor(Color.BLACK);
        //titleBar.setImmersive(true);

        android.widget.SearchView search = view.findViewById(R.id.searchView_find);
        search.setOnQueryTextListener(new android.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                findAdapter.getFilter().filter(newText);
                return true;
            }
        });
        search.setIconified(false);
        search.setIconifiedByDefault(false);
        search.setSubmitButtonEnabled(false);
        search.clearFocus();
        search.clearFocus();
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

        getCount();

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void getCount(){
        XVolley.getInstance()
                .doPost()
                .url("http://193.112.44.141:80/citi/merchant/getNum")
                .build()
                .execute(getContext(), new CallBack<String>() {
                    @Override
                    public void onSuccess(Context context, String response) {
                        System.out.println(response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            getInfos(jsonObject.getInt("num"));

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
                        dialog = ProgressDialog.show(getContext(), "", "正在获取商家信息...");
                    }
                });
    }

    private void getInfos(int n) {
        XVolley.getInstance()
                .doPost()
                .url("http://193.112.44.141:80/citi/merchant/getInfos")
                .addParam("start", "0")
                .addParam("n", "20")
                .build()
                .execute(getContext(), new CallBack<String>() {
                    @Override
                    public void onSuccess(Context context, String response) {
                        System.out.println(response);
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String merchantID = jsonObject.getString("merchantID");
                                String name = jsonObject.getString("name");
                                String description = jsonObject.getString("description");
                                String businessType;
                                switch (jsonObject.getString("businessType")){
                                    case "catering":
                                        businessType = "餐饮";
                                        break;
                                    case "exercise":
                                        businessType = "运动";
                                        break;
                                    case "bank":
                                        businessType = "银行";
                                        break;
                                    case "costume":
                                        businessType = "服饰";
                                        break;
                                    case "education":
                                        businessType = "教育";
                                        break;
                                    case "communication":
                                        businessType = "通讯";
                                        break;
                                    default:
                                        businessType = "一般";
                                }
                                String merchantLogoURL = jsonObject.getString("merchantLogoURL");
                                findAdapter.addData(name, merchantID, merchantLogoURL, businessType, description);
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

}
