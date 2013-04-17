package com.dredom.rss;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import com.dredom.rss.model.BlogData;
import com.dredom.rss.model.BlogEntry;
import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndContentImpl;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.feed.synd.SyndFeedImpl;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedOutput;

public class RssFeedWriter {

    public void write(BlogData data, Writer writer) throws IOException, FeedException {
        // Build the feed
        SyndFeed feed = toFeed(data);
        // Build the entries
        // Write the output
        SyndFeedOutput output = new SyndFeedOutput();
        output.output(feed, writer);
    }

    private SyndFeed toFeed(BlogData data) {
        SyndFeed feed = new SyndFeedImpl();
        feed.setFeedType("rss_2.0");
        feed.setTitle(data.getTitle());
        feed.setDescription(data.getDescription());
        feed.setLink(data.getLink());
        feed.setPublishedDate(data.getPubDate());
        BlogEntry[] entries = data.getEntries();
        if (entries != null) {
            List<SyndEntry> feedEntries = new ArrayList<SyndEntry>();
            for (BlogEntry entry : entries) {
                feedEntries.add(toFeedEntry(entry));
            }
            feed.setEntries(feedEntries);
        }
        return feed;
    }

    private SyndEntry toFeedEntry(BlogEntry data) {
        assert data.getTitle() != null;
        assert data.getDescription() != null;
        assert data.getLink() != null;
        SyndEntry entry = new SyndEntryImpl();
        entry.setTitle(data.getTitle());
        SyndContent content = new SyndContentImpl();
        content.setValue(data.getDescription());
        entry.setDescription(content);
        entry.setLink(data.getLink());
        entry.setPublishedDate(data.getPubDate());
        return entry;
    }

}
