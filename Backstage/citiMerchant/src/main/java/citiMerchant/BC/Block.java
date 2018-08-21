package citiMerchant.BC;

import java.util.Date;

public class Block {

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
        this.previousHash = previousHash;
        this.data = data;
        this.timeStamp = new Date().getTime();
        this.nonce = 0;
        this.hash = calculateThisHash();
    }

    //get Init-Block
    static Block getInitBlock() {
        DealData data = new DealData(null, null, null, 0.0);
        Block block = new Block(null, null, BC_Data.Data2BC_Data(data));
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


}