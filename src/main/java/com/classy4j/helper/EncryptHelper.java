package com.classy4j.helper;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import com.classy4j.util.Encrypter;
import org.springframework.stereotype.Component;

@Component
public class EncryptHelper {
    private static final String ALGORITHM = "AES";
    private static final String DEFAULT_SECRET_KEY = "classy4j_default_key_123456789";

    public String encrypt(String value, String secretKey) {
        try {
            SecretKeySpec key = new SecretKeySpec(getKey(secretKey), ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encryptedBytes = cipher.doFinal(value.getBytes());
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            throw new RuntimeException("Failed to encrypt value", e);
        }
    }

    public String decrypt(String encryptedValue, String secretKey) {
        try {
            SecretKeySpec key = new SecretKeySpec(getKey(secretKey), ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedValue));
            return new String(decryptedBytes, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException("Failed to decrypt value", e);
        }
    }

    private byte[] getKey(String secretKey) {
        if (secretKey == null || secretKey.isEmpty()) {
            secretKey = DEFAULT_SECRET_KEY;
        }
        return secretKey.getBytes(StandardCharsets.UTF_8);
    }

    public static void main(String[] args) {
        try {
            String tenantId = "70a70885-be37-49e5-92f6-0fd221e8175b";
            String token = "SFlCUklEOgWvumj6AOYqIo5sWOcE9CBrVIvBgjevSQX35wQsMi+Wlaq7M+kBq3V5/uh+01A3Zp+NDN597RoLi3ndwKYN7g65i22ASBmfch8PS99BSDzlb5WpCu2U5W8FL19StTep/3NncFVFZ7WzxYKJbGkdxte0/oIdhoBmo7ckYGu+nNGsYzjFvOrgXib6GjPxp5WO7o3t+3szNxyWy2NB2wkgYNlMneiQc/w0zSCvi3sBNVoNt1ikDm1sEWa/Jm5R257UrKBvSPpaj3K7BhAx23j2BI97YQylrKW0UM1vluORYr90yOah+52JnMNrEuJo4rty2AYTGk42/VToCf3Q5yix6tR3yIPYCPRwrjLpRqC/TA8ZQW9l21BK674tq0/cb+EJRK2iGlTAHB2giHu6KgaZNcd6H2gdaNlcC0x+61zS3fQ/nE7F";
            String decryptedToken = new EncryptHelper().decrypt(token, tenantId);
            System.out.println("Decrypted Token: " + decryptedToken);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}