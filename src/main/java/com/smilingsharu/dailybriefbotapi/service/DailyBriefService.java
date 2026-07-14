package com.smilingsharu.dailybriefbotapi.service;

import com.smilingsharu.dailybriefbotapi.config.DailyBriefConfig;
import com.smilingsharu.dailybriefbotapi.dto.NewsItemResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DailyBriefService {

    private final RssNewsService rssNewsService;

    @Value("${news.rss.chennai}")
    private String chennaiRss;

    @Value("${news.rss.tamilnadu}")
    private String tamilNaduRss;

    @Value("${news.rss.india}")
    private String indiaRss;

    @Value("${news.rss.world}")
    private String worldRss;

    public String buildNews() {

        List<NewsItemResponse> chennaiNews =
                rssNewsService.getTopHeadlines(
                        chennaiRss,
                        DailyBriefConfig.CHENNAI_LIMIT);

        List<NewsItemResponse> tamilNaduNews =
                rssNewsService.getTopHeadlines(
                        tamilNaduRss,
                        DailyBriefConfig.TAMIL_NADU_LIMIT);

        List<NewsItemResponse> indiaNews =
                rssNewsService.getTopHeadlines(
                        indiaRss,
                        DailyBriefConfig.INDIA_LIMIT);

        List<NewsItemResponse> worldNews =
                rssNewsService.getTopHeadlines(
                        worldRss,
                        DailyBriefConfig.WORLD_LIMIT);
        String date = LocalDate.now()
                .format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"));
        StringBuilder message = new StringBuilder();

        message.append("☀️ Good Morning! Here is the daily news \n");
        message.append("📅 ").append(date).append("\n\n");
        appendSection(message, "🏙 Chennai", chennaiNews);
        appendSection(message, "🏛 Tamil Nadu", tamilNaduNews);
        appendSection(message, "🇮🇳 India", indiaNews);
        appendSection(message, "🌍 International", worldNews);

        return message.toString();
    }

    private void appendSection(
            StringBuilder message,
            String sectionTitle,
            List<NewsItemResponse> newsItems) {

        message.append(sectionTitle).append("\n");

        if (newsItems == null || newsItems.isEmpty()) {
            message.append("No news available.\n\n");
            return;
        }

        for (int i = 0; i < newsItems.size(); i++) {

            NewsItemResponse item = newsItems.get(i);
            message.append(i + 1)
                    .append(". <b>")
                    .append(item.getTitle())
                    .append("</b>\n");

            message.append("\"") .append(item.getUrl())
                    .append("\">Read More</a>\n\n");


        }
    }
}