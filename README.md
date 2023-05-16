# CMS Gateway

## 作用

承接静态资源请求，之后去 CDN 上获取一个模板引擎(FreeMarker)文件，之后从 CMS 系统获取变量，之后将结果返回

![Template + Java Object == Output(html)](https://freemarker.apache.org/images/overview.png)

### 变量如何获取?

1. 一个数据库，变量和项目相匹配(单独系统)
2. 在cms系统中添加一张配置表(cms开一个接口) => ssr 做不了

### 运维支持：

1. build 读取 package.json 中的 version，之后将打包后的文件推送到 CDN 指定文件夹下（需创建该 version
   同名的文件夹）[原来放置到指定服务器上]

### front end

build 时自动更改 package.json 中的 version 字段

问题: build 更改了 version 之后需要推送到 gitlab 上(恶心🤮)，上面自动化的方案最终会付出巨多成本，解决 version 频繁改动的问题

### CDN 存储

1. 模板引擎文件(CMS 系统直接调用 CDN 接口，将模板引擎文件保存在 CDN 中)
2. 带有版本号的相关 js + css(jenkins build 的时候将打包后的文件上传到 CDN 中)
