package com.classy4j.crypto;

import org.bouncycastle.crypto.params.RSAKeyParameters;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;

public class RSAUtil {
    private static final String HYBRID_PREFIX = "HYBRID:";
    private static final int GCM_IV_LENGTH = 12;
    private static final int GCM_TAG_LENGTH = 16;

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    /**
     * Generates an RSA key pair.
     *
     * @return Base64 encoded public key
     * @throws Exception if key generation fails
     */
    public static String generateKeyPair(String privateKeyPath) throws Exception {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        KeyPair keyPair = keyGen.generateKeyPair();

        // Store private key
        byte[] privateKeyBytes = keyPair.getPrivate().getEncoded();
        // TODO: Store private key to privateKeyPath using appropriate storage mechanism

        // Return public key in Base64 format
        return Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded());
    }

    /**
     * Encrypts text using hybrid encryption (RSA + AES-GCM).
     *
     * @param text Text to encrypt
     * @param publicKey Base64 encoded public key
     * @return Encrypted data with prefix
     */
    public static String encrypt(String text, String publicKey) throws Exception {
        // Generate AES key
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128);
        SecretKey aesKey = keyGen.generateKey();

        // Generate GCM IV
        byte[] iv = new byte[GCM_IV_LENGTH];
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);

        // Encrypt data with AES-GCM
        Cipher aesCipher = Cipher.getInstance("AES/GCM/NoPadding");
        GCMParameterSpec gcmSpec = new GCMParameterSpec(GCM_TAG_LENGTH * 8, iv);
        aesCipher.init(Cipher.ENCRYPT_MODE, aesKey, gcmSpec);
        byte[] ciphertext = aesCipher.doFinal(text.getBytes());

        // Encrypt AES key with RSA-OAEP
        byte[] publicKeyBytes = Base64.getDecoder().decode(publicKey);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey rsaPublicKey = keyFactory.generatePublic(new X509EncodedKeySpec(publicKeyBytes));
        
        RSAPublicKey rsaKey = (RSAPublicKey) rsaPublicKey;
        RSAKeyParameters rsaKeyParams = new RSAKeyParameters(
            false,
            rsaKey.getModulus(),
            rsaKey.getPublicExponent()
        );
        
        PKCS1OAEPCipher rsaCipher = new PKCS1OAEPCipher(rsaKeyParams, null);
        byte[] encryptedAesKey = rsaCipher.encrypt(aesKey.getEncoded());

        // Combine all components
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream.write(encryptedAesKey);
        outputStream.write(iv);
        outputStream.write(ciphertext);

        return HYBRID_PREFIX + Base64.getEncoder().encodeToString(outputStream.toByteArray());
    }

    /**
     * Decrypts text using hybrid decryption (RSA + AES-GCM).
     *
     * @param encryptedText Encrypted text with prefix
     * @param privateKeyPath Path to private key file
     * @return Decrypted text
     */
    public static String decrypt(String encryptedText, String privateKeyPath) throws Exception {
        if (!encryptedText.startsWith(HYBRID_PREFIX)) {
            throw new IllegalArgumentException("Invalid encrypted text format");
        }

        // Remove prefix and decode Base64
        byte[] encryptedData = Base64.getDecoder().decode(
            encryptedText.substring(HYBRID_PREFIX.length())
        );

        // TODO: Load private key from privateKeyPath
        // For now, assuming private key is loaded into privateKeyBytes
        byte[] privateKeyBytes = null; // Replace with actual private key loading

        // Parse private key
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(
            new PKCS8EncodedKeySpec(privateKeyBytes)
        );
        
        RSAPrivateKey rsaKey = (RSAPrivateKey) privateKey;
        RSAKeyParameters rsaKeyParams = new RSAKeyParameters(
            true,
            rsaKey.getModulus(),
            rsaKey.getPrivateExponent()
        );

        // Extract components
        int rsaKeySize = rsaKey.getModulus().bitLength() / 8;
        byte[] encryptedAesKey = Arrays.copyOfRange(encryptedData, 0, rsaKeySize);
        byte[] iv = Arrays.copyOfRange(encryptedData, rsaKeySize, rsaKeySize + GCM_IV_LENGTH);
        byte[] ciphertext = Arrays.copyOfRange(encryptedData, rsaKeySize + GCM_IV_LENGTH, encryptedData.length);

        // Decrypt AES key
        PKCS1OAEPCipher rsaCipher = new PKCS1OAEPCipher(rsaKeyParams, null);
        byte[] aesKeyBytes = rsaCipher.decrypt(encryptedAesKey);
        SecretKey aesKey = new SecretKeySpec(aesKeyBytes, "AES");

        // Decrypt data
        Cipher aesCipher = Cipher.getInstance("AES/GCM/NoPadding");
        GCMParameterSpec gcmSpec = new GCMParameterSpec(GCM_TAG_LENGTH * 8, iv);
        aesCipher.init(Cipher.DECRYPT_MODE, aesKey, gcmSpec);
        byte[] decryptedBytes = aesCipher.doFinal(ciphertext);

        return new String(decryptedBytes);
    }
}