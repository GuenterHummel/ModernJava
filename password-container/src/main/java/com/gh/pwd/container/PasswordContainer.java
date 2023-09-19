package com.gh.pwd.container;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.HexFormat;
import java.util.Objects;

public class PasswordContainer {
    private static final String SECRET_KEY = "SECRET_KEY";
    private static final String SALT = "SALT_SALT_SALT";
    private static final IvParameterSpec IV_SPEC =
            new IvParameterSpec(new byte[] {1, 2, 3, 4, 5, 6, 7, 8, 1, 2, 3, 4, 5, 6, 7, 8});

    private record Result(SecretKeySpec secretKey, Cipher cipher) {
        public Result {
            Objects.requireNonNull(secretKey);
            Objects.requireNonNull(cipher);
        }
    }

    Result getEncryptingCipher () {
        return getCipher(Cipher.ENCRYPT_MODE);
    }

    Result getDecryptingCipher () {
        return getCipher(Cipher.DECRYPT_MODE);
    }

    Result getCipher (int cipherMode) {
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALT.getBytes(), 65536, 256);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(cipherMode, secretKey, IV_SPEC);

            return new Result (secretKey, cipher);
        } catch (Exception e) {
            System.out.println("Error while encrypting: " + e);
        }
        return null;
    }

    /**
     * Encrypt a plain text string to Base64 encoded encrypted string
     * @param plainText the plain text string
     * @return the encrypted string
     */
    public String encryptToBase64 (String plainText) {
        try {
            Cipher cipher = getEncryptingCipher().cipher();
            byte[] encryptedData = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(encryptedData);
        } catch (Exception e) {
            System.out.println("Error while encrypting: " + e);
        }
        return "";
    }

    /**
     * Decrypt a Base64 encoded encrypted string to a plain text string
     * @param encryptedTextAsBase64 the encrypted string
     * @return the plain text string
     */
    public String decryptFromBase64 (String encryptedTextAsBase64) {
        try {
            Cipher cipher = getDecryptingCipher().cipher();
            byte[] inputData = Base64.getDecoder().decode(encryptedTextAsBase64);
            byte[] decryptedData = cipher.doFinal(inputData);
            return new String(decryptedData);
        } catch (Exception e) {
            System.out.println("Error while decrypting: " + e);
        }
        return "";
    }

    /**
     * Encrypt a plain text string to an encrypted Hex string
     * @param plainText the plain text string
     * @return the encrypted text string
     */
    public String encryptToHexString (String plainText) {
        try {
            Cipher cipher = getEncryptingCipher().cipher();
            byte[] encryptedData = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
            return HexFormat.of().formatHex(encryptedData);
        } catch (Exception e) {
            System.out.println("Error while encrypting: " + e);
        }
        return "";
    }

    /**
     * Decrypt an encrypted Hex string to a plain text string
     * @param encryptedTextAsHexString the encrypted string
     * @return the plain text string
     */
    public String decryptFromHexString (String encryptedTextAsHexString) {
        try {
            Cipher cipher = getDecryptingCipher().cipher();
            byte[] inputData = HexFormat.of().parseHex(encryptedTextAsHexString);
            byte[] decryptedData = cipher.doFinal(inputData);
            return new String(decryptedData);
        } catch (Exception e) {
            System.out.println("Error while decrypting: " + e);
        }
        return "";
    }
}