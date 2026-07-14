package com.smilingsharu.dailybriefbotapi.service;

import com.smilingsharu.dailybriefbotapi.config.AppConfig;
import com.smilingsharu.dailybriefbotapi.dto.SendMessageRequest;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

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
public void sendMessage(){
        String url = "https://api.telegram.org/bot" + botToken +"/sendMessage";
    SendMessageRequest sendMsg = new SendMessageRequest();
    sendMsg.setChatId(botID);
    sendMsg.setText("My first message sharu");
    String response = restClient.post().uri(url).body(sendMsg).retrieve().body(String.class);
    System.out.println(response);
}
}

