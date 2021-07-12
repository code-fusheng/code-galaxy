# 个人网站 - Wiki

## 一、开发日志
```
(2021/03/01 09:00 --- 2021/03/08 10:45) build : 项目构建 --- 初步搭建项目整体架构，包括认证中心、公共中心、网关中心、业务中心
(2021/03/16 09:00 --- 2021/03/16 09:00) test : 版本测试 --- Git多账户测试
(2021/03/18 07:42 --- 2021/03/24 00:20) build : 项目构建 --- GateWay网关统一Swagger接口文档管理(目前仅实现子系统接口文档管理)
(2021/04/07 09:00 --- 2021/04/08 14:18) feature : 框架搭建(认证授权) --- 初步完成 Swagger 聚合接口文档、Feign 服务调用、Oauth2.0 统一认证
(2021/04/08 14:25 --- 2021/04/08 15:43) feature : 架构设计 --- OAuth2 结合 Gateway 网关认证
(2021/04/12 09:00 --- 2021/04/12 20:00) feature : 异常处理 --- 新增Validation异常处理、新增自定义异常处理（网关统一异常处理）
(2021/04/12 22:04 --- 2021/04/21 14:52) feature : 字典类型 --- 新增字典类型 DictType 相关操作
(2021/04/21 15:23 --- 2021/04/21 22:39) feature : 字典数据 --- 新增字典数据 DictData 相关操作,数据库沿用creator_name与updater_name字段设计
(2021/04/22 08:00 --- 2021/04/23 00:14) feature/fix : 题库管理/分页 --- 新增题库管理 Repository 相关接口, 优化分页查询对象
(2021/04/24 21:00 --- 2021/04/24 23:38) feature : 试题管理 --- 新增试题 Question 相关对象实体类
(2021/04/25 08:00 --- 2021/04/26 01:02) feature : 选项管理/项目部署 --- 新增选项相关接口/初探微服务项目Docker容器化部署
(2021/04/26 14:12 --- 2021/**/** **:**) rebuild/fix : 认证重构 --- 重构认证部分相关逻辑与代码，修复网关拦截登录接口(login)的问题
(2021/05/01 09:00 --- 2021/05/06 23:07) feature : 试题管理/规则管理 --- 新增考试管理后台试题/规则分页接口
(2021/05/12 10:00 --- 2021/05/13 02:19) feature : 系统完善
(2021/05/14 09:00 --- 2021/05/14 12:55) feature : 项目部署 --- 新增 Docekr-Compose、Dockerfile 部署相关配置、新增 profile.active 环境配置
(2021/06/06 07:00 --- 2021/06/06 10:54) rebuild : 项目重构 --- 后台项目整体结构重构,新增对外Api结构层
(2021/06/06 17:00 --- 2021/06/06 19:29) rebuild : 系统重构 --- 系统整体认证相关代码重构
(2021/06/06 11:00 --- 2021/06/07 00:47) rebuild : 网关重构 --- 重构网关相关认证授权逻辑与限流机制
(2021/06/15 00:00 --- 2021/06/29 16:36) feature : 登录认证 --- 新增单点登录认证授权,创建文章服务对象
(2021/07/07 20:00 --- 2021/07/08 02:00) feature : 登录日志 --- 通过分割取第一个IP,处理微服务网关转发之后存在多个IP的非法情况
(2021/07/07 21:36 --- 2021/07/08 13:24) feature : 消息队列 --- 新增RabbitMQ消息队列处理登录后的异步用户信息处理
(2021/07/09 10:00 --- 2021/07/09 16:46) feature : 系统监控 --- 新增 Grafana、Github Dingding 机器人
```


[TOC]

## 二、系统环境搭建



# 个人网站 - Wiki



[TOC]

## 一、系统环境搭建



### 1、Linux 搭建 JDK 环境（离线Shell脚本）

