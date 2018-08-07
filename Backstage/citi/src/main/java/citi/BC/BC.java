package citi.BC;

import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

public class BC {

    private static int difficulty = 6;
    public static ArrayList<Block> BC = new ArrayList<>();
    private static ConcurrentLinkedQueue<DealData> clq = new ConcurrentLinkedQueue<>();


    static final public String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCTJu5H9n0ixlxSpxbBUL02ywuEhCXpDhKFlIYbIsDzRKHxMXT//FTeprfti8sN6vBI4TB0MAGklezTNnP1TRvtbSzqAVCewsuUNJZtnodem4fipN9Ko9vJDtM4vCLuFq5c3yWBIcSVZRyTJQjA8A5E3eFnOncFPEK9rIw6qvSPMQIDAQAB";
    static final public String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJMm7kf2fSLGXFKnFsFQvTbLC4SEJekOEoWUhhsiwPNEofExdP/8VN6mt+2Lyw3q8EjhMHQwAaSV7NM2c/VNG+1tLOoBUJ7Cy5Q0lm2eh16bh+Kk30qj28kO0zi8Iu4WrlzfJYEhxJVlHJMlCMDwDkTd4Wc6dwU8Qr2sjDqq9I8xAgMBAAECgYBmFp07acNZC9rjMK61wSj0SZuc/P08GZo5+FetncfVPelH5vLv4YSTz4BNGi3uYu7+8NPn16JcJ/NceSurpCpZ02E1Mn0fp3CWz+SBQFiCfU1S97aKq1F2bPY/qDVruXCww3or6V46vvXdecj6DG+DcFiyqbNK9DzFpI9RZvSoUQJBAPByAwHsuZOjYvLvUokb4Ai948PSw+1T9q5fmFHGCg1EUfOBjKrG0GC2ZBl4aHwM9ZRaLOxAe7EzTfDHTdea9t0CQQCcq+ZIxSkczOMy3vtM8uWHAWuaX2BR2JHSOPcMeuyLkMdwQUX6wpTjEDt9jjfYR8f+Zng+crB8cIXqZTDOYjJlAkBwVEM1bftQjt8WMVo0tbIXQ2cZv+hKgpRW6FAHu+ZMRmOmyBoIWnNVC0B+abMeaQt9sBcWlBV1NkuuNd4W4M19AkBFDBFiktnqonWjDtir8gSsOjkFAPG/QHYLsyVV77reS4FJ/MptmGHrrFS7ofkEraX7fYeS5hLX1PN/+PbIjkhtAkEAlW/2V/daEzvAXpc7Vfs4/mP2OWOzuHDtXSr91QAG2G15ZB6F3849f0lp9/L2yqfUD0TSQXyqAVFP+4X6hjkbdw==";


    public static void setDifficulty(int _difficulty) {
        difficulty = _difficulty;
    }


    public static void addBlock(DealData dealData) {
        clq.offer(dealData);
    }


    //TODO: 找一个地方调用线程。如果找不到程序入口，就改成一个静态线程池，在添加的时候就唤醒。
    //后台线程，一直在创建区块
    public void run() {
        if (false)
            return;

        synchronized (this) {
            while (true) {
                if (!clq.isEmpty()) {
                    DealData dealData = clq.poll();
                    Block lastBLock = BC.get(BC.size() - 1);
                    Block newBlock = new Block(lastBLock, lastBLock.getHash(), BC_Data.Data2BC_Data(dealData));
                    newBlock.mineBlock(difficulty);
                    blockchain.add(newBlock);
                    notify_all(newBlock);
                }
                try {
                    Thread.sleep(3000);//wait 3 sec.
                } catch (InterruptedException e) {
                    System.err.println("error with bg threads to wait.");
                }
            }
        }

    }


    //TODO: 向全网公开区块
    public static void notify_all(Block newBlock) {


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


    public static ArrayList<Block> blockchain = new ArrayList<>();

    public static void test(String[] args) {

        Map<String, Object> merchant_K1 = Encrypt.init();
        Encrypt.register_merchant("1", RSA.getPublicKey(merchant_K1));
        System.out.println("KEY of 1: \n" + RSA.getPublicKey(merchant_K1) + "\n" + RSA.getPrivateKey(merchant_K1));

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            System.err.println("InterruptedException");
        }

        Map<String, Object> merchant_K2 = Encrypt.init();
        Encrypt.register_merchant("2", RSA.getPublicKey(merchant_K2));
        System.out.println("KEY of 2: \n" + RSA.getPublicKey(merchant_K2) + "\n" + RSA.getPrivateKey(merchant_K2));

        System.out.println("\n\n");

        Block genesisBlock = Block.getInitBlock();
        System.out.println("Trying to Mine block 1... ");
        genesisBlock.mineBlock(difficulty);
        blockchain.add(genesisBlock);


        DealData data2 = new DealData(DealData.DealType.IN, "1", "1", 678.0);
        Block secondBlock = new Block(genesisBlock, genesisBlock.getHash(), BC_Data.Data2BC_Data(data2));
        System.out.println("Trying to Mine block 2... ");
        secondBlock.mineBlock(difficulty);
        blockchain.add(secondBlock);


        DealData data3 = new DealData(DealData.DealType.IN, "1", "123", 23.3);
        Block thirdBlock = new Block(secondBlock, secondBlock.getHash(), BC_Data.Data2BC_Data(data3));
        System.out.println("Trying to Mine block 3... ");
        thirdBlock.mineBlock(difficulty);
        blockchain.add(thirdBlock);


        System.out.println("\nBlockchain is Valid: " + isChainValid());


        String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
        System.out.println("\nThe block chain: ");
        System.out.println(blockchainJson);


        System.out.println("\n decrypt: \n");


        if (secondBlock.data.merchantID == "1") {
            System.out.println("The 2nd block chain decoded by 1: ");
            System.out.println(RSA.decryptByPrivate(secondBlock.data.encrypted_data, RSA.getPrivateKey(merchant_K1)));
        }
        if (secondBlock.data.merchantID == "1") {
            System.out.println("The 3rd block chain decoded by 1: ");
            System.out.println(RSA.decryptByPrivate(thirdBlock.data.encrypted_data, RSA.getPrivateKey(merchant_K1)));
        }
        if (secondBlock.data.merchantID == "2") {
            System.out.println("The 2nd block chain decoded by 2: ");
            System.out.println(RSA.decryptByPrivate(secondBlock.data.encrypted_data, RSA.getPrivateKey(merchant_K2)));
        }

    }


}