package citi.BC;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

public class BC implements Runnable{

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

    private static String RegisterUrl="http://www.byzhong.cn/merchantDemo/block/";

    private static Gson gson=new Gson();

    //TODO: 找一个地方调用线程。如果找不到程序入口，就改成一个静态线程池，在添加的时候就唤醒。
    //后台线程，一直在创建区块
    @Override
    public void run() {

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
                    System.out.println("run");
                    Thread.sleep(3000);//wait 3 sec.
                } catch (InterruptedException e) {
                    System.err.println("error with bg threads to wait.");
                }
            }
        }

    }


    //TODO: 向全网公开区块
    public static void notify_all(Block newBlock) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(RegisterUrl);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
//            // 设置通用的请求属性
//            conn.setRequestProperty("accept", "*/*");
//            conn.setRequestProperty("connection", "Keep-Alive");
//            conn.setRequestProperty("user-agent",
//                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
//            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(gson.toJson(newBlock));
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
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


        if (secondBlock.data.merchantID.equals("1")) {
            System.out.println("The 2nd block chain decoded by 1: ");
            System.out.println(RSA.decryptByPrivate(secondBlock.data.encrypted_data, RSA.getPrivateKey(merchant_K1)));
        }
        if (secondBlock.data.merchantID.equals("1")) {
            System.out.println("The 3rd block chain decoded by 1: ");
            System.out.println(RSA.decryptByPrivate(thirdBlock.data.encrypted_data, RSA.getPrivateKey(merchant_K1)));
        }
        if (secondBlock.data.merchantID.equals("2")) {
            System.out.println("The 2nd block chain decoded by 2: ");
            System.out.println(RSA.decryptByPrivate(secondBlock.data.encrypted_data, RSA.getPrivateKey(merchant_K2)));
        }

    }


}