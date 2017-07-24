package com.dredom.io;

import static junit.framework.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.Test;

public class PipedIOTest {

    @Test
    public void pipedFiltering() throws Exception {
        PipedIO piped = new PipedIO();

        InputStream inStream = new ByteArrayInputStream("Roses are red".getBytes());
        final String filename = "poem.txt";

        PipedIO.Status result = piped.writeToStorage(inStream, filename);

        assertNotNull(result);
        assertEquals(PipedIO.Status.success, result);
    }

    @Test
    public void pipedFilteringAbort() throws Exception {
        PipedIO piped = new PipedIO();
        piped.setMaxSize(56);

        final String body = "Roses are red, violets are blue, "
                + "I never thought, it would be for you.";
        InputStream inStream = new ByteArrayInputStream(body.getBytes());
        final String filename = "poem.txt";

        PipedIO.Status result = piped.writeToStorage(inStream, filename);

        assertNotNull(result);
        assertEquals(PipedIO.Status.interrupted, result);
    }
}
