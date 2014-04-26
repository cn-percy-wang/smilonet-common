package com.smilonet.common.security.coder;

import java.io.ByteArrayInputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

/**
 * RSA安全编码组件
 * 
 * @author wanglong(a)smilonet.com
 * @since 2012-03-19
 */
public abstract class RSACoder extends Coder {
    public static final String KEY_ALGORITHM = "RSA";
    // public static final String SIGNATURE_ALGORITHM = "MD5withRSA";
    public static final String SIGNATURE_ALGORITHM = "SHA1withRSA";

    private static final String PUBLIC_KEY = "RSAPublicKey";
    private static final String PRIVATE_KEY = "RSAPrivateKey";

    /**
     * 用私钥对信息生成数字签名
     * 
     * @param data
     *            加密数据
     * @param privateKeyBytes
     *            私钥
     * 
     * @return
     * @throws Exception
     */
    public static String sign(byte[] data, byte[] privateKeyBytes) throws Exception {

        // 构造PKCS8EncodedKeySpec对象
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);

        // KEY_ALGORITHM 指定的加密算法
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);

        // 取私钥匙对象
        PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);

        // 用私钥对信息生成数字签名
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initSign(priKey);
        signature.update(data);

        return encryptBASE64(signature.sign());
    }

    /**
     * 用私钥对信息生成数字签名
     * 
     * @param data
     *            加密数据
     * @param privateKey
     *            私钥
     * 
     * @return
     * @throws Exception
     */
    public static String sign(byte[] data, String privateKey) throws Exception {
        // 解密由base64编码的私钥
        byte[] keyBytes = decryptBASE64(privateKey);

        // 构造PKCS8EncodedKeySpec对象
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);

        // KEY_ALGORITHM 指定的加密算法
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);

        // 取私钥匙对象
        PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);

        // 用私钥对信息生成数字签名
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initSign(priKey);
        signature.update(data);

        return encryptBASE64(signature.sign());
    }

    /**
     * 校验数字签名
     * 
     * @param data
     *            加密数据
     * @param publicKey
     *            公钥
     * @param sign
     *            数字签名
     * 
     * @return 校验成功返回true 失败返回false
     * @throws Exception
     * 
     */
    public static boolean verify(byte[] data, String publicKey, String sign) throws Exception {

        // 解密由base64编码的公钥
        byte[] keyBytes = decryptBASE64(publicKey);

        // 构造X509EncodedKeySpec对象
        // X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        //
        // // KEY_ALGORITHM 指定的加密算法
        // KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        //
        // // 取公钥匙对象
        // PublicKey pubKey = keyFactory.generatePublic(keySpec);

        CertificateFactory certificateFactory = CertificateFactory.getInstance("X509");
        Certificate certificate = certificateFactory.generateCertificate(new ByteArrayInputStream(keyBytes));
        PublicKey pubKey = certificate.getPublicKey();

        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initVerify(pubKey);
        signature.update(data);

        // 验证签名是否正常
        return signature.verify(decryptBASE64(sign));
    }

    /**
     * 解密<br>
     * 用私钥解密
     * 
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPrivateKey(byte[] data, String key) throws Exception {
        // 对密钥解密
        byte[] keyBytes = decryptBASE64(key);

        // 取得私钥
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);

        // 对数据解密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateKey);

        return cipher.doFinal(data);
    }

    /**
     * 解密<br>
     * 用私钥解密
     * 
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPublicKey(byte[] data, String key) throws Exception {
        // 对密钥解密
        byte[] keyBytes = decryptBASE64(key);

        // 取得公钥
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicKey = keyFactory.generatePublic(x509KeySpec);

        // 对数据解密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, publicKey);

        return cipher.doFinal(data);
    }

    /**
     * 加密<br>
     * 用公钥加密
     * 
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPublicKey(byte[] data, String key) throws Exception {
        // 对公钥解密
        byte[] keyBytes = decryptBASE64(key);

        // 取得公钥
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicKey = keyFactory.generatePublic(x509KeySpec);

        // 对数据加密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        return cipher.doFinal(data);
    }

    /**
     * 加密<br>
     * 用私钥加密
     * 
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPrivateKey(byte[] data, String key) throws Exception {
        // 对密钥解密
        byte[] keyBytes = decryptBASE64(key);

        // 取得私钥
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);

        // 对数据加密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);

        return cipher.doFinal(data);
    }

    /**
     * 取得私钥
     * 
     * @param keyMap
     * @return
     * @throws Exception
     */
    public static String getPrivateKey(Map<String, Object> keyMap) throws Exception {
        Key key = (Key) keyMap.get(PRIVATE_KEY);

        return encryptBASE64(key.getEncoded());
    }

    /**
     * 取得公钥
     * 
     * @param keyMap
     * @return
     * @throws Exception
     */
    public static String getPublicKey(Map<String, Object> keyMap) throws Exception {
        Key key = (Key) keyMap.get(PUBLIC_KEY);

        return encryptBASE64(key.getEncoded());
    }

    /**
     * 初始化密钥
     * 
     * @return
     * @throws Exception
     */
    public static Map<String, Object> initKey() throws Exception {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        keyPairGen.initialize(1024);

        KeyPair keyPair = keyPairGen.generateKeyPair();

        // 公钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();

        // 私钥
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

        Map<String, Object> keyMap = new HashMap<String, Object>(2);

        keyMap.put(PUBLIC_KEY, publicKey);
        keyMap.put(PRIVATE_KEY, privateKey);
        return keyMap;
    }

    public static void main(String[] args) throws Exception {

        String publicKey = "MIICtjCCAh+gAwIBAgIJAKh78D+AbrxDMA0GCSqGSIb3DQEBBQUAMEcxCzAJBgNV" + "BAYTAkNOMREwDwYDVQQIEwhTaSBDaHVhbjERMA8GA1UEBxMIQ2hlbmcgRHUxEjAQ" + "BgNVBAoTCWlrYW1vYmlsZTAeFw0xMTEyMjkwNTI2MzVaFw0xMjAxMjgwNTI2MzVa" + "MEcxCzAJBgNVBAYTAkNOMREwDwYDVQQIEwhTaSBDaHVhbjERMA8GA1UEBxMIQ2hl" + "bmcgRHUxEjAQBgNVBAoTCWlrYW1vYmlsZTCBnzANBgkqhkiG9w0BAQEFAAOBjQAw" + "gYkCgYEAte+OE2aauLEtc2GT4ORSmzmuDy7OTkHr8wpb0o13RrNqPOtuOJCBTx+P" + "mnb/OAUsk6wGILC10i5zreE3gtTBRnvsvhA6H2rg8607pvLkuXno7C/bl4m8RXM9" + "cmoWN/CUYTrBN9c896Hzf++BSV2Aiqa1D+snuOuVU/cx0d0fNN0CAwEAAaOBqTCB" + "pjAdBgNVHQ4EFgQUt6mc3+d7mL39aL9y4Rsy39nrlCAwdwYDVR0jBHAwboAUt6mc" + "3+d7mL39aL9y4Rsy39nrlCChS6RJMEcxCzAJBgNVBAYTAkNOMREwDwYDVQQIEwhT" + "aSBDaHVhbjERMA8GA1UEBxMIQ2hlbmcgRHUxEjAQBgNVBAoTCWlrYW1vYmlsZYIJ"
                + "AKh78D+AbrxDMAwGA1UdEwQFMAMBAf8wDQYJKoZIhvcNAQEFBQADgYEAOS0NNC2C" + "BeFSv8lhDDSOFs42g4spgu+FNiXMzuLsXrI1o1rqBMdP3rj4FaoETHOt959iDAXA" + "Zia5uYKDrxGSljAIgkK9TXwh6XJYEMc8CYTwD2zZ3KNvbuC6okVSW0ySj+OBcQE9" + "RE0DGCikYDcaMR2StFvQtUHdeewN70dJ9Zw=";

        // read public key
        // FileInputStream fsPublicKey = new FileInputStream(new
        // File("d:/cf.cert.pem"));
        // BufferedInputStream bfsPublicKey = new
        // BufferedInputStream(fsPublicKey);
        // byte[] bytePublicKey = new byte[bfsPublicKey.available()];
        // bfsPublicKey.read(bytePublicKey);
        // bfsPublicKey.close();

        // 使用KeyFactory产生公钥

        // try{
        // BufferedReader br = new BufferedReader(new
        // FileReader("d:/cf.cert.pem"));
        // String s = br.readLine();
        // StringBuffer publickey = new StringBuffer();
        // s = br.readLine();
        // while (s.charAt(0) != '-') {
        // publickey.append(s + "\r");
        // s = br.readLine();
        // }
        //
        // byte[] keybyte = decryptBASE64(publickey.toString());
        //
        // KeyFactory kf = KeyFactory.getInstance("RSA");
        //
        // X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keybyte);
        //
        // kf.generatePublic(keySpec);
        // }catch(Exception e)
        // {
        // e.printStackTrace();
        // }

        // build public key
        // X509EncodedKeySpec pubKeySpec = new
        // X509EncodedKeySpec(decryptBASE64(new String(publicKey)));
        // KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        // PublicKey pubKey = keyFactory.generatePublic(pubKeySpec);

        // CertificateFactory certificateFactory =
        // CertificateFactory.getInstance("X509");
        // Certificate certificate = certificateFactory.generateCertificate(new
        // ByteArrayInputStream(decryptBASE64(new String(publicKey))));
        // certificate.getPublicKey();

        // 验证签名
        boolean status = RSACoder.verify("123".getBytes(), publicKey, "sFHJkDCKOB5DgHbaYhx9+93xI6j54hG+auTj/seWeN44CZwKklC4sgPQ1H0qysvkkfTfwDtkw7D8aWcaa5GCDLEzTQbSIe5jXwoQurxPs3B3lBUCGL7DQiqtcW6cbiKLweekIL64juEqp2JDIQJaE8d23c5aHe/oGyaxHL1+A8Y=");
        System.err.println("状态:\r" + status);
    }
}
