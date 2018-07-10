package citi.vo;

public class Item {
    private String ItemID;
    private String description;
    private String merchantID;
    private String points;
    private String type;
    private double originalPrice;
    private double afterPrice;
    private double discount;

    public Item(String itemID, String description, String merchantID, String points, String type, double originalPrice, double afterPrice, double discount) {
        ItemID = itemID;
        this.description = description;
        this.merchantID = merchantID;
        this.points = points;
        this.type = type;
        this.originalPrice = originalPrice;
        this.afterPrice = afterPrice;
        this.discount = discount;
    }

    public String getItemID() {
        return ItemID;
    }

    public String getDescription() {
        return description;
    }

    public String getMerchantID() {
        return merchantID;
    }

    public String getPoints() {
        return points;
    }

    public String getType() {
        return type;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    public double getAfterPrice() {
        return afterPrice;
    }

    public double getDiscount() {
        return discount;
    }
}
