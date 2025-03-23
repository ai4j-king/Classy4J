# Classy4J

Classy4J是一款基于Java语言开发的开源AI应用开发平台，旨在为开发者、企业人员提供便捷、高效、可定制的AI开发解决方案，帮助他们快速构建各种AI应用，如智能聊天机器人、文本分析工具等。

## 项目架构

本项目采用前后端分离架构：

- 前端项目（Classy4J-Web）：基于Vue 3的现代化Web应用
- 后端项目（Classy4J）：基于Spring Boot的Java服务端应用

## 技术栈

### 前端技术栈

- Vue 3：渐进式JavaScript框架
- Vue Router：官方路由管理器
- Element Plus：基于Vue 3的组件库
- Vite：现代前端构建工具
- Axios：HTTP客户端
- Pinia：状态管理工具

### 后端技术栈

- Spring Boot 3.2.3：应用开发框架
- Spring AI：AI功能集成
- LangChain4j：大语言模型应用开发框架
- Project Reactor：响应式编程支持
- Maven：项目管理工具

## 主要功能

- 智能对话：支持多轮对话的AI助手
- 提示词生成：智能提示词生成和管理
- 工作流编排：可视化的AI工作流设计
- 应用模板：预置多种AI应用模板
- 自定义配置：灵活的AI模型和参数配置

## 快速开始

### 前端项目启动

```bash
# 安装依赖
cd Classy4J-Web
npm install

# 开发环境运行
npm run dev

# 生产环境构建
npm run build
```

### 后端项目启动

```bash
# 编译打包
cd Classy4J
mvn clean package

# 运行应用
java -jar target/demo-0.0.1-SNAPSHOT.jar
```

## 项目结构

### 前端结构

```
Classy4J-Web/
├── src/
│   ├── api/          # API接口
│   ├── assets/       # 静态资源
│   ├── components/   # 公共组件
│   ├── config/       # 配置文件
│   ├── router/       # 路由配置
│   ├── views/        # 页面视图
│   └── App.vue       # 根组件
```

### 后端结构

```
Classy4J/
├── src/
│   └── main/
│       ├── java/     # Java源代码
│       └── resources/ # 配置资源
```

## 贡献指南

欢迎提交问题和改进建议！如果您想为项目做出贡献，请：

1. Fork本仓库
2. 创建您的特性分支
3. 提交您的改动
4. 推送到您的分支
5. 创建Pull Request

## 许可证

本项目采用MIT许可证 - 详见 [LICENSE](LICENSE) 文件
