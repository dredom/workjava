package com.lvls;

import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * http://www.java2s.com/Code/Java/Security/CBCusingDESwithanIVbasedonanonceahypotheticalmessagenumber.htm
 * CBC using DES with an IV based on a nonce. In this case a hypothetical
 * message number.
 *
 * @author auntiedt
 *
 */
public class TwoWaySample {
    public static void main(String[] args) throws Exception {
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        byte[] input = "www.java2s.com".getBytes();
        byte[] keyBytes = new byte[] { 0x01, 0x23, 0x45, 0x67, (byte) 0x89, (byte) 0xab, (byte) 0xcd,
            (byte) 0xef };
        byte[] msgNumber = new byte[] { 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 };

        IvParameterSpec zeroIv = new IvParameterSpec(new byte[8]);
        SecretKeySpec key = new SecretKeySpec(keyBytes, "DES");
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS7Padding", "BC");
        System.out.println("input : " + new String(input));

        // encryption pass
        cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);
        IvParameterSpec encryptionIv = new IvParameterSpec(cipher.doFinal(msgNumber), 0, 8);

        // encrypt message
        cipher.init(Cipher.ENCRYPT_MODE, key, encryptionIv);
        byte[] cipherText = new byte[cipher.getOutputSize(input.length)];
        int ctLength = cipher.update(input, 0, input.length, cipherText, 0);
        ctLength += cipher.doFinal(cipherText, ctLength);
        System.out.println("cipher: " + new String(cipherText) + " bytes: " + ctLength);
        cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);

        // decrypt message

        IvParameterSpec decryptionIv = new IvParameterSpec(cipher.doFinal(msgNumber), 0, 8);
        cipher.init(Cipher.DECRYPT_MODE, key, decryptionIv);
        byte[] plainText = new byte[cipher.getOutputSize(ctLength)];
        int ptLength = cipher.update(cipherText, 0, ctLength, plainText, 0);
        ptLength += cipher.doFinal(plainText, ptLength);
        System.out.println("plain : " + new String(plainText) + " bytes: " + ptLength);
      }
}
