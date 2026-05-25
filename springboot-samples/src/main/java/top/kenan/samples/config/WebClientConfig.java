package top.kenan.samples.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${app.weather.api-key}")
    private String apiKey;

    @Bean
    public WebClient weatherWebClient() {
        return WebClient.builder()
                .baseUrl("https://devapi.qweather.com")
                .defaultHeader("Authorization", "Bearer " + apiKey)
                .build();
    }
}