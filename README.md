# 项目名称 ⭐

该项目是一个基于 Vue 和 Spring Cloud 的在线判题系统（Open Judge）系统

## 技术栈

- **后端**：
  - 🚀 Spring Cloud：用于构建微服务架构，提供服务注册、配置中心等功能。
  - 🚀 Redis：用作缓存系统，提高系统性能和并发处理能力。
  - 🚀 MongoDB：用作非结构化数据存储，例如用户提交的代码、评测记录等。
  - 🚀 Docker：用于容器化部署，简化开发、测试和部署流程。同时作为远程Code Sand Box编译用户代码。
  - 🚀 MySQL：作为关系型数据库，用于存储用户信息、题目信息等。

- **前端**：
  - Vue 3：用于构建用户界面，实现前端页面的交互和展示。
  - Typescript
  - ByteMd
  - TinyMCE
  - Vite
  - Websocket
  

## 功能特性

- 用户注册与登录：用户可以注册新账号并登录系统。
- 游戏管理：管理员可以添加、编辑和删除编程游戏。
- Post模块：负责作为平台的社交属性，并且会对上传的东西进行多次本地的以及远程的内容检测（ALIYUN GREEN）。
- 代码评测：用户可以提交代码进行评测，系统会根据评测结果给出相应的反馈，通过docker sand box controller远程调用返回代码运行结果。
  🔔 使用本地运行以及远程docker沙箱实现，目前支持语言：cpp,python,java
  🔔 本地运行语言为java，采用Process类搭建
- 对局回放：可以通过类似查看video的方式查看对局记录。
- 参加比赛：玩家可以参加管理员办理的比赛，在时间内上传指定代码，比赛开始后后台使用代码自动比赛。
- 代码分享：用户可以分享自己的解题代码，与他人交流学习。


## 如何运行

1. 克隆项目到本地：

   ```bash
   git clone https://github.com/fujiwarazz/nawoj
   ```
2. 前端：
   ```bash
    npm install
    npm run serve
   ```
