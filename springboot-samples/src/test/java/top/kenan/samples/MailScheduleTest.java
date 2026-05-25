package top.kenan.samples;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import top.kenan.samples.task.MailScheduledTask;

import jakarta.annotation.Resource;

/**
 * 终极测试类
 * 测试：富文本邮件 + 附件 + 定时任务 全套功能
 */
@SpringBootTest
@Slf4j
public class MailScheduleTest {

    // 注入【定时任务类】（直接调用定时方法，测试全套逻辑）
    @Resource
    private MailScheduledTask mailScheduledTask;

    /**
     * 测试1：直接运行定时任务方法
     * 等价于 19:10 定时触发的效果
     * 测试：富文本 + 附件 + 定时逻辑
     */
    @Test
    public void testScheduleMailAllInOne() {
        log.info("===== 开始测试：定时邮件（富文本+附件）全套功能 =====");

        // 直接调用定时任务的核心方法（模拟19:10自动触发）
        mailScheduledTask.sendMailAt1910();

        log.info("===== 测试执行完成！请查看收件邮箱 =====");
    }

    /**
     * 测试2：单独验证项目环境（定时任务/邮件服务是否加载成功）
     */
    @Test
    public void testContextLoads() {
        log.info("===== SpringBoot 容器加载成功 =====");
        log.info("定时任务Bean：{}", mailScheduledTask);
        log.info("功能正常，可以等待17:10自动发送！");
    }
}