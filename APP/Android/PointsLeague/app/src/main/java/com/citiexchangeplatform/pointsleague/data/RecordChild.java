package com.citiexchangeplatform.pointsleague.data;

public class RecordChild {


    //public static final int TYPE_CLOTHES = 2;


    public String name;
    public String usePoints;
    public String exchangePoints;
    public int index;


    public int reBindTimes = 0;

    public RecordChild() {
    }

    public RecordChild(String name, String usePoints, String exchangePoints, int index, int reBindTimes) {
        this.name = name;
        this.usePoints = usePoints;
        this.exchangePoints = exchangePoints;
        this.index = index;
        this.reBindTimes = reBindTimes;
    }
}
