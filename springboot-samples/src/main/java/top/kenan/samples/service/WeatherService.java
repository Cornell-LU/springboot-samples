package top.kenan.samples.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class WeatherService {

    private final WebClient webClient;

    // 从配置文件读取API Key和城市ID
    @Value("${app.weather.api-key:}")
    private String apiKey;

    @Value("${app.weather.city:101190101}")
    private String cityId;

    // 初始化WebClient，固定用免费版域名
    public WeatherService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://devapi.qweather.com").build();
    }

    /**
     * 测试天气API调用，只打印结果，不入库
     */
    public void getWeatherNow() {
        System.out.println("【定时任务】开始调用和风天气API，城市ID：" + cityId);

        // 调用实时天气接口
        Mono<String> responseMono = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/v7/weather/now")
                        .queryParam("location", cityId)
                        .queryParam("key", apiKey)
                        .build())
                .retrieve()
                .bodyToMono(String.class);

        // 处理响应结果
        responseMono.subscribe(
                // 成功：打印返回的天气数据
                response -> System.out.println("【API调用成功】返回数据：\n" + response),
                // 失败：打印错误信息
                error -> System.err.println("【API调用失败】错误原因：" + error.getMessage())
        );
    }
}