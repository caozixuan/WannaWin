package citi.funcModule.Recommend;

public class ItemSimilarity{
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
