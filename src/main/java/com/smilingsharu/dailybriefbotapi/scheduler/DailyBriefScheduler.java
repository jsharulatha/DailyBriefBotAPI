package com.smilingsharu.dailybriefbotapi.scheduler;

import com.smilingsharu.dailybriefbotapi.service.TelegramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DailyBriefScheduler {

   /*  @Autowired
    private TelegramService telegramService;

   @Scheduled(cron = "0 0 6 * * *",zone = "Asia/Kolkata")
    public void sendDailyBrief(){
        telegramService.sendMessage("Good Morning!! your daily brief is here");
    }*/
}
