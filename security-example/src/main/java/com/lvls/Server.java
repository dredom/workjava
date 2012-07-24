package com.lvls;

import static java.lang.System.err;

//import java.security.MessageDigest;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class Server {

    static long PRIVATE_SECRET = 123L;
    static long VARIANCE = 1000L;
    static String VALID_PASSWORD = "password";
    private static final String ENCRYPTION_KEY = "AES";

    private SecretKey secretKey;

    public void initEncryption() {
        secretKey = new SecretKeySpec("secret".getBytes(), ENCRYPTION_KEY);
    }

    public long getNonce() {
        long time = System.currentTimeMillis();
        long nonce = time + PRIVATE_SECRET;
        return nonce;
//        MessageDigest digestive = MessageDigest.getInstance("MD5");
//        digestive.update(nonce.getBytes());
    }

    boolean validateNonce(long nonce) {
        long time = nonce - PRIVATE_SECRET;
        long now = System.currentTimeMillis();
        boolean valid = now <= (time + VARIANCE);
        return valid;
    }

    public boolean login(long nonce, String password)  {
        if (! validateNonce(nonce)) {
            err.printf(" >>Server: bad nonce '%d' \n", nonce);
            return false;
        }

        boolean valid = password.equals(password);
        return valid;
    }

    public boolean loginEncrypted(long nonce, String encryptedPassword) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        if (! validateNonce(nonce)) {
            err.printf(" >>Server: bad nonce '%d' \n", nonce);
            return false;
        }
        Cipher cipher = Cipher.getInstance(ENCRYPTION_KEY);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        byte[] cleartext = cipher.doFinal(encryptedPassword.getBytes());
        String password = new String(cleartext);
        boolean valid = password.equals(VALID_PASSWORD);

        return valid;
    }
}
