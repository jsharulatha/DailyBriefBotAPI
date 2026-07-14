package com.smilingsharu.dailybriefbotapi.controller;

import com.smilingsharu.dailybriefbotapi.dto.SendMessageRequest;
import com.smilingsharu.dailybriefbotapi.service.DailyBriefService;
import com.smilingsharu.dailybriefbotapi.service.TelegramService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dailyBrief")
public class DailyBriefController {

private DailyBriefService dailyBriefService;
private TelegramService telegramService;

    public DailyBriefController(DailyBriefService dailyBriefService, TelegramService telegramService) {
        this.dailyBriefService = dailyBriefService;
        this.telegramService = telegramService;
    }

    @GetMapping("/today")
    public String sendTodayBrief(){
String message = dailyBriefService.buildNews();
         telegramService.sendDailyBrief(message);
            return "Todays daily brief sent successfully";
    }
}
