package citiMerchant.vo;

import java.sql.Timestamp;
import java.util.UUID;

public class Item {
    private String ItemID;
    private String name;
    private String description;
    private String merchantID;
    private String logoURL;
    private double originalPrice;
    private int points;
    private Timestamp overdueTime;
    private long stock;

    public Item(String itemID, String name, String description, String merchantID, String logoURL, double originalPrice, int points, Timestamp overdueTime, long stock) {
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

    public Item(){
        this.ItemID = UUID.randomUUID().toString();
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

    public double getOriginalPrice() {
        return originalPrice;
    }

    public int getPoints() {
        return points;
    }

    public Timestamp getOverdueTime() {
        return overdueTime;
    }

    public long getStock() {
        return stock;
    }

}
