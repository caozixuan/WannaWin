package citi.vo;

public class Item {
    private String ItemID;
    private String name;
    private String description;
    private String merchantID;
    private int discount;
    private String logoURL;

    public Item(String itemID, String name, String description, String merchantID, int discount, String logoURL) {
        ItemID = itemID;
        this.name = name;
        this.description = description;
        this.merchantID = merchantID;
        this.discount = discount;
        this.logoURL = logoURL;
    }

    public String getName() {
        return name;
    }

    public String getLogoURL() {
        return logoURL;
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

    public int getDiscount() {
        return discount;
    }
}
