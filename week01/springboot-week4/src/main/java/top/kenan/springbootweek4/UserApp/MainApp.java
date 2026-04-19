package top.kenan.springbootweek4.UserApp;
// MainApp.java
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import top.kenan.springbootweek4.User.UserService;
import top.kenan.springbootweek4.Config.AppConfig;

public class MainApp {
    public static void main(String[] args) {
        // 加载配置类，初始化 Spring 容器
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        // 从容器中获取 Bean
        UserService userService = context.getBean(UserService.class);

        // 调用 Bean 的方法
        userService.sayHello();
    }
}