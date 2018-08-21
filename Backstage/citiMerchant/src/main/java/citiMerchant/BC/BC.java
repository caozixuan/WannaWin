package citiMerchant.BC;

import java.util.ArrayList;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/*
 * 商户端对于区块链的操作应该在商户自己的服务器上！！！
 */
public class BC {

    static private final String magic_hash = "No previous Block";
    private static ArrayList<Block> blockchain = new ArrayList<>();

    private static Boolean isInit = false;
    private static ConcurrentLinkedQueue<Block> waitingQueue = new ConcurrentLinkedQueue<>();

    // <previousHash, Block>
    private static ConcurrentHashMap<String, Block> waitingBlock = new ConcurrentHashMap<>();

    final public static int difficulty = 6;


    static final public String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCTJu5H9n0ixlxSpxbBUL02ywuEhCXpDhKFlIYbIsDzRKHxMXT//FTeprfti8sN6vBI4TB0MAGklezTNnP1TRvtbSzqAVCewsuUNJZtnodem4fipN9Ko9vJDtM4vCLuFq5c3yWBIcSVZRyTJQjA8A5E3eFnOncFPEK9rIw6qvSPMQIDAQAB";

    static final private String pub_K;
    static final private String priv_K;

    static {
        Map<String, Object> merchant_K1 = Encrypt.init();
        pub_K = RSA.getPublicKey(merchant_K1);
        priv_K = RSA.getPrivateKey(merchant_K1);
    }

    static public merchantInfo register_merchant(String merchantID) {
        return new merchantInfo(merchantID, pub_K);
    }

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


    static public void getBlock(Block block) {
        if (block.previousHash == magic_hash) {
            blockchain.add(block);
            isInit = true;
            return;
        }
        waitingQueue.add(block);
    }


    //必须单线程跑，用于接受区块，并组成链.
    public void run() {
        while (!isInit) {
            try {
                Thread.sleep(1L * 1000 * 60);
            } catch (InterruptedException e) {
            }
        }

        while (true) {
            if (waitingQueue.isEmpty()) {
                try {
                    Thread.sleep(1L * 1000 * 60);
                } catch (InterruptedException e) {
                }
                continue;
            }
            Block block = waitingQueue.poll();

            if (blockchain.get(blockchain.size() - 1).getHash() == block.previousHash) {
                blockchain.add(block);
                continue;
            } else {
                waitingBlock.put(block.previousHash, block);
            }

            //process "waitingBlock"
            while (true) {
                String prev = blockchain.get(blockchain.size() - 1).getHash();
                Block b = waitingBlock.remove(prev);
                if (b == null) break;
                blockchain.add(b);
            }

        }
    }

    //return Dealdata or null
    //格式： type, userID, points_citi  eg. IN,1,678.0
    static public DealData read(Block block, String merchantID, String private_K) {
        if (merchantID != block.data.merchantID) return null;
        String message = RSA.decryptByPrivate(block.data.encrypted_data, private_K);
        StringTokenizer st = new StringTokenizer(message, ",");
        String type = st.nextToken();
        String userID = st.nextToken();
        Double points_citi = Double.parseDouble(st.nextToken());
        return new DealData(DealData.DealType.getType(type), merchantID, userID, points_citi);
    }

}

class merchantInfo {
    final public String merchantID;
    final public String pub_K;

    public merchantInfo(String merchantID, String pub_K) {
        this.merchantID = merchantID;
        this.pub_K = pub_K;
    }
}
