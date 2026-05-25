package top.kenan.week04.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.kenan.week04.Service.UserService;

@Configuration  // 标注这是一个配置类
public class AppConfig {
    @Bean  // 将方法返回值注册为 Bean
    public UserService userService() {
        return new UserService();
    }
}
