package citi.BC;

import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

public class BC {

    private static int difficulty = 6;
    public static ArrayList<Block> BC = new ArrayList<>();
    private static ConcurrentLinkedQueue<DealData> clq = new ConcurrentLinkedQueue<>();

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
        blockchain.add(genesisBlock);
        System.out.println("Trying to Mine block 1... ");
        blockchain.get(0).mineBlock(difficulty);


        DealData data2 = new DealData(DealData.DealType.IN, "1", "1", 678.0);
        Block secondBlock = new Block(genesisBlock, genesisBlock.getHash(), BC_Data.Data2BC_Data(data2));
        blockchain.add(secondBlock);
        System.out.println("Trying to Mine block 2... ");
        blockchain.get(1).mineBlock(difficulty);


        DealData data3 = new DealData(DealData.DealType.IN, "1", "123", 23.3);
        Block thirdBlock = new Block(secondBlock, secondBlock.getHash(), BC_Data.Data2BC_Data(data3));
        blockchain.add(thirdBlock);
        System.out.println("Trying to Mine block 3... ");
        blockchain.get(2).mineBlock(difficulty);


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