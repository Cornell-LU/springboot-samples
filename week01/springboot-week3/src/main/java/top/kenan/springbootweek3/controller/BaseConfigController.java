package top.kenan.springbootweek3.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.kenan.springbootweek3.config.AppConfig;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/config")
public class BaseConfigController {
    @Value("${server.port}")
    private Integer port;


    @GetMapping("/port")
    public String getPort(){
        return "当前端口号为" + port;
    }

    @Value("${app.name}")
    private String appName;


    @Value("${app.version}")
    private String appVersion;


    @Value("${app.description}")
    private String appDescription;

    @Autowired
    private AppConfig appConfig;

    @GetMapping("/getConfigInfo")
    public String getConfigInfo() {
        return """
                serverPort: %s
                applicationName: springboot-week03
                appName: %s
                appVersion: %s
                applicationDescription: %s
                """.formatted(port, appName, appVersion, appDescription);
    }

    @GetMapping("/info2")
    public Map<String, Object> getConfigInfo2(){
        System.out.println(appConfig.getName());
        log.info(String.valueOf(appConfig));
        return Map.of(
                "appName",appConfig.getName(),
                "version",appConfig.getVersion(),
                "description",appConfig.getDescription()
        );
    }
}