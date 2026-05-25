package com.example.demo.controller;

import com.example.demo.model.EnvVO;
import com.example.demo.model.HealthVO;
import com.example.demo.vo.ResultVO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class SystemController {

    // 读取配置文件中的项目信息
    @Value("${app.name}")
    private String appName;

    @Value("${app.version}")
    private String appVersion;

    // 读取环境配置
    @Value("${env.description}")
    private String envDesc;

    @Value("${env.debug}")
    private Boolean envDebug;

    // Spring 环境上下文
    private final Environment environment;

    // 构造器注入
    public SystemController(Environment environment) {
        this.environment = environment;
    }

    // ===================== 必做任务：健康检查 =====================
    @GetMapping("/health")
    public ResultVO<HealthVO> health() {
        HealthVO health = new HealthVO(
                appName,
                appVersion,
                LocalDateTime.now(),
                "UP" // 简单演示，实际可检查数据库、Redis等
        );
        return ResultVO.success(health);
    }

    // ===================== 选做任务 1：系统信息 =====================
    @GetMapping("/system/info")
    public ResultVO<Map<String, Object>> systemInfo() {
        Map<String, Object> info = new HashMap<>();
        info.put("os.name", System.getProperty("os.name"));
        info.put("os.arch", System.getProperty("os.arch"));
        info.put("java.version", System.getProperty("java.version"));
        info.put("user.dir", System.getProperty("user.dir"));
        return ResultVO.success(info);
    }

    // ===================== 选做任务 2：环境变量 =====================
    @GetMapping("/system/env")
    public ResultVO<EnvVO> systemEnv() {
        EnvVO env = new EnvVO();
        // 获取当前激活的环境（dev/prod）
        env.setActiveProfile(String.join(",", environment.getActiveProfiles()));
        env.setDescription(envDesc);
        env.setDebug(envDebug);
        return ResultVO.success(env);
    }

    // ===================== 选做任务 3：JVM 指标 =====================
    @GetMapping("/system/metrics")
    public ResultVO<Map<String, Object>> jvmMetrics() {
        Runtime runtime = Runtime.getRuntime();
        RuntimeMXBean mxBean = ManagementFactory.getRuntimeMXBean();

        Map<String, Object> metrics = new HashMap<>();
        // 内存信息 (字节)
        metrics.put("maxMemory", runtime.maxMemory() / 1024 / 1024); // MB
        metrics.put("usedMemory", (runtime.totalMemory() - runtime.freeMemory()) / 1024 / 1024); // MB
        // 线程信息
        metrics.put("threadCount", Thread.activeCount());
        metrics.put("daemonThreadCount", mxBean.getDaemonThreadCount());
        // 运行时间
        metrics.put("uptime", mxBean.getUptime() / 1000); // 秒

        return ResultVO.success(metrics);
    }
}