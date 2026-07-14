package com.smilingsharu.dailybriefbotapi.controller;

import com.smilingsharu.dailybriefbotapi.service.TelegramService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dailyBrief")
public class DailyBriefController {
    private TelegramService telegramService;

    public DailyBriefController(TelegramService telegramService) {
        this.telegramService = telegramService;
    }

    @GetMapping("/today")
    public String sendTodayBrief(){

        telegramService.sendDailyBrief();
        return "Daily brief sent successfully";
    }
}
