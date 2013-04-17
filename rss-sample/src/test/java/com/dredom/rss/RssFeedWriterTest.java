package com.dredom.rss;

import java.io.PrintWriter;
import java.util.Date;

import org.junit.Test;

import com.dredom.rss.model.BlogData;
import com.dredom.rss.model.BlogEntry;

public class RssFeedWriterTest {

    @Test
    public void write() throws Exception {
        final String title = "My Blog!";
        final Date pubDate = new Date();
        final String link = "http://www.dredom.com/blog";
        BlogData data = new BlogData();
        data.setTitle(title);
        data.setDescription("Blogging the world as we know it.");
        data.setLink(link);
        data.setPubDate(pubDate);

        final String entryTitle = "Today I blogged for the first time!";
        BlogEntry entry = new BlogEntry();
        entry.setTitle(entryTitle);
        entry.setDescription("Revealing the inner self.");
        entry.setLink(link + "/1");
        entry.setPubDate(pubDate);
        data.setEntries(new BlogEntry[] { entry });

        RssFeedWriter service = new RssFeedWriter();
        service.write(data, new PrintWriter(System.out));
    }
}
