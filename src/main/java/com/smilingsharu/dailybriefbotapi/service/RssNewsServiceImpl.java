package com.smilingsharu.dailybriefbotapi.service;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.smilingsharu.dailybriefbotapi.dto.NewsItemResponse;
import org.springframework.stereotype.Service;
import com.rometools.rome.io.XmlReader;


import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

@Service
public class RssNewsServiceImpl implements RssNewsService {

    @Override
    public List<NewsItemResponse> getTopHeadlines(
            String rssUrl,
            int limit) {

        List<NewsItemResponse> newsItems = new ArrayList<>();

        try {

            URL url = new URL(rssUrl);

            URLConnection connection = url.openConnection();

            connection.setConnectTimeout(5000); // 5 seconds to connect

            connection.setReadTimeout(8000); // 8 seconds to read

            connection.setRequestProperty("User-Agent", "DailyBriefBot/1.0");

            SyndFeed feed = new SyndFeedInput()
                    .build(new XmlReader(url));

            for (SyndEntry entry : feed.getEntries()) {

                NewsItemResponse newsItem =
                        new NewsItemResponse();

                newsItem.setTitle(entry.getTitle());

                if (entry.getDescription() != null) {
                    newsItem.setDescription(
                            entry.getDescription().getValue());
                }

                newsItem.setUrl(entry.getLink());

               newsItems.add(newsItem);

                if (newsItems.size() >= limit) {
                    break;
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return newsItems;
    }
}