# Community 社区问答系统

一个基于 Spring Boot 的社区问答（Q&A）Web 应用（类似简化版 SegmentFault / 牛客网校招刷题社区），支持 GitHub OAuth 登录、发帖提问、Markdown 编辑、评论回复与站内通知。

## 技术栈

| 分类 | 技术 |
| --- | --- |
| 后端框架 | Spring Boot 2.1.4、Spring MVC |
| 模板引擎 | Thymeleaf |
| 持久层 | MyBatis + MyBatis Generator、PageHelper 分页 |
| 数据库 | MySQL 5.7（`utf8mb4`） |
| 前端 | Bootstrap 3.3.7、Editormd（Markdown 编辑器，内置 CodeMirror） |
| 登录认证 | GitHub OAuth（授权码换取 access_token） |
| 其他 | Lombok、Hutool、spring-boot-devtools |

## 功能特性

- **GitHub 第三方登录**：`/callback` 接收 OAuth 授权码，换取 token 并拉取用户信息，首次登录自动注册，会话保存在 Session；`/logout` 退出
- **提问/编辑**：Markdown 编辑发布问题（`/publish`），仅作者本人可编辑自己的问题，支持标签；标签历史通过本地文件缓存（`TagCache`）提供联想
- **首页列表**：分页展示问题（PageHelper），支持关键字搜索
- **问题详情**：`/question/{id}` 展示正文（Markdown 渲染）、评论列表、相关问题推荐，并累计浏览量
- **评论体系**：对问题评论、对评论回复（多层级，`parent_id` + `type` 区分），AJAX 提交（`POST /comment`）
- **站内通知**：他人回复你的问题或评论时生成通知，`/notification/{id}` 标记已读并跳转到对应问题
- **个人主页**：`/profile/questions` 我的问题、`/profile/replies` 最新回复，均分页
- **文件上传**：`/file/upload` 供编辑器插入图片
- **统一异常与错误页**：自定义 `CustomizeException` / 错误码枚举 + 全局异常处理器，400/500 Thymeleaf 错误页

## 项目结构

```
src/main/java/top/aftery/community
├── advice        # 全局异常处理（CustomizeExceptionHandler）
├── cache         # 标签本地缓存（TagCache）
├── controller    # 各功能入口：首页/发布/问题/评论/通知/个人/授权/文件/错误页
├── dto           # 视图与接口传输对象
├── enums         # 通知类型/状态、评论类型、通用结果码
├── exception     # 自定义异常与错误码
├── interceptor   # 登录会话拦截器
├── mapper        # MyBatis DAO 接口
├── model         # 数据库实体（Generator 生成）
├── provider      # GitHub OAuth API 封装
└── service       # 业务逻辑层
src/main/resources
├── application.yml   # 数据源、GitHub OAuth、MyBatis、PageHelper、日志配置
├── community.sql     # 建库脚本（含测试数据）
├── mapper/           # MyBatis XML
├── templates/        # Thymeleaf 页面
└── static/           # Bootstrap、Editormd 等静态资源
```

## 数据库

`community` 库共三张表 + 一个视图（见 `src/main/resources/community.sql`）：

- `user` — 用户（GitHub accountId、token、头像等）
- `question` — 问题（标题、描述、标签、评论/浏览/点赞计数）
- `comment` — 评论（`parent_id` + `type` 支持对问题或对评论的评论）
- `questionuser` 视图 — 问题与作者联表，供列表页查询

## 快速开始

1. 创建并导入数据库：

   ```sql
   CREATE DATABASE community DEFAULT CHARACTER SET utf8mb4;
   ```

   然后执行 `src/main/resources/community.sql`。

2. 配置连接信息。凭据不再写入配置文件，改为通过环境变量注入（`application.yml` 中已全部改为占位符）：

   | 环境变量 | 说明 | 默认值 |
   | --- | --- | --- |
   | `DB_URL` | MySQL JDBC URL | `jdbc:mysql:///community?...` |
   | `DB_USERNAME` | 数据库账号 | `root` |
   | `DB_PASSWORD` | 数据库密码 | 空 |
   | `GITHUB_CLIENT_ID` | 在 [GitHub Developer Settings](https://github.com/settings/applications/new) 创建 OAuth App 后获取 | 空 |
   | `GITHUB_CLIENT_SECRET` | 同上 | 空 |
   | `GITHUB_REDIRECT_URI` | OAuth 回调地址 | `http://localhost:8080/callback` |

   ```bash
   export DB_USERNAME=root DB_PASSWORD='你的密码'
   export GITHUB_CLIENT_ID='xxx' GITHUB_CLIENT_SECRET='yyy'
   ```

3. 启动（需 JDK 8）：

   ```bash
   mvn spring-boot:run
   ```

4. 浏览器访问 `http://localhost:8080`，点击右上角登录即可通过 GitHub 授权登录。

## 主要路由

| 路径 | 说明 |
| --- | --- |
| `GET /` | 首页问题列表（`page`/`size` 分页，`search` 搜索） |
| `GET/POST /publish` | 发布问题；`GET /publish/{id}` 编辑自己的问题 |
| `GET /question/{id}` | 问题详情 |
| `POST /comment` | 提交评论/回复（JSON） |
| `GET /notification/{id}` | 读取通知并跳转 |
| `GET /profile/{action}` | 个人主页（`questions` / `replies`） |
| `GET /callback`、`GET /logout` | GitHub OAuth 登录回调、退出 |
| `POST /file/upload` | 图片上传 |
