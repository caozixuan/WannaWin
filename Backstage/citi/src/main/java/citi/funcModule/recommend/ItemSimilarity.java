package citi.funcModule.Recommend;

import java.io.Serializable;

public class ItemSimilarity implements Serializable {
    String itemID1;
    String itemID2;
    double similarity;

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
