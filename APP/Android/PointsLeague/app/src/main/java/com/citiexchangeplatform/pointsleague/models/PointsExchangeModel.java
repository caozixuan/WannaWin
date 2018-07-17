package com.citiexchangeplatform.pointsleague.models;

public class PointsExchangeModel {

    public static final int PARENT_ITEM = 0;//父布局
    public static final int CHILD_ITEM = 1;//子布局

    private boolean isExpand;// 是否展开
    private PointsExchangeModel childBean;


    private int type;// 显示类型
    private String ID;
    private String date;
    private String exchangeTotalPoint;
    private String msCardPoint;
    private String exchangePoint;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isExpand() {
        return isExpand;
    }

    public void setExpand(boolean expand) {
        isExpand = expand;
    }

    public PointsExchangeModel getChildBean() {
        return childBean;
    }

    public void setChildBean(PointsExchangeModel childBean) {
        this.childBean = childBean;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getExchangeTotalPoint() {
        return exchangeTotalPoint;
    }

    public void setExchangeTotalPoint(String exchangeTotalPoint) {
        this.exchangeTotalPoint = exchangeTotalPoint;
    }

    public String getMsCardPoint() {
        return msCardPoint;
    }

    public void setMsCardPoint(String msCardPoint) {
        this.msCardPoint = msCardPoint;
    }

    public String getExchangePoint() {
        return exchangePoint;
    }

    public void setExchangePoint(String exchangePoint) {
        this.exchangePoint = exchangePoint;
    }
}