```shell
#!bin/bash
# 使用本脚本，请务必提前使用 scp 将jdk资源上传至服务器，并与本脚本文件在同一路径下！！！
currentPath=`pwd`
echo "当前路径:$currentPath"
# JDK目标安装路径
javaTargetPath="/usr/local/java"

# 检查是否存在目录，如果不存在就创建
if [ ! -d "$javaTargetPath" ]
then
	echo "安装路径不全,开始创建:$javaTargetPath"
	mkdir $javaTargetPath
	if [ -d "$javaTargetPath" ]
	then
		echo "安装路径创建成功"
	fi
fi

# 解压
if [ -d "jdk-8u221-linux-x64.tar.gz" ]
then
	echo "未找到资源 jdk-8u221-linux-x64.tar.gz"
	exit
else
  tar -zxvf jdk-8u221-linux-x64.tar.gz -C /usr/local/java
  if [ -d "/usr/local/java/jdk1.8.0_221" ]
  then
  	echo "解压成功"
  fi
fi

# 环境配置
echo "#java jdk环境变量" >> /etc/profile
echo "export JAVA_HOME=/usr/local/java/jdk1.8.0_221" >> /etc/profile
echo "export PATH=\$JAVA_HOME/bin:\$PATH" >> /etc/profile
echo "export CLASSPATH=.:\$JAVA_HOME/lib/dt.jar:\$JAVA_HOME/lib/tools.jar" >> /etc/profile
echo "刷新环境变量"
source /etc/profile
java -version
echo "jdk 安装完成"
```



### 2、Linux 搭建 MySQL 环境（Docker安装MySQL5.7）

```shell
# 1、查看 docker 仓库中的 mysql
$ docker search mysql
# 2、选定需要pull到系统中的数据库镜像
$ docker pull mysql:5.7
# 3、启动 mysql 容器（并设置忽略大小写）
$ docker run -p 3306:3306 --name mysql -e MYSQL_ROOT_PASSWORD=zH1314520? -d mysql:5.7 --lower_case_table_names=1
```



### 3、Linux 搭建 Docker 环境（在线Shell脚本 docker-ce）

```shell
#!/bin/sh
# Linux 搭建 Docker 环境

echo "---查看Linux版本信息--"
cat /etc/redhat-release

echo "---安装必要依赖---"
yum -y install gcc gcc-c++
yum install -y yum-utils device-mapper-persistent-data lvm2

echo "---卸载旧版本---"
yum remove docker \
                  docker-client \
                  docker-client-latest \
                  docker-common \
                  docker-latest \
                  docker-latest-logrotate \
                  docker-logrotate \
                  docker-engine

echo "---设置Docker yum源---"
yum-config-manager --add-repo https://download.docker.com/linux/centos/docker-ce.repo

echo "---安装---"
yum install docker-ce
echo "---设置允许开机启动---"
systemctl start docker
echo "---启动docker---"
systemctl enable docker
echo "---查看docker版本信息---"
docker version
```

```shell
# 配置 Docker 阿里云加速镜像
mkdir -p /etc/docker
vim  /etc/docker/daemon.json
# 阿里云镜像加速器{"registry-mirrors": ["http://hub-mirror.c.163.com"] }
systemctl daemon-reload
systemctl restart docker
```

```shell
# docker compose
# mac 本地
$ scp docker-compose-Linux-x86_64 root@47.111.158.6:/root/Downloads
# linux 服务器
$ mkdir /usr/local/bin
$ cp docker-compose-Linux-x86_64 /usr/local/bin
$ cd /usr/local/bin
$ mv docker-compose-Linux-x86_64 docker-compose
$ chmod +x /usr/local/bin/docker-compose

# 进一步搭建
$ vi /lib/systemd/system/docker.service
# 开放 2376 端口 建议改成12375
ExecStart=/usr/bin/dockerd -H fd:// --containerd=/run/containerd/containerd.sock -H tcp://0.0.0.0:12375  -H unix:///var/run/docker.sock
# 重启 docker 服务
$ systemctl daemon-reload
$ systemctl restart docker.service

# 检查
$ netstat -nptl
```



### 4、Linux 搭建 Tomcat 环境

略



### 5、Linux 搭建 Redis 环境（在线Docker 方式）

