package com.smilingsharu.dailybriefbotapi.controller;

import com.smilingsharu.dailybriefbotapi.dto.SendMessageRequest;
import com.smilingsharu.dailybriefbotapi.service.DailyBriefService;
import com.smilingsharu.dailybriefbotapi.service.TelegramService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dailyBrief")
public class DailyBriefController {

private final DailyBriefService dailyBriefService;

    public DailyBriefController(DailyBriefService dailyBriefService) {
        this.dailyBriefService = dailyBriefService;
    }

    @GetMapping(value = "/todayTrigger")
    public ResponseEntity<String> sendTodayBrief(){
        dailyBriefService.sendDailyBriefAsync();
       return ResponseEntity.accepted().build();
    }
}
