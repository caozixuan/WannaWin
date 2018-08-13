package com.citiexchangeplatform.pointsleague.models;

public class DetailFindItemModel {
    private String itemID;
    private String name;
    private String description;
    private int points;

    public DetailFindItemModel(String itemID, String name, String description, int points) {
        this.itemID = itemID;
        this.name = name;
        this.description = description;
        this.points = points;
    }

    public String getItemID() {
        return itemID;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getPoints() {
        return points;
    }
}
