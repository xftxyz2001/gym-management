package com.xftxyz.gymadmin.scheduled;

import com.xftxyz.gymadmin.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ScheduledTask {

    private final CardService cardService;

    // 生成cron表达式 https://cron.qqe2.com/

    @Scheduled(cron = "0 0 0 * * ?") // 每天0点执行一次
    public void checkCardStatus() {
        cardService.checkCardStatus();
    }

}
