package citi.BC;

import com.google.gson.annotations.Expose;

import java.util.Date;
import java.util.Random;

public class Block {

    static private final String magic_hash = "No previous Block";

    @Expose(serialize = false, deserialize = false)
    final public transient Block previousBlock;

    private String hash;
    final public String previousHash;
    final public BC_Data data;
    final public Long timeStamp; //as number of milliseconds since 1/1/1970.
    private Integer nonce;
    private String enStr;
    private String signature;

    public String getEnStr() {
        return enStr;
    }

    public String getSignature() {
        return signature;
    }

    public String getHash() {
        return hash;
    }

    public Integer getNonce() {
        return nonce;
    }

    //Ctor
    public Block(Block previousBlock, String previousHash, BC_Data data) {
        this.previousBlock = previousBlock;
        this.previousHash = previousHash;
        this.data = data;
        this.timeStamp = new Date().getTime();
        this.nonce = 0;
        this.hash = calculateThisHash();
    }

    //get Init-Block
    static Block getInitBlock() {
        DealData data = new DealData(null, null, null, 0.0);
        Block block = new Block(null, magic_hash, BC_Data.Data2BC_Data(data));
        block.hash = block.calculateThisHash();
        return block;
    }

    //calculateHash
    static public String calculateHash(String previousHash, String data, Long timeStamp, Integer nonce) {
        String calculatedhash = StringUtil.applySha256(
                previousHash +
                        Long.toString(timeStamp) +
                        Integer.toString(nonce) +
                        data
        );
        return calculatedhash;
    }

    public String calculateThisHash() {
        return calculateHash(this.previousHash, this.data.toString(), this.timeStamp, this.getNonce());
    }

    // No proof-of-work model !!!
    public void mineBlock(int difficulty) {
        /*
         * Instead of proof-of-work model,
         * we use the model where a centre keeps accounts and others audit accounts decentrally.
         */
        /*
        String target = new String(new char[difficulty]).replace('\0', '0'); //Create a string with difficulty * "0"
        while (!hash.substring(0, difficulty).equals(target)) {
            nonce++;
            hash = calculateThisHash();
        }
        */

        Random random = new Random();
        nonce = random.nextInt();
        hash = calculateThisHash();

        //System.out.println("Block Mined!!! : " + hash);

        String str = data.merchantID +
                Long.toString(timeStamp) +
                Integer.toString(nonce);
        enStr = RSA.encryptByPrivate(str, BC.privateKey);
        // 产生签名
        signature = RSA.sign(enStr, BC.privateKey);

    }

}