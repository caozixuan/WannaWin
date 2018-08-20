//package com.citiexchangeplatform.pointsleague;
//
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//
//import android.support.v7.widget.DividerItemDecoration;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.widget.TextView;
//
//import com.citiexchangeplatform.pointsleague.models.PointsExchangeModel;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class PointsExchangeRecordActivity extends AppCompatActivity {
//
//    private RecyclerView pointsExchangeRecordRecyclerView;
//    private PointsExchangeRecordAdapter pointsRecordAdapter;
//
//    private List<PointsExchangeModel> records;
//    private PointsExchangeModel model;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_points_exchange_record);
//
//        //获取页面中的元素
//        initView();
//
//        //获得初始化数据
//        initData();
//        //设置RecyclerView管理器
//        setRecyclerView();
//
//
//    }
//
//    private void initView(){
//        //会员卡列表
//        pointsExchangeRecordRecyclerView = (RecyclerView) findViewById(R.id.recycleView_exchange_record);
//
//    }
//
//    /*获得各项积分卡数据：logo merchantName posses_points rate generalPoints*/
//    protected void initData()
//    {
//        records = new ArrayList<>();
//        for (int i = 1; i <= 50; i++) {
//            model = new PointsExchangeModel();
//
//            model.setID(String.valueOf(i));
//            model.setType(0);
//            model.setExchangeTotalPoint("父--"+i);
//            model.setDate("父内容--"+i);
//            model.setExchangePoint("子--"+i);
//            model.setMsCardPoint("子内容--"+i);
//            model.setChildBean(model);
//            records.add(model);
//        }
//
//    }
//
//    protected void setRecyclerView(){
//        pointsExchangeRecordRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        pointsRecordAdapter = new PointsExchangeRecordAdapter(PointsExchangeRecordActivity.this,records);
//
//        pointsExchangeRecordRecyclerView.setAdapter(pointsRecordAdapter);
//        //添加Android自带的分割线
//        pointsExchangeRecordRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
//
//        //滚动监听
//        pointsRecordAdapter.setOnScrollListener(new PointsExchangeRecordAdapter.OnScrollListener() {
//
//            @Override
//            public void scrollTo(int pos) {
//                pointsExchangeRecordRecyclerView.scrollToPosition(pos);
//            }
//        });
//
//    }
//}
