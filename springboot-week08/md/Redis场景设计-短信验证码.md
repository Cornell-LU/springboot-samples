# Redis 应用场景设计：短信验证码

## 一、功能描述

### 1.1 业务目标

用户在「登录 / 找回密码」等流程中输入手机号后，系统向该号码发送一条短数字验证码；用户在同一页面输入收到的验证码，服务端校验通过后允许进入下一步业务（如设置新密码、完成登录）。

### 1.2 功能要点

| 要点 | 说明 |
|------|------|
| 生成与发送 | 服务端生成短时有效的数字串（本项目为 **6 位** 数字），通过短信网关下发；**真正发短信**属于渠道能力，Redis 只负责**服务端存什么、存多久**。 |
| 存储与过期 | 验证码在几分钟内有效，过期后无论对错均视为无效，由 Redis **TTL 自动清理**。 |
| 校验 | 用户提交**手机号 + 验证码**，服务端从 Redis 取出比对；**校验成功即删除**该 key，实现一次性使用。 |
| 安全与合规（扩展） | 限流、错误次数锁定、生产环境**不向前端回传**明文验证码等，见后文「可选增强」；`SendCodeResponse.codePlain` 仅用于**联调/课堂演示**。 |

## 二、设计思路

### 2.1 数据类型选取

- 每个「手机号 → 当前有效验证码」一对一，用**一个 key、一个 String 值**即可。
- 生命周期短，适合 `SET ... EX`（本项目通过 `RedisUtil.set(key, value, ttl, timeUnit)`），无需独立定时任务。
- 验证成功后**删除 key** 即可表达「已消费」。

不选用 Hash / List 类型的原因：无多字段结构、无队列顺序需求，String 最简单。

### 2.2 Key 设计

- **前缀常量**：`SmsRedisKey.PREFIX` = `week08:sms:code:`
- **完整 key**：`SmsRedisKey.ofPhone(phone)` → `week08:sms:code:{11位手机号}`
- 同一手机号再次获取验证码时，**新码覆盖旧码**（同一 key 上再次 `set`）。

### 2.3 值与时间

| 项目 | 实现 |
|------|------|
| 值 | 6 位数字字符串，由 `String.format("%06d", random)` 生成 |
| 有效时间 | `SmsVerifyCodeService.DEFAULT_TTL_SECONDS` = **300 秒**（5 分钟） |

### 2.4 与整体架构的关系

```
[客户端] → [SmsVerifyCodeController] → [SmsVerifyCodeService] → Redis（String + TTL）
              ↓
         [生产环境可接] 短信网关
```

## 三、实现方法

### 3.1 工程中的类与包

| 说明 | 路径 |
|------|------|
| Redis Key 工具 | `src/main/java/top/mqxu/week08/sms/SmsRedisKey.java` |
| 业务服务 | `src/main/java/top/mqxu/week08/sms/SmsVerifyCodeService.java` |
| HTTP 接口 | `src/main/java/top/mqxu/week08/sms/SmsVerifyCodeController.java` |
| 入参/出参 DTO | `src/main/java/top/mqxu/week08/sms/dto/*.java` |
| 底层 Redis 封装 | `src/main/java/top/mqxu/week08/util/RedisUtil.java` |
| Redis 与序列化 | `src/main/java/top/mqxu/week08/config/RedisConfig.java` |

### 3.2 Redis 操作（`SmsVerifyCodeService`）

| 步骤 | 方法/说明 |
|------|-----------|
| 发码写入 | `redisUtil.set(key, code, DEFAULT_TTL_SECONDS, TimeUnit.SECONDS)` |
| 读取比对 | `redisUtil.get(key)`，与入参 `code` 做 `String.equals` |
| 成功删除 | `redisUtil.delete(key)`，防止重复使用 |

对应源码：`SmsVerifyCodeService#sendCode`、`#validateCode`。

### 3.3 HTTP 接口

**Controller 基础路径**：`/api/sms`（类上加注解 `@RequestMapping`）

| 方法 | 路径 | 请求体 | 成功响应（`ApiResult`） |
|------|------|--------|-------------------------|
| `POST` | `/api/sms/verify-codes` | `SendCodeRequest`：`{ "phone": "1xxxxxxxxxx" }` | `data` 为 `SendCodeResponse`：含 `phone`、`ttlSeconds`、**联调用** `codePlain` |
| `POST` | `/api/sms/verify-codes/validate` | `ValidateCodeRequest`：`{ "phone", "code" }` | 成功：`200`，`data.valid = true`；业务失败：`422`，`code=422`，`message=验证码错误或已过期` |
| 参数非法 | — | Bean Validation 不通过 | `400`，`code=400`，`message` 为校验提示（由 `SmsVerifyCodeController` 内 `MethodArgumentNotValidException` 处理） |

**说明**：

- `server.port` 以 `application.yml` 为准
- 联调时完整 URL 示例：`http://localhost:{port}/api/sms/verify-codes`

### 3.4 请求/响应 JSON 示例

**发码**

```http
POST /api/sms/verify-codes
Content-Type: application/json

{"phone":"13800138000"}
```

**成功响应（节选）**

```json
{
  "code": 0,
  "message": "success",
  "data": {
    "phone": "13800138000",
    "ttlSeconds": 300,
    "codePlain": "123456"
  }
}
```

**校验**

```http
POST /api/sms/verify-codes/validate
Content-Type: application/json

{"phone":"13800138000","code":"123456"}
```

**校验成功（节选）**

```json
{
  "code": 0,
  "message": "success",
  "data": { "valid": true }
}
```

**校验失败（短信码错误或已过期，节选）**

```json
{
  "code": 422,
  "message": "验证码错误或已过期",
  "data": null
}
```

### 3.5 与 `RedisUtil` 的依赖关系

短信模块仅依赖 `RedisUtil` 的 String 能力：`set`（带过期）、`get`、`delete`。详见 [RedisUtil 使用场景与说明](./RedisUtil使用场景与说明.md) 中「3. String」。

### 3.6 可选增强

| 方向 | 做法 |
|------|------|
| 防刷 | 对同一手机号或 IP 使用 `increment` + 短 TTL 做发送频率限制 |
| 连续输错 | 使用 Hash/String 记录错误次数，超阈值后临时锁定该手机号 key |
| 生产安全 | 删除 `SendCodeResponse.codePlain` 字段；发码后仅由短信触达用户 |
| 多实例 | 多应用实例共连同一 Redis 即可，无需同步 |

## 四、小结

短信验证码场景使用 **Redis String + TTL** 即可实现「按手机号隔离、短期有效、校验后一次性消费」。


