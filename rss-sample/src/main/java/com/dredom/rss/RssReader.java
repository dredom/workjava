package com.dredom.rss;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

public class RssReader {

    public void printFeed(URL feedUrl) throws IllegalArgumentException, FeedException, IOException {
        SyndFeedInput input = new SyndFeedInput();
        SyndFeed feed = input.build(new XmlReader(feedUrl));
        System.out.printf("title=%s\n", feed.getTitle());
        System.out.printf("description=%s\n", feed.getDescription());
        System.out.printf("link=%s\n", feed.getLink());
        System.out.printf("type=%s\n", feed.getFeedType());
        List<SyndEntry> entries = feed.getEntries();
        for (SyndEntry entry : entries) {
            System.out.println(entry);

        }
    }

}
