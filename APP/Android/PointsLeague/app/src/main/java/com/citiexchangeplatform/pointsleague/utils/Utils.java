package com.citiexchangeplatform.pointsleague.utils;

import android.util.Log;

import com.citiexchangeplatform.pointsleague.adapter.NestedAdapter;
import com.citiexchangeplatform.pointsleague.data.Goods;
import com.citiexchangeplatform.pointsleague.data.Shop;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;


import java.util.ArrayList;
import java.util.List;



public class Utils {

    public static void showJson(List<Shop> shopList) {
        String s = new Gson().toJson(shopList);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jp = new JsonParser();
        JsonElement je = jp.parse(s);
        s = gson.toJson(je);
        Log.d(NestedAdapter.TAG, "showJson: \n" + s);
    }

    public static List<Shop> geneRandomData() {
        List<Shop> shopList = new ArrayList<>();
        int count = (int) (Math.random() * 30);
        for (int i = 0; i < count; i++) {
            Shop shop = new Shop();
            shop.index = i;
            shop.shopName = "兑换积分" + i;

            shop.goods = geneGoodsInfo(i);

            shopList.add(shop);
        }

        return shopList;

    }

    private static List<Goods> geneGoodsInfo(int i) {
        List<Goods> goodsList = new ArrayList<>();

        int count = (int) (Math.random() * 5);
        for (int j = 0; j < count; j++) {
            Goods goods = new Goods();

            goods.index = j;
            goods.name = "使用积分" + j;

            goodsList.add(goods);
        }

        return goodsList;
    }
}
