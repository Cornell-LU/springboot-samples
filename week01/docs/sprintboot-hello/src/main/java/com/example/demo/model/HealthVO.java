package com.example.demo.model;

import java.time.LocalDateTime;

public class HealthVO {
    private String appName;
    private String appVersion;
    private LocalDateTime serverTime;
    private String status;

    // 构造函数 + Getter & Setter
    public HealthVO(String appName, String appVersion, LocalDateTime serverTime, String status) {
        this.appName = appName;
        this.appVersion = appVersion;
        this.serverTime = serverTime;
        this.status = status;
    }

    public String getAppName() { return appName; }
    public void setAppName(String appName) { this.appName = appName; }
    public String getAppVersion() { return appVersion; }
    public void setAppVersion(String appVersion) { this.appVersion = appVersion; }
    public LocalDateTime getServerTime() { return serverTime; }
    public void setServerTime(LocalDateTime serverTime) { this.serverTime = serverTime; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}