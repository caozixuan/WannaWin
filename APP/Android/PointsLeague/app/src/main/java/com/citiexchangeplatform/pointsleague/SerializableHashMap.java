package com.citiexchangeplatform.pointsleague;

import java.io.Serializable;
import java.util.HashMap;

public class SerializableHashMap implements Serializable {

    private HashMap<Integer,Boolean> map;

    public HashMap<Integer, Boolean> getMap() {
        return map;
    }

    public void setMap(HashMap<Integer, Boolean> map) {
        this.map = map;
    }
}