package com.dredom.rss;

import java.net.URL;

import org.junit.Test;

public class RssReaderTest {

    static String filename = "src/test/resources/rss-sample.xml";

    @Test
    public void printFeed() throws Exception {
        RssReader service = new RssReader();
        URL feedUrl = new URL("file", "localhost", filename);
        service.printFeed(feedUrl);
    }
}
