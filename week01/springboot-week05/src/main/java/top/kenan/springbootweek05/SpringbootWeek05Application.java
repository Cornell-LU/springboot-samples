package top.kenan.springbootweek05;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 1. 绝对不能加 exclude = DataSourceAutoConfiguration.class！加了会直接禁用数据源配置，写了yml也没用
// 2. @MapperScan 必须精确到你的Mapper接口所在的包，一个字符都不能错
@SpringBootApplication
@MapperScan("top.kenan.springbootweek05.mapper")
public class SpringbootWeek05Application {
    public static void main(String[] args) {
        SpringApplication.run(SpringbootWeek05Application.class, args);
    }
}