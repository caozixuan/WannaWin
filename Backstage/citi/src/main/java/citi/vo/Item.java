package citi.vo;

public class Item {
    private String ItemID;
    private String description;
    private String merchantID;
    private int discount;

    public Item(String itemID, String description, String merchantID, int discount) {
        ItemID = itemID;
        this.description = description;
        this.merchantID = merchantID;
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

    public int getDiscount() {
        return discount;
    }
}
