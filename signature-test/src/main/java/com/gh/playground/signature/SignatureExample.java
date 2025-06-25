package com.gh.playground.signature;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HexFormat;
import java.util.function.Consumer;

/**
 * Example for usage of synchronized keyword
 */
public class SignatureExample {

    /**
     * @param args command line args
     */
    public static void main(String[] args) {
    }

    private static String generateSHA512(String input)
    {
        try {
            // getInstance() method is called with algorithm SHA-512
            MessageDigest md = MessageDigest.getInstance("SHA-512");

            // digest() method is called
            // to calculate message digest of the input string
            // returned as array of byte
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);

            // Ensure the hash is padded to 128 characters
            while (hashtext.length() < 128) {
                hashtext = "0" + hashtext;
            }

            // return the HashText
            return hashtext;
        }

        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private static String encodeToBase64(String input) {
        return Base64.getEncoder().encodeToString(HexFormat.of().parseHex(input));
    }

    private static String readKey(String pemFilename) {
        Path keyFilePath = null;
        try {
            keyFilePath = Path.of(SignatureExample.class.getClassLoader().getResource(pemFilename).toURI());

            String key = new String(Files.readAllBytes(keyFilePath), Charset.defaultCharset());
            return key;
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            return "";
        }
    }

    private ECPublicKey getECPublicKey()
    {
        String key = readKey(SignatureExample.class.getClassLoader().getResource("ec_public.pem").getPath());
        String publicKeyPEM = key
                .replace("-----BEGIN PUBLIC KEY-----", "")
                .replaceAll(System.lineSeparator(), "")
                .replace("-----END PUBLIC KEY-----", "");

        byte[] encoded = Base64.getDecoder().decode(publicKeyPEM);

        try {
            KeyFactory keyFactory = KeyFactory.getInstance("EC");
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(encoded);
            return (ECPublicKey) keyFactory.generatePublic(keySpec);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private ECPrivateKey getECPrivateKey()
    {
        String key = readKey(ApiExampleV3.class.getClassLoader().getResource("ec_private.pem").getPath());
        String privateKeyPEM = key
                .replace("-----BEGIN PRIVATE KEY-----", "")
                .replaceAll(System.lineSeparator(), "")
                .replace("-----END PRIVATE KEY-----", "");

        byte[] encoded = Base64.getDecoder().decode(privateKeyPEM);

        try {
            KeyFactory keyFactory = KeyFactory.getInstance("EC");
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(encoded);
            return (ECPrivateKey) keyFactory.generatePrivate(keySpec);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }