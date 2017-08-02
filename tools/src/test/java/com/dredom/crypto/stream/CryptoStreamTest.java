package com.dredom.crypto.stream;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.junit.Test;

import junit.framework.Assert;

public class CryptoStreamTest {

    CryptoStreamExample crypto = new CryptoStreamExample();

    @Test
    public void writeStringEncrypted() throws Exception {
        final String body = "How now brown cow.";
        final String filename = "target/cryptotest.txt";
        File file = new File(filename);
        FileOutputStream fos = new FileOutputStream(file);

        crypto.writeCrypto(body, fos);

        FileInputStream fis = new FileInputStream(file);
        int count = 0;
        int b;
        while (( b = fis.read()) != -1) {
            count++;
        }
        fis.close();
//        Assert.assertEquals(body.length(), count);    // bigger

        // Decrypt encypted file
        fis = new FileInputStream(file);
        InputStream resultInputStream = crypto.readCrypto(fis);

        // Get output
        ByteArrayOutputStream resultOutput = new ByteArrayOutputStream();
        while ((b = resultInputStream.read()) != -1) {
            resultOutput.write(b);
        }
        String resultString = resultOutput.toString();
        Assert.assertEquals(body, resultString);
    }

    @Test
    public void writeFileEncrypted() throws Exception {
        final String filename = "wave1.jpg";
        final String filenameout = "target/" + filename + ".crypto";
        File filein = new File(filename);
        File fileout = new File(filenameout);
        FileInputStream fis = new FileInputStream(filein);
        FileOutputStream fos = new FileOutputStream(fileout);

        crypto.writeCrypto(fis, fos);
    }
}
