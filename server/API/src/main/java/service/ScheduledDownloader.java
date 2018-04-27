package service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import parsers.daily.DailyMain;

import java.io.IOException;

@Component
public class ScheduledDownloader {

    @Scheduled(cron = "0 0 7 * 1-6,8-12 1-5")
    public void downloadDailyMenus() {
        DailyMain.downloadMenus();
    }
}
