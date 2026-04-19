package com.example.demo.controller;

import com.example.demo.vo.ResultVO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/system")
public class SystemInfoController {

    private final Environment environment;

    @Value("${env.description}")
    private String envDesc;

    @Value("${env.debug}")
    private Boolean envDebug;

    public SystemInfoController(Environment environment) {
        this.environment = environment;
    }

    // 1. 系统信息
    @GetMapping("/info")
    public ResultVO<Map<String, String>> systemInfo() {
        Map<String, String> info = new HashMap<>();
        info.put("osName", System.getProperty("os.name"));
        info.put("osArch", System.getProperty("os.arch"));
        info.put("javaVersion", System.getProperty("java.version"));
        info.put("userDir", System.getProperty("user.dir"));
        return ResultVO.success(info);
    }

    // 2. 环境变量
    @GetMapping("/env")
    public ResultVO<Map<String, Object>> systemEnv() {
        Map<String, Object> env = new HashMap<>();
        env.put("activeProfile", String.join(",", environment.getActiveProfiles()));
        env.put("description", envDesc);
        env.put("debug", envDebug);
        return ResultVO.success(env);
    }

    // 3. JVM 指标
    @GetMapping("/metrics")
    public ResultVO<Map<String, Object>> systemMetrics() {
        Runtime runtime = Runtime.getRuntime();
        RuntimeMXBean mxBean = ManagementFactory.getRuntimeMXBean();

        Map<String, Object> metrics = new HashMap<>();
        metrics.put("maxMemoryMB", runtime.maxMemory() / 1024 / 1024);
        metrics.put("usedMemoryMB", (runtime.totalMemory() - runtime.freeMemory()) / 1024 / 1024);
        metrics.put("threadCount", Thread.activeCount());
        metrics.put("daemonThreadCount", mxBean.getDaemonThreadCount());
        metrics.put("uptimeSeconds", mxBean.getUptime() / 1000);
        return ResultVO.success(metrics);
    }
}