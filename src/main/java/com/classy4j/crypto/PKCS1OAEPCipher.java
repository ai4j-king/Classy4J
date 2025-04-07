package com.classy4j.crypto;

import org.bouncycastle.crypto.AsymmetricBlockCipher;
import org.bouncycastle.crypto.encodings.OAEPEncoding;
import org.bouncycastle.crypto.engines.RSAEngine;
import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.crypto.params.RSAKeyParameters;
import org.bouncycastle.util.Arrays;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * PKCS#1 OAEP cipher implementation for RSA encryption/decryption.
 * This class provides functionality equivalent to Python's PKCS1_OAEP implementation.
 */
public class PKCS1OAEPCipher {
    private final RSAKeyParameters key;
    private final SecureRandom random;
    private final byte[] label;

    /**
     * Creates a new PKCS1 OAEP cipher instance.
     *
     * @param key RSA key parameters (public or private)
     * @param label Optional label (can be null)
     */
    public PKCS1OAEPCipher(RSAKeyParameters key, byte[] label) {
        this.key = key;
        this.random = new SecureRandom();
        this.label = label != null ? Arrays.clone(label) : new byte[0];
    }

    /**
     * Encrypts a message using PKCS#1 OAEP.
     *
     * @param message The message to encrypt
     * @return The encrypted message
     * @throws Exception If encryption fails
     */
    public byte[] encrypt(byte[] message) throws Exception {
        AsymmetricBlockCipher cipher = new OAEPEncoding(
            new RSAEngine(),
            new SHA1Digest(),
            new SHA1Digest(),
            label
        );
        cipher.init(true, key);
        return cipher.processBlock(message, 0, message.length);
    }

    /**
     * Decrypts a message using PKCS#1 OAEP.
     *
     * @param ciphertext The encrypted message
     * @return The decrypted message
     * @throws Exception If decryption fails
     */
    public byte[] decrypt(byte[] ciphertext) throws Exception {
        AsymmetricBlockCipher cipher = new OAEPEncoding(
            new RSAEngine(),
            new SHA1Digest(),
            new SHA1Digest(),
            label
        );
        cipher.init(false, key);
        return cipher.processBlock(ciphertext, 0, ciphertext.length);
    }

    /**
     * Creates a new PKCS1OAEPCipher instance.
     *
     * @param modulus RSA modulus
     * @param exponent RSA exponent (public or private)
     * @param isPrivate true if the key is private, false if public
     * @param label Optional label (can be null)
     * @return A new PKCS1OAEPCipher instance
     */
    public static PKCS1OAEPCipher getInstance(
        BigInteger modulus,
        BigInteger exponent,
        boolean isPrivate,
        byte[] label
    ) {
        RSAKeyParameters key = new RSAKeyParameters(isPrivate, modulus, exponent);
        return new PKCS1OAEPCipher(key, label);
    }
}