```shell
docker run -d --restart=always --name redis -p 6390:6379 redis --requirepass "Xcode-redis?"
```

### 6、Linux 搭建 RabbitMQ （在线/离线方式）

```shell
# 1、更新基本环境
$ yum -y update

# 2、安装 Erlang
# 安装 EPEL
$ yum -y install epel-release
# 安装 Erlang
$ yum -y install erlang socat
# 检查 Erlang 版本
$ erl -version

# 3、安装 wget
$ yum -y install wget

# 4、下载安装 RabbitMQ
$ wget https://www.rabbitmq.com/releases/rabbitmq-server/v3.6.10/rabbitmq-server-3.6.10-1.el7.noarch.rpm
# 导入 GPG 密钥
$ rpm -import https://www.rabbitmq.com/rabbitmq-release-signing-key.asc
# 运行 rpm 安装 RabbitMQ rpm 包
$ rpm -Uvh rabbitmq-server-3.6.10-1.el7.noarch.rpm

# 5、使用命令
# 运行
$ systemctl start rabbitmq-server
# 开机自启
$ systemctl enable rabbitmq-server
# 检查状态
$ systemctl status rabbitmq-server

# 6、访问 WEB 控制台
$ rabbitmq-plugins enable rabbitmq_management
$ chown -R rabbitmq:rabbitmq /var/lib/rabbitmq/

# 7、添加用户
$ rabbitmqctl add_user rabbit rabbit
$ rabbitmqctl set_permissions -p "/" rabbit ".*" ".*" ".*"
$ rabbitmqctl set_user_tags rabbit administrator
```



### 7、Linux 搭建 Maven 环境（离线方式）

PS：需要下载 apache-maven-3.6.3-bin.tar.gz 资源至 /root/download 目录下进行以下操作

```shell
# 解压压缩包
$ cp -r apache-maven-3.6.3-bin.tar.gz /usr/local
$ tar -zxvf apache-maven-3.6.3-bin.tar.gz
$ rm -rf apache-maven-3.6.3-bin.tar.gz
# 创建仓库
$ mkdir apache-maven-3.6.3/repo
# 配置Maven仓库位置以及镜像源
$ cd apache-maven-3.6.3/conf
$ vim settings.xml
# 替换 mirrors 标签中内容
<mirrors>
  <localRepository>/usr/local/apache-maven-3.6.3/repo</localRepository>
  <mirror>
     <id>nexus-aliyun</id>
     <mirrorOf>*</mirrorOf>
     <name>Nexus aliyun</name>
     <url>http://maven.aliyun.com/nexus/content/groups/public</url>
  </mirror>
</mirrors>
# 刷新配置使其生效
$ source /etc/profile
$ mvn -version
```



### 8、Linux 搭建 Nginx 环境

```shell
# 切换到资源文件目录
$ cd /root/Downloads

# 在线下载 nginx
$ wget http://nginx.org/download/nginx-1.16.1.tar.gz

# 安装必要依赖
$ yum -y install gcc zlib zlib-devel pcre-devel openssl openssl-devel

# 解压
$ tar -zxvf nginx-1.16.1.tar.gz
# 切换到 nginx-1.16.1 下
$ cd nginx-1.16.1
# 创建nginx安装目标路径 并指定
$ mkdir /usr/local/nginx
$ ./configure --prefix=/usr/local/nginx
# 编译 安装
$ make
$ make install

# 切换至最终安装目录 启动 nginx
$ cd /usr/local/nginx
$ ./sbin/nginx

# PS : 修改配置后的重载
$ ./sbin/nginx -s reload
```



*暂时不可用*

