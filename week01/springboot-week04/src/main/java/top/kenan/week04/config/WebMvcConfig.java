package top.kenan.week04.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author kenan
 * @date 2026/3/28
 * @description Spring MVC 配置类
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * 自定义 Jackson 消息转换器
     * 解决实际开发中常见的两个问题：日期格式不统一和 Long 类型精度丢失
     */
    @Bean
    public MappingJackson2HttpMessageConverter customJacksonConverter() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        // 日期格式化
        builder.serializerByType(LocalDateTime.class,
                new com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer(
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                ));
        builder.deserializerByType(LocalDateTime.class,
                new com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer(
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                ));
        // Long 类型转字符串，避免前端精度丢失
        builder.serializerByType(Long.class, com.fasterxml.jackson.databind.ser.std.ToStringSerializer.instance);
        return new MappingJackson2HttpMessageConverter(builder.build());
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 把 /upload/** 请求，映射到文件实际存放目录
        // 注意：如果你的文件存在项目根目录的 upload 文件夹，要加 file: 前缀
        registry.addResourceHandler("/upload/**")
                .addResourceLocations("file:" + System.getProperty("user.dir") + "/upload/");
    }
}