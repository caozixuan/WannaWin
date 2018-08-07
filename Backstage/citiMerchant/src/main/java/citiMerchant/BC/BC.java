package citiMerchant.BC;

import java.util.ArrayList;

public class BC {

    public static ArrayList<Block> blockchain = new ArrayList<Block>();
    final public static int difficulty = 4;

    static final public String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCTJu5H9n0ixlxSpxbBUL02ywuEhCXpDhKFlIYbIsDzRKHxMXT//FTeprfti8sN6vBI4TB0MAGklezTNnP1TRvtbSzqAVCewsuUNJZtnodem4fipN9Ko9vJDtM4vCLuFq5c3yWBIcSVZRyTJQjA8A5E3eFnOncFPEK9rIw6qvSPMQIDAQAB";

    static public Boolean isChainValid() {
        Block currentBlock;
        Block previousBlock;

        //loop through blockchain to check hashes:
        for (int i = 1; i < blockchain.size(); i++) {
            currentBlock = blockchain.get(i);
            previousBlock = blockchain.get(i - 1);
            //compare registered hash and calculated hash:
            if (!currentBlock.getHash().equals(currentBlock.calculateThisHash())) {
                System.out.println("Current Hashes not equal");
                return false;
            }
            //compare previous hash and registered previous hash
            if (!previousBlock.getHash().equals(currentBlock.previousHash)) {
                System.out.println("Previous Hashes not equal");
                return false;
            }
            //compare signature
            if (!RSA.verify(currentBlock.getEnStr(), publicKey, currentBlock.getSignature())) {
                // 验证签名
                System.out.println("Signature not verify");
                return false;
            }
        }
        return true;
    }

}