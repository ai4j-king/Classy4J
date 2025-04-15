# 应用平台API接口文档

## 1. 应用管理接口

### 1.1 创建应用

**接口路径**：`/api/v1/apps`

**请求方法**：POST

**请求参数**：
```json
{
    "name": "string",       // 应用名称（唯一标识）
    "type": "string",       // 应用类型：chat/agent/workflow
    "description": "string", // 应用描述
    "tags": ["string"]      // 应用标签列表
}
```

**返回值**：
```json
{
    "code": 200,
    "message": "success",
    "data": {
        "appId": "string",    // 应用ID
        "name": "string",     // 应用名称
        "type": "string",     // 应用类型
        "description": "string",
        "tags": ["string"],
        "createTime": "string",
        "status": "enabled"    // 应用状态：enabled/disabled
    }
}
```

### 1.2 配置应用参数

**接口路径**：`/api/v1/apps/{appId}/config`

**请求方法**：PUT

**请求参数**：
```json
{
    "modelConfig": {          // 模型配置
        "modelName": "string",  // 大语言模型名称
        "temperature": 0.7,     // 温度参数(0-1)
        "maxLength": 2000,      // 最大长度
        "contextWindow": 5      // 上下文窗口大小
    },
    "customConfig": {         // 自定义配置，根据应用类型不同而不同
        "key": "value"
    }
}
```

**返回值**：
```json
{
    "code": 200,
    "message": "success",
    "data": {
        "appId": "string",
        "config": {
            "modelConfig": {},
            "customConfig": {}
        },
        "updateTime": "string"
    }
}
```

### 1.3 获取应用列表

**接口路径**：`/api/v1/apps`

**请求方法**：GET

**请求参数**：
```
page: integer       // 页码，默认1
pageSize: integer   // 每页大小，默认10
keyword: string     // 搜索关键词（可选）
type: string        // 应用类型筛选（可选）
status: string      // 状态筛选（可选）：enabled/disabled
```

**返回值**：
```json
{
    "code": 200,
    "message": "success",
    "data": {
        "total": 100,
        "list": [{
            "appId": "string",
            "name": "string",
            "type": "string",
            "description": "string",
            "tags": ["string"],
            "status": "string",
            "createTime": "string",
            "updateTime": "string"
        }]
    }
}
```

### 1.4 更新应用状态

**接口路径**：`/api/v1/apps/{appId}/status`

**请求方法**：PUT

**请求参数**：
```json
{
    "status": "string"  // enabled/disabled
}
```

**返回值**：
```json
{
    "code": 200,
    "message": "success",
    "data": null
}
```

### 1.5 删除应用

**接口路径**：`/api/v1/apps/{appId}`

**请求方法**：DELETE

**返回值**：
```json
{
    "code": 200,
    "message": "success",
    "data": null
}
```

## 2. 聊天应用接口

### 2.1 发送消息

**接口路径**：`/api/v1/apps/{appId}/chat/messages`

**请求方法**：POST

**请求参数**：
```json
{
    "content": "string",    // 消息内容
    "type": "text",        // 消息类型：text/code
    "language": "string"   // 当type为code时的编程语言
}
```

**返回值**：
```json
{
    "code": 200,
    "message": "success",
    "data": {
        "messageId": "string",
        "content": "string",
        "type": "string",
        "createTime": "string",
        "aiResponse": {
            "content": "string",
            "type": "string"
        }
    }
}
```

### 2.2 获取历史记录

**接口路径**：`/api/v1/apps/{appId}/chat/messages`

**请求方法**：GET

**请求参数**：
```
page: integer       // 页码，默认1
pageSize: integer   // 每页大小，默认20
startTime: string   // 开始时间（可选）
endTime: string     // 结束时间（可选）
keyword: string     // 搜索关键词（可选）
```

**返回值**：
```json
{
    "code": 200,
    "message": "success",
    "data": {
        "total": 100,
        "list": [{
            "messageId": "string",
            "content": "string",
            "type": "string",
            "createTime": "string",
            "aiResponse": {
                "content": "string",
                "type": "string"
            }
        }]
    }
}
```

### 2.3 删除历史记录

**接口路径**：`/api/v1/apps/{appId}/chat/messages/{messageId}`

**请求方法**：DELETE

**返回值**：
```json
{
    "code": 200,
    "message": "success",
    "data": null
}
```

### 2.4 导出对话记录

**接口路径**：`/api/v1/apps/{appId}/chat/messages/export`

**请求方法**：GET

**请求参数**：
```
startTime: string   // 开始时间
endTime: string     // 结束时间
format: string      // 导出格式：markdown/json
```

**返回值**：
文件流