//package top.kenan.samples.mail.impl;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.stereotype.Service;
//import top.kenan.samples.mail.MailService;
//
//import jakarta.mail.internet.MimeMessage;
//import java.io.File;
//
//@Service
//@Slf4j
//@RequiredArgsConstructor
//public class MailServiceImpl implements MailService {
//
//    private final JavaMailSender mailSender;
//    private final String fromEmail = "1878996697@qq.com";
//
//    // 普通文本邮件
//    @Override
//    public void sendSimpleMail(String to, String title, String content) {
//        try {
//            org.springframework.mail.SimpleMailMessage message = new org.springframework.mail.SimpleMailMessage();
//            message.setFrom(fromEmail);
//            message.setTo(to);
//            message.setSubject(title);
//            message.setText(content);
//            mailSender.send(message);
//            log.info("普通邮件发送成功！");
//        } catch (Exception e) {
//            log.error("普通邮件发送失败！", e);
//        }
//    }
//
//    // 纯富文本邮件
//    @Override
//    public void sendHtmlMail(String to, String title, String htmlContent) {
//        try {
//            MimeMessage message = mailSender.createMimeMessage();
//            MimeMessageHelper helper = new MimeMessageHelper(message, true);
//            helper.setFrom(fromEmail);
//            helper.setTo(to);
//            helper.setSubject(title);
//            helper.setText(htmlContent, true);
//            mailSender.send(message);
//            log.info("富文本邮件发送成功！");
//        } catch (Exception e) {
//            log.error("富文本邮件发送失败！", e);
//        }
//    }
//
//    // ===================== 【最终版】带附件的富文本邮件 =====================
//    @Override
//    public void sendHtmlMailWithAttachment(String to, String title, String htmlContent, String filePath) {
//        try {
//            MimeMessage message = mailSender.createMimeMessage();
//            // 第二个参数 true = 支持附件+富文本
//            MimeMessageHelper helper = new MimeMessageHelper(message, true);
//
//            helper.setFrom(fromEmail);
//            helper.setTo(to);
//            helper.setSubject(title);
//            // 开启HTML解析
//            helper.setText(htmlContent, true);
//
//            // ========== 添加附件 ==========
//            File attachment = new File(filePath);
//            // 附件名（自动获取文件名）
//            helper.addAttachment(attachment.getName(), attachment);
//
//            // 发送邮件
//            mailSender.send(message);
//            log.info("带附件+富文本邮件发送成功！");
//        } catch (Exception e) {
//            log.error("带附件邮件发送失败！", e);
//        }
//    }
//}