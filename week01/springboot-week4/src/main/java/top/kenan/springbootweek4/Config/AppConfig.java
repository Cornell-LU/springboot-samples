package top.kenan.springbootweek4.Config;// AppConfig.java
import top.kenan.springbootweek4.User.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration  // 标注这是一个配置类
public class AppConfig {
    @Bean  // 将方法返回值注册为 Bean
    public UserService userService() {
        return new UserService();
    }
}
