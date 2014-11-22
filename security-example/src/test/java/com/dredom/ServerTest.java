package com.dredom;

import static java.lang.System.out;

import java.security.Security;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.SecretKeySpec;

import com.dredom.Server;

public class ServerTest {

    public static void main(String[] args) throws Exception {
        Server server = new Server();
        long nonce = server.getNonce();
//        Thread.sleep(1001L);
        boolean valid = server.validateNonce(nonce);
        out.printf(" nonce '%d' is %s \n", nonce, (valid ? "VALID" : "BAD"));

//        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DSA");
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        SecretKey secretKey = new SecretKeySpec("secretlyxxxxxxxx".getBytes(),"AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding", "BC");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        cipher.update("password".getBytes());
        byte[] ciphertext = cipher.doFinal();
        String encryptedPassword = new String(ciphertext);
        boolean validPassword = server.loginEncrypted(nonce, encryptedPassword);
        out.printf(" password '%s' is %s \n", encryptedPassword, (validPassword ? "VALID" : "BAD"));
    }
}
