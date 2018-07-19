package citiMerchant.vo;

import java.sql.Timestamp;
import java.util.UUID;

public class Item {
    private String ItemID;
    private String name;
    private String description;
    private String merchantID;
    private String logoURL;
    private Double originalPrice;
    private Integer points;
    private Timestamp overdueTime;
    private Long stock;

    public Item(String itemID, String name, String description, String merchantID, String logoURL, Double originalPrice, Integer points, Timestamp overdueTime, Long stock) {
        ItemID = itemID;
        this.name = name;
        this.description = description;
        this.merchantID = merchantID;
        this.logoURL = logoURL;
        this.originalPrice = originalPrice;
        this.points = points;
        this.overdueTime = overdueTime;
        this.stock = stock;
    }

    public Item() {
        this.ItemID = UUID.randomUUID().toString();
    }

    public Item(String name, String description, String merchantID, String logoURL, Double originalPrice, Integer points, Timestamp overdueTime, Long stock) {
        this.ItemID = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.merchantID = merchantID;
        this.logoURL = logoURL;
        this.originalPrice = originalPrice;
        this.points = points;
        this.overdueTime = overdueTime;
        this.stock = stock;
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

}
