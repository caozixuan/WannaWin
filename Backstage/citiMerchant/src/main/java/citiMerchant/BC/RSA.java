package citiMerchant.BC;

import javax.crypto.Cipher;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Map;

public class RSA {

    //定义加密方式
    static final public String KEY_RSA = "RSA";
    //定义公钥关键词
    static final public String KEY_RSA_PUBLICKEY = "RSAPublicKey";
    //定义私钥关键词
    static final public String KEY_RSA_PRIVATEKEY = "RSAPrivateKey";
    //定义签名算法
    static final private String KEY_RSA_SIGNATURE = "MD5withRSA";


    /**
     * 获取Base64编码的公钥字符串
     */
    static public String getPublicKey(Map<String, Object> map) {
        String str = "";
        Key key = (Key) map.get(KEY_RSA_PUBLICKEY);
        str = encryptBase64(key.getEncoded());
        return str;
    }

    /**
     * 获取Base64编码的私钥字符串
     */
    static public String getPrivateKey(Map<String, Object> map) {
        String str = "";
        Key key = (Key) map.get(KEY_RSA_PRIVATEKEY);
        str = encryptBase64(key.getEncoded());
        return str;
    }

    /**
     * BASE64 解码
     *
     * @param key 需要Base64解码的字符串
     * @return 字节数组
     */
    public static byte[] decryptBase64(String key) {
        return Base64.getDecoder().decode(key);
    }

    /**
     * BASE64 编码
     *
     * @param key 需要Base64编码的字节数组
     * @return 字符串
     */
    public static String encryptBase64(byte[] key) {
        return new String(Base64.getEncoder().encode(key));
    }


    /**
     * 公钥加密
     *
     * @param encryptingStr
     * @param publicKeyStr
     * @return
     */
    public static String encryptByPublic(String encryptingStr, String publicKeyStr) {
        try {
            // 将公钥由字符串转为UTF-8格式的字节数组
            byte[] publicKeyBytes = decryptBase64(publicKeyStr);
            // 获得公钥
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
            // 取得待加密数据
            byte[] data = encryptingStr.getBytes("UTF-8");
            KeyFactory factory;
            factory = KeyFactory.getInstance(KEY_RSA);
            PublicKey publicKey = factory.generatePublic(keySpec);
            // 对数据加密
            Cipher cipher = Cipher.getInstance(factory.getAlgorithm());
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            // 返回加密后由Base64编码的加密信息
            return encryptBase64(cipher.doFinal(data));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 私钥解密
     *
     * @param encryptedStr
     * @param privateKeyStr
     * @return
     */
    public static String decryptByPrivate(String encryptedStr, String privateKeyStr) {
        try {
            // 对私钥解密
            byte[] privateKeyBytes = decryptBase64(privateKeyStr);
            // 获得私钥
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
            // 获得待解密数据
            byte[] data = decryptBase64(encryptedStr);
            KeyFactory factory = KeyFactory.getInstance(KEY_RSA);
            PrivateKey privateKey = factory.generatePrivate(keySpec);
            // 对数据解密
            Cipher cipher = Cipher.getInstance(factory.getAlgorithm());
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            // 返回UTF-8编码的解密信息
            return new String(cipher.doFinal(data), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 私钥加密
     *
     * @param encryptingStr
     * @param privateKeyStr
     * @return
     */
    public static String encryptByPrivate(String encryptingStr, String privateKeyStr) {
        try {
            byte[] privateKeyBytes = decryptBase64(privateKeyStr);
            // 获得私钥
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
            // 取得待加密数据
            byte[] data = encryptingStr.getBytes("UTF-8");
            KeyFactory factory = KeyFactory.getInstance(KEY_RSA);
            PrivateKey privateKey = factory.generatePrivate(keySpec);
            // 对数据加密
            Cipher cipher = Cipher.getInstance(factory.getAlgorithm());
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
            // 返回加密后由Base64编码的加密信息
            return encryptBase64(cipher.doFinal(data));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    /**
     * 公钥解密
     *
     * @param encryptedStr
     * @param publicKeyStr
     * @return
     */
    public static String decryptByPublic(String encryptedStr, String publicKeyStr) {
        try {
            // 对公钥解密
            byte[] publicKeyBytes = decryptBase64(publicKeyStr);
            // 取得公钥
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
            // 取得待加密数据
            byte[] data = decryptBase64(encryptedStr);
            KeyFactory factory = KeyFactory.getInstance(KEY_RSA);
            PublicKey publicKey = factory.generatePublic(keySpec);
            // 对数据解密
            Cipher cipher = Cipher.getInstance(factory.getAlgorithm());
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
            // 返回UTF-8编码的解密信息
            return new String(cipher.doFinal(data), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    /**
     * 用私钥对加密数据进行签名
     *
     * @param encryptedStr
     * @param privateKey
     * @return
     */
    public static String sign(String encryptedStr, String privateKey) {
        String str = "";
        try {
            //将私钥加密数据字符串转换为字节数组
            byte[] data = encryptedStr.getBytes();
            // 解密由base64编码的私钥
            byte[] bytes = decryptBase64(privateKey);
            // 构造PKCS8EncodedKeySpec对象
            PKCS8EncodedKeySpec pkcs = new PKCS8EncodedKeySpec(bytes);
            // 指定的加密算法
            KeyFactory factory = KeyFactory.getInstance(KEY_RSA);
            // 取私钥对象
            PrivateKey key = factory.generatePrivate(pkcs);
            // 用私钥对信息生成数字签名
            Signature signature = Signature.getInstance(KEY_RSA_SIGNATURE);
            signature.initSign(key);
            signature.update(data);
            str = encryptBase64(signature.sign());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }


    /**
     * 校验数字签名
     *
     * @param encryptedStr
     * @param publicKey
     * @param sign
     * @return 校验成功返回true，失败返回false
     */
    public static boolean verify(String encryptedStr, String publicKey, String sign) {
        boolean flag = false;
        try {
            //将私钥加密数据字符串转换为字节数组
            byte[] data = encryptedStr.getBytes();
            // 解密由base64编码的公钥
            byte[] bytes = decryptBase64(publicKey);
            // 构造X509EncodedKeySpec对象
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(bytes);
            // 指定的加密算法
            KeyFactory factory = KeyFactory.getInstance(KEY_RSA);
            // 取公钥对象
            PublicKey key = factory.generatePublic(keySpec);
            // 用公钥验证数字签名
            Signature signature = Signature.getInstance(KEY_RSA_SIGNATURE);
            signature.initVerify(key);
            signature.update(data);
            flag = signature.verify(decryptBase64(sign));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }


}
