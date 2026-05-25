package top.kenan.samples;

import jakarta.mail.MessagingException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import top.kenan.samples.mail.MailService;

import jakarta.annotation.Resource;

@SpringBootTest
public class MailServiceTest {

    // @Resource 注入服务
    @Resource
    private MailService mailService;

    @Test
    public void testSendMail() {
        mailService.sendSimpleMail(
                "1878996697@qq.com",
                "Spring Boot 邮件测试",
                "发送成功！"
        );
        System.out.println("邮件发送成功");
    }
    @Test
    public void testSendHtmlMail() throws MessagingException {
        // 收件人
        String to = "1878996697@qq.com";
        String subject = "Spring Boot 富文本HTML邮件测试";

        String htmlContent = """
                <html>
                    <body>
                        <h2 style="color: #1E90FF;">👋 你好！这是一封富文本邮件</h2>
                        <p>这是通过 <b>Spring Boot</b> 发送的 <span style="color:red">HTML邮件</span></p>
                        <p>支持：字体、颜色、图片、链接、表格等所有网页样式</p>
                        <a href="https://www.baidu.com" style="background: #42b983; color:white; padding:8px 16px; text-decoration:none; border-radius:5px;">
                            点击访问百度
                        </a>
                    </body>
                </html>
                """;


        mailService.sendHtmlMail(to, subject, htmlContent);
        System.out.println("✅ 富文本邮件发送成功！");
    }

}