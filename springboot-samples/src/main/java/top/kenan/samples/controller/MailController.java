//package top.kenan.samples.controller;
//
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
////import top.kenan.samples.mail.MailService;
//
//import jakarta.annotation.Resource;
//
//@RestController
//public class MailController {
//
//    @Resource
//    private MailService mailService;
//
//    @GetMapping("/send")
//    public String send(@RequestParam String to,
//                       @RequestParam String subject,
//                       @RequestParam String content) {
//        try {
//            mailService.sendSimpleMail(to, subject, content);
//            return "发送成功";
//        } catch (Exception e) {
//            return "发送失败：" + e.getMessage();
//        }
//    }
//}
