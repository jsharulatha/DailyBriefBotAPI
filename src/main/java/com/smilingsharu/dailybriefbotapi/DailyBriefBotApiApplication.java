package com.smilingsharu.dailybriefbotapi;

import com.smilingsharu.dailybriefbotapi.service.TelegramService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@EnableAsync
public class DailyBriefBotApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(DailyBriefBotApiApplication.class, args);
    }
/*@Bean
    CommandLineRunner run(TelegramService ts){
        return args -> ts.sendMessage();
}*/
}
