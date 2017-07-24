/**
 *
 */
package com.dredom.io;

import java.io.InputStream;
import java.util.concurrent.Callable;

/**
 * @author andre
 *
 */
public class StorageWriter implements Callable<MetaData> {

    private S3Service storageService;

    private InputStream inputStream;
    private String filename;

    public StorageWriter(InputStream inputStream, String filename) {
        super();
        this.inputStream = inputStream;
        this.filename = filename;
    }

    @Override
    public MetaData call() throws Exception {
        long size = storageService.writeFile(inputStream, filename);
        if (Thread.currentThread().isInterrupted()) {
            storageService.deleteFile(filename);
            return null;
        }
        MetaData meta = new MetaData(filename, size);
        return meta;
    }

    public void setStorageService(S3Service storageService) {
        this.storageService = storageService;
    }

}
