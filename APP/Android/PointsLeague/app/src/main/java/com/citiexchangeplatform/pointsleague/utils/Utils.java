package com.citiexchangeplatform.pointsleague.utils;

import android.util.Log;

import com.citiexchangeplatform.pointsleague.adapter.NestedAdapter;
import com.citiexchangeplatform.pointsleague.data.RecordChild;
import com.citiexchangeplatform.pointsleague.data.RecordParent;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;


import java.util.ArrayList;
import java.util.List;



public class Utils {

    public static void showJson(List<RecordParent> shopList) {
        String s = new Gson().toJson(shopList);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jp = new JsonParser();
        JsonElement je = jp.parse(s);
        s = gson.toJson(je);
        Log.d(NestedAdapter.TAG, "showJson: \n" + s);
    }

    public static List<RecordParent> geneRandomData() {
        List<RecordParent> recordList = new ArrayList<>();
        int count = (int) (Math.random() * 30);
        for (int i = 0; i < count; i++) {
            RecordParent recordParent = new RecordParent();
            recordParent.index = i;
            recordParent.totalExchangePoint = "兑换积分" + i;
            recordParent.date = "2018-7-17";


            recordParent.childs = geneChildsInfo(i);

            recordList.add(recordParent);
        }

        return recordList;

    }

    private static List<RecordChild> geneChildsInfo(int i) {
        List<RecordChild> recordChildList = new ArrayList<>();

        int count = (int) (Math.random() * 5);
        for (int j = 0; j < count; j++) {
            RecordChild recordChild = new RecordChild();

            recordChild.index = j;
            recordChild.name = "使用积分" + 1000;

            recordChildList.add(recordChild);
        }

        return recordChildList;
    }
}
