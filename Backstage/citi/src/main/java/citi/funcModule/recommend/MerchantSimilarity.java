package citi.funcModule.recommend;

import java.io.Serializable;

public class MerchantSimilarity implements Serializable{
    String merchantID1;
    String merchantID2;
    double similarity;

    public MerchantSimilarity(String merchantID1, String merchantID2, double similarity) {
        this.merchantID1 = merchantID1;
        this.merchantID2 = merchantID2;
        this.similarity = similarity;
    }

    @Override
    public String toString() {
        return "merchantID1:" + merchantID1 + ";merchantID2:" + merchantID2+";similarity:" + similarity;
    }
}
