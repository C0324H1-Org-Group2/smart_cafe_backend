package com.group2.smart_cafe_backend.config;

import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class SchedulerConfig {

    @Autowired
    private PasswordExpiryChecker passwordExpirationService;

    @Scheduled(cron = "0 0 0 * * ?")
    public void scheduleFixedRateTask() throws MessagingException {
        passwordExpirationService.checkPasswordExpiration();
    }
}

