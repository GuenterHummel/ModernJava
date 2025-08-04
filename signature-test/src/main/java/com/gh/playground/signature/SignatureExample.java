package com.gh.playground.signature;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HexFormat;

/**
 * Example for running SHA512, EC DSA signature generate and verify
 */
public class SignatureExample {

    public static void main(String[] args) {
        System.out.println("==================");
        System.out.println("Signature Example");
        System.out.println("=================\n");

        System.out.println("==================");
        System.out.println("TEST MESSAGE");
        System.out.println("==================");
        String message = "This is a test message";
        System.out.println(message);

        String messageHash = generateSHA512(message);

        String messageSignature = signMessage(messageHash);
        boolean signatureValid = verifySignature(messageHash, messageSignature);

        System.out.println("signature " + (signatureValid ? "is valid" : "is not valid") + "\n");
    }

    private static String generateSHA512(String input) {
        System.out.println("==================");
        System.out.println("GENERATE SHA512");
        System.out.println("==================");

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

            System.out.println("SHA512 HASH: \n" + hashtext);

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

    private static String signMessage (String message)  {

        System.out.println("==================");
        System.out.println("SIGN MESSAGE");
        System.out.println("==================");

        PrivateKey privateKey = getECPrivateKey();

        try {
            Signature signature = Signature.getInstance("SHA256withECDSA", "SunEC");

            signature.initSign(privateKey);
            signature.update( message.getBytes(StandardCharsets.UTF_8));

            byte[] digitalSignature = signature.sign();
            System.out.println("Digital Signature before Base64 Encode: \n" + HexFormat.of().formatHex(digitalSignature));

            String digitalSignatureBase64 = Base64.getEncoder().encodeToString(digitalSignature);
            System.out.println("Digital Signature Base64 Encode: \n" + digitalSignatureBase64);

            return digitalSignatureBase64;

        } catch (InvalidKeyException | NoSuchAlgorithmException | SignatureException | NoSuchProviderException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean verifySignature (String message, String digitalSignatureBase64)  {
        System.out.println("==================");
        System.out.println("VERIFY SIGNATURE");
        System.out.println("==================");

        System.out.println("Digital Signature Base64 Encode: \n" + digitalSignatureBase64);

        byte[] digitalSignature = Base64.getDecoder().decode(digitalSignatureBase64);
        System.out.println("Digital Signature after Base64 Decode: \n" + HexFormat.of().formatHex(digitalSignature));

        try {
            PublicKey publicKey = getECPublicKey();

            Signature signature = Signature.getInstance("SHA256withECDSA", "SunEC");
            signature.initVerify(publicKey);
            signature.update(message.getBytes(StandardCharsets.UTF_8));

            return signature.verify(digitalSignature);
        } catch (InvalidKeyException | NoSuchAlgorithmException | SignatureException | NoSuchProviderException e) {
            throw new RuntimeException(e);
        }
    }

    private static ECPublicKey getECPublicKey() {
        String key = readKey("ec_public.pem");

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

    private static ECPrivateKey getECPrivateKey() {
        String key = readKey("ec_private.pem");

        String privateKeyPEM = key
                .replace("-----BEGIN PRIVATE KEY-----", "")
                .replaceAll(System.lineSeparator(), "")
                .replace("-----END PRIVATE KEY-----", "");

        byte[] encoded = Base64.getDecoder().decode(privateKeyPEM);

        try {
            KeyFactory keyFactory = KeyFactory.getInstance("EC");
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(encoded);
            return (ECPrivateKey) keyFactory.generatePrivate(keySpec);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String readKey(String pemFilename) {
        String filename = "/keys/" + pemFilename;

        try (var inputStream = SignatureExample.class.getResourceAsStream(filename)) {
            return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}