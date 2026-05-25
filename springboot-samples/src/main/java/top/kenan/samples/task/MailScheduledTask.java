//package top.kenan.samples.task;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//import top.kenan.samples.mail.MailService;
//
//@Component
//@Slf4j
//@RequiredArgsConstructor
//public class MailScheduledTask {
//
//    private final MailService mailService;
//
//    // ===================== 17:10 定时发送：富文本 + 附件 =====================
//    @Scheduled(cron = "0 10 19 * * ?")
//    public void sendMailAt1910() {
//        // 1. 收件人
//        String to = "1878996697@qq.com";
//        // 2. 标题
//        String title = "19:10 定时通知【富文本+附件】";
//        // 3. 富文本内容（HTML样式）
//        String htmlContent = "<div style='padding:20px; background:#eef2fa; border-radius:8px;'>"
//                + "<h2 style='color:#1E90FF; text-align:center;'>📩 定时任务邮件</h2>"
//                + "<p style='font-size:16px;'>你好，这是一封 <b style='color:red'>带附件的富文本邮件</b>！</p>"
//                + "<p>发送时间：19:10</p>"
//                + "<p>请查收附件~</p>"
//                + "<p>发送人：卢柯男 2505223106</p>"
//                + "</div>";
//
//        // 4. ⚠️【必须修改】本地附件路径（换成你电脑上的文件路径）
//        // Windows示例：D:\\test\\文档.pdf 、D:\\图片.jpg
//        // Mac/Linux示例：/Users/xxx/test/文档.pdf
//        String filePath = "D:\\截图\\IMG_4708.JPG";
//
//        // 5. 调用最终方法
//        mailService.sendHtmlMailWithAttachment(to, title, htmlContent, filePath);
//        log.info("========== 17:10 带附件富文本邮件已发送 ==========");
//    }
//}