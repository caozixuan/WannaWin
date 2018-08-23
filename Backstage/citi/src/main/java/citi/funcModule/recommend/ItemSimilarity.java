package citi.funcModule.recommend;

import java.io.Serializable;

public class ItemSimilarity implements Serializable {
    public String itemID1;
    public String itemID2;
    public double similarity;

    public ItemSimilarity(String itemID1, String itemID2, double similarity) {
        this.itemID1 = itemID1;
        this.itemID2 = itemID2;
        this.similarity = similarity;
    }

    @Override
    public String toString() {
        return "itemID1:" + itemID1 + ";itemID2:" + itemID2+";similarity:" + similarity;
    }
}
