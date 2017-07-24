package com.dredom.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * Dummy S3 storage service.
 */
public class S3Service {

    public long writeFile(InputStream input, String filename) {
        long size = 0;
        byte[] buf = new byte[128];
        int len;
        try {
            while ((len = input.read(buf)) > 0) {
                size += len;
                System.out.println("len=" + len);
                System.out.println(new String(buf, 0, len));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                input.close();
            } catch (IOException e) {
            }
        }
        System.out.println("S3 write " + filename + " size=" + size);
        return size;
    }

    public void deleteFile(String filename) {
        System.out.println("S3 delete " + filename);

    }
}
