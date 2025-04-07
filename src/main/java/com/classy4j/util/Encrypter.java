package com.classy4j.util;

import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

@Service
public class Encrypter {

    public  String decryptToken(String tenantId, String token) throws Exception {
        // 获取租户的私钥
        PrivateKey privateKey = getTenantPrivateKey(tenantId);

        // 解码Base64编码的token
        byte[] decodedToken = Base64.getDecoder().decode(token);

        // 使用私钥解密token
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedBytes = cipher.doFinal(decodedToken);

        // 将解密后的字节数组转换为字符串
        return new String(decryptedBytes);
    }

    private  PrivateKey getTenantPrivateKey(String tenantId) throws Exception {
        // 这里假设你有一个方法来获取租户的私钥
        // 例如，从数据库或其他存储中获取私钥的字节数组
        byte[] privateKeyBytes = getPrivateKeyBytesFromStorage(tenantId);

        // 将私钥字节数组转换为PrivateKey对象
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        return keyFactory.generatePrivate(keySpec);
    }

    private  byte[] getPrivateKeyBytesFromStorage(String tenantId) throws Exception {
        // 实现从存储中获取私钥字节数组的逻辑
        // 这里只是一个示例，你需要根据实际情况实现
        // 例如，从数据库中查询私钥
        return tenantId.getBytes(); // 替换为实际的私钥字节数组
    }

    public static void main(String[] args) {
        try {
            String tenantId = "70a70885-be37-49e5-92f6-0fd221e8175b";
            String token = "SFlCUklEOgWvumj6AOYqIo5sWOcE9CBrVIvBgjevSQX35wQsMi+Wlaq7M+kBq3V5/uh+01A3Zp+NDN597RoLi3ndwKYN7g65i22ASBmfch8PS99BSDzlb5WpCu2U5W8FL19StTep/3NncFVFZ7WzxYKJbGkdxte0/oIdhoBmo7ckYGu+nNGsYzjFvOrgXib6GjPxp5WO7o3t+3szNxyWy2NB2wkgYNlMneiQc/w0zSCvi3sBNVoNt1ikDm1sEWa/Jm5R257UrKBvSPpaj3K7BhAx23j2BI97YQylrKW0UM1vluORYr90yOah+52JnMNrEuJo4rty2AYTGk42/VToCf3Q5yix6tR3yIPYCPRwrjLpRqC/TA8ZQW9l21BK674tq0/cb+EJRK2iGlTAHB2giHu6KgaZNcd6H2gdaNlcC0x+61zS3fQ/nE7F";
            String decryptedToken = new Encrypter().decryptToken(tenantId, token);
            System.out.println("Decrypted Token: " + decryptedToken);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