```shell
#!/bin/bash
function IXDBA(){
cat << EOF
+----------------+
|Nginx在线安装脚本
+----------------+
EOF
}
IXDBA

if [ ! -d /usr/local ]
	then
	mkdir /usr/local
fi
if [ ! -d /usr/local/nginx ]
	then
	mkdir /usr/local/nginx
fi
LOG_DIR = /usr/local
#########################
function NGINX_INSTALL() {
yum -y install gcc zlib zlib-devel pcre-devel openssl openssl-devel &>/dev/nul
	if [ $? -eq 0 ]
		then
			cd $ /usr/local	/
			$$ wget http://nginx.org/download/nginx-1.16.1.tar.gz &>/dev/null /
			$$ tar -zxvf nginx-1.16.1.tar.gz /
			$$ cd nginx-1.16.1 /
			$$ ./configure --prefix=/usr/local/nginx &>/dev/null	/
			$$ make &>/dev/null /
      $$ make install &>/dev/null
	fi
	if [ -e /usr/local/nginx/sbin/nginx ];then
		/usr/local/nginx/sbin/nginx && echo "Nginx 安装并启动成功！！！"
	fi
}
echo "开始在线安装 Nginx 请稍后..." && NGINX_INSTALL
```





### 9、Linux 搭建 Nacos 环境

```shell
# 解压 nacos-server-1.4.1.tar.gz
$ tar -zxvf nacos-server-1.4.1.tar.gz

# 拷贝至 /usr/local 目录下
$ cp -r nacos /usr/local

# 切换至 /usr/local/nacos/bin 目录下
$ cd /usr/local/nacos/bin

# standaloe 单机模式运行 非集群
$ sh startup.sh -m standalone

# 查看输出日志
$ tail -f /usr/local/nacos/logs/start.out
```



### 10、Linux 搭建 Seata 环境

```shell
# 解压 seata-server-1.4.1.tar.gz
$ tar -zxvf seata-server-1.4.1.tar.gz

# 拷贝至 /usr/local
$ cp -r seate /usr/local

# 切换至 /usr/local/seate/bin
$ cd /usr/local/seate/bin

# 启动 seate-server 服务
$ sh ./bin/seate-server.sh
```







## 三、架构设计


### 边界隔离 提升拓展空间
> Vo 视图层对象
> Dto 传输层对象
> Entity 实体对象


## 四、开发规约

### Redis 缓存
缓存不能够成为数据持久化的解决方案，只能作为中间优化处理过程。即便缓存挂了也不能影响功能正常使用。

### Enum 枚举
枚举是一类及其稳定的数据规范，在非必要的情况下绝对禁止修改，可以添加新的枚举类型，对于原有的不需要用到的需要进行弃用处理

### Exception 异常
对于异常,通常来说应该捕获那些知道如何处理的异常,将那些不知道如何处理的异常继续传递。
另外通过使用  `e.getMessage()` 获取详细的异常信息


## 帮助文档
### Docker 打包部署指令
```shell script
X!code-galaxy?

mvn clean package -P prod -D skipTests

mvn install -DskipTests=true

mvn clean package docker:build -DskipTests
mvn clean package docker:build -Pprod -DskipTests

docker run -it -m 512M -d -p 9999:9999 --rm gateway-server
docker run -it -m 256M -d -p 9000:9000 --rm auth-server
docker run -it -m 256M -d -p 10100:10100 --rm user-server
docker run -it -m 256M -d -p 10199:10199 --rm user-admin-server
docker run -it -m 256M -d -p 10399:10399 --rm exam-admin-server
docker run -it -m 256M -d -p 10099:10099 --rm sys-admin-server

scp -P 22221 -r gateway-server-1.0.0.jar root@47.111.158.6:/root/App/code-galaxy


nohup java -jar gateway-server-1.0.0.jar  --server.port=9999  > gateway-server.log>&1 &
nohup java -jar auth-server-1.0.0.jar  --server.port=9000  > auth-server.log>&1 &
nohup java -jar user-server-1.0.0.jar  --server.port=10100  > user-server.log>&1 &
nohup java -jar auth-server-1.0.0.jar  --server.port=9000  > auth-server.log>&1 &
nohup java -jar exam-admin-server-1.0.0.jar --server.port=10399  > exam-admin-server.log>&1 &

```

```shell script
# 内存大小排查
docker images
docker stats
docker ps -a
ps -ef | grep java
top -p PID

# 硬盘问题排查
# 检查服务器磁盘使用情况
df -h
```

```shell script
# 查看服务器入侵记录
$ last -f /var/log/wtmp
```
