package citi.BC;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class Encrypt {

    static ConcurrentHashMap<String, String> pub_Ks = new ConcurrentHashMap<>();

    static void register_merchant(String merchantID, String pub_K) {
        pub_Ks.put(merchantID, pub_K);
    }


    static String getPubK(String merchantID) {
        String pub_K;
        try {
            pub_K = pub_Ks.get(merchantID);
        } catch (Exception e) {
            return null;
        }
        return pub_K;
    }


    /**
     * 生成公私密钥对
     */
    static public Map<String, Object> init() {
        Map<String, Object> map = null;
        try {
            KeyPairGenerator generator = KeyPairGenerator.getInstance(RSA.KEY_RSA);
            //设置密钥对的bit数，越大越安全，但速度减慢，一般使用512或1024
            final Integer keysize = 512 + new Random(System.currentTimeMillis() / 1000).nextInt(512);
            generator.initialize(1024);
            KeyPair keyPair = generator.generateKeyPair();
            // 获取公钥
            RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
            // 获取私钥
            RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
            // 将密钥对封装为Map
            map = new HashMap<String, Object>();
            map.put(RSA.KEY_RSA_PUBLICKEY, publicKey);
            map.put(RSA.KEY_RSA_PRIVATEKEY, privateKey);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return map;
    }


}
