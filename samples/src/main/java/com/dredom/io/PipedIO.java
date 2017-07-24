/**
 *
 */
package com.dredom.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Filter an InputStream as it gets written.
 * <p>Scenario: Check the size of a file being uploaded and abort if too large.
 * The file is passed to some storage service as an InputStream.
 * Because the file could be large we do not save it in memory, rather consume it as an InputStream
 * and write it to OutputStream.  But the storage service, e.g. Amazon S3, requires an InputStream.
 * Thus the pipe.
 *
 * <p>Write an InputStream to a PipedOuputStream which feeds a PipedInputStream.
 * This PipedInputStream is then the filtered InputStream which can consumed.
 *
 * <p>
 * 1. Prepare to write InputStream to PipedOutputStream.
 * 2. Create PipedInputStream that consumes PipedOutputStream.
 * 3. Start thread with a Callable that returns a Future.
 *    a. This thread calls the storage service with the PipedInputStream.
 *    b. If interrupted, delete file from storage service.
 * 4. Consume upload InputStream, writing to PipedOutputStream.
 * 5. If size exceeded, use Future to interrupt (cancel) Callable, terminate write.
 *
 * @author andre
 *
 */
public class PipedIO {
    public enum Status { success, failure, interrupted; }

    private int pipeSize = 256;
    private long maxSize = 4096;

    public Status writeToStorage(InputStream inputFileStream, String filename) {

        ExecutorService executor = Executors.newFixedThreadPool(1);

        try (PipedInputStream pipedIn = new PipedInputStream(pipeSize);
                PipedOutputStream pipedOut = new PipedOutputStream(pipedIn);
                ) {

            StorageWriter writer = new StorageWriter(pipedIn, filename);
            writer.setStorageService(new S3Service());

            // Start thread to write to S3
            Future<MetaData> future = executor.submit(writer);

            // Filter uploaded stream to check size
            byte[] buf = new byte[pipeSize];
            long size = 0;
            int len;
            while ((len = inputFileStream.read(buf)) > 0) {
                size += len;
                if (size > maxSize) {
                    System.out.println("Size " + size + " exceeds " + maxSize + ". Aborting.");
                    pipedOut.close();
                    future.cancel(true);
                    break;
                }
                pipedOut.write(buf);
            }

            // Get results of write
            long waitMs = 10000;
//            MetaData result = future.get(waitMs, TimeUnit.MILLISECONDS);
            MetaData result = future.get();
            System.out.println("result " + result);

        } catch (Exception e) {
            e.printStackTrace();
            return Status.failure;
        } finally {
            executor.shutdown();
            try {
                inputFileStream.close();
            } catch (IOException e) {
                // Ignore
            }
        }
        return Status.success;
    }

    public void setPipeSize(int pipeSize) {
        this.pipeSize = pipeSize;
    }

    public void setMaxSize(long maxSize) {
        this.maxSize = maxSize;
    }
}
