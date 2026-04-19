package com.example.demo.controller;

import com.example.demo.model.HealthVO;
import com.example.demo.vo.ResultVO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api")
public class HealthController {

    @Value("${app.name}")
    private String appName;

    @Value("${app.version}")
    private String appVersion;

    @GetMapping("/health")
    public ResultVO<HealthVO> health() {
        HealthVO healthVO = new HealthVO(
                appName,
                appVersion,
                LocalDateTime.now(),
                "UP"
        );
        return ResultVO.success(healthVO);
    }
}