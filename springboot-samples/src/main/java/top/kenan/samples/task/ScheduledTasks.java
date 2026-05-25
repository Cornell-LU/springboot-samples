package top.kenan.samples.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import top.kenan.samples.service.WeatherService;

@Component
public class ScheduledTasks {

    @Autowired
    private WeatherService weatherService;

    // 每5秒执行一次，快速测试，后续可改为5分钟
    @Scheduled(fixedRate = 5*1000)
    public void weatherTask() {
        weatherService.getWeatherNow();
    }
}