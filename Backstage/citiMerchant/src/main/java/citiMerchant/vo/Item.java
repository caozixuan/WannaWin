package citiMerchant.vo;

import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.util.UUID;

public class Item {
    private String itemID;
    private String name;
    private String description;
    private String merchantID;
    private String logoURL;
    private Double originalPrice;
    private Integer points;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Timestamp overdueTime;
    private Long stock;

    public Item(String itemID, String name, String description, String merchantID, String logoURL, Double originalPrice, Integer points, Timestamp overdueTime, Long stock) {
        this.itemID = itemID;
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

    }

    public Item(String name, String description, String merchantID, String logoURL, Double originalPrice, Integer points, Timestamp overdueTime, Long stock) {
        this.itemID = UUID.randomUUID().toString();
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
        return itemID;
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

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMerchantID(String merchantID) {
        this.merchantID = merchantID;
    }

    public void setLogoURL(String logoURL) {
        this.logoURL = logoURL;
    }

    public void setOriginalPrice(Double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public void setOverdueTime(Timestamp overdueTime) {
        this.overdueTime = overdueTime;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }
}
