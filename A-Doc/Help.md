### 1、本地 Nacos 服务启动
```shell script
# 切换至 nacos 执行文件目录
$ cd /Users/zhanghao/Downloads/local/nacos/bin
# 执行启动脚本命令 启动 Nacos 服务
# standaloe 单机模式运行 非集群
$ sh startup.sh -m standalone 
# 查看输出日志
$ tail -f /Users/zhanghao/Downloads/local/nacos/logs/start.out
# 关闭服务
$ sh shutdown.sh
```

### 2、在 yml 中使用 pom 中的变量
```xml
<!-- 添加 filtering true 配置 -->
<resource>
    <directory>src/main/resources</directory>
    <filtering>true</filtering>
</resource>
```
**通过@@使用**
```yml
spring:
  profiles:
    active: @profiles.active@
```

### 3、非对称加密指令
```shell script
keytool -genkeypair -alias oauth2 -keyalg RSA -keypass oauth2 -keystore oauth2.jks -storepass oauht2
CN=zhanghao, OU=fusheng, O=fusheng, L=changsha, ST=湖南, C=f
```
