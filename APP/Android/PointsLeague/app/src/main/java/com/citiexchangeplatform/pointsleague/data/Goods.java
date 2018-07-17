package com.citiexchangeplatform.pointsleague.data;

public class Goods {


    //public static final int TYPE_CLOTHES = 2;


    public String name;
    public int index;


    public int reBindTimes = 0;

    public Goods() {
    }

    public Goods(String name, int index, int reBindTimes) {
        this.name = name;
        this.index = index;
        this.reBindTimes = reBindTimes;
    }
}
