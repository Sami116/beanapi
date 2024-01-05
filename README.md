# Bean API

### 介绍
项目地址：[Bean API](http://api.beanstd.cn)

项目介绍：基于 React + Spring Boot + Dubbo + Gateway 的 API 接口开放调用平台。管理员可以接入并发布接口，可视化各接口调用情况；用户可以开通接口调用权限、浏览接口及在线调试，并通过客户端 SDK 在自己的项目中轻松调用接口。

#### 系统架构
<img width="470" alt="image" src="https://github.com/Sami116/beanapi/assets/56545550/185afd37-e908-48bb-81a8-f078fe0b8a61">


### 项目展示

#### 首页
<img width="1279" alt="image" src="https://github.com/Sami116/beanapi/assets/56545550/a2abd1cc-e34b-46f3-9af3-144ae64cb7c3">

#### 接口详情页
<img width="1273" alt="image" src="https://github.com/Sami116/beanapi/assets/56545550/c0526453-cffa-489a-8d7b-2b776a0b6c0b">

#### 接口管理
<img width="1280" alt="image" src="https://github.com/Sami116/beanapi/assets/56545550/badc5fe3-50fb-4447-a6e4-0b9586340ffc">

#### 接口分析
<img width="1280" alt="image" src="https://github.com/Sami116/beanapi/assets/56545550/6b91cf63-48d1-4147-b5ae-ac3e3b8bcfce">

#### 用户管理
<img width="1280" alt="image" src="https://github.com/Sami116/beanapi/assets/56545550/24f9f1e9-f2f4-4a62-ad31-867c0135c7c8">

#### 个人中心
<img width="1279" alt="image" src="https://github.com/Sami116/beanapi/assets/56545550/ebcca7ab-c73b-4a34-8322-083a87e150a9">

## 技术栈

### 前端技术栈

- 开发框架：React、Umi
- 脚手架：Ant Design Pro
- 组件库：Ant Design、Ant Design Components
- 语法扩展：TypeScript、Less
- 打包工具：Webpack
- 代码规范：ESLint、StyleLint、Prettier

### 后端技术栈

- 主语言：Java
- 框架：SpringBoot 2.7.0、Mybatis-plus、Spring Cloud
- 数据库：Mysql8.0、Redis
- 中间件：RabbitMq
- 注册中心：Nacos
- 服务调用：Dubbo
- 网关：Spring Cloud Gateway
- 负载均衡：Spring cloud Loadbalancer

## 项目模块

- beanapi-frontend ：为项目前端，前端项目启动具体看readme.md文档
- beanapi-common ：为公共封装类（如公共实体、公共常量，统一响应实体，统一异常处理）
- beanapi-backend ：为接口管理平台，主要包括用户、接口相关的功能
- beanapi-gateway ：为网关服务，**涉及到网关限流，统一鉴权，统一日志处理，接口统计，接口数据一致性处理**
- beanapi-third-party：为第三方服务，主要涉及到腾讯云短信、支付宝沙箱支付功能
- beanapi-interface：为接口服务，提供可供调用的接口
- beanapi-client-sdk：提供给开发者的SDK


## 快速上手

### 后端

1. 将各模块配置修改成你自己本地的端口、账号、密码
2. 启动Nacos、Mysql、Redis、RabbitMq
3. 将公共服务 beanapi-common 以及客户端 SDK 安装到本地仓库
4. 按顺序启动服务

服务启动顺序参考：

1. beanapi-backend
2. beanapi-gateway
3. beanapi-thirdparty
4. beanapi-interface

### 前端

环境要求：Node.js >= 16

安装依赖：

```
yarn
```


启动：

```
npm run start:dev
```
