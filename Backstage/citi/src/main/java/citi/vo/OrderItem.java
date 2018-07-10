package citi.vo;

public class OrderItem {
    private String orderID;
    private String itemID;
    private int num;

    public OrderItem(String orderID, String itemID, int num) {
        this.orderID = orderID;
        this.itemID = itemID;
        this.num = num;
    }

    public String getOrderID() {
        return orderID;
    }

    public String getItemID() {
        return itemID;
    }

    public int getNum() {
        return num;
    }
}
