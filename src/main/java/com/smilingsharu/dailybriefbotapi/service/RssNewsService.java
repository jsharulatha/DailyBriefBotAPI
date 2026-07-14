package com.smilingsharu.dailybriefbotapi.service;

import com.smilingsharu.dailybriefbotapi.dto.NewsItemResponse;

import java.util.List;

public interface RssNewsService {
    List<NewsItemResponse> getTopHeadlines(String rssUrl, int limit);
}
