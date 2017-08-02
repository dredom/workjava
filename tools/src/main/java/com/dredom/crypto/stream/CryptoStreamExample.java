package com.dredom.crypto.stream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Properties;

import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.crypto.stream.CryptoInputStream;
import org.apache.commons.crypto.stream.CryptoOutputStream;

public class CryptoStreamExample {
    private String key = "1234567890123456";
    private String algorithm = "AES";
    private String transform = "AES/CBC/PKCS5Padding";

    public static void main(String[] args) throws IOException {
        final SecretKeySpec key = new SecretKeySpec(getUTF8Bytes("1234567890123456"), "AES");
        final IvParameterSpec iv = new IvParameterSpec(getUTF8Bytes("1234567890123456"));
        Properties properties = new Properties();
        final String transform = "AES/CBC/PKCS5Padding";

        String input = "hello world!";
        // Encryption with CryptoOutputStream.

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try (CryptoOutputStream cos = new CryptoOutputStream(transform, properties, outputStream, key, iv)) {
            cos.write(getUTF8Bytes(input));
            cos.flush();
        }

        // The encrypted data:
        System.out.println("Encrypted: " + Arrays.toString(outputStream.toByteArray()));

        // Decryption with CryptoInputStream.
        InputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());

        try (CryptoInputStream cis = new CryptoInputStream(transform, properties, inputStream, key, iv)) {
            byte[] decryptedData = new byte[1024];
            int decryptedLen = 0;
            int i;
            while ((i = cis.read(decryptedData, decryptedLen, decryptedData.length - decryptedLen)) > -1) {
                decryptedLen += i;
            }
            System.out.println("Decrypted: " + new String(decryptedData, 0, decryptedLen, StandardCharsets.UTF_8));
        }
    }

    /**
     * Converts String to UTF8 bytes
     *
     * @param input the input string
     * @return UTF8 bytes
     */
    private static byte[] getUTF8Bytes(String input) {
        return input.getBytes(StandardCharsets.UTF_8);
    }

    public void writeCrypto(String input, OutputStream output) throws IOException {
        final SecretKeySpec secretKey = new SecretKeySpec(getUTF8Bytes(key), algorithm);
        final IvParameterSpec initializationVector = new IvParameterSpec(getUTF8Bytes(key));
        Properties properties = new Properties();

        try (CryptoOutputStream cos = new CryptoOutputStream(transform, properties, output, secretKey, initializationVector)) {
            cos.write(getUTF8Bytes(input));
            cos.flush();
        }
    }

    public void writeCrypto(InputStream input, OutputStream output) throws IOException {
        final SecretKeySpec secretKey = new SecretKeySpec(getUTF8Bytes(key), algorithm);
        final IvParameterSpec initializationVector = new IvParameterSpec(getUTF8Bytes(key));
        Properties properties = new Properties();

        try (CryptoOutputStream cos = new CryptoOutputStream(transform, properties, output, secretKey, initializationVector)) {
            byte[] buf = new byte[1024 * 16];
            int len;
            while ((len = input.read(buf)) != -1) {
                cos.write(buf, 0, len);
            }
            input.close();
            cos.flush();
        }
    }

    public InputStream readCrypto(InputStream inputStream) throws IOException  {
        final SecretKeySpec secretKey = new SecretKeySpec(getUTF8Bytes(key), algorithm);
        final IvParameterSpec initializationVector = new IvParameterSpec(getUTF8Bytes(key));
        Properties properties = new Properties();
        CryptoInputStream cis = new CryptoInputStream(transform, properties, inputStream, secretKey, initializationVector);
        return cis;
    }
}
