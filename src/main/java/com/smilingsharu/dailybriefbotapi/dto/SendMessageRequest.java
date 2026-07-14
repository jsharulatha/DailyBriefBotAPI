package com.smilingsharu.dailybriefbotapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SendMessageRequest {
    @JsonProperty("chat_id")
    private String chatId;
    private String text;
    private String parseMode;
}
