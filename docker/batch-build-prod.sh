#!/bin/sh
# 开始构建 Docker 微服务镜像

cd /Users/zhanghao/IdeaProjects/core/code-galaxy/docker/

cd ../common-center/core-common
mvn clean install -P prod -D skipTests

cd /Users/zhanghao/IdeaProjects/core/code-galaxy/docker/

cd ../gateway-server
mvn clean package -P prod -D skipTests

cd ../auth-server
mvn clean package -P prod -D skipTests

cd /Users/zhanghao/IdeaProjects/core/code-galaxy/docker/
cd ../server-center
cd /sys-server
mvn clean package -P prod -D skipTests

cd ../server-center
cd /user-server
mvn clean package -P prod -D skipTests

cd ../server-center
cd /article-server
mvn clean package -P prod -D skipTests

cd ../server-center
cd /exam-server
mvn clean package -P prod -D skipTests

cd ../server-center
cd /bill-server
mvn clean package -P prod -D skipTests

cd ../server-center
cd /test-server
mvn clean package -P prod -D skipTests
