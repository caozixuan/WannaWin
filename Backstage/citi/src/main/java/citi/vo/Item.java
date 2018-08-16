package citi.vo;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;


public class Item {
    protected String ItemID;
    protected String name;
    protected String description;
    protected String merchantID;
    protected String logoURL;
    protected Double originalPrice;
    protected Integer points;
    protected Timestamp overdueTime;
    protected Long stock;
    protected String itemType;


    public List<Type.ItemType> getItemTypeList() {
        return Type.ItemType.DBStr2enum(itemType);
    }

    //for DB
    public Item(String itemID, String name, String description, String merchantID, String logoURL, Double originalPrice, Integer points, Timestamp overdueTime, Long stock, String itemType) {
        ItemID = itemID;
        this.name = name;
        this.description = description;
        this.merchantID = merchantID;
        this.logoURL = logoURL;
        this.originalPrice = originalPrice;
        this.points = points;
        this.overdueTime = overdueTime;
        this.stock = stock;
        this.itemType = itemType;
    }


    public Item() {
        this.ItemID = UUID.randomUUID().toString();
    }


    //for programmer
    public Item(String name, String description, String merchantID, String logoURL, Double originalPrice, Integer points, Timestamp overdueTime, Long stock, List<Type.ItemType> itemTypes) {
        this.ItemID = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.merchantID = merchantID;
        this.logoURL = logoURL;
        this.originalPrice = originalPrice;
        this.points = points;
        this.overdueTime = overdueTime;
        this.stock = stock;
        this.itemType = Type.ItemType.enum2DBStr(itemTypes);
    }

    //for programmer
    public Item(String name, String description, String merchantID, String logoURL, Double originalPrice, Integer points, Timestamp overdueTime, Long stock, Type.TypeWrapper tw) {
        this.ItemID = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.merchantID = merchantID;
        this.logoURL = logoURL;
        this.originalPrice = originalPrice;
        this.points = points;
        this.overdueTime = overdueTime;
        this.stock = stock;
        this.itemType = tw.toString();
    }

    public String getItemID() {
        return ItemID;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getMerchantID() {
        return merchantID;
    }

    public String getLogoURL() {
        return logoURL;
    }

    public Double getOriginalPrice() {
        return originalPrice;
    }

    public Integer getPoints() {
        return points;
    }

    public Timestamp getOverdueTime() {
        return overdueTime;
    }

    public Long getStock() {
        return stock;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getItemType(){
        return this.itemType;
    }

}