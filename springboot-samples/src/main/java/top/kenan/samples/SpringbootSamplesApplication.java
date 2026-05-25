package top.kenan.samples;

//import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
//@MapperScan("top.kenan.samples.mapper") // 你的Mapper接口所在的包
public class SpringbootSamplesApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringbootSamplesApplication.class, args);
        System.out.println("==================== 项目启动成功 ====================");
    }
}