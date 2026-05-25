# /api/health 接口规格文档

## 核心目标
提供系统健康状态检查能力，返回项目基本信息与运行状态。

## 业务规则
- 接口无权限校验，公开可访问；
- 项目名称、版本号从配置文件读取；
- 服务器时间为当前系统时间；
- 运行状态固定返回 `UP`（可扩展为依赖检查）。

## 技术约束
- 请求方式：GET
- 路径：/api/health
- 响应格式：application/json
- 依赖 Spring Boot 环境配置读取能力。

## 输入输出
### 请求参数
无

### 成功响应示例
```json
{
  "code": 200,
  "message": "success",
  "data": {
    "appName": "springboot-hello",
    "appVersion": "1.0.0",
    "serverTime": "2025-10-01T14:30:00",
    "status": "UP"
  }
}
```
## 字段说明
    字段名	类型	说明
    code	Integer	状态码，200 表示成功
    message	String	状态描述
    data	Object	健康数据对象
    appName	String	项目名称（配置读取）
    appVersion	String	版本号（配置读取）
    serverTime	String	当前服务器时间（ISO 格式）
    status	String	运行状态，固定为 UP

## 验收标准
    -访问接口返回 200 状态码；
    -响应 JSON 格式与示例一致；
    -appName、appVersion 与配置文件一致；
    -serverTime 为当前时间。

#### 2. `docs/system-info-spec.md`（扩展接口合集）
```markdown
# 系统信息相关接口规格文档

## 1. /api/system/info 接口
### 核心目标
返回应用运行环境的基础系统信息。

### 业务规则
- 公开接口，无权限限制；
- 信息来自 JVM 系统属性，安全无敏感数据。

### 输入输出
#### 成功响应示例
```json
{
  "code": 200,
  "message": "success",
  "data": {
    "osName": "Windows 11",
    "osArch": "amd64",
    "javaVersion": "17.0.10",
    "userDir": "/path/to/project"
  }
}
```