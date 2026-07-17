package com.smilingsharu.dailybriefbotapi.service;

import com.smilingsharu.dailybriefbotapi.config.DailyBriefConfig;
import com.smilingsharu.dailybriefbotapi.dto.NewsItemResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service

public class DailyBriefService{

    private final RssNewsService rssNewsService;

    @Value("${news.rss.chennai}")
    private String chennaiRss;

    @Value("${news.rss.tamilnadu}")
    private String tamilNaduRss;

    @Value("${news.rss.india}")
    private String indiaRss;

    @Value("${news.rss.world}")
    private String worldRss;
    @Autowired
    private final TelegramService telegramService;

    public DailyBriefService(RssNewsService rssNewsService, TelegramService telegramService) {
        this.rssNewsService = rssNewsService;
        this.telegramService = telegramService;
    }

    @Async
    public void sendDailyBriefAsync() {

        String message = buildNews();
        telegramService.sendDailyBrief(message);

    }

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
        System.out.println("message to telegram"+ message.toString());
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
int count=1;
        for (int i = 0; i < newsItems.size(); i++) {

            NewsItemResponse item = newsItems.get(i);
            message.append(count++)
                    .append(". <b>")
                    .append(escapeHtml(item.getTitle()))
                    .append("</b>\n")
                    .append("<a href=\"")  .append(item.getUrl())
                    .append("\">Read More</a>\n\n");


        }
    }
    private String escapeHtml(String text) {
        if (text == null) {
            return "";
        }

        return text.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;");
    }
}