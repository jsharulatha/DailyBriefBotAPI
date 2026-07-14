package com.smilingsharu.dailybriefbotapi.service;

import com.smilingsharu.dailybriefbotapi.config.AppConfig;
import com.smilingsharu.dailybriefbotapi.dto.SendMessageRequest;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Slf4j
@Service
public class TelegramService {
    @Value("${telegram.bot.token}")
    private String botToken;
    @Value("${telegram.chat.id}")
    private String botID;

private final RestClient restClient;

    public TelegramService(RestClient restClient) {
        this.restClient = restClient;
    }

    @PostConstruct
    public void init() {
        System.out.println("id -->"+ botID);
        //System.out.println("token -->"+ botToken);
    }
public void sendDailyBrief(){
        String url = "https://api.telegram.org/bot" + botToken +"/sendMessage";
    SendMessageRequest sendMsg = new SendMessageRequest();
    sendMsg.setChatId(botID);
    sendMsg.setText("today's brief");
    try {
        String response = restClient.post().uri(url).body(sendMsg).retrieve().body(String.class);
        System.out.println(response);
    } catch (Exception e) {
        log.error("send message exception in telegram"+e);
    }
}
}

