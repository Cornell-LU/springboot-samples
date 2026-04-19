package top.kenan.week04;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import top.kenan.week04.Service.UserService;
import top.kenan.week04.config.AppConfig;

@SpringBootApplication
public class SpringbootWeek04Application {

	public static void main(String[] args) {
        SpringApplication.run(SpringbootWeek04Application.class, args);
	}


}
