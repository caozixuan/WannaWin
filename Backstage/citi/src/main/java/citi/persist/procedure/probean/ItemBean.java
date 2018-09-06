package citi.persist.procedure.probean;

import citi.vo.Item;
import citi.vo.Type;

import java.sql.Timestamp;

public class ItemBean extends Item {
    private String merchantName;
    private String merchantLogoURL;

    public ItemBean(String itemID, String name, String description, String merchantID, String logoURL, Double originalPrice, Integer points, Timestamp overdueTime, Long stock, String itemType, String merchantName, String merchantLogoURL) {
        super(itemID, name, description, merchantID, logoURL, originalPrice, points, overdueTime, stock, itemType);
        this.merchantName = merchantName;
        this.merchantLogoURL = merchantLogoURL;
    }

    public ItemBean(Item item, String merchantName, String merchantLogoURL){
        super(item.getItemID(),item.getName(),item.getDescription(),item.getMerchantID(),item.getLogoURL(),item.getOriginalPrice(),item.getPoints(),Timestamp.valueOf(item.getOverdueTime()),item.getStock(),"暂无");
        this.merchantName = merchantName;
        this.merchantLogoURL = merchantLogoURL;
    }
}
