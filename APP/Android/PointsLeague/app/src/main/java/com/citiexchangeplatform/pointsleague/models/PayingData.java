package com.citiexchangeplatform.pointsleague.models;

import java.util.HashMap;
import java.util.List;

public class PayingData {

    private String general_point;
    private String posses_point;
    private String choose_point;
    private String logo_url;
    private String name;
    // 存储勾选框状态的map集合
    private HashMap<Integer, Boolean> map = new HashMap<>();


    public PayingData(String general_point, String posses_point, String logo_url, String name) {
        this.general_point = general_point;
        this.posses_point = posses_point;
        this.logo_url = logo_url;
        this.name = name;
    }

    public HashMap<Integer, Boolean> getMap() {
        return map;
    }

    public void setMap(HashMap<Integer, Boolean> map) {
        this.map = map;
    }

    public String getGeneral_point() {
        return general_point;
    }

    public void setGeneral_point(String general_point) {
        this.general_point = general_point;
    }

    public String getPosses_point() {
        return posses_point;
    }

    public void setPosses_point(String posses_point) {
        this.posses_point = posses_point;
    }

    public String getLogo_url() {
        return logo_url;
    }

    public void setLogo_url(String logo_url) {
        this.logo_url = logo_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChoose_point() {
        return choose_point;
    }

    public void setChoose_point(String choose_point) {
        this.choose_point = choose_point;
    }
}
