package com.smilingsharu.dailybriefbotapi.dto;

import lombok.Data;

@Data
public class NewsItemResponse {
    public Long id;
    public String title;
    public String description;
    public String url;

}
