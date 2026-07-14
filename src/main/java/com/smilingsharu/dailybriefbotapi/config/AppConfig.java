package com.smilingsharu.dailybriefbotapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;


@Configuration
public class AppConfig {

    @Bean
  public RestClient restClinet(){
        return RestClient.create();
    }

}
