package top.kenan.samples.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class ScheduledJobs {

    //表示方法执行完成后5秒再开始执行
    @Scheduled(fixedDelay = 5000)
    public void fixedDelayJob() throws InterruptedException {
        log.info("fixedDelayJob start:{}", new Date());
        Thread.sleep(10 * 1000);
        log.info("fixedDelayJob stop:{}", new Date());
    }

    //表示每隔3秒
    @Scheduled(fixedRate = 3000)
    public void fixedRateJob() throws InterruptedException {
        log.info("fixedRateJob start:{}", new Date());
        Thread.sleep(5 * 1000);
        log.info("fixedRateJob stop:{}", new Date());
    }

    //表示每隔3秒执行一次
    @Scheduled(cron = "0/3 * * * * ? ")
    public void cronJob() {
        log.info("==================== cron: {}", new Date());
    }
}
