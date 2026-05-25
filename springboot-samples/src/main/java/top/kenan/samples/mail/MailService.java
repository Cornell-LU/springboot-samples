//package top.kenan.samples.mail;
//

//import org.springframework.core.io.FileSystemResource;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.Resource;
//import jakarta.mail.MessagingException;
//import jakarta.mail.internet.MimeMessage;
//
//import java.io.File;
//
//@Service
//public class MailService {
//
//    @Resource
//    private JavaMailSender javaMailSender;
//
//    private final String FROM_EMAIL = "1878996697@qq.com";
//
//    // ====================== 原有：纯文本邮件 ======================
//    public void sendSimpleMail(String to, String subject, String content) {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom(FROM_EMAIL);
//        message.setTo(to);
//        message.setSubject(subject);
//        message.setText(content);
//        javaMailSender.send(message);
//    }
//
//    // ====================== 新增：富文本(HTML)邮件 ======================
//    public void sendHtmlMail(String to, String subject, String htmlContent) throws MessagingException {
//        // 1. 创建 MimeMessage 对象（复杂邮件核心对象）
//        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
//
//        // 2. 创建 MimeMessageHelper 对象（辅助工具）
//        // 参数1：MimeMessage
//        // 参数2：true = 开启多功能邮件支持(HTML/附件/图片)
//        // 参数3：编码格式
//        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
//
//        // 3. 设置富文本邮件参数
//        helper.setFrom(FROM_EMAIL);    // 发件人
//        helper.setTo(to);              // 收件人
//        helper.setSubject(subject);    // 主题
//        // 第二个参数 true = 开启HTML解析
//        helper.setText(htmlContent, true);
//
//        // 发送邮件
//        javaMailSender.send(mimeMessage);
//    }
//    public void sendAttachMail(String to, String subject, String content, File... files)
//            throws MessagingException {
//
//        // 1. 创建 MimeMessage 对象
//        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
//
//        // 2. 创建 Helper，第二个参数 true 表示支持【附件+富文本】
//        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
//        // 和富文本邮件一样，设置收发件人、主题、正文
//        helper.setFrom(FROM_EMAIL);
//        helper.setTo(to);
//        helper.setSubject(subject);
//        helper.setText(content, true); // true 开启HTML富文本
//
//        // 3. 遍历附件文件，加入邮件附件列表
//        for (File file : files) {
//            if (file.exists()) {
//                FileSystemResource resource = new FileSystemResource(file);
//                // 参数1：附件显示名称  参数2：文件资源
//                helper.addAttachment(file.getName(), resource);
//            }
//        }
//
//        // 发送
//        javaMailSender.send(mimeMessage);
//    }
//
//}
public interface MailService {
    /**
     * 发送普通文本邮件
     */
    void sendSimpleMail(String to, String title, String content);

    /**
     * 发送富文本(HTML)邮件  【新增】
     */
    void sendHtmlMail(String to, String title, String htmlContent);

    void sendHtmlMailWithAttachment(String to, String title, String htmlContent, String filePath);
}